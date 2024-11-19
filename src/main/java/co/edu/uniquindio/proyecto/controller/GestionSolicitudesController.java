package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.ProyectoApplication;
import co.edu.uniquindio.proyecto.model.Prestamo;
import co.edu.uniquindio.proyecto.model.Solicitud;
import co.edu.uniquindio.proyecto.model.Usuario;
import co.edu.uniquindio.proyecto.repositories.EmpleadoNicoRepo;
import co.edu.uniquindio.proyecto.repositories.PeriodoSolicitudRepo;
import co.edu.uniquindio.proyecto.repositories.PrestamoRepo;
import co.edu.uniquindio.proyecto.repositories.SolicitudRepo;
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
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private ChoiceBox<String> choiceEstado;

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

    @Autowired
    private PeriodoSolicitudRepo periodoSolicitudRepo;

    @Autowired
    private PrestamoRepo prestamoRepo;

    @FXML
    public void initialize () {
        Usuario usuario = VariablesGlobales.getUsuarioLogueado();
        // Inicializar la lista de solicitudes
        if (usuario.getNivelAcceso().getNombre().equals("PRINCIPAL")) {
            listViewSolicitudes.getItems().addAll(solicitudRepo.findAll());
            btnNuevo.setDisable(true);
        } else if (usuario.getNivelAcceso().getNombre().equals("TESORERIA")) {
            listViewSolicitudes.getItems().addAll(solicitudRepo.findAll());
            btnNuevo.setDisable(true);
            btnEditar.setDisable(true);
            btnEliminar.setDisable(true);
            btnGuardarCambios.setDisable(true);
        } else {
            choiceEstado.setDisable(true);
            //choiceEstado.setValue("PENDIENTE");
            listViewSolicitudes.getItems().addAll(solicitudRepo.findByEmpleado_IdEmpleado((empleadoNicoRepo.findByUsuario_IdUsuario(usuario.getIdUsuario()).get(0).getIdEmpleado())));
        }

        // Inicializar la lista de meses
        choiceMeses.getItems().addAll("24", "36", "48", "60", "72");

        // Inicializa la lista de estados
        choiceEstado.getItems().addAll("PENDIENTE", "APROBADO", "RECHAZADO");
    }

    @FXML
    void atras(ActionEvent event) throws IOException {
        nuevaVentana("paginaPrincipal.fxml");
    }

    @FXML
    void editar(ActionEvent event) {
        // Editar la solicitud seleccionada solo si no está en estado aprobado
        Solicitud solicitud = listViewSolicitudes.getSelectionModel().getSelectedItem();
        if (solicitud != null && !solicitud.getEstado().equals("APROBADO")) {
            choiceEstado.setValue(solicitud.getEstado());
            txtMonto.setText((solicitud.getMontoSolicitado())+"");
            choiceEstado.setValue(solicitud.getEstado());
            choiceMeses.setValue(solicitud.getPeriodoSolicitud().getMeses()+"");
            solicitud.setPeriodoSolicitud(solicitud.getPeriodoSolicitud());
        }
        else {
            lblInfoSolicitud.setText("No se puede editar una solicitud aprobada");
        }
    }

    @FXML
    void eliminar(ActionEvent event) {
        // Eliminar la solicitud seleccionada solo si no está en estado aprobado
        Solicitud solicitud = listViewSolicitudes.getSelectionModel().getSelectedItem();
        if (solicitud != null && !solicitud.getEstado().equals("APROBADO")) {
            solicitudRepo.delete(solicitud);
            listViewSolicitudes.getItems().remove(solicitud);
        }
        else {
            lblInfoSolicitud.setText("No se puede eliminar una solicitud aprobada");
        }
        // actualizar la lista de solicitudes
        listViewSolicitudes.getItems().clear();
        if (VariablesGlobales.getUsuarioLogueado().getNivelAcceso().getNombre().equals("PRINCIPAL")) {
            listViewSolicitudes.getItems().addAll(solicitudRepo.findAll());
        } else {
            listViewSolicitudes.getItems().addAll(solicitudRepo.findByEmpleado_IdEmpleado((empleadoNicoRepo.findByUsuario_IdUsuario(VariablesGlobales.getUsuarioLogueado().getIdUsuario()).get(0).getIdEmpleado())));
        }
    }

    @FXML
    void guardarCambios(ActionEvent event) {
        // Guardar los cambios de la solicitud
        Solicitud solicitud = listViewSolicitudes.getSelectionModel().getSelectedItem();
        // Todo: falta agregar validación para que el monto sea mayor a 0 y que el empleado pueda hacer una
        // Todo: solicitud de acuerdo a su tipo de empleado
        solicitud.setMontoSolicitado(Float.parseFloat(txtMonto.getText()));
        solicitud.setEstado(choiceEstado.getValue());
        solicitud.setPeriodoSolicitud(periodoSolicitudRepo.findByMeses(Integer.parseInt(choiceMeses.getValue())).get());
        solicitudRepo.save(solicitud);
        if (solicitud.getEstado().equals("APROBADO")) {
            aprobarSolicitud(solicitud);
        }
        listViewSolicitudes.getItems().clear();
        if (VariablesGlobales.getUsuarioLogueado().getNivelAcceso().getNombre().equals("PRINCIPAL")) {
            listViewSolicitudes.getItems().addAll(solicitudRepo.findAll());
        } else {
            listViewSolicitudes.getItems().addAll(solicitudRepo.findByEmpleado_IdEmpleado((empleadoNicoRepo.findByUsuario_IdUsuario(VariablesGlobales.getUsuarioLogueado().getIdUsuario()).get(0).getIdEmpleado())));
        }
    }

    @FXML
    void limpiar(ActionEvent event) {
        // Limpiar los campos de texto
        txtMonto.clear();
        choiceMeses.setValue(null);
        choiceEstado.setValue(null);
    }

    @FXML
    void nuevo(ActionEvent event) {
        // Se crea una nueva solicitud
        Solicitud solicitud = new Solicitud();
        // Se asigna un id a la solicitud
        solicitud.setIdSolicitud((int) solicitudRepo.count() + 1);
        // Se asigna la fecha del momento en el que se hace la solicitud en formato DD/MM/YY
        solicitud.setFecha(new SimpleDateFormat("dd/MM/yy").format(new Date()));
        // Se asigna el empleado que hace la solicitud
        solicitud.setEmpleado(empleadoNicoRepo.findByUsuario_IdUsuario(VariablesGlobales.getUsuarioLogueado().getIdUsuario()).get(0));
        // Se asigna el monto solicitado
        // Todo: falta agregar validación para que el monto sea mayor a 0 y que el empleado pueda hacer una
        // Todo: solicitud de acuerdo a su tipo de empleado
        solicitud.setMontoSolicitado(Float.parseFloat(txtMonto.getText()));
        // Se asigna el estado de la solicitud en pendiente
        solicitud.setEstado("PENDIENTE");
        choiceEstado.setValue("PENDIENTE");
        // Se asigna el periodo de la solicitud
        // Por el momento solo sirve con 24 meses
        solicitud.setPeriodoSolicitud(periodoSolicitudRepo.findByMeses(Integer.parseInt(choiceMeses.getValue())).get());
        // Se guarda la solicitud
        solicitudRepo.save(solicitud);

        // Se actualiza la lista de solicitudes
        listViewSolicitudes.getItems().clear();
        if (VariablesGlobales.getUsuarioLogueado().getNivelAcceso().getNombre().equals("PRINCIPAL")) {
            listViewSolicitudes.getItems().addAll(solicitudRepo.findAll());
        } else {
            listViewSolicitudes.getItems().addAll(solicitudRepo.findByEmpleado_IdEmpleado((empleadoNicoRepo.findByUsuario_IdUsuario(VariablesGlobales.getUsuarioLogueado().getIdUsuario()).get(0).getIdEmpleado())));
        }
    }

    @FXML
    void select(MouseEvent event) {
        // Se obtiene la solicitud seleccionada
        Solicitud solicitud = listViewSolicitudes.getSelectionModel().getSelectedItem();
        if (solicitud != null) {
            // Se muestra la información de la solicitud
            lblInfoSolicitud.setText("Solicitud: " + solicitud.getIdSolicitud() + "\n" +
                    "Fecha: " + solicitud.getFecha() + "\n" +
                    "Empleado: " + solicitud.getEmpleado().getNombre() + " " + solicitud.getEmpleado().getApellido() + "\n" +
                    "Monto solicitado: " + solicitud.getMontoSolicitado() + "\n" +
                    "Estado: " + solicitud.getEstado() + "\n" +
                    "Periodo: " + solicitud.getPeriodoSolicitud().getMeses() + " meses");
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

    private void aprobarSolicitud(Solicitud solicitud) {
        // Si se aprueba la solicitud, se debe crear un nuevo registro en la tabla de prestamos
        Prestamo prestamo = new Prestamo();
        prestamo.setIdPrestamo((int) prestamoRepo.count() + 1);
        prestamo.setSolicitud(solicitud);
        prestamo.setFechaDesembolso(new SimpleDateFormat("dd/MM/yy").format(new Date()));
        prestamo.setSaldo(solicitud.getMontoSolicitado());
        prestamoRepo.save(prestamo);
    }

}