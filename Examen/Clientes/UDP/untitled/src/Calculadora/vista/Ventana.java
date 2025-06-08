package Calculadora.vista;

import Calculadora.Cliente;
import Calculadora.impl.OperacionesImpl;
import Calculadora.interfaz.Operaciones;

import javax.swing.*;

public class Ventana extends JFrame{
    private JTextField txtNumero1;
    private JTextField txtNumero2;
    private JButton btnSumar;
    private JButton btnRestar;
    private JButton btnMultiplicar;
    private JButton btnDividir;
    private JLabel lblResultado;

    Cliente cliente = new Cliente();

    String ip = "192.168.56.1";

    public Ventana() {
        setTitle("Calculadora");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        Operaciones op = new OperacionesImpl();

        txtNumero1 = new JTextField();
        txtNumero1.setBounds(50, 20, 200, 30);
        add(txtNumero1);

        txtNumero2 = new JTextField();
        txtNumero2.setBounds(50, 60, 200, 30);
        add(txtNumero2);

        btnSumar = new JButton("+");
        btnSumar.setBounds(50, 100, 50, 30);
        add(btnSumar);

        btnRestar = new JButton("-");
        btnRestar.setBounds(100, 100, 50, 30);
        add(btnRestar);

        btnMultiplicar = new JButton("*");
        btnMultiplicar.setBounds(150, 100, 50, 30);
        add(btnMultiplicar);

        btnDividir = new JButton("/");
        btnDividir.setBounds(200, 100, 50, 30);
        add(btnDividir);

        lblResultado = new JLabel("Resultado: ");
        lblResultado.setBounds(50, 180, 200, 30);
        add(lblResultado);

        // Action listeners for buttons
        btnSumar.addActionListener(e -> {
            double num1 = Double.parseDouble(txtNumero1.getText());
            double num2 = Double.parseDouble(txtNumero2.getText());
            double resultado = cliente.enviarNumeros(ip, 5000, num1, num2, 1);
            lblResultado.setText("Resultado: " + resultado);
        });

        btnRestar.addActionListener(e -> {
            double num1 = Double.parseDouble(txtNumero1.getText());
            double num2 = Double.parseDouble(txtNumero2.getText());
            double resultado = cliente.enviarNumeros(ip, 5000, num1, num2, 2);
            lblResultado.setText("Resultado: " + resultado);
        });

        btnMultiplicar.addActionListener(e -> {
            double num1 = Double.parseDouble(txtNumero1.getText());
            double num2 = Double.parseDouble(txtNumero2.getText());
            double resultado = cliente.enviarNumeros(ip, 5000, num1, num2, 3);
            lblResultado.setText("Resultado: " + resultado);
        });

        btnDividir.addActionListener(e -> {
            double num1 = Double.parseDouble(txtNumero1.getText());
            double num2 = Double.parseDouble(txtNumero2.getText());
            if (num2 != 0) {
                double resultado = cliente.enviarNumeros(ip, 5000, num1, num2, 4);
                lblResultado.setText("Resultado: " + resultado);
            } else {
                lblResultado.setText("Error: División por cero");
            }
        });

    }

    public static void main(String[] args) {
        Ventana ventana = new Ventana();
        ventana.setVisible(true);
    }
}