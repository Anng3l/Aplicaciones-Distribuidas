package clase;

import java.sql.*;
import java.util.ArrayList;

public class Consulta {

    public Consulta() {}

    public static ArrayList<Persona> getPersonas() {
        ArrayList<Persona> lista = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Carga el driver
            Class.forName("org.sqlite.JDBC");
            // Conecta a la base de datos (empleados.db)
            String ruta = "C:/Users/angel/Desktop/Aplicaciones-Distribuidas/Servidor RMI/Protocolo RNI/src/BD/empleados.db";
            conn = DriverManager.getConnection("jdbc:sqlite:" + ruta);
            // Crea la consulta
            String sql = "SELECT id, nombre, correo, cargo, sueldo FROM empleado";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            // Recorre resultados y crea objetos Persona
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                String cargo = rs.getString("cargo");
                double sueldo = rs.getDouble("sueldo");

                Persona p = new Persona(id, nombre, correo, cargo, sueldo);
                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cierra recursos
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        return lista;
    }
}
