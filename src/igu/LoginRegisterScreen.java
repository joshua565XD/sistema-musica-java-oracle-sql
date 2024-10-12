package igu;

import spooty.models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginRegisterScreen extends JFrame {

    // Paneles para registro y login
    private JPanel panelRegister;
    private JPanel panelLogin;

    // Constructor para configurar la ventana
    public LoginRegisterScreen() {
        // Configuración básica de la ventana
        setTitle("Login y Registro");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new CardLayout());

        // Crear los paneles de registro y login
        initRegisterPanel();
        initLoginPanel();

        // Mostrar el panel de registro por defecto
        add(panelRegister, "Registro");
        add(panelLogin, "Login");

        // Mostrar la pantalla de registro por defecto
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "Registro");
    }

    // Método para inicializar el panel de registro
    private void initRegisterPanel() {
        panelRegister = new JPanel();
        panelRegister.setLayout(new GridLayout(6, 1));

        // Campos de texto para el registro
        JTextField txtNombre = new JTextField();
        JTextField txtApellido = new JTextField();
        JTextField txtCorreo = new JTextField();
        JPasswordField txtPassword = new JPasswordField();

        // Botones para registrar y cambiar a login
        JButton btnRegister = new JButton("Registrarse");
        JButton btnToLogin = new JButton("Ir a Login");

        // Agregar componentes al panel de registro
        panelRegister.add(new JLabel("Nombre:"));
        panelRegister.add(txtNombre);
        panelRegister.add(new JLabel("Apellido:"));
        panelRegister.add(txtApellido);
        panelRegister.add(new JLabel("Correo:"));
        panelRegister.add(txtCorreo);
        panelRegister.add(new JLabel("Contraseña:"));
        panelRegister.add(txtPassword);
        panelRegister.add(btnRegister);
        panelRegister.add(btnToLogin);

        // Evento para cambiar al panel de login
        btnToLogin.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
            cardLayout.show(getContentPane(), "Login");
        });

        // Evento para registrar al usuario
        btnRegister.addActionListener(e -> {
            // Crear un nuevo usuario
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String correo = txtCorreo.getText();
            String contrasena = new String(txtPassword.getPassword());

            // Validación simple
            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                return;
            }

            // Crear el usuario y guardarlo en la base de datos
            Usuario nuevoUsuario = new Usuario(nombre, apellido, correo, contrasena);
            nuevoUsuario.guardar(); // Guardar en la base de datos

            JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente. Por favor, inicie sesión.");
            // Limpiar campos después de registrar
            txtNombre.setText("");
            txtApellido.setText("");
            txtCorreo.setText("");
            txtPassword.setText("");

            // Cambiar al panel de login
            CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
            cardLayout.show(getContentPane(), "Login");
        });
    }

    // Método para inicializar el panel de login
    private void initLoginPanel() {
        panelLogin = new JPanel();
        panelLogin.setLayout(new GridLayout(4, 1));

        // Campos de texto para el login
        JTextField txtCorreo = new JTextField();
        JPasswordField txtPassword = new JPasswordField();

        // Botones para login y cambiar a registro
        JButton btnLogin = new JButton("Iniciar Sesión");
        JButton btnToRegister = new JButton("Ir a Registro");

        // Agregar componentes al panel de login
        panelLogin.add(new JLabel("Correo:"));
        panelLogin.add(txtCorreo);
        panelLogin.add(new JLabel("Contraseña:"));
        panelLogin.add(txtPassword);
        panelLogin.add(btnLogin);
        panelLogin.add(btnToRegister);

        // Evento para cambiar al panel de registro
        btnToRegister.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
            cardLayout.show(getContentPane(), "Registro");
        });

        // Evento para login
        btnLogin.addActionListener(e -> {
            // Obtener los datos ingresados
            String correo = txtCorreo.getText();
            String contrasena = new String(txtPassword.getPassword());

            // Validar el login utilizando el método estático de Usuario
            Usuario usuarioEncontrado = Usuario.buscarPorCorreo(correo);

            if (usuarioEncontrado != null && usuarioEncontrado.getContrasena().equals(contrasena)) {
                JOptionPane.showMessageDialog(null, "Login exitoso");
                dispose(); // Cerrar la ventana después de un login exitoso
            } else {
                JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos");
            }
        });
    }

    // Método main para correr la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginRegisterScreen().setVisible(true));
    }
}
