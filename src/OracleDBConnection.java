import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDBConnection {
    // Parámetros de conexión
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl"; // Cambia 'orcl' por tu SID
    private static final String USER = "tu_usuario"; // Cambia por tu usuario
    private static final String PASSWORD = "tu_contraseña"; // Cambia por tu contraseña

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Establecer la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión a la base de datos Oracle establecida.");

            // Aquí puedes agregar tu lógica para trabajar con la base de datos

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        } finally {
            // Cerrar la conexión
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Conexión cerrada.");
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
}
