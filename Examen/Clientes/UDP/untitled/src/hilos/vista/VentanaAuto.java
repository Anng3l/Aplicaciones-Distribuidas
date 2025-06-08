package hilos.vista;

import hilos.clase.Auto;

import javax.swing.*;

public class VentanaAuto extends JFrame {

    private JTextField marcaField;
    private JTextField modeloField;
    private JTextField precioField;
    private JLabel x;
    private JLabel y;
    private JLabel z;

    private JLabel xy;
    private JLabel yy;
    private JLabel zy;


    private JButton botonSetear;


    Auto auto = new Auto();

    public VentanaAuto() {
        setTitle("Auto");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        x = new JLabel("Marca: ");
        x.setBounds(10, 20, 50, 30);
        add(x);
        marcaField = new JTextField();
        marcaField.setBounds(50, 20, 200, 30);
        add(marcaField);

        y = new JLabel("Modelo: ");
        y.setBounds(10, 60, 50, 30);
        add(y);
        modeloField = new JTextField();
        modeloField.setBounds(50, 60, 200, 30);
        add(modeloField);

        z = new JLabel("Precio: ");
        z.setBounds(10, 100, 50, 30);
        add(z);
        precioField = new JTextField();
        precioField.setBounds(50, 100, 200, 30);
        add(precioField);

        botonSetear = new JButton("Setear");
        botonSetear.setBounds(50, 150, 100, 30);
        add(botonSetear);

        botonSetear.addActionListener(e -> {
            auto.setMarca(marcaField.getText());
            auto.setModelo(modeloField.getText());
            Float precio = Float.parseFloat(precioField.getText());
            auto.setPrecio(precio);

            VentanaResultado ventanaResultado = new VentanaResultado(auto.getMarca(), auto.getModelo(), precioField.getText());
            ventanaResultado.setVisible(true);
        });

    }

    public static void main(String[] args) {
        VentanaAuto ventanaAuto = new VentanaAuto();
        ventanaAuto.setVisible(true);

    }
}
