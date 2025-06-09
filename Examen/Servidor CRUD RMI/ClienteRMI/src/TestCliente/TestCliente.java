package TestCliente;


import Ventana.VentanaCarrera;
import clase.Servidor;

import java.rmi.Naming;


public class TestCliente {
    public static void main(String[] args) throws Exception {
        String rmiObjectName = "rmi://192.168.100.21:1099/Datos";
        Servidor servicio = null;
        servicio = (Servidor) Naming.lookup(rmiObjectName);
        new VentanaCarrera(servicio);
    }
}
