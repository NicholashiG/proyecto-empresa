package co.edu.uniquindio.proyecto.controller;

import co.edu.uniquindio.proyecto.ProyectoApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.util.HashMap;

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
reporteMorosos();
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
    private void reporteMorosos (){
        try {
            // Ruta del archivo .jrxml
            String rutaJRXML = "src/main/resources/reports/XMLreportes/reporte_morosos.jrxml";

            // Ruta de salida del archivo PDF
            String salidaPDF = "src/main/java/reportesPDF/reporte_morosos.pdf";

            // Conexión a la base de datos
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:59485;trustServerCertificate=true;databaseName=PruebaSQL", "usuario", "usuario");

            // Cargar y compilar el archivo JRXML
            JasperDesign jasperDesign = JRXmlLoader.load(rutaJRXML);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // Parámetros del reporte
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("FechaCorte", new java.util.Date()); // Agrega la fecha actual como parámetro

            // Llenar el reporte con datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);

            // Exportar el reporte a un archivo PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, salidaPDF);

            System.out.println("Reporte generado con éxito en: " + salidaPDF);

            // Cerrar la conexión
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

