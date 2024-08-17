/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

public class PanelReproductor extends JPanel {
    private AdvancedPlayer player;
    private FileInputStream fileInputStream;
    private Thread playerThread;
    private volatile boolean isPaused = false;
    private volatile boolean isStopped = false;
    private volatile long pausePosition = 0;
    private float volumen = 0.5f;

    public void play(String filePath) {
        stop(); // Detiene la reproducción actual si existe

        try {
            fileInputStream = new FileInputStream(filePath);
            player = new AdvancedPlayer(fileInputStream);
            isStopped = false;
            isPaused = false;
            pausePosition = 0;
            playerThread = new Thread(() -> {
                try {
                    while (!isStopped) {
                        if (!isPaused) {
                            player.play((int) pausePosition, Integer.MAX_VALUE);
                            pausePosition = fileInputStream.getChannel().position();
                        }
                        Thread.sleep(100); // Pequeña pausa para evitar el uso intensivo del CPU
                    }
                } catch (JavaLayerException | IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
            playerThread.start();
        } catch (JavaLayerException | IOException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        isPaused = true;
        System.out.println("Pause");
    }

    public void resume() {
        if (isPaused) {
            isPaused = false;
            synchronized (playerThread) {
                playerThread.notify();
            }
        }
    }
    
    public void stop() {
 // Implementar la lógica de detener
        if (player != null) {
            player.close();
        }
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Método para obtener los metadatos
    public Cancion obtenerMetadatos(String filePath) {
        File selectedFile = new File(filePath);
        String titulo = selectedFile.getName();
        String artista = "Artista Desconocido";
        String album = "Álbum Desconocido";
        String duracion = "00:00";

        try {
            AudioFile audioFile = AudioFileIO.read(selectedFile);
            Tag tag = audioFile.getTag();
            artista = tag.getFirst(FieldKey.ARTIST);
            album = tag.getFirst(FieldKey.ALBUM);
            titulo = tag.getFirst(FieldKey.TITLE);
            duracion = audioFile.getAudioHeader().getTrackLength() / 60 + ":" + audioFile.getAudioHeader().getTrackLength() % 60;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Cancion(titulo, artista, album, duracion, filePath);
    }
    public void setVolumen(double nuevoVolumen) {
        volumen = (float) nuevoVolumen;
    }
    
}