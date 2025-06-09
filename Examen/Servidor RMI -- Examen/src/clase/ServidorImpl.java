package clase;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServidorImpl extends UnicastRemoteObject implements Servidor {

    static private String ruta = "C:/Users/angel/Desktop/Commit/Aplicaciones-Distribuidas/Examen/Servidor RMI -- Examen/src/BD/empleados.db";
    public ServidorImpl() throws RemoteException {
        super();
    }









    //Lógica para la implementación de la interfaz Servidor

    private static ArrayList<Persona> listPersonas() {
        return Consulta.getPersonas();
    }


    private static String getPersona(int id) {
        Persona p = listPersonas().stream().filter(per -> per.getClave() == id).findFirst().orElse(null);
        if (p == null) return "No encontrado";

        return "Nombre: " + p.getNombre() + "\n" +
                "Correo: " + p.getCorreo() + "\n" +
                "Cargo: " + p.getCargo() + "\n" +
                "Sueldo: " + p.getSueldo();
    }










    //   Implementación de las firmas de la interfaz Servidor


    //Listar 1 persona por ID
    @Override
    public String consultar(int id) throws RemoteException {
        return getPersona(id);
    }

    //Listar todos
    @Override
    public List<Persona> listar() throws RemoteException {
        return Consulta.getPersonas();
    }

    //Listar por nombre
    @Override
    public List<Persona> buscarPorNombre(String nombre) throws RemoteException {
        List<Persona> resultado = new ArrayList<>();


        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + ruta)) {
            String sql = "SELECT * FROM empleado WHERE nombre LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + nombre + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nombre");
                String correo = rs.getString("correo");
                String cargo = rs.getString("cargo");
                double sueldo = rs.getDouble("sueldo");

                Persona p = new Persona(id, nom, correo, cargo, sueldo);
                resultado.add(p);
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Error en consultarPorNombre", e);
        }
        return resultado;
    }




    //Crear 1 persona
    @Override
    public boolean crear(Persona p) throws RemoteException {
        return Consulta.createPersona(p);
    }

    //Actualizar los datos de 1 persona
    @Override
    public boolean actualizar(Persona p) throws RemoteException {
        return Consulta.updatePersona(p);
    }

    //Eliminar 1 persona
    @Override
    public boolean eliminar(int id) throws RemoteException {
        return Consulta.deletePersona(id);
    }
}
