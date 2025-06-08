package udp.servidor;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor {
    public static void servicio(int puerto){
        try {
            DatagramSocket socket = new DatagramSocket(puerto);
            System.out.println("Servidor corriendo en Linux...");
            //While para poder seguir realizando solicitudes al servidor
            while (true) {
                byte[] bufferEntrada = new byte[1024];
                DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada,bufferEntrada.length);
                socket.receive(paqueteEntrada);

                String datosRecibidos = new String(paqueteEntrada.getData(),0,paqueteEntrada.getLength());
                System.out.println("Cliente envio: "+datosRecibidos);

                String[] partes=datosRecibidos.split(",");
                Double resultado = 0.0;
                //Aumentar el length porque ahora se envian 3 valores
                if (partes.length==3){
                    try {
                        //Números a operar
                        double num1=Double.parseDouble(partes[0].trim());
                        double num2=Double.parseDouble(partes[1].trim());
                        //Valor para categorizar la operación a realizar sobre los números
                        int num3=Integer.parseInt(partes[2].trim());
                        resultado = switch (num3) {
                            case 1 -> num1 + num2;
                            case 2 -> num1 - num2;
                            case 3 -> num1 * num2;
                            case 4 -> num1 / num2;
                            default -> resultado;
                        };

                        String respuesta= String.valueOf(resultado);
                        byte[] bufferSalida=respuesta.getBytes();
                        DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida,bufferSalida.length,paqueteEntrada.getAddress(), paqueteEntrada.getPort());
                        socket.send(paqueteSalida);
                        System.out.println("Enviando resultado al  cliente: "+respuesta);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    System.out.println("Error al transmitir los datos");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
