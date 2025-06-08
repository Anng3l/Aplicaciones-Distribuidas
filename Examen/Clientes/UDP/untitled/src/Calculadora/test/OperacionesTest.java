package Calculadora.test;

import Calculadora.impl.OperacionesImpl;

import javax.swing.*;
import java.util.Scanner;

public class OperacionesTest {
    public static void main(String[] args) {
        System.out.println("Hola mundo x4");

        OperacionesImpl op = new OperacionesImpl();

        double x=2.5;
        double y=3.6;


        System.out.println("La suma es: " + op.sumar(x, y));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese x: ");
        x = scanner.nextDouble();
        System.out.println("Ingrese y: ");
        y = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el segundo número"));
        double z = op.restar(x, y);
        JOptionPane.showMessageDialog(null, "Resultado de la resta: " + z + "!");
        System.out.println("La resta es: " + op.restar(x, y));


        System.out.println("Ingrese x: ");
        x = scanner.nextDouble();
        System.out.println("Ingrese y: ");
        y = scanner.nextDouble();
        System.out.println("La multiplicación es: " + op.multiplicar(x, y));

        System.out.println("Ingrese x: ");
        x = scanner.nextDouble();
        System.out.println("Ingrese y: ");
        y = scanner.nextDouble();
        System.out.println("La división es: " + op.dividir(x, y));
    }
}