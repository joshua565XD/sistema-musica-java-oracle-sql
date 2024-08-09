/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spooty.models;

/**
 *
 * @author alane
 */
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.IOException;

public class JLayerPlugin implements MusicPlayerPlugin {

    private AdvancedPlayer player;
    private FileInputStream fileInputStream;

    @Override
    public void play(String filePath) {
        try {
            fileInputStream = new FileInputStream(filePath);
            player = new AdvancedPlayer(fileInputStream);
            new Thread(() -> {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (JavaLayerException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pause() {
        // Implementar la lógica de pausa
    }

    @Override
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

    @Override
    public void setVolume(int volume) {
        // Implementar la lógica para ajustar el volumen
    }

    @Override
    public void seek(int position) {
        // Implementar la lógica para buscar una posición en el archivo
    }
}
