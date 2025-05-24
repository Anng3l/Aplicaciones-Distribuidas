/*
package TCP.clase;

import TCP.servicios.Servicios;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


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
    static String getFecha(String nombre, Time ingresoTrabajo, Time salidaAlmuerzo, Time ingresoAlmuerzo, Time salidaTrabajo)
    {
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = formatoFecha.format(new Date());

        return nombre + "\n Fecha: " + fechaActual
                    + "\n Hora de ingreso trabajo: " + formato.format(ingresoTrabajo)
                    + "\n Hora de salida almuerzo: " + formato.format(salidaAlmuerzo)
                    + "\n Hora de ingreso almuerzo: " + formato.format(ingresoAlmuerzo)
                    + "\n Hora de salida trabajo: " + formato.format(salidaTrabajo) + "\n";
    }

    public static String getAsistencias(String username) throws Exception {
        String ruta = "C:/Users/angel/Desktop/Archivos/asistencia_" + username + ".dat";

        Servicios servicios = new Servicios();

        String asistencias = servicios.leer(ruta);

        return asistencias;
    }


    public static void procesarSolicitud(int puerto) throws Exception {
        ServerSocket servidor = new ServerSocket(puerto);
        System.out.println("Servidor corriendo en puerto " + puerto);

        while (true) {
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado");

            try {
                InputStream in = cliente.getInputStream();
                OutputStream out = cliente.getOutputStream();

                DataInputStream dis = new DataInputStream(in);
                DataOutputStream dos = new DataOutputStream(out);

                // Primero leemos el tipo de solicitud: "registrar" o "consultar"
                String datos = dis.readUTF();
                String[] datosSeparados = datos.split(",");
                String tipo = datosSeparados[0];
                System.out.println("Tipo de solicitud: " + tipo);

                if (tipo.equals("x")) break;

                if (tipo.equalsIgnoreCase("registrar")) {
                    String nombre = datosSeparados[1];
                    String ingresoTrabajo = datosSeparados[2];
                    String salidaAlmuerzo = datosSeparados[3];
                    String ingresoAlmuerzo = datosSeparados[4];
                    String salidaTrabajo = datosSeparados[5];

                    String resultado = getFecha(nombre, Time.valueOf(ingresoTrabajo), Time.valueOf(salidaAlmuerzo),
                            Time.valueOf(ingresoAlmuerzo), Time.valueOf(salidaTrabajo));

                    String ruta = "C:/Users/angel/Desktop/Archivos/asistencia_" + nombre + ".dat";
                    Servicios servicios = new Servicios();
                    servicios.escribir(resultado, ruta);

                    System.out.println("Archivo almacenado exitosamente");
                    dos.writeUTF("Asistencia registrada:\n" + resultado);

                }

                else if (tipo.equalsIgnoreCase("consultar")) {
                    // Recibimos el nombre del usuario
                    String username = datosSeparados[1];
                    String ruta = "C:/Users/angel/Desktop/Archivos/asistencia_" + username + ".dat";
                    Servicios servicios = new Servicios();
                    String asistencias = servicios.leer(ruta);
                    dos.writeUTF("Asistencias de " + username + ":\n" + asistencias);
                    System.out.println(asistencias);
                }

                cliente.close();

            } catch (Exception e) {
                System.out.println("Error en el servidor:");
                e.printStackTrace();
            }
        }

        servidor.close();
    }

}
