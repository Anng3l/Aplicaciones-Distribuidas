package Ventana;

import clase.Servidor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCarrera extends JFrame {

    public VentanaCarrera(Servidor servicio) {
        super("Búsqueda de usuarios");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jpanel = new JPanel();
        jpanel.setLayout(null);
        jpanel.setBackground(Color.WHITE);
        add(jpanel);

        JLabel input = new JLabel("Ingrese ID de usuario:");
        JTextField idUsuario = new JTextField();
        JButton buscar = new JButton("Buscar");
        JTextArea lblResultado = new JTextArea("Resultado:");
        lblResultado.setEditable(false);
        lblResultado.setLineWrap(true);
        lblResultado.setWrapStyleWord(true);

        input.setBounds(50, 50, 200, 30);
        idUsuario.setBounds(50, 90, 200, 30);
        buscar.setBounds(50, 140, 150, 40);
        lblResultado.setBounds(50, 200, 200, 200);

        jpanel.add(input);
        jpanel.add(idUsuario);
        jpanel.add(buscar);
        jpanel.add(lblResultado);

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputTexto = idUsuario.getText().trim();

                //Validación de la Id ingresada
                if (!inputTexto.matches("^[1-9]\\d*$")) {
                    lblResultado.setText("Por favor, ingrese un ID válido (número entero mayor que 0, sin letras ni símbolos).");
                    return;
                }

                try {
                    int userId = Integer.parseInt(inputTexto);
                    String resultado = servicio.consultar(userId);
                    lblResultado.setText("Resultado:\n" + resultado);
                } catch (Exception ex) {
                    lblResultado.setText("Error al consultar el servicio:\n" + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }
}