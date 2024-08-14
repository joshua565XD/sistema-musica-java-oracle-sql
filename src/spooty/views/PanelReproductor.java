package spooty.views;

import java.io.File;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JPanel;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import spooty.models.Cancion;

/**
 * Clase PanelReproductor extiende JPanel y gestiona la reproducción de audio.
 */
public class PanelReproductor extends JPanel {

    private AdvancedPlayer player; // Objeto para la reproducción avanzada de audio
    private FileInputStream fileInputStream; // Flujo de entrada para el archivo de audio
    private Thread playerThread; // Hilo que gestiona la reproducción del audio
    private volatile boolean isPaused = false; // Indicador de si la reproducción está en pausa
    private volatile boolean isStopped = false; // Indicador de si la reproducción está detenida
    private volatile long pausePosition = 0; // Posición de pausa en el archivo de audio
    private float volumen = 0.5f; // Nivel de volumen actual

    /**
     * Inicia la reproducción del archivo de audio especificado.
     * 
     * @param filePath Ruta del archivo de audio a reproducir.
     */
    public void play(String filePath) {
        stop(); // Detiene la reproducción actual si existe

        try {
            // Inicializa el flujo de entrada y el reproductor
            fileInputStream = new FileInputStream(filePath);
            player = new AdvancedPlayer(fileInputStream);
            isStopped = false;
            isPaused = false;
            pausePosition = 0;

            // Crea y arranca el hilo para gestionar la reproducción
            playerThread = new Thread(() -> {
                try {
                    while (!isStopped) { // Reproduce mientras no esté detenido
                        if (!isPaused) { // Solo reproduce si no está en pausa
                            player.play((int) pausePosition, Integer.MAX_VALUE); // Reproduce desde la posición de pausa
                            pausePosition = fileInputStream.getChannel().position(); // Actualiza la posición de pausa
                        }
                        Thread.sleep(100); // Pausa breve para evitar el uso intensivo del CPU
                    }
                } catch (JavaLayerException | IOException | InterruptedException e) {
                    e.printStackTrace(); // Maneja las excepciones durante la reproducción
                }
            });
            playerThread.start(); // Inicia el hilo de reproducción
        } catch (JavaLayerException | IOException e) {
            e.printStackTrace(); // Maneja las excepciones durante la inicialización
        }
    }

    /**
     * Pausa la reproducción del audio.
     */
    public void pause() {
        isPaused = true; // Establece el estado de pausa
        System.out.println("Pause"); // Imprime un mensaje en la consola
    }

    /**
     * Reanuda la reproducción del audio.
     */
    public void resume() {
        if (isPaused) { // Solo reanuda si estaba en pausa
            isPaused = false;
            synchronized (playerThread) {
                playerThread.notify(); // Notifica al hilo de reproducción para que continúe
            }
        }
    }
    
    /**
     * Detiene la reproducción del audio y cierra los recursos.
     */
    public void stop() {
        isStopped = true; // Cambia la bandera para detener el hilo de reproducción

        // Interrumpe el hilo de reproducción si está en ejecución
        if (playerThread != null && playerThread.isAlive()) {
            playerThread.interrupt();
        }

        // Cierra el reproductor y el flujo de entrada
        if (player != null) {
            player.close();
        }
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace(); // Maneja excepciones al cerrar el flujo de entrada
            }
        }
    }
    
    /**
     * Obtiene los metadatos del archivo de audio especificado.
     * 
     * @param filePath Ruta del archivo de audio.
     * @return Un objeto Cancion con los metadatos obtenidos.
     */
    public Cancion obtenerMetadatos(String filePath) {
        File selectedFile = new File(filePath); // Crea un objeto File con la ruta especificada
        String titulo = selectedFile.getName(); // Obtiene el nombre del archivo como título por defecto
        String artista = "Artista Desconocido"; // Artista por defecto
        String album = "Álbum Desconocido"; // Álbum por defecto
        String duracion = "00:00"; // Duración por defecto

        try {
            // Lee el archivo de audio y obtiene los metadatos
            AudioFile audioFile = AudioFileIO.read(selectedFile);
            Tag tag = audioFile.getTag();
            artista = tag.getFirst(FieldKey.ARTIST); // Obtiene el nombre del artista
            album = tag.getFirst(FieldKey.ALBUM); // Obtiene el nombre del álbum
            titulo = tag.getFirst(FieldKey.TITLE); // Obtiene el título de la pista
            // Calcula la duración en formato MM:SS
            duracion = audioFile.getAudioHeader().getTrackLength() / 60 + ":" + audioFile.getAudioHeader().getTrackLength() % 60;
        } catch (Exception e) {
            e.printStackTrace(); // Maneja excepciones al obtener metadatos
        }

        return new Cancion(titulo, artista, album, duracion, filePath); // Crea y retorna un objeto Cancion con los metadatos
    }

    /**
     * Establece el nivel de volumen de reproducción.
     * 
     * @param nuevoVolumen El nuevo nivel de volumen (debe estar en el rango 0.0 a 1.0).
     */
    public void setVolumen(double nuevoVolumen) {
        volumen = (float) nuevoVolumen; // Actualiza el nivel de volumen
    }
}
