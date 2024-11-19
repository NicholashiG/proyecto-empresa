package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.ProyectoApplication;
import co.edu.uniquindio.proyecto.model.PagoCuota;
import co.edu.uniquindio.proyecto.model.Prestamo;
import co.edu.uniquindio.proyecto.repositories.PagoCuotaRepo;
import co.edu.uniquindio.proyecto.repositories.PrestamoRepo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class GestionCuotasController implements Initializable {

    @Autowired
    private PagoCuotaRepo pagoCuotaRepo;

    @Autowired
    private PrestamoRepo prestamoRepo;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnPagarCuota;

    @FXML
    private Label lblInfoCuota;

    @FXML
    private ListView<Prestamo> listViewCuotas;

    @FXML
    private ListView<PagoCuota> listViewCuotasPagadas;

    private int idPrestamoSeleccionado;

    int numeroPrestamo;
    int numeroPago;

    @FXML
    void atras(ActionEvent event) throws IOException {
        nuevaVentana("paginaPrincipal.fxml");
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


    @FXML
    void pagarCuota(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/pagoPrestamo.fxml"));
            fxmlLoader.setControllerFactory(ProyectoApplication.getSpringContext()::getBean);

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Registrar Pago de Préstamo");
            stage.show();

            // Pasar datos al controlador de la nueva vista
            PagoPrestamosController pagoPrestamoController = fxmlLoader.getController();
           // PagoCuota cuotaSeleccionada = listViewCuotas.getSelectionModel().getSelectedItem();
            pagoPrestamoController.init(numeroPrestamo, numeroPago);
           /* if (cuotaSeleccionada != null) {
                pagoPrestamoController.setCuotaSeleccionada(cuotaSeleccionada);
           } */

            // Cerrar la ventana actual (opcional)
            Stage currentStage = (Stage) btnPagarCuota.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo abrir la ventana de pago.");
            e.printStackTrace();
        }
    }

    @FXML
    void select(MouseEvent event) {

        Prestamo prestamoSeleccionado = listViewCuotas.getSelectionModel().getSelectedItem();

        Optional<PagoCuota> pc = pagoCuotaRepo.findFirstByPrestamo_IdPrestamoOrderByNumeroCuotaDesc(prestamoSeleccionado.getIdPrestamo());

        if(prestamoSeleccionado != null) {

            numeroPrestamo = prestamoSeleccionado.getIdPrestamo();

            if (pc.isPresent()) {
                numeroPago = pc.get().getNumeroCuota();
            } else {
                numeroPago = 1; // Valor predeterminado si no hay PagoCuota
            }





            System.out.println(numeroPrestamo);
            System.out.println(numeroPago);

            lblInfoCuota.setText("Número de cuota: " + numeroPago +
                    "\nMonto solicitado" + prestamoSeleccionado.getSolicitud().getMontoSolicitado());

        }

        /*PagoCuota cuotaSeleccionada = listViewCuotas.getSelectionModel().getSelectedItem();
        if (cuotaSeleccionada != null) {
            lblInfoCuota.setText("Número de cuota: " + cuotaSeleccionada.getNumeroCuota() +
                    "\nValor: " + cuotaSeleccionada.getValorPago() +
                    "\nFecha de pago: " + cuotaSeleccionada.getFechaPago());
        } */
    }

    public void inicializarDatos(int idPrestamo) {
        this.idPrestamoSeleccionado = idPrestamo;
        actualizarListaCuotas();
    }

    private void actualizarListaCuotas() {
        //List<PagoCuota> cuotas = pagoCuotaRepo.findByPrestamo_IdPrestamo(idPrestamoSeleccionado);
        //listViewCuotas.getItems().setAll(cuotas);
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Prestamo> prestamos = prestamoRepo.findAll();
        List<PagoCuota> pagos = pagoCuotaRepo.findAll();

        listViewCuotas.getItems().addAll(prestamos);
        listViewCuotas.refresh();

        listViewCuotasPagadas.getItems().addAll(pagos);
        listViewCuotasPagadas.refresh();

        System.out.println(VariablesGlobales.getUsuarioLogueado()  );




    }
}
