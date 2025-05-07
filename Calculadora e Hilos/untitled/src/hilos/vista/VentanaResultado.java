package hilos.vista;

import hilos.clase.Auto;

import javax.swing.*;

public class VentanaResultado extends JFrame {

    private JLabel x;
    private JLabel y;
    private JLabel z;

    private JLabel xy;
    private JLabel yy;
    private JLabel zy;

    private String marca;
    private String modelo;
    private String precio;

    Auto auto = new Auto();

    public VentanaResultado(String marca, String modelo, String precio) {
        setTitle("Auto");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        x = new JLabel("Marca: ");
        x.setBounds(10, 20, 50, 30);
        add(x);
        xy = new JLabel(marca);
        xy.setBounds(60, 20, 200, 30);
        add(xy);

        y = new JLabel("Modelo: ");
        y.setBounds(10, 60, 50, 30);
        add(y);
        yy = new JLabel(modelo);
        yy.setBounds(60, 60, 200, 30);
        add(yy);

        z = new JLabel("Precio: ");
        z.setBounds(10, 100, 50, 30);
        add(z);

        zy = new JLabel(precio);
        zy.setBounds(60, 100, 200, 30);
        add(zy);

        VentanaAuto ventanaAuto = new VentanaAuto();
        ventanaAuto.setVisible(false);
    }
}
