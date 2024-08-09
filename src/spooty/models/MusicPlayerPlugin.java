/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spooty.models;

/**
 *
 * @author alane
 */
public interface MusicPlayerPlugin {
    void play(String filePath);
    void pause();
    void stop();
    void setVolume(int volume);
    void seek(int position);
}
