package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.ProyectoApplication;
import co.edu.uniquindio.proyecto.repositories.UsuarioRepo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class PaginaPrincipalController {

    private static final Logger logger = LoggerFactory.getLogger(PaginaPrincipalController.class);

    @Autowired
    private UsuarioRepo usuarioRepo;

    @FXML
    private Button btnAyuda;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnEmpleados;

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
        lblSaludo.setText("Bienvenido, " + VariablesGlobales.getUsuarioLogueado().getLogin());

        if(VariablesGlobales.getUsuarioLogueado().getNivelAcceso().getNombre().equals("PRINCIPAL")){
            btnUsuarios.setVisible(true);
            btnReportes.setVisible(true);
            btnEmpleados.setVisible(true);
        }
        else if(VariablesGlobales.getUsuarioLogueado().getNivelAcceso().getNombre().equals("TESORERIA")){
            btnUsuarios.setVisible(false);
            btnReportes.setVisible(true);
            btnEmpleados.setVisible(false);
        }
        else {
            btnUsuarios.setVisible(false);
            btnReportes.setVisible(false);
            btnEmpleados.setVisible(false);
        }

    }

    @FXML
    void cerrarSesion(ActionEvent event) throws IOException {
        logger.info("Cerrando sesión para usuario: {}", VariablesGlobales.getUsuarioLogueado().getLogin());
        VariablesGlobales.setUsuarioLogueado(null);
        nuevaVentana("inicioSesion.fxml");
    }

    @FXML
    void generarReportes(ActionEvent event) throws IOException {
        nuevaVentana("generacionReportes.fxml");
    }

    @FXML
    void gestionarEmpleados(ActionEvent event) throws IOException {
        nuevaVentana("gestionEmpleados.fxml");
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

    @FXML
    void abrirAyuda(ActionEvent event) throws IOException {
        nuevaVentana("menuAyuda.fxml");
    }

    private void nuevaVentana(String url) throws IOException {
        // Cerrar la ventana actual
        Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
        stage.close();

        // Abre la nueva ventana con el contexto de Spring
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/" + url));
        fxmlLoader.setControllerFactory(ProyectoApplication.getSpringContext()::getBean);
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
