package co.edu.uniquindio.proyecto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class GeneracionReportesController {

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnDineroMunicipio;

    @FXML
    private Button btnDineroSucursal;

    @FXML
    private Button btnReporteMorosos;

    @FXML
    void atras(ActionEvent event) throws IOException {
        nuevaVentana("paginaPrincipal.fxml");
    }

    @FXML
    void generarReporteDineroPorMunicipio(ActionEvent event) {

    }

    @FXML
    void generarReporteDineroPorSucursal(ActionEvent event) {

    }

    @FXML
    void generarReporteMorosos(ActionEvent event) {

    }

    private void nuevaVentana(String url) throws IOException {
        // Cerrar la ventana actual
        Stage stage = (Stage) btnAtras.getScene().getWindow();
        stage.close();
        // Abre la nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/"+url));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
