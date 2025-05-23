package TCP.Cliente.clase;

import java.io.*;
import java.net.Socket;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.sql.Time;


public class Cliente {

    private static final int PUERTO = 8080;
    private static final String IP = "192.168.56.1";

    public String leer(String ruta) throws Exception {
        FileInputStream fis = new FileInputStream(ruta);
        ObjectInputStream ois = new ObjectInputStream(fis);
        String p = (String) ois.readObject();
        ois.close();
        return p;
    };

    public static String enviarAsistencias(String nombre, String ingresoTrabajo, String salidaAlmuerzo, String ingresoAlmuerzo, String salidaTrabajo) throws Exception {
        Socket cliente = new Socket(IP, PUERTO);

        InputStream in = cliente.getInputStream();
        OutputStream out = cliente.getOutputStream();

        //Envair datos al server
        DataOutputStream dos = new DataOutputStream(out);
        String datos = nombre+","+ingresoTrabajo+","+salidaAlmuerzo+","+ingresoAlmuerzo+","+salidaTrabajo;
        dos.writeUTF(datos);

        //Leer la respuesta
        DataInputStream dis = new DataInputStream(in);
        String respuesta = dis.readUTF();
        cliente.close();

        return respuesta;
    }
}
