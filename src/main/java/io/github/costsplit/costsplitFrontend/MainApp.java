package io.github.costsplit.costsplitFrontend;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import io.github.costsplit.utilidad.Navegador;
import io.github.costsplit.api.DefaultApi;
import io.github.costsplit.api.invoker.ApiClient;

/**
 * Clase principal.
 */
public class MainApp extends Application {
    
    /** The stage. */
    private static Stage stage;
    
    /** The api. */
    private static DefaultApi api;
    
    /** The api client. */
    private static ApiClient apiClient;

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
        	apiClient=new ApiClient();
        	apiClient.setHost("localhost");
        	apiClient.setPort(8080);
	        api = new DefaultApi(apiClient);
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
     * Gets the api.
     *
     * @return the api
     */
    public static DefaultApi getApi() {
		return api;
	}
    
    /**
     * Sets the api.
     *
     * @param api the new api
     */
    public static void setApi(DefaultApi api) {
		MainApp.api = api;
	}
    
    /**
     * Gets the api client.
     *
     * @return the api client
     */
    public static ApiClient getApiClient() {
		return apiClient;
	}

}
