package spooty.models;

import DBConnection.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ListaReproduccion {

    private int id;
    private String nombre;

    // Constructor
    public ListaReproduccion(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    // Método para obtener las listas de reproducción de un usuario
    public static List<ListaReproduccion> obtenerListasPorUsuario(int usuarioId) {
        List<ListaReproduccion> listas = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT id, nombre FROM listas_reproduccion WHERE usuario_id = ? ORDER BY id";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, usuarioId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                // Crear un objeto ListaReproduccion con los datos obtenidos
                ListaReproduccion lista = new ListaReproduccion(id, nombre);
                listas.add(lista);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listas;
    }

    public static void crearLista(int usuarioId, String nombreLista) {
        // Conectar a la base de datos
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Preparar la instrucción SQL para insertar la nueva lista
            String sqlInsertLista = "INSERT INTO listas_reproduccion (nombre, usuario_id) VALUES (?, ?) ";
            try (PreparedStatement stmt = connection.prepareStatement(sqlInsertLista)) {
                stmt.setString(1, nombreLista);
                stmt.setInt(2, usuarioId);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejar errores de la base de datos
        }
    }

    public static void agregarCancionALista(int cancionId, int listaId) {
        // Conectar a la base de datos
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Preparar la instrucción SQL para insertar la relación canción-lista
            String sqlInsertCancionLista = "INSERT INTO LISTA_CANCIONES (cancion_id, lista_id) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sqlInsertCancionLista)) {
                stmt.setInt(1, cancionId);
                stmt.setInt(2, listaId);
                stmt.executeUpdate(); // Ejecutar la inserción
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejar errores de la base de datos
            // Aquí puedes optar por lanzar una excepción, si deseas manejar el error en otro lugar
        }
    }

    // Método para obtener las canciones de una lista de reproducción con los detalles de la lista
    public static List<Object[]> obtenerCancionesDeListasPorUsuario(int usuarioId) {
        List<Object[]> cancionesConLista = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getConnection();
            String query = "SELECT lr.nombre AS lista_nombre, c.titulo, c.artista, c.album, c.duracion, c.ruta_archivo "
                    + "FROM listas_reproduccion lr "
                    + "JOIN lista_canciones lc ON lr.id = lc.lista_id "
                    + "JOIN canciones c ON lc.cancion_id = c.id "
                    + "WHERE lr.usuario_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, usuarioId);

            ResultSet rs = pstmt.executeQuery();

            // Recorremos los resultados y agregamos los datos combinados
            while (rs.next()) {
                String nombreLista = rs.getString("lista_nombre");
                String titulo = rs.getString("titulo");
                String artista = rs.getString("artista");
                String album = rs.getString("album"); // Obtener el álbum
                String duracion = rs.getString("duracion"); // Cambiar a String
                String ruta = rs.getString("ruta_archivo"); // Cambiar a ruta_archivo

                // Agregamos la fila combinada a la lista
                Object[] fila = {nombreLista, titulo, artista, album, duracion, ruta}; // Incluir álbum en la fila
                cancionesConLista.add(fila);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cancionesConLista;
    }

}
