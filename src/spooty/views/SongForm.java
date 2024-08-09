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
import spooty.models.Song;

public class SongForm {
    private JFrame frame;
    private JTextField titleField;
    private JTextField artistField;
    private JTextField albumField;
    private JTextField durationField;
    private JTextField filePathField;
    public JButton addButton;
    public JButton editButton;
    public JButton deleteButton;

    public SongForm() {
        createForm();
    }

    private void createForm() {
        frame = new JFrame("Add/Edit Song");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new java.awt.GridLayout(6, 2));

        titleField = new JTextField();
        artistField = new JTextField();
        albumField = new JTextField();
        durationField = new JTextField();
        filePathField = new JTextField();
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");

        frame.add(new JLabel("Title:"));
        frame.add(titleField);
        frame.add(new JLabel("Artist:"));
        frame.add(artistField);
        frame.add(new JLabel("Album:"));
        frame.add(albumField);
        frame.add(new JLabel("Duration:"));
        frame.add(durationField);
        frame.add(new JLabel("File Path:"));
        frame.add(filePathField);
        frame.add(addButton);
        frame.add(editButton);
        frame.add(deleteButton);
    }

    public void showForm() {
        frame.setVisible(true);
    }

    public Song getSongFromInput() {
        String title = titleField.getText();
        String artist = artistField.getText();
        String album = albumField.getText();
        int duration = Integer.parseInt(durationField.getText());
        String filePath = filePathField.getText();
        return new Song(title, artist, album, duration, filePath);
    }

    public void setSong(Song song) {
        titleField.setText(song.getTitle());
        artistField.setText(song.getArtist());
        albumField.setText(song.getAlbum());
        durationField.setText(String.valueOf(song.getDuration()));
        filePathField.setText(song.getFilePath());
    }
}
