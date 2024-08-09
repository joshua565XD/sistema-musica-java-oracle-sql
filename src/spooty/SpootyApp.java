/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spooty;

/**
 *
 * @author alane
 */
import java.util.Scanner;
import spooty.models.JLayerPlugin;

public class SpootyApp {
    public static void main(String[] args) {
        // Crear una instancia del plugin de JLayer
        JLayerPlugin musicPlayer = new JLayerPlugin();

        // Mensaje de bienvenida
        System.out.println("Bienvenido a Spooty, el reproductor de música!");

        // Solicitar al usuario la ruta del archivo MP3
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la ruta del archivo MP3 que desea reproducir: ");
        String filePath = scanner.nextLine();

        // Reproducir el archivo MP3
        musicPlayer.play(filePath);

        // Mensaje de confirmación
        System.out.println("Reproduciendo: " + filePath);

        // Esperar que el usuario presione Enter para detener la reproducción
        System.out.println("Presione Enter para detener la reproducción...");
        scanner.nextLine();

        // Detener la reproducción
        musicPlayer.stop();
        System.out.println("Reproducción detenida.");

        // Cerrar el scanner
        scanner.close();
    }
}
