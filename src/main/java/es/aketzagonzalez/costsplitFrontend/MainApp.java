package es.aketzagonzalez.costsplitFrontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import es.aketzagonzalez.utilidad.Navegador;

/**
 * Clase principal.
 */
public class MainApp extends Application {
    
    /** The stage. */
    private static Stage stage;

    /**
     * Start.
     *
     * @param s the s
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    public void start(@SuppressWarnings("exports") Stage s) throws IOException {
    	Navegador.setStage(s);
        stage=s;
        stage.setResizable(false);
        Navegador.cargarVista("IniciarSesion", null);
        stage.show();
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Gets the stage.
     *
     * @return the stage
     */
    public static Stage getStage() {
		return stage;
	}

}
