package es.aketzagonzalez.ctrl;

import es.aketzagonzalez.costsplitFrontend.MainApp;
import es.aketzagonzalez.utilidad.Navegador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
    	//TODO hacer el login
    	Navegador.cargarVista("PestaniaPrincipal", null);
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

}
