/*
package TCP.clase;

import TCP.servicios.Servicios;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Servidor {

    private String prueba;

    public Servidor(){}




    //Método para capturar la fecha
    public static String getFecha(String nombre)
    {
        Date date = new Date();
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return nombre + "\n Fecha y hora de ingreso registrada en el servidor: " +
                "" + formato.format(date);
    }


    //Método para iniciar el servidor
    public static void procesarSolicitud(int puerto) throws Exception {
        //Se crea el socket del servidor
        ServerSocket servidor = new ServerSocket(puerto);
        System.out.println("Servidor de Fechas corriendo... ");

        while (true)
        {
            //Acepta solicitudes desde otros sockets
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado");
            InputStream in = cliente.getInputStream();
            OutputStream out = cliente.getOutputStream();

            //Leer Nombre del empleado
            DataInputStream dis = new DataInputStream(in);
            String nombre = dis.readUTF();
            if (nombre.equals("x")) break;
            String resultado = Servidor.getFecha(nombre);
            Servicios servicios = new Servicios();
            servicios.escribir(resultado, "C:/Users/APP DISTRIBUIDAS/Desktop/asistenciaUsuario.dat");
            System.out.println("Archivo almacenado exitosamente");
            System.out.println("Mensaje recibido exitosamente");

            //Devolver la respuesta al cliente
            DataOutputStream dos = new DataOutputStream(out);
            dos.writeUTF(resultado);
            cliente.close();
        }
        servidor.close();
    }
}
*/


package TCP.clase;

import TCP.servicios.Servicios;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class Servidor {

    private String prueba;

    public Servidor(){}




    //Método para capturar la fecha
    public static String getFecha(String nombre, Time ingresoTrabajo, Time salidaAlmuerzo, Time ingresoAlmuerzo, Time salidaTrabajo)
    {
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        return nombre + "\n Hora de ingreso trabajo: " + formato.format(ingresoTrabajo)
                    + "\n Hora de salida almuerzo: " + formato.format(salidaAlmuerzo)
                    + "\n Hora de ingreso almuerzo: " + formato.format(ingresoAlmuerzo)
                    + "\n Hora de salida trabajo: " + formato.format(salidaTrabajo) + "\n";
    }


    //Método para iniciar el servidor
    public static void procesarSolicitud(int puerto) throws Exception {
        //Se crea el socket del servidor
        ServerSocket servidor = new ServerSocket(puerto);
        System.out.println("Servidor de Fechas corriendo... ");

        while (true)
        {
            //Acepta solicitudes desde otros sockets
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado");
            InputStream in = cliente.getInputStream();
            OutputStream out = cliente.getOutputStream();

            //Leer Nombre del empleado
            DataInputStream dis = new DataInputStream(in);
            String data = dis.readUTF();
            if (data.equals("x")) break;

            String[] partes = data.split(",");
            System.out.println("###############: "+ Arrays.toString(partes));
            if (partes.length == 5)
            {
                try
                {
                    String nombre = partes[0];
                    String ingresoTrabajo = partes[1];
                    String salidaAlmuerzo = partes[2];
                    String ingresoAlmuerzo = partes[3];
                    String salidaTrabajo = partes[4];

                    String resultado = Servidor.getFecha(nombre, Time.valueOf(ingresoTrabajo), Time.valueOf(salidaAlmuerzo),
                            Time.valueOf(ingresoAlmuerzo), Time.valueOf(salidaTrabajo));
                    System.out.println(resultado);
                    Servicios servicios = new Servicios();
                    String ruta = "C:/Users/angel/Desktop/Archivos/asistencia_" + nombre + ".dat";
                    servicios.escribir(resultado, ruta);
                    System.out.println("Archivo almacenado exitosamente");
                    System.out.println("Mensaje recibido exitosamente");

                    //Devolver la respuesta al cliente
                    DataOutputStream dos = new DataOutputStream(out);
                    dos.writeUTF(resultado);
                    cliente.close();
                }

                catch(Exception e)
                {
                    System.out.println("Error en el servidor");
                }

            }


        }
        servidor.close();
    }
}
