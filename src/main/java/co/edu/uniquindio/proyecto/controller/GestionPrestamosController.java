package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.ProyectoApplication;
import co.edu.uniquindio.proyecto.model.Prestamo;
import co.edu.uniquindio.proyecto.model.Solicitud;
import co.edu.uniquindio.proyecto.model.Usuario;
import co.edu.uniquindio.proyecto.repositories.EmpleadoNicoRepo;
import co.edu.uniquindio.proyecto.repositories.PrestamoRepo;
import co.edu.uniquindio.proyecto.repositories.SolicitudRepo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class GestionPrestamosController {

    @FXML
    private Button btnAyuda;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardarCambios;

    @FXML
    private Label lblInfoPrestamo;

    @FXML
    private ListView<Prestamo> listViewPrestamos;

    @Autowired
    private PrestamoRepo prestamoRepo;

    @Autowired
    private SolicitudRepo solicitudRepo;

    @Autowired
    private EmpleadoNicoRepo empleadoNicoRepo;

    @FXML
    private void initialize() {
        Usuario usuario = VariablesGlobales.getUsuarioLogueado();
        ArrayList<Solicitud> solicitudes = new ArrayList<>();
        // Inicializar la lista de solicitudes
        if (usuario.getNivelAcceso().getNombre().equals("PRINCIPAL")) {
            listViewPrestamos.getItems().addAll(prestamoRepo.findAll());
        } else if (usuario.getNivelAcceso().getNombre().equals("TESORERIA")) {
            listViewPrestamos.getItems().addAll(prestamoRepo.findAll());
            btnEliminar.setDisable(true);
            btnGuardarCambios.setDisable(true);
        } else {
            solicitudes.addAll(solicitudRepo.findByEmpleado_IdEmpleado((empleadoNicoRepo.findByUsuario_IdUsuario(usuario.getIdUsuario()).get(0).getIdEmpleado())));
            btnEliminar.setDisable(true);
            btnGuardarCambios.setDisable(true);
            // Dada la lista de solicitudes, se filtran las solicitudes que corresponden al usuario logueado y
            // se muestran los prestamos correspondientes a esas solicitudes
            for (Solicitud solicitud : solicitudes) {
                prestamoRepo.findBySolicitud_IdSolicitud(solicitud.getIdSolicitud()).ifPresent(prestamo -> {
                    listViewPrestamos.getItems().addAll(prestamo);
                });
            }
        }

    }

    @FXML
    void atras(ActionEvent event) throws IOException {
        nuevaVentana("paginaPrincipal.fxml");
    }

    @FXML
    void ayuda(ActionEvent event) throws IOException{
        
    }

    @FXML
    void eliminar(ActionEvent event) {
        // Eliminar el préstamo seleccionado
        Prestamo prestamo = listViewPrestamos.getSelectionModel().getSelectedItem();
        if (prestamo != null) {
            prestamoRepo.delete(prestamo);
            listViewPrestamos.getItems().remove(prestamo);
        }
        // Limpiar la información del préstamo
        lblInfoPrestamo.setText("");

        // Actualizar la lista de préstamos
        Usuario usuario = VariablesGlobales.getUsuarioLogueado();
        ArrayList<Solicitud> solicitudes = new ArrayList<>();
        listViewPrestamos.getItems().clear();
        if (usuario.getNivelAcceso().getNombre().equals("PRINCIPAL")) {
            listViewPrestamos.getItems().addAll(prestamoRepo.findAll());
        } else if (usuario.getNivelAcceso().getNombre().equals("TESORERIA")) {
            listViewPrestamos.getItems().addAll(prestamoRepo.findAll());
        } else {
            solicitudes.addAll(solicitudRepo.findByEmpleado_IdEmpleado((empleadoNicoRepo.findByUsuario_IdUsuario(usuario.getIdUsuario()).get(0).getIdEmpleado())));
            // Dada la lista de solicitudes, se filtran las solicitudes que corresponden al usuario logueado y
            // se muestran los prestamos correspondientes a esas solicitudes
            for (Solicitud solicitud : solicitudes) {
                prestamoRepo.findBySolicitud_IdSolicitud(solicitud.getIdSolicitud()).ifPresent(prestamor -> {
                    listViewPrestamos.getItems().addAll(prestamor);
                });
            }
        }

    }

    @FXML
    void guardarCambios(ActionEvent event) {
        // Guardar los cambios del préstamo seleccionado
        Prestamo prestamo = listViewPrestamos.getSelectionModel().getSelectedItem();
        if (prestamo != null) {
            prestamoRepo.save(prestamo);
        }
    }

    @FXML
    void select(MouseEvent event) {

        // Se muestra la información del préstamo seleccionado
        Prestamo prestamo = listViewPrestamos.getSelectionModel().getSelectedItem();
        if (prestamo != null) {
            lblInfoPrestamo.setText("ID: " + prestamo.getIdPrestamo() + "\n" +
                    "Fecha desembolso: " + prestamo.getFechaDesembolso() + "\n" +
                    "Saldo: " + prestamo.getSaldo());
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