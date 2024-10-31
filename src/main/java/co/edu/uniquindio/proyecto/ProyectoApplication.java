package co.edu.uniquindio.proyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "co.edu.uniquindio.proyecto")
public class ProyectoApplication extends Application {
    private static ConfigurableApplicationContext springContext;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(ProyectoApplication.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Código para iniciar tu aplicación JavaFX
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/inicioSesion.fxml"));
        loader.setControllerFactory(springContext::getBean);
        AnchorPane rootLayout = loader.load();
        Scene scene = new Scene(rootLayout);
        stage.setScene(scene);
        stage.setTitle("Inicio de Sesión");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
    }

    public static ConfigurableApplicationContext getSpringContext() {
        return springContext;
    }
}
