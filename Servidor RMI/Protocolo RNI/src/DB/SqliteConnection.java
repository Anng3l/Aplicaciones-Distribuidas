package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {
    private static final String URL = "jdbc:sqlite:C:/Users/APP DISTRIBUIDAS/Desktop/Protocolo RNI/src/empleados.db";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}

