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

    private JTextArea lblResultado = new JTextArea("Resultado: ");

    public Ventana() {
        setTitle("Calculadora");
        setSize(400, 400);
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

        lblResultado.setBounds(50, 260, 280, 80); // Aumenta el tamaño para más texto
        add(lblResultado);
        lblResultado.setEditable(false); // ❌ No editable
        lblResultado.setLineWrap(true);

        // Action listeners for buttons
        botonEnviar.addActionListener(e -> {
            String username = textUserName.getText();
            String ingresoTrabajo = textIngresoTrabajo.getText();
            String salidaAlmuerzo = textSalidaAlmuerzo.getText();
            String ingresoAlmuerzo = textIngresoAlmuerzo.getText();
            String salidaTrabajo = textSalidaTrabajo.getText();


            //Llamada al servidor
            try {
                String resultado = Cliente.enviarAsistencias(username, ingresoTrabajo, salidaAlmuerzo, ingresoAlmuerzo, salidaTrabajo);
                System.out.println(resultado);
                lblResultado.setText(resultado);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

    }
}


