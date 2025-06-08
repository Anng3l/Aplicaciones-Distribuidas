package Ventana;

import clase.Persona;
import clase.Servidor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaCarrera extends JFrame {

    public VentanaCarrera(Servidor servicio) {
        super("Gestión de Empleados");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jpanel = new JPanel();
        jpanel.setLayout(null);
        jpanel.setBackground(Color.WHITE);
        add(jpanel);

















        //Inicialización de los elementos
        JLabel lblId = new JLabel("ID:");
        JTextField txtId = new JTextField();

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblBuscarPorNombre = new JLabel("Buscar Nombre:");
        JTextField txtBuscarNombre = new JTextField();

        JLabel lblCorreo = new JLabel("Correo:");
        JTextField txtCorreo = new JTextField();

        JLabel lblCargo = new JLabel("Cargo:");
        JTextField txtCargo = new JTextField();

        JLabel lblSueldo = new JLabel("Sueldo:");
        JTextField txtSueldo = new JTextField();

        JTextArea areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(areaResultado);

        JButton btnCrear = new JButton("Crear");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnBuscarNombre = new JButton("Buscar por Nombre");
        JButton btnBuscarPorNombre = new JButton("Buscar por Nombre");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnListar = new JButton("Listar Todo");

















        // Tamaños y posiciones
        lblId.setBounds(50, 20, 100, 30);
        txtId.setBounds(150, 20, 200, 30);

        lblNombre.setBounds(50, 60, 100, 30);
        txtNombre.setBounds(150, 60, 200, 30);

        lblCorreo.setBounds(50, 100, 100, 30);
        txtCorreo.setBounds(150, 100, 200, 30);

        lblCargo.setBounds(50, 140, 100, 30);
        txtCargo.setBounds(150, 140, 200, 30);

        lblSueldo.setBounds(50, 180, 100, 30);
        txtSueldo.setBounds(150, 180, 200, 30);

        btnCrear.setBounds(400, 20, 120, 30);
        btnBuscar.setBounds(400, 60, 120, 30);
        btnBuscarNombre.setBounds(400, 90, 150, 30);
        btnActualizar.setBounds(400, 130, 120, 30);
        btnEliminar.setBounds(400, 170, 120, 30);
        btnListar.setBounds(400, 210, 120, 30);

        scroll.setBounds(50, 270, 470, 370);













        // Agregar al panel
        jpanel.add(lblId); jpanel.add(txtId);
        jpanel.add(lblNombre); jpanel.add(txtNombre);
        jpanel.add(lblCorreo); jpanel.add(txtCorreo);
        jpanel.add(lblCargo); jpanel.add(txtCargo);
        jpanel.add(lblSueldo); jpanel.add(txtSueldo);

        jpanel.add(btnCrear); jpanel.add(btnBuscar);
        jpanel.add(btnActualizar); jpanel.add(btnEliminar);
        jpanel.add(btnListar);

        jpanel.add(lblBuscarPorNombre); jpanel.add(txtBuscarNombre); jpanel.add(btnBuscarPorNombre);
        jpanel.add(btnBuscarNombre);
        jpanel.add(scroll);











        btnCrear.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText().trim();
                String correo = txtCorreo.getText().trim();
                String cargo = txtCargo.getText().trim();
                double sueldo = Double.parseDouble(txtSueldo.getText().trim());

                Persona nueva = new Persona(0, nombre, correo, cargo, sueldo);
                boolean resultado = servicio.crear(nueva);
                areaResultado.setText(resultado ? "Empleado creado correctamente." : "Error al crear.");
            } catch (Exception exception) {
                areaResultado.setText("Error: " + exception.getMessage());
            }
        });

        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                String resultado = servicio.consultar(id);
                areaResultado.setText(resultado);
            } catch (Exception exception) {
                areaResultado.setText("Error: " + exception.getMessage());
            }
        });

        btnBuscarNombre.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText().trim();
                if (nombre.isEmpty()) {
                    areaResultado.setText("Por favor ingresa un nombre.");
                    return;
                }

                ArrayList<Persona> resultados = new ArrayList<Persona>();
                resultados = (ArrayList<Persona>) servicio.buscarPorNombre(nombre);
                if (resultados.isEmpty()) {
                    areaResultado.setText("No se encontró un empleado con ese nombre.");
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (Persona p : resultados) {
                        sb.append(String.format("ID: %d | Nombre: %s | Correo: %s | Cargo: %s | Sueldo: %.2f\n",
                                p.getClave(), p.getNombre(), p.getCorreo(), p.getCargo(), p.getSueldo()));
                    }
                    areaResultado.setText(sb.toString());
                }
            } catch (Exception exception) {
                areaResultado.setText("Error: " + exception.getMessage());
            }
        });


        btnActualizar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                String nombre = txtNombre.getText().trim();
                String correo = txtCorreo.getText().trim();
                String cargo = txtCargo.getText().trim();

                double sueldo;
                if (txtSueldo.getText().trim().isEmpty()) {
                    sueldo = -1;
                } else {
                    sueldo = Double.parseDouble(txtSueldo.getText().trim());
                }

                Persona p = new Persona(id, nombre, correo, cargo, sueldo);
                boolean resultado = servicio.actualizar(p);
                areaResultado.setText(resultado ? "Empleado actualizado correctamente." : "No se encontró el ID.");

            } catch (Exception exception) {
                areaResultado.setText("Error: " + exception.getMessage());
            }
        });

        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                boolean resultado = servicio.eliminar(id);
                areaResultado.setText(resultado ? "Empleado eliminado." : "ID no encontrado.");
            } catch (Exception exception) {
                areaResultado.setText("Error: " + exception.getMessage());
            }
        });

        btnListar.addActionListener(e -> {
            try {
                ArrayList<Persona> personas = (ArrayList<Persona>) servicio.listar();
                StringBuilder sb = new StringBuilder();
                for (Persona p : personas) {
                    sb.append(String.format("ID: %d | Nombre: %s | Correo: %s | Cargo: %s | Sueldo: %.2f\n",
                            p.getClave(), p.getNombre(), p.getCorreo(), p.getCargo(), p.getSueldo()));
                }
                areaResultado.setText(sb.toString());
            } catch (Exception exception) {
                areaResultado.setText("Error al listar: " + exception.getMessage());
            }
        });


        setVisible(true);
    }
}
