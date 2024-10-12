package spooty.models;

import DBConnection.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Cancion {
    private int id; // ID de la canción
    private String titulo;
    private String artista;
    private String album;
    private String duracion;
    private String rutaArchivo;
    private int usuarioId;  // ID del usuario que la registró

    // Constructor
    public Cancion(String titulo, String artista, String album, String duracion, String rutaArchivo, int usuarioId) {
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.duracion = duracion;
        this.rutaArchivo = rutaArchivo;
        this.usuarioId = usuarioId;
    }

    // CRUD Operations

    // Crear (Insertar una nueva canción en la base de datos)
    public void guardar() {
        String sql = "INSERT INTO canciones (titulo, artista, album, duracion, ruta_archivo, usuario_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, this.titulo);
            pstmt.setString(2, this.artista);
            pstmt.setString(3, this.album);
            pstmt.setString(4, this.duracion);
            pstmt.setString(5, this.rutaArchivo);
            pstmt.setInt(6, this.usuarioId);  // Asociar el ID del usuario

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.id = generatedKeys.getInt(1); // Asignar el ID generado
                    System.out.println("Canción guardada exitosamente con ID: " + this.id);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar la canción: " + e.getMessage());
        }
    }

    // Leer (Buscar una canción por título)
    public static Cancion buscarPorTitulo(String titulo) {
        String sql = "SELECT * FROM canciones WHERE titulo = ?";
        Cancion cancion = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, titulo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                cancion = new Cancion(rs.getString("titulo"),
                                      rs.getString("artista"),
                                      rs.getString("album"),
                                      rs.getString("duracion"),
                                      rs.getString("ruta_archivo"),
                                      rs.getInt("usuario_id"));  // Obtener el usuario que registró la canción
                cancion.setId(rs.getInt("id")); // Establecer el ID al crear la canción
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar la canción: " + e.getMessage());
        }
        return cancion;
    }

    // Leer (Listar todas las canciones)
    public static List<Cancion> listarCanciones() {
        List<Cancion> canciones = new ArrayList<>();
        String sql = "SELECT * FROM canciones";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cancion cancion = new Cancion(rs.getString("titulo"),
                                              rs.getString("artista"),
                                              rs.getString("album"),
                                              rs.getString("duracion"),
                                              rs.getString("ruta_archivo"),
                                              rs.getInt("usuario_id"));  // Agregar el ID del usuario
                cancion.setId(rs.getInt("id")); // Establecer el ID al listar la canción
                canciones.add(cancion);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar canciones: " + e.getMessage());
        }
        return canciones;
    }
    
    public static List<Cancion> listarCancionesPorUsuario(int usuarioId) {
    List<Cancion> canciones = new ArrayList<>();
    String sql = "SELECT * FROM canciones WHERE usuario_id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        // Establecer el ID del usuario en la consulta
        pstmt.setInt(1, usuarioId);
        ResultSet rs = pstmt.executeQuery();

        // Iterar sobre los resultados
        while (rs.next()) {
            Cancion cancion = new Cancion(rs.getString("titulo"),
                                          rs.getString("artista"),
                                          rs.getString("album"),
                                          rs.getString("duracion"),
                                          rs.getString("ruta_archivo"),
                                          rs.getInt("usuario_id"));  // Obtener el ID del usuario
            cancion.setId(rs.getInt("id"));  // Establecer el ID de la canción
            canciones.add(cancion);
        }
    } catch (SQLException e) {
        System.out.println("Error al listar canciones por usuario: " + e.getMessage());
    }

    return canciones;
}

        public static void eliminar(String titulo) {
        String sql = "DELETE FROM canciones WHERE titulo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, titulo);
            pstmt.executeUpdate();
            System.out.println("Canción eliminada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar la canción: " + e.getMessage());
        }
    }


    // Otros métodos como actualizar y eliminar también deberán tener en cuenta el `usuario_id`.

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getId() {
        return id; // Método para obtener el ID de la canción
    }

    public void setId(int id) {
        this.id = id; // Método para establecer el ID de la canción
    }
}
