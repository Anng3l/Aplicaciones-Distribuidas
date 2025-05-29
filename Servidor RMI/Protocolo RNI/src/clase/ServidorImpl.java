package clase;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServidorImpl extends UnicastRemoteObject implements Servidor {

    private static ArrayList<Persona> listPersonas() {
        /*ArrayList<Persona> lista = new ArrayList<Persona>();
        lista.add(new Persona(1, "Juan Perez", "jp@gmail.com", "administrador", 2500));
        lista.add(new Persona(2, "Luis Medina", "lm@gmail.com", "técnico", 1500));
        lista.add(new Persona(3, "Pablo Medina", "pm@gmail.com", "Jefe de sistemas", 3500));
        lista.add(new Persona(4, "Pom Yanez", "py@gmail.com", "Secretaria", 1200));
        lista.add(new Persona(5, "Juan Montalvo", "jm@gmail.com", "colaborador", 1000));
        lista.add(new Persona(6, "Juana Arcos", "ja@gmail.com", "secretaria", 1200));
        lista.add(new Persona(7, "Carlos Mazo", "cm@gmail.com", "colaborador", 1000));
        lista.add(new Persona(8, "Jack Manostijeras", "jm@gmail.com", "colaborador", 1000));

        return lista;*/

        return Consulta.getPersonas();
    }

    /*
    private static String getPersona(int id) {
        return "Nombre " + listPersonas().get(id-1).getNombre() + "\n" +
                "Correo " + listPersonas().get(id-1).getCorreo() + "\n" +
                "Cargo " + listPersonas().get(id-1).getCargo() + "\n" +
                "Sueldo " + listPersonas().get(id-1).getSueldo() + "\n";
    }
    */
    private static String getPersona(int id) {
        return "Nombre " + listPersonas().get(id-1).getNombre() + "\n" +
                "Correo " + listPersonas().get(id-1).getCorreo() + "\n" +
                "Cargo " + listPersonas().get(id-1).getCargo() + "\n" +
                "Sueldo " + listPersonas().get(id-1).getSueldo() + "\n";
    }

    public ServidorImpl() throws RemoteException { super(); }

    @Override
    public String consultar(int id) throws Exception {
        if (id<listPersonas().size()+1) {
            return getPersona(id);}
        else {
            return "No existen datos del empleado";
        }
    }
}
