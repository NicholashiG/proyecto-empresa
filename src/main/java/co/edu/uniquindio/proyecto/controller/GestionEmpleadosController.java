package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.ProyectoApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class GestionEmpleadosController {

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardarCambios;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnNuevo;

    @FXML
    private ChoiceBox<?> choiceSucursal;

    @FXML
    private ChoiceBox<?> choiceTipoEmpleado;

    @FXML
    private ChoiceBox<?> choiceUsuario;

    @FXML
    private Label lblInfoEmpleado;

    @FXML
    private ListView<?> listViewEmpleado;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtNombre;

    @FXML
    void atras(ActionEvent event) throws IOException {
        nuevaVentana("paginaPrincipal.fxml");
    }

    @FXML
    void editar(ActionEvent event) {

    }

    @FXML
    void eliminar(ActionEvent event) {

    }

    @FXML
    void guardarCambios(ActionEvent event) {

    }

    @FXML
    void limpiar(ActionEvent event) {

    }

    @FXML
    void nuevo(ActionEvent event) {

    }

    @FXML
    void select(MouseEvent event) {

    }

    private void nuevaVentana(String url) throws IOException {
        // Cerrar la ventana actual
        Stage stage = (Stage) btnAtras.getScene().getWindow();
        stage.close();

        // Abre la nueva ventana con el contexto de Spring
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/" + url));
        fxmlLoader.setControllerFactory(ProyectoApplication.getSpringContext()::getBean);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

}