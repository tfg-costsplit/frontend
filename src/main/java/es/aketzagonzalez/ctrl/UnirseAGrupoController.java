package es.aketzagonzalez.ctrl;

import es.aketzagonzalez.costsplitFrontend.MainApp;
import es.aketzagonzalez.utilidad.Navegador;
import io.github.costsplit.api.invoker.ApiException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * The Class UnirseAGrupoController.
 */
public class UnirseAGrupoController {

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The btn unirse. */
    @FXML
    private Button btnUnirse;

    /** The txt token grupo. */
    @FXML
    private TextField txtTokenGrupo;

    /**
     * Accion cancelar.
     *
     * @param event the event
     */
    @FXML
    void accionCancelar(ActionEvent event) {
    	Navegador.cargarVista("PestaniaPrincipal", null);
    }

    /**
     * Accion unirse.
     *
     * @param event the event
     */
    @FXML
    void accionUnirse(ActionEvent event) {
    	Alert al=new Alert(AlertType.ERROR);
    	al.setHeaderText(null);
    	String error="";
    	if(txtTokenGrupo.getText().isBlank()) {
	    	error+="El token no puede estar vacio";
    	}else {
    		try {
				Integer idGrupo=MainApp.getApi().joinGroup(txtTokenGrupo.getText());
				IniciarSesionController.getToken().getGroups().add(idGrupo);
				Navegador.cargarVista("PestaniaPrincipal", null);
			} catch (ApiException e) {
				error+="No se ha podido aniadir al grupo";
			}
    	}
    	if(error.equals("")) {
    		error="Te has unido al grupo correctamente";
    		al.setAlertType(AlertType.INFORMATION);
    	}
    	al.setContentText(error);
    	al.showAndWait();
    }

}
