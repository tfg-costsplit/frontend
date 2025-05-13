package es.aketzagonzalez.ctrl;

import es.aketzagonzalez.costsplitFrontend.MainApp;
import es.aketzagonzalez.utilidad.Navegador;
import io.github.costsplit.api.DefaultApi;
import io.github.costsplit.api.model.Login;
import io.github.costsplit.api.model.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * The Class IniciarSesionController.
 */
public class IniciarSesionController {

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The btn iniciar sesion. */
    @FXML
    private Button btnIniciarSesion;

    /** The btn registrarse. */
    @FXML
    private Button btnRegistrarse;
    
    /** The txt contrasenia. */
    @FXML
    private TextField txtContrasenia;

    /** The txt email. */
    @FXML
    private TextField txtEmail;
    
    /** The token. */
    private static UserData token;

    /**
     * Accion cancelar.
     *
     * @param event the event
     */
    @FXML
    void accionCancelar(ActionEvent event) {
    	MainApp.getStage().close();
    }

    /**
     * Accion login.
     *
     * @param event the event
     */
    @FXML
    void accionLogin(ActionEvent event) {
    	String email=txtEmail.getText();
    	String contrasenia=txtContrasenia.getText();
    	String error="";
    	Alert al=new Alert(AlertType.ERROR);
    	al.setHeaderText(null);
    	if(email.isBlank()) {
    		error+="El email no puede estar vacio\n";
    	}
    	if(contrasenia.isBlank()) {
    		error+="La contraseña no puede estar vacia\n";
    	}
    	if(error.isEmpty()) {
    		Login login=new Login();
    		login.setEmail(email);
    		login.setPassword(contrasenia);
    		try {
	    		token=MainApp.getApi().loginUser(login);
	    		MainApp.getApiClient().setRequestInterceptor(request -> request.header("Authorization", "Bearer " + token.getToken()));
	    		MainApp.setApi(new DefaultApi(MainApp.getApiClient()));
	    		Navegador.cargarVista("PestaniaPrincipal", null);
	    		MainApp.getStage().setTitle(token.getName());
    		}catch(Exception e) {
    			error="Usuario o contraseña inválido";
    			al.setContentText(error);
    			al.showAndWait();
    		}
    	}else {
    		al.setContentText(error);
    		al.showAndWait();
    	}
    }

    /**
     * Accion registrarse.
     *
     * @param event the event
     */
    @FXML
    void accionRegistrarse(ActionEvent event) {
    	Navegador.cargarVista("Registrarse", null);
    }
    
    /**
     * Gets the token.
     *
     * @return the token
     */
    public static UserData getToken() {
		return token;
	}

}
