/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spooty.models;

import java.util.ArrayList;

/**
 *
 * @author recinos
 */
public class ListaReproduccion {
    private ArrayList<Cancion> playlist = new ArrayList<>();

    public void agregarCancion(Cancion cancion) {
        playlist.add(cancion);
        imprimirPlaylist();
    }


    public void eliminarCancion(int index) {
        playlist.remove(index);
        imprimirPlaylist();
    }


    public ArrayList<Cancion> getPlaylist() {
        return playlist;
    }
    public void imprimirPlaylist() {
        System.out.println("Lista de Reproducción:");
        for (Cancion cancion : playlist) {
            System.out.println(cancion);
        }
    }
    
}
