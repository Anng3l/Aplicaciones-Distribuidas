package cliente.test;

import cliente.clase.Cliente;

public class Test {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();

/*
        cliente.enviarNumeros("localhost", 5000, 10, 11);
        // o 192.168.56.1
        cliente.enviarNumeros("192.168.56.1", 5000, 10, 11);
        //172.31.116.71
        cliente.enviarNumeros("172.31.116.71", 5000, 10, 11);
*/

        //172.29.0.1 Walter
        //cliente.enviarNumeros("172.29.0.1", 5000, 10, 11);

        //ip VM 192.168.56.102
        cliente.enviarNumeros("192.168.56.102", 5000, 10, 11, 1);
    }
}
