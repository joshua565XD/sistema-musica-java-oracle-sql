/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spooty.views;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JPanel;

public class PanelReproductor extends JPanel {
    private AdvancedPlayer player;
    private FileInputStream fileInputStream;
    private Thread playerThread;
    private volatile boolean isPaused = false;
    private volatile boolean isStopped = false;
    private volatile long pausePosition = 0;

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
        isStopped = true;
        if (playerThread != null) {
            playerThread.interrupt();
        }
        try {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
