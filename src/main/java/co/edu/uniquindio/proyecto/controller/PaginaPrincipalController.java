package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.repositories.UsuarioRepo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class PaginaPrincipalController {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Label lblSaludo;

    @FXML
    private Button btnPagos;

    @FXML
    private Button btnPrestamos;

    @FXML
    private Button btnReportes;

    @FXML
    private Button btnSolicitudes;

    @FXML
    private Button btnUsuarios;


    //Metodo que se ejecuta automáticamente al cargar la ventana
    @FXML
    public void initialize() {
        lblSaludo.setText("Bienvenido, " + UsuarioLogueadoGlobal.getUsuarioLogueado().getLogin());
    }

    @FXML
    void cerrarSesion(ActionEvent event) throws IOException {
        UsuarioLogueadoGlobal.setUsuarioLogueado(null);
        nuevaVentana("inicioSesion.fxml");
    }

    @FXML
    void generarReportes(ActionEvent event) throws IOException {
        nuevaVentana("generacionReportes.fxml");
    }

    @FXML
    void gestionarPagos(ActionEvent event) throws IOException {
        nuevaVentana("gestionCuotas.fxml");
    }

    @FXML
    void gestionarPrestamos(ActionEvent event) throws IOException {
        nuevaVentana("gestionPrestamos.fxml");
    }

    @FXML
    void gestionarSolicitudes(ActionEvent event) throws IOException {
        nuevaVentana("gestionSolicitudes.fxml");
    }

    @FXML
    void gestionarUsuarios(ActionEvent event) throws IOException {
        nuevaVentana("gestionUsuarios.fxml");
    }

    private void nuevaVentana(String url) throws IOException {
        // Cerrar la ventana actual
        Stage stage = (Stage) btnSolicitudes.getScene().getWindow();
        stage.close();
        // Abre la nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/"+url));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    private void cerrarVentana(){
        // Cerrar la ventana actual
        Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
        stage.close();
    }




}
