package cliente.clase;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//Código original
/*
public class Cliente {
    public void enviarNumeros(String ip, int puerto, int num1, int num2, int opcion) {
        try {
            DatagramSocket socket = new DatagramSocket(); // Crear Datagram
            InetAddress direccionIp_servidor = InetAddress.getByName(ip);

            while (true)
            {
                String mensajeSalida = num1 + "," + num2; // Crear mensaje en formato "num1,num2"
                byte[] bufferSalida = mensajeSalida.getBytes();

                DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionIp_servidor, puerto);
                socket.send(paqueteSalida);

                byte[] bufferEntrada = new byte[1024]; // Esperar respuesta del servidor (la suma)
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                socket.receive(paqueteEntrada);

                String respuestaServidor = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
                System.out.println("Resultado recibido del servidor: " + respuestaServidor);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
*/

public class Cliente {
    public void enviarNumeros(String ip, int puerto, int num1, int num2, int opcion) {
        try {
            DatagramSocket socket = new DatagramSocket(); // Crear Datagram
            InetAddress direccionIp_servidor = InetAddress.getByName(ip);

            while (true)
            {
                String mensajeSalida = num1 + "," + num2 + "," + opcion; // Crear mensaje en formato "num1,num2"
                byte[] bufferSalida = mensajeSalida.getBytes();

                DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionIp_servidor, puerto);
                socket.send(paqueteSalida);

                byte[] bufferEntrada = new byte[1024]; // Esperar respuesta del servidor (la suma)
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                socket.receive(paqueteEntrada);

                String respuestaServidor = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
                System.out.println("Resultado recibido del servidor: " + respuestaServidor);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}