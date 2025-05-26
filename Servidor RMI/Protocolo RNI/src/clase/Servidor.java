package clase;

import java.rmi.Remote;

public interface Servidor extends Remote {

    //Consultar id de empleado. Devolverá lo consultado en String.
    public String consultar(int id) throws Exception;

}
