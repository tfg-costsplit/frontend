package es.aketzagonzalez.costsplitFrontend;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import es.aketzagonzalez.utilidad.Navegador;
import io.github.costsplit.api.DefaultApi;
import io.github.costsplit.api.invoker.ApiClient;

/**
 * Clase principal.
 */
public class MainApp extends Application {
    
    /** The stage. */
    private static Stage stage;
    private static DefaultApi api;

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
        try {
	        api = new DefaultApi(new ApiClient()
	        	    .setHost("localhost")
	        	    .setPort(8080));
        }catch(Exception e) {
        	e.printStackTrace();
        }
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
    
    /**
     * Gets the api
     * 
     * @return the api
     */
    public static DefaultApi getApi() {
		return api;
	}

}
