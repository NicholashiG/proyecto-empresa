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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Optional;

@Controller
public class InicioSesionController {

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
        System.out.println("Usuario: " + txtUsuario.getText());
        System.out.println("Contraseña: " + txtContrasena.getText());

        // Se obtiene el usuario desde la base de datos
        Optional<Usuario> usuario = usuarioRepo.findByLogin(txtUsuario.getText());
        System.out.println("Usuario: " + usuario.isPresent());

        if (!usuario.isPresent()) {
            txtUsuario.setText("Usuario no encontrado");
            System.out.println("Usuario no encontrado");
            return;
        }else if (!usuario.get().getClave().equals(txtContrasena.getText())) {
            System.out.println("Contraseña incorrecta");
            return;
        } else {

            // NOTA: En la base de datos tienen que crear un nuevo usuario con login=admin y clave=admin
            // NOTA: Para el login y contraseña, usar admin y admin

            System.out.println("Inicio de sesión exitoso");
            UsuarioLogueadoGlobal.setUsuarioLogueado(usuario.get());
            nuevaVentana("paginaPrincipal.fxml");
        }



    }

    private void nuevaVentana(String url) throws IOException {
        // Cerrar la ventana actual
        Stage stage = (Stage) btnIniciarSesion.getScene().getWindow();
        stage.close();
        // Abre la nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/"+url));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

}
