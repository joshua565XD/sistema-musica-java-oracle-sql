package report;

import DBConnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.Document;

public class ReportesDAO {

    // Método para contar usuarios
    public static int contarUsuarios() {
        String sql = "SELECT COUNT(*) AS total FROM usuarios";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // En caso de error o si no hay usuarios
    }

    // Método para contar canciones
    public static int contarCanciones() {
        String sql = "SELECT COUNT(*) AS total FROM canciones";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // En caso de error o si no hay canciones
    }

    // Método para contar listas de reproducción
    public static int contarListas() {
        String sql = "SELECT COUNT(*) AS total FROM listas_reproduccion";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // En caso de error o si no hay listas
    }
    
    public static List<Object[]> obtenerReporteUsuarios() {
    List<Object[]> reporteUsuarios = new ArrayList<>();

    String sql = "SELECT u.nombre, COUNT(DISTINCT c.id) AS total_canciones, "
                 + "COUNT(DISTINCT lr.id) AS total_listas "
                 + "FROM usuarios u "
                 + "LEFT JOIN canciones c ON u.id = c.usuario_id "
                 + "LEFT JOIN listas_reproduccion lr ON u.id = lr.usuario_id "
                 + "GROUP BY u.nombre";

    try (Connection conn = DatabaseConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            String nombre = rs.getString("nombre");
            int totalCanciones = rs.getInt("total_canciones");
            int totalListas = rs.getInt("total_listas");
            
            Object[] fila = {nombre, totalCanciones, totalListas};
            reporteUsuarios.add(fila);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return reporteUsuarios;
}
    

}
