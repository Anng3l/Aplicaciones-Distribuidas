package TestCliente;

import Cliente.Cliente;

import java.util.Scanner;

public class TestCliente {
    public static void main(String[] args) throws Exception {
        String op=null;
        int id = -1;
        Scanner scanner = null;
        do {
            scanner = new Scanner(System.in);
            System.out.println("Buscar datos del empleado: ");
            id = scanner.nextInt();
            System.out.println(Cliente.consultar(id));
            System.out.println("Desea salir si(s) / no(n)");
            op = scanner.next();
        } while (op.equals("n"));
        scanner.close();
    }
}
