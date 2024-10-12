package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe"; // Cambia esto
    private static final String USER = "C##ALAN"; // Cambia esto
    private static final String PASSWORD = "1234"; // Cambia esto

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}