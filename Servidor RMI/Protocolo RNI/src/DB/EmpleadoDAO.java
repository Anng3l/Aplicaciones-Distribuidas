package DB;

import clase.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    public List<Persona> obtenerTodos() {
        List<Persona> usuarios = new ArrayList<>();
        String sql = "SELECT id, nombre FROM usuarios";

        try (Connection conn = SqliteConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Persona u = new Persona(rs.getInt("id"), rs.getString("nombre"), rs.getString("correo"),
                                        rs.getString("cargo"), rs.getDouble("sueldo"));
                usuarios.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios: " + e.getMessage());
        }

        return usuarios;
    }

    public void insertarUsuario(String nombre) {
        String sql = "INSERT INTO usuarios(nombre) VALUES(?)";

        try (Connection conn = SqliteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.executeUpdate();
            System.out.println("Usuario insertado");
        } catch (SQLException e) {
            System.out.println("Error al insertar usuario: " + e.getMessage());
        }
    }
}
