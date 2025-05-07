package Calculadora;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {
    private double valor;

    public Cliente(double valor)
    {
        this.valor = valor;
    }
    public Cliente()
    {
    }

    public double enviarNumeros(String ip, int puerto, double num1, double num2, int opcion) {
        try {
            DatagramSocket socket = new DatagramSocket(); // Crear Datagram
            InetAddress direccionIp_servidor = InetAddress.getByName(ip);

            //While true hace que se mantenga enviando la request realizada.
            //Nunca sale del while, se queda en bucle infinito realizando requests
            //El server se queda en bucle infinito respondiendo a esas requests,
            //por lo que no se puede realizar ninguna nueva, pues nunca alcanza
            //el return, no se detiene el método y no se muestra el valor en la ventana.

            //while (true)
            //{
                String mensajeSalida = num1 + "," + num2 + "," + opcion; // Crear mensaje en formato "num1,num2"
                byte[] bufferSalida = mensajeSalida.getBytes();

                DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionIp_servidor, puerto);
                socket.send(paqueteSalida);

                byte[] bufferEntrada = new byte[1024]; // Esperar respuesta del servidor (la suma)
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                socket.receive(paqueteEntrada);

                String respuestaServidor = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
                System.out.println(respuestaServidor);
                valor = Double.parseDouble(respuestaServidor);
                System.out.println(valor);
            //}
        } catch (Exception e) { e.printStackTrace(); }
        return valor;
    }
}
