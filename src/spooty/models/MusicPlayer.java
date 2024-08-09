/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spooty.models;

/**
 *
 * @author alane
 */
import spooty.models.Song;
import java.util.ArrayList;

public class MusicPlayer {
    private ArrayList<Song> songList;

    public MusicPlayer() {
        songList = new ArrayList<>();
    }

    public void addSong(Song song) {
        songList.add(song);
    }

    public void editSong(int index, Song song) {
        if (index >= 0 && index < songList.size()) {
            songList.set(index, song);
        }
    }

    public void removeSong(int index) {
        if (index >= 0 && index < songList.size()) {
            songList.remove(index);
        }
    }

    public void playSong(Song song) {
        // Implement play functionality
    }

    public void pauseSong() {
        // Implement pause functionality
    }

    public void stopSong() {
        // Implement stop functionality
    }

    public void exportPlaylist(String fileName) {
        // Implement export functionality
    }

    public ArrayList<Song> getSongList() {
        return songList;
    }
}

