package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.ProyectoApplication;
import co.edu.uniquindio.proyecto.model.EstadoEmpleado;
import co.edu.uniquindio.proyecto.model.PagoCuota;
import co.edu.uniquindio.proyecto.model.Prestamo;
import co.edu.uniquindio.proyecto.repositories.EstadoEmpleadoRepo;
import co.edu.uniquindio.proyecto.repositories.PagoCuotaRepo;
import co.edu.uniquindio.proyecto.repositories.PrestamoRepo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

@Controller
public class PagoPrestamosController {

    @FXML
    private DatePicker DatePicker;

    @FXML
    private Button btnRealizarPago;

    @FXML
    private Label labelNumPago;

    @FXML
    private Label labelNumPrestamo;

    @FXML
    private TextField txtValorPago;

    @FXML
    private Button btnVolver;

    @Autowired
    private PagoCuotaRepo pagoCuotaRepo;
    @Autowired
    private PrestamoRepo prestamoRepo;

    @Autowired
    private EstadoEmpleadoRepo estadoEmpleadoRepo;

    @FXML
    void realizarPago() {

        Random random = new Random();

        Optional<Prestamo> p = prestamoRepo.findPrestamoByIdPrestamo(Integer.parseInt(labelNumPrestamo.getText()));

        double valor = Double.parseDouble(this.txtValorPago.getText());

        float montoSolicitado = p.get().getSolicitud().getMontoSolicitado();

        System.out.println(montoSolicitado);
        System.out.println(p.toString());
        if (valor < montoSolicitado) {

            Prestamo prestamoNuevo = new Prestamo();
            prestamoNuevo.setIdPrestamo(p.get().getIdPrestamo());
            prestamoNuevo.setFechaDesembolso(p.get().getFechaDesembolso());
            prestamoNuevo.setSolicitud(p.get().getSolicitud());

            double valorADescontar = (p.get().getSaldo() - valor);

            prestamoNuevo.setSaldo((float) valorADescontar);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pago realizado");
            alert.setHeaderText(null);
            alert.setContentText("El valor ingresado coincide con el valor de la cuota a pagar.");
            alert.showAndWait();

            prestamoRepo.save(prestamoNuevo);

            PagoCuota pagoCuota = new PagoCuota();

            pagoCuota.setIdPagoCuota((int) pagoCuotaRepo.count() + 1);
            if(Integer.parseInt(labelNumPago.getText()) == 0){
                pagoCuota.setNumeroCuota(Integer.parseInt(labelNumPago.getText()));
            }else{
                int numeroCuota = Integer.parseInt(labelNumPago.getText()) + 1;
                pagoCuota.setNumeroCuota(numeroCuota);
            }


            pagoCuota.setFechaPago(DatePicker.getValue().toString());
            pagoCuota.setPrestamo(p.get());
            pagoCuota.setValorPago(Integer.parseInt(txtValorPago.getText()));

            pagoCuotaRepo.save(pagoCuota);



            // Convertir la fecha del DatePicker al formato esperado
            String fechaString = DatePicker.getValue().toString();
            DateTimeFormatter datePickerFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaPago = LocalDate.parse(fechaString, datePickerFormatter);

            // Manejo de la fecha de desembolso
            String fechaDesembolsoString = p.get().getFechaDesembolso();
            DateTimeFormatter desembolsoFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            LocalDate fechaDesembolso = LocalDate.parse(fechaDesembolsoString, desembolsoFormatter);

            // Calcular la fecha límite
            LocalDate fechaLimite = fechaDesembolso.plusMonths(1).withDayOfMonth(10);

            // Actualizar estado del empleado
            EstadoEmpleado estadoEmpleado = estadoEmpleadoRepo.findByIdEmpleado(p.get().getSolicitud().getEmpleado().getIdEmpleado())
                    .orElse(new EstadoEmpleado());

            estadoEmpleado.setIdEmpleado(p.get().getSolicitud().getEmpleado().getIdEmpleado());

            System.out.println("Fecha del pago: " + fechaPago);
            System.out.println("Fecha de desembolso: " + fechaDesembolso);

            if (fechaPago.isAfter(fechaLimite)) {
                estadoEmpleado.setEstado("MOROSO");
            } else {
                estadoEmpleado.setEstado("AL DÍA");
            }
            estadoEmpleado.setFechaActualizacion(Timestamp.valueOf(LocalDateTime.now()));

            estadoEmpleadoRepo.save(estadoEmpleado);
            System.out.println("Estado actualizado: " + estadoEmpleado.getEstado());
        }
    }

    public void init(int numeroPrestamo, int numeroPago){
        labelNumPrestamo.setText(String.valueOf(numeroPrestamo));
        labelNumPago.setText(String.valueOf(numeroPago));
    }


    @FXML
    void volver(ActionEvent event) throws IOException {
        nuevaVentana("paginaPrincipal.fxml");
    }


    private void nuevaVentana(String url) throws IOException {
        // Cerrar la ventana actual
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();

        // Abre la nueva ventana con el contexto de Spring
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/" + url));
        fxmlLoader.setControllerFactory(ProyectoApplication.getSpringContext()::getBean);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void setCuotaSeleccionada(PagoCuota cuotaSeleccionada) {
    }
}
