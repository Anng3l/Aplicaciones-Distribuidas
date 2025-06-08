package clase;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Servidor extends Remote {
    public String consultar(int id) throws Exception;

    //Métodos faltantes del CRUD
    List<Persona> listar() throws RemoteException;
    List<Persona> buscarPorNombre(String nombre) throws Exception;
    boolean crear(Persona p) throws RemoteException;
    boolean actualizar(Persona p) throws RemoteException;
    boolean eliminar(int id) throws RemoteException;
}
