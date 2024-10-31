package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.ProyectoApplication;
import co.edu.uniquindio.proyecto.model.Solicitud;
import co.edu.uniquindio.proyecto.model.Usuario;
import co.edu.uniquindio.proyecto.repositories.EmpleadoNicoRepo;
import co.edu.uniquindio.proyecto.repositories.SolicitudRepo;
import co.edu.uniquindio.proyecto.repositories.UsuarioRepo;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class GestionSolicitudesController {

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
    private ChoiceBox<String> choiceMeses;

    @FXML
    private Label lblInfoSolicitud;

    @FXML
    private ListView<Solicitud> listViewSolicitudes;

    @FXML
    private TextField txtMonto;


    public GestionSolicitudesController(SolicitudRepo solicitudRepo) {
    }

    @Autowired
    private SolicitudRepo solicitudRepo;

    @Autowired
    private EmpleadoNicoRepo empleadoNicoRepo;

    @FXML
    public void initialize () {
        Usuario usuario = UsuarioLogueadoGlobal.getUsuarioLogueado();
        // Inicializar la lista de solicitudes
        if (usuario.getNivelAcceso().getNombre().equals("PRINCIPAL")) {
            listViewSolicitudes.getItems().addAll(solicitudRepo.findAll());
        } else if (usuario.getNivelAcceso().getNombre().equals("TESORERIA")) {
            listViewSolicitudes.getItems().addAll(solicitudRepo.findAll());
        } else {
            listViewSolicitudes.getItems().addAll(solicitudRepo.findByEmpleado_IdEmpleado((empleadoNicoRepo.findByUsuario_IdUsuario(usuario.getIdUsuario()).get(0).getIdEmpleado())));
        }

        // Inicializar la lista de meses
        choiceMeses.getItems().addAll("24", "36", "48", "60", "72");
    }

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
