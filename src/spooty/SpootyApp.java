package spooty;

import DBConnection.DatabaseConnection;
import igu.FormCancion;
import igu.LoginRegisterScreen;

import java.sql.Connection;
import java.sql.SQLException;

public class SpootyApp {
    public static void main(String[] args) {
        // Mostrar la pantalla de Login/Registro
        LoginRegisterScreen loginScreen = new LoginRegisterScreen();
        loginScreen.setVisible(true);
        loginScreen.setLocationRelativeTo(null);

        // Conectar a la base de datos
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Conexión a la base de datos establecida correctamente.");
        } catch (SQLException e) {
                        System.out.println("Error de conexios.");

            e.printStackTrace();
        }

        // Agregar un listener para cuando la ventana de login se cierra
        loginScreen.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // Cuando la ventana de login se cierra, mostrar el FormCancion
                FormCancion panta = new FormCancion();
                panta.setVisible(true);
                panta.setLocationRelativeTo(null);
            }
        });
    }
}
