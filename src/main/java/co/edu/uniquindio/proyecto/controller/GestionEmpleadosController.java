package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.ProyectoApplication;
import co.edu.uniquindio.proyecto.model.EmpleadoNico;
import co.edu.uniquindio.proyecto.model.Sucursal;
import co.edu.uniquindio.proyecto.model.TipoEmpleado;
import co.edu.uniquindio.proyecto.model.Usuario;
import co.edu.uniquindio.proyecto.repositories.EmpleadoNicoRepo;
import co.edu.uniquindio.proyecto.repositories.SucursalRepo;
import co.edu.uniquindio.proyecto.repositories.TipoEmpleadoRepo;
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
    private ChoiceBox<Sucursal> choiceSucursal;

    @FXML
    private ChoiceBox<TipoEmpleado> choiceTipoEmpleado;

    @FXML
    private ChoiceBox<Usuario> choiceUsuario;

    @FXML
    private Label lblInfoEmpleado;

    @FXML
    private ListView<EmpleadoNico> listViewEmpleado;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtNombre;

    @Autowired
    private EmpleadoNicoRepo empleadoNicoRepo;

    @Autowired
    private SucursalRepo sucursalRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private TipoEmpleadoRepo tipoEmpleadoRepo;

    @FXML
    public void initialize() {
        // Cargar los empleados en la lista usando la repo de empleados
        listViewEmpleado.getItems().addAll(empleadoNicoRepo.findAll());
        // Cargar las sucursales en el choice box
        choiceSucursal.getItems().addAll(sucursalRepo.findAll());
        // Cargar los usuarios en el choice box
        choiceUsuario.getItems().addAll(usuarioRepo.findAll());
        // Cargar los tipos de empleados en el choice box
        choiceTipoEmpleado.getItems().addAll(tipoEmpleadoRepo.findAll());
    }

    @FXML
    void atras(ActionEvent event) throws IOException {
        nuevaVentana("paginaPrincipal.fxml");
    }

    @FXML
    void editar(ActionEvent event) {
        // Obtener el empleado seleccionado
        EmpleadoNico empleado = listViewEmpleado.getSelectionModel().getSelectedItem();
        if (empleado != null) {
            // Mostrar la información del empleado en los campos de texto
            txtNombre.setText(empleado.getNombre());
            txtApellido.setText(empleado.getApellido());
            choiceSucursal.setValue(empleado.getSucursal());
            choiceTipoEmpleado.setValue(empleado.getTipoEmpleado());
            choiceUsuario.setValue(empleado.getUsuario());
        }
    }

    @FXML
    void eliminar(ActionEvent event) {
        // Obtener el empleado seleccionado
        EmpleadoNico empleado = listViewEmpleado.getSelectionModel().getSelectedItem();
        if (empleado != null) {
            // Eliminar el empleado
            empleadoNicoRepo.delete(empleado);
            // Actualizar la lista de empleados
            listViewEmpleado.getItems().clear();
            listViewEmpleado.getItems().addAll(empleadoNicoRepo.findAll());
        }
    }

    @FXML
    void guardarCambios(ActionEvent event) {
        // Obtener el empleado seleccionado
        EmpleadoNico empleado = listViewEmpleado.getSelectionModel().getSelectedItem();
        if (empleado != null) {
            // Actualizar los datos del empleado
            empleado.setNombre(txtNombre.getText());
            empleado.setApellido(txtApellido.getText());
            empleado.setSucursal(choiceSucursal.getValue());
            empleado.setTipoEmpleado(choiceTipoEmpleado.getValue());
            empleado.setUsuario(choiceUsuario.getValue());
            // Guardar los cambios
            empleadoNicoRepo.save(empleado);
            // Actualizar la lista de empleados
            listViewEmpleado.getItems().clear();
            listViewEmpleado.getItems().addAll(empleadoNicoRepo.findAll());
        }
    }

    @FXML
    void limpiar(ActionEvent event) {
        // Limpiar los campos de texto
        txtNombre.clear();
        txtApellido.clear();
        choiceSucursal.setValue(null);
        choiceTipoEmpleado.setValue(null);
        choiceUsuario.setValue(null);
    }

    @FXML
    void nuevo(ActionEvent event) {
        // Crear un nuevo empleado con los datos de los campos de texto
        EmpleadoNico empleado = new EmpleadoNico();
        empleado.setNombre(txtNombre.getText());
        empleado.setApellido(txtApellido.getText());
        empleado.setSucursal(choiceSucursal.getValue());
        empleado.setTipoEmpleado(choiceTipoEmpleado.getValue());
        empleado.setUsuario(choiceUsuario.getValue());
        // Guardar el nuevo empleado
        empleadoNicoRepo.save(empleado);
        // Actualizar la lista de empleados
        listViewEmpleado.getItems().clear();
        listViewEmpleado.getItems().addAll(empleadoNicoRepo.findAll());
    }

    @FXML
    void select(MouseEvent event) {
        // Obtener el empleado seleccionado
        EmpleadoNico empleado = listViewEmpleado.getSelectionModel().getSelectedItem();
        if (empleado != null) {
            // Mostrar la información del empleado en el label
            lblInfoEmpleado.setText(empleado.toString());
        }
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
