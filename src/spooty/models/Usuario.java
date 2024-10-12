package spooty.models;

import DBConnection.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;  // ID autogenerado
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private boolean esAdministrador;

    // Constructor sin ID (para nuevos registros)
    public Usuario(String nombre, String apellido, String correo, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
        this.esAdministrador = false; // Por defecto no es administrador
    }

    // Constructor con ID (para usuarios existentes)
    public Usuario(int id, String nombre, String apellido, String correo, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
        this.esAdministrador = false; // Por defecto no es administrador
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isEsAdministrador() {
        return esAdministrador;
    }

    public void setEsAdministrador(boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Nombre: %s %s, Correo: %s, Administrador: %b",
                             id, nombre, apellido, correo, esAdministrador);
    }

    // CRUD Operations

 // Crear
    //registro
    public void guardar() {
        String sql = "INSERT INTO usuarios (nombre, apellido, correo, contrasena, es_administrador) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, this.nombre);
            pstmt.setString(2, this.apellido);
            pstmt.setString(3, this.correo);
            pstmt.setString(4, this.contrasena);
            pstmt.setInt(5, this.esAdministrador ? 1 : 0); // Convertir boolean a número

            pstmt.executeUpdate();
            System.out.println("Usuario guardado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
        }
    }



    // Leer (Buscar por correo)
    // login
    public static Usuario buscarPorCorreo(String correo) {
        String sql = "SELECT * FROM usuarios WHERE correo = ?";
        Usuario usuario = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(rs.getInt("id"),
                                      rs.getString("nombre"),
                                      rs.getString("apellido"),
                                      rs.getString("correo"),
                                      rs.getString("contrasena"));
                usuario.setEsAdministrador(rs.getInt("es_administrador") == 1);
                            UsuarioSesion.iniciarSesion(usuario);

            }
            System.out.println(usuario);
        } catch (SQLException e) {
            System.out.println("Error al buscar el usuario: " + e.getMessage());
        }
        return usuario;
    }

    // Leer (Listar todos los usuarios)
    public static List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt("id"),
                                              rs.getString("nombre"),
                                              rs.getString("apellido"),
                                              rs.getString("correo"),
                                              rs.getString("contrasena"));
                usuario.setEsAdministrador(rs.getInt("es_administrador") == 1);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    // Actualizar
    public void actualizar(String correo) {
        String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, contrasena = ?, es_administrador = ? WHERE correo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, this.nombre);
            pstmt.setString(2, this.apellido);
            pstmt.setString(3, this.contrasena);
            pstmt.setInt(4, this.esAdministrador ? 1 : 0);
            pstmt.setString(5, correo);

            pstmt.executeUpdate();
            System.out.println("Usuario actualizado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    // Eliminar
    public static void eliminar(String correo) {
        String sql = "DELETE FROM usuarios WHERE correo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);
            pstmt.executeUpdate();
            System.out.println("Usuario eliminado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el usuario: " + e.getMessage());
        }
    }
}
