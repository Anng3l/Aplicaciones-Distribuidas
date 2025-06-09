package clase;

import java.sql.*;
import java.util.ArrayList;

public class Consulta {

    static private String ruta = "C:/Users/angel/Desktop/Commit/Aplicaciones-Distribuidas/Examen/Servidor RMI -- Examen/src/BD/empleados.db";
    //Métodos para las consultas a la BD

    public static ArrayList<Persona> getPersonas() {
        ArrayList<Persona> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, correo, cargo, sueldo FROM empleado";
        //String ruta = "C:/Users/angel/Desktop/Aplicaciones-Distribuidas/Servidor RMI/Protocolo RNI/src/BD/empleados.db";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + ruta);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Persona p = new Persona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("cargo"),
                        rs.getDouble("sueldo")
                );
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }









    public static ArrayList<Persona> buscarPorNombre(String nombre) {
        ArrayList<Persona> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, correo, cargo, sueldo FROM empleado WHERE nombre LIKE ?";
        //String ruta = "C:/Users/angel/Desktop/Aplicaciones-Distribuidas/Servidor RMI/Protocolo RNI/src/BD/empleados.db";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + ruta);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + nombre + "%"); // Coincidencias parciales
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nombre");
                String correo = rs.getString("correo");
                String cargo = rs.getString("cargo");
                double sueldo = rs.getDouble("sueldo");

                lista.add(new Persona(id, nom, correo, cargo, sueldo));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }










    public static boolean createPersona(Persona p) {
        //String ruta = "C:/Users/angel/Desktop/Aplicaciones-Distribuidas/Servidor RMI/Protocolo RNI/src/BD/empleados.db";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + ruta)) {
            String sql = "INSERT INTO empleado (nombre, correo, cargo, sueldo) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, p.getNombre());
            pstmt.setString(2, p.getCorreo());
            pstmt.setString(3, p.getCargo());
            pstmt.setDouble(4, p.getSueldo());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }










    public static boolean updatePersona(Persona p) {
        //String ruta = "C:/Users/angel/Desktop/Aplicaciones-Distribuidas/Servidor RMI/Protocolo RNI/src/BD/empleados.db";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + ruta)) {
            String sqlSelect = "SELECT * FROM empleado WHERE id = ?";
            PreparedStatement selectStmt = conn.prepareStatement(sqlSelect);
            selectStmt.setInt(1, p.getClave());
            ResultSet rs = selectStmt.executeQuery();

            if (!rs.next()) {
                return false;
            }

            String nombre = (p.getNombre() == null || p.getNombre().trim().isEmpty())
                    ? rs.getString("nombre")
                    : p.getNombre();

            String correo = (p.getCorreo() == null || p.getCorreo().trim().isEmpty())
                    ? rs.getString("correo")
                    : p.getCorreo();

            String cargo = (p.getCargo() == null || p.getCargo().trim().isEmpty())
                    ? rs.getString("cargo")
                    : p.getCargo();

            double sueldo = (p.getSueldo() == -1)
                    ? rs.getDouble("sueldo")
                    : p.getSueldo();

            String sqlUpdate = "UPDATE empleado SET nombre = ?, correo = ?, cargo = ?, sueldo = ? WHERE id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(sqlUpdate);
            updateStmt.setString(1, nombre);
            updateStmt.setString(2, correo);
            updateStmt.setString(3, cargo);
            updateStmt.setDouble(4, sueldo);
            updateStmt.setInt(5, p.getClave());

            int filasAfectadas = updateStmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }










    public static boolean deletePersona(int id) {
        //String ruta = "C:/Users/angel/Desktop/Aplicaciones-Distribuidas/Servidor RMI/Protocolo RNI/src/BD/empleados.db";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + ruta)) {
            String sql = "DELETE FROM empleado WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

