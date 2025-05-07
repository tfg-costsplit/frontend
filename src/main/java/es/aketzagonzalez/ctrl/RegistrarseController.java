package es.aketzagonzalez.ctrl;

import es.aketzagonzalez.costsplitFrontend.MainApp;
import es.aketzagonzalez.utilidad.Navegador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    	//TODO hacer el registro
    	
    	
    	Navegador.cargarVista("PestaniaPrincipal", null);
    }

}
