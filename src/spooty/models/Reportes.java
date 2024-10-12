package spooty.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Reportes {
    private ArrayList<String> cancionesEnListas; // Almacena las canciones que están en listas
    private ArrayList<String> reproducciones; // Almacena las reproducciones de canciones

    // Constructor
    public Reportes() {
        this.cancionesEnListas = new ArrayList<>();
        this.reproducciones = new ArrayList<>();
    }

    // Método para agregar una canción a una lista
    public void agregarCancionEnLista(String tituloCancion) {
        cancionesEnListas.add(tituloCancion);
    }

    // Método para agregar una reproducción
    public void agregarReproduccion(String tituloCancion) {
        reproducciones.add(tituloCancion);
    }

    // Generar reporte de la canción en más listas
    public String cancionEnMasListas() {
        if (cancionesEnListas.isEmpty()) {
            return "No hay canciones en listas.";
        }

        // Usamos un HashMap para contar las apariciones de cada canción
        Map<String, Integer> conteoCanciones = new HashMap<>();
        for (String cancion : cancionesEnListas) {
            conteoCanciones.put(cancion, conteoCanciones.getOrDefault(cancion, 0) + 1);
        }

        // Encontrar la canción con más apariciones
        String cancionMasComun = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : conteoCanciones.entrySet()) {
            if (entry.getValue() > maxCount) {
                cancionMasComun = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return cancionMasComun + " está en " + maxCount + " listas.";
    }

    // Generar reporte de la canción con más reproducciones
    public String cancionConMasReproducciones() {
        if (reproducciones.isEmpty()) {
            return "No hay reproducciones registradas.";
        }

        // Usamos un HashMap para contar las reproducciones de cada canción
        Map<String, Integer> conteoReproducciones = new HashMap<>();
        for (String cancion : reproducciones) {
            conteoReproducciones.put(cancion, conteoReproducciones.getOrDefault(cancion, 0) + 1);
        }

        // Encontrar la canción con más reproducciones
        String cancionMasReproducida = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : conteoReproducciones.entrySet()) {
            if (entry.getValue() > maxCount) {
                cancionMasReproducida = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return cancionMasReproducida + " ha sido reproducida " + maxCount + " veces.";
    }
}
