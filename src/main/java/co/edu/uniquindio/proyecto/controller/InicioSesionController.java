package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.model.Usuario;
import co.edu.uniquindio.proyecto.repositories.UsuarioRepo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import co.edu.uniquindio.proyecto.ProyectoApplication;
import java.io.IOException;
import java.util.Optional;

@Controller
public class InicioSesionController {

    private static final Logger logger = LoggerFactory.getLogger(InicioSesionController.class);

    // Se inyecta el repositorio de usuarios
    @Autowired
    private UsuarioRepo usuarioRepo;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private TextField txtUsuario;


    @FXML
    void iniciarSesion(ActionEvent event) throws Exception {
        logger.info("Intento de inicio de sesión con usuario: {}", txtUsuario.getText());
        System.out.println("Usuario: " + txtUsuario.getText());
        System.out.println("Contraseña: " + txtContrasena.getText());

        // Se obtiene el usuario desde la base de datos
        Optional<Usuario> usuario = usuarioRepo.findByLogin(txtUsuario.getText());
        System.out.println("Usuario: " + usuario.isPresent());

        if (!usuario.isPresent()) {
            logger.warn("Usuario no encontrado: {}", txtUsuario.getText());
            txtUsuario.setText("Usuario no encontrado");
            System.out.println("Usuario no encontrado");
            return;
        }else if (!usuario.get().getClave().equals(txtContrasena.getText())) {
            logger.warn("Contraseña incorrecta para usuario: {}", txtUsuario.getText());
            System.out.println("Contraseña incorrecta");
            return;
        } else {
            logger.info("Inicio de sesión exitoso para usuario: {}", txtUsuario.getText());

            // NOTA: En la base de datos tienen que crear un nuevo usuario con login=admin y clave=admin
            // NOTA: Para el login y contraseña, usar admin y admin

            System.out.println("Inicio de sesión exitoso");
            VariablesGlobales.setUsuarioLogueado(usuario.get());
            nuevaVentana("paginaPrincipal.fxml");
        }



    }

    private void nuevaVentana(String url) throws IOException {
        // Cerrar la ventana actual
        Stage stage = (Stage) btnIniciarSesion.getScene().getWindow();
        stage.close();

        // Abre la nueva ventana con el contexto de Spring
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/" + url));
        fxmlLoader.setControllerFactory(ProyectoApplication.getSpringContext()::getBean);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

}
