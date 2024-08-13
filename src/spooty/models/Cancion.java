/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spooty.models;

/**
 *
 * @author recinos
 */
public class Cancion {
    private String titulo;
    private String artista;
    private String album;
    private String duracion;
    private String rutaArchivo;

    // Constructor
    public Cancion(String titulo, String artista, String album, String duracion, String rutaArchivo) {
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.duracion = duracion;
        this.rutaArchivo = rutaArchivo;
    }
    @Override
    public String toString() {
        return String.format("Título: %s, Artista: %s, Álbum: %s, Duración: %s, Ruta: %s",
                             titulo, artista, album, duracion, rutaArchivo);
    }
    

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }
    
}
