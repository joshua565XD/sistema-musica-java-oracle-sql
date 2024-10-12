package spooty.models;

import java.util.ArrayList;

/**
 * Clase que representa una lista de reproducción de canciones.
 */
public class ListaReproduccion {
    private ArrayList<Cancion> playlist;

    // Constructor
    public ListaReproduccion() {
        this.playlist = new ArrayList<>();
    }

    // Método para agregar una canción a la lista de reproducción
    public void agregarCancion(Cancion cancion) {
        playlist.add(cancion);
        System.out.println("Canción agregada: " + cancion.getTitulo());
        imprimirPlaylist();
    }

    // Método para eliminar una canción de la lista de reproducción por índice
    public void eliminarCancion(int index) {
        if (index >= 0 && index < playlist.size()) {
            Cancion cancionEliminada = playlist.remove(index);
            System.out.println("Canción eliminada: " + cancionEliminada.getTitulo());
            imprimirPlaylist();
        } else {
            System.out.println("Índice inválido. No se pudo eliminar la canción.");
        }
    }

    // Método para obtener la lista completa de canciones
    public ArrayList<Cancion> getPlaylist() {
        return playlist;
    }

    // Método para imprimir la lista de reproducción
    public void imprimirPlaylist() {
        if (playlist.isEmpty()) {
            System.out.println("La lista de reproducción está vacía.");
        } else {
            System.out.println("Lista de Reproducción:");
            for (int i = 0; i < playlist.size(); i++) {
                Cancion cancion = playlist.get(i);
                System.out.println((i + 1) + ". " + cancion);
            }
        }
    }

    // Método para obtener el número de canciones en la lista
    public int getNumeroDeCanciones() {
        return playlist.size();
    }

    // Método para limpiar toda la lista de reproducción
    public void limpiarPlaylist() {
        playlist.clear();
        System.out.println("La lista de reproducción ha sido vaciada.");
    }
}
