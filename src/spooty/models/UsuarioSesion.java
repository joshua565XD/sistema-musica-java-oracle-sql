package spooty.models;

public class UsuarioSesion {
    private static Usuario usuarioActual;

    // Método para establecer el usuario actual
    public static void iniciarSesion(Usuario usuario) {
        usuarioActual = usuario;
    }

    // Método para obtener el usuario actual
    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    // Método para cerrar sesión
    public static void cerrarSesion() {
        usuarioActual = null; // Se elimina el usuario actual
    }

    // Verificar si hay un usuario logueado
    public static boolean haySesionIniciada() {
        return usuarioActual != null;
    }
}
