package io.github.costsplit.utilidad;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Class Navegador.
 * @author Aketza
 * @version 1.0
 */
public class Navegador {
    
    /** The stage. */
    private static Stage stage; // El Stage principal de la aplicación

    /**
     * Sets the stage.
     *
     * @param primaryStage the new stage
     * @author Aketza
     */
    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    /**
     * Cargar vista con el fxml que se le dice.
     *
     * @author Aketza
     * @param fxml the fxml
     * @param bundle the bundle
     * @author Aketza
     */
    public static void cargarVista(String fxml, ResourceBundle bundle) {
        try {
            FXMLLoader loader = new FXMLLoader(Navegador.class.getResource("/fxml/"+fxml + ".fxml"), bundle);
            Scene nuevaEscena = new Scene(loader.load());
            stage.setScene(nuevaEscena); // Cambia la escena
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Stage getStage() {
		return stage;
	}
}

