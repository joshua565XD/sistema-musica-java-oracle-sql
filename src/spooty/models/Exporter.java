/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spooty.models;

/**
 *
 * @author alane
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Exporter {
    public void exportToTextFile(ArrayList<Song> songs, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Song song : songs) {
                writer.write(song.getTitle() + " by " + song.getArtist() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportToCSV(ArrayList<Song> songs, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Title,Artist,Album,Duration,File Path\n");
            for (Song song : songs) {
                writer.write(song.getTitle() + "," + song.getArtist() + "," + song.getAlbum() + "," + song.getDuration() + "," + song.getFilePath() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
