package TCP.Cliente.test;


import TCP.Cliente.clase.Cliente;

import java.util.Scanner;
import javax.swing.*;

public class Ventana extends JFrame {
    private JTextField textUserName;
    private JTextField textIngresoTrabajo;
    private JTextField textSalidaAlmuerzo;
    private JTextField textIngresoAlmuerzo;
    private JTextField textSalidaTrabajo;


    private JButton botonEnviar;
    private JButton botonVisualizar;

    private JTextArea lblResultado = new JTextArea("Resultado: ");

    public Ventana() {
        setTitle("Calculadora");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);


        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(10, 20, 130, 30);
        add(usernameLabel);
        JLabel ingresoTrabajoLabel = new JLabel("Ingreso trabajo");
        ingresoTrabajoLabel.setBounds(10, 60, 130, 30);
        add(ingresoTrabajoLabel);
        JLabel salidaAlmuerzoLabel = new JLabel("Salida almuerzo");
        salidaAlmuerzoLabel.setBounds(10, 100, 130, 30);
        add(salidaAlmuerzoLabel);
        JLabel ingresoAlmuerzoLabel = new JLabel("Ingreso almuerzo");
        ingresoAlmuerzoLabel.setBounds(10, 140, 130, 30);
        add(ingresoAlmuerzoLabel);
        JLabel salidaTrabajoLabel = new JLabel("Salida trabajo");
        salidaTrabajoLabel.setBounds(10, 180, 130, 30);
        add(salidaTrabajoLabel);



        textUserName = new JTextField();
        textUserName.setBounds(150, 20, 200, 30);
        add(textUserName);

        textIngresoTrabajo = new JTextField();
        textIngresoTrabajo.setBounds(150, 60, 200, 30);
        add(textIngresoTrabajo);

        textSalidaAlmuerzo = new JTextField();
        textSalidaAlmuerzo.setBounds(150, 100, 200, 30);
        add(textSalidaAlmuerzo);

        textIngresoAlmuerzo = new JTextField();
        textIngresoAlmuerzo.setBounds(150, 140, 200, 30);
        add(textIngresoAlmuerzo);

        textSalidaTrabajo = new JTextField();
        textSalidaTrabajo.setBounds(150, 180, 200, 30);
        add(textSalidaTrabajo);


        botonEnviar = new JButton("Enviar");
        botonEnviar.setBounds(50, 220, 150, 30);
        add(botonEnviar);

        botonVisualizar = new JButton("Visualizar asistencias de un empleado");
        botonVisualizar.setBounds(50, 400, 300, 30);
        add(botonVisualizar);

        lblResultado.setBounds(50, 260, 280, 110);
        add(lblResultado);
        lblResultado.setEditable(false);
        lblResultado.setLineWrap(true);



        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 450, 280, 80);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);
        textArea.setText("Resultado: ");


        // Action listeners for buttons
        botonEnviar.addActionListener(e -> {
            String username = textUserName.getText();
            String ingresoTrabajo = textIngresoTrabajo.getText();
            String salidaAlmuerzo = textSalidaAlmuerzo.getText();
            String ingresoAlmuerzo = textIngresoAlmuerzo.getText();
            String salidaTrabajo = textSalidaTrabajo.getText();


            //Llamada al servidor
            try {
                String resultado = Cliente.enviarAsistencias("registrar",username, ingresoTrabajo, salidaAlmuerzo, ingresoAlmuerzo, salidaTrabajo);
                System.out.println(resultado);
                lblResultado.setText(resultado);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        botonVisualizar.addActionListener(e -> {
            String username = textUserName.getText();
            System.out.println("1 ventana");
            try
            {
                System.out.println("2 ventana");
                String asistencias = Cliente.visualizarAsistencias("consultar", username);
                System.out.println(asistencias);
                textArea.setText(asistencias);
            }
            catch (Exception exception)
            {

            }
        });

    }
}


