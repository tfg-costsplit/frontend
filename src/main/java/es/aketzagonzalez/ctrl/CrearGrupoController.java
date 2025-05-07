package es.aketzagonzalez.ctrl;

import es.aketzagonzalez.costsplitFrontend.MainApp;
import es.aketzagonzalez.model.ModeloUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * The Class CrearGrupoController.
 */
public class CrearGrupoController {

    /** The btn aniadir miembro. */
    @FXML
    private Button btnAniadirMiembro;

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The btn guardar. */
    @FXML
    private Button btnGuardar;

    /** The lista mienbros. */
    @FXML
    private ListView<ModeloUsuario> listaMienbros;

    /** The txt nombre grupo. */
    @FXML
    private TextField txtNombreGrupo;

    /**
     * Aniadir miembro.
     *
     * @param event the event
     */
    @FXML
    void AniadirMiembro(ActionEvent event) {

    }

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
     * Accion guardar.
     *
     * @param event the event
     */
    @FXML
    void accionGuardar(ActionEvent event) {

    }

}
