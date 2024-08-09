/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spooty.views;

/**
 *
 * @author alane
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import spooty.models.Song;

public class PlaylistView {
    private JTable songTable;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;

    public PlaylistView() {
        createTable();
    }

    private void createTable() {
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Title", "Artist", "Album", "Duration", "File Path"});
        songTable = new JTable(tableModel);
        scrollPane = new JScrollPane(songTable);
    }

    public void updateTable(ArrayList<Song> songs) {
        tableModel.setRowCount(0);
        for (Song song : songs) {
            tableModel.addRow(new Object[]{song.getTitle(), song.getArtist(), song.getAlbum(), song.getDuration(), song.getFilePath()});
        }
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}
