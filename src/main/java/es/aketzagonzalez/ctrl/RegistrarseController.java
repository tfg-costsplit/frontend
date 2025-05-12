package es.aketzagonzalez.ctrl;

import es.aketzagonzalez.costsplitFrontend.MainApp;
import es.aketzagonzalez.utilidad.Navegador;
import io.github.costsplit.api.invoker.ApiException;
import io.github.costsplit.api.model.CreateUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * The Class RegistrarseController.
 */
public class RegistrarseController {

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The btn registrarse. */
    @FXML
    private Button btnRegistrarse;

    /** The txt contrasenia. */
    @FXML
    private TextField txtContrasenia;

    /** The txt email. */
    @FXML
    private TextField txtEmail;

    /** The txt nombre usuario. */
    @FXML
    private TextField txtNombreUsuario;

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
     * Accion registrarse.
     *
     * @param event the event
     */
    @FXML
    void accionRegistrarse(ActionEvent event) {
    	Alert al=new Alert(AlertType.ERROR);
    	al.setHeaderText(null);
    	String nombre=txtNombreUsuario.getText();
    	String email=txtEmail.getText();
    	String contrasenia=txtContrasenia.getText();
    	String error="";
    	if(nombre.isBlank()) {
    		error+="El nombre no puede estar vacio\n";
    	}
    	if(email.isBlank()) {
    		error+="El email no puede estar vacio\n";
    	}
    	if(contrasenia.isBlank()) {
    		error+="La contraseña no puede estar vacia\n";
    	}else {
    		error = validarContrasenia(contrasenia, error);
    	}
    	if(error.isEmpty()) {
    		CreateUser cu=new CreateUser();
    		cu.setName(nombre);
    		cu.setEmail(email);
    		cu.setPassword(contrasenia);
    		try {
				MainApp.getApi().createUser(cu);
				al.setContentText("Usuario creado correctamente");
				al.setAlertType(AlertType.INFORMATION);
				al.showAndWait();
				Navegador.cargarVista("IniciarSesion", null);
			} catch (ApiException e) {
				al.setContentText("Error al registrar al usuario");
				al.showAndWait();
			}
    	}else {
    		al.setContentText(error);
    		al.showAndWait();
    	}
    }

	/**
	 * Validar contrasenia.
	 *
	 * @param contrasenia the contrasenia
	 * @param error the error
	 * @return the string
	 */
	private String validarContrasenia(String contrasenia, String error) {
		contrasenia=contrasenia.trim();
		if(contrasenia.length()<8) {
			error+="La contraseña debe tener una longitud mínima de 8 caracteres\n";
		}
		boolean digito=false;
		for(int i=0;i<contrasenia.length();i++) {
			if(!digito) {
				try {
					Integer.parseInt(contrasenia.charAt(i)+"");
					digito=true;
				}catch(NumberFormatException e) {
					
				}
			}
		}
		if(!digito) {
			error+="La contraseña debe incluir al menos 1 digito\n";
		}
		boolean mayuscula=false;
		for(int i=0;i<contrasenia.length();i++) {
			if(!mayuscula) {
				if(contrasenia.charAt(i)>='A'&&contrasenia.charAt(i)<='Z') {
					mayuscula=true;
				}
			}
		}
		if(!mayuscula) {
			error+="La contraseña debe incluir al menos 1 mayúscula\n";
		}
		boolean minuscula=false;
		for(int i=0;i<contrasenia.length();i++) {
			if(!minuscula) {
				if(contrasenia.charAt(i)>='a'&&contrasenia.charAt(i)<='z') {
					minuscula=true;
				}
			}
		}
		if(!minuscula) {
			error+="La contraseña debe incluir al menos 1 minúscula\n";
		}
		boolean tieneEspecial = false;
		for (int i = 0; i < contrasenia.length(); i++) {
		    char c = contrasenia.charAt(i);
		    if (!Character.isLetterOrDigit(c)) {
		        tieneEspecial = true;
		        break;
		    }
		}
		if (!tieneEspecial) {
		    error += "La contraseña debe incluir al menos 1 carácter especial\n";
		}
		return error;
	}
    
}
