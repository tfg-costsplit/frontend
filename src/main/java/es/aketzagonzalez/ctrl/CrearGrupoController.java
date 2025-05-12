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
 * The Class CrearGrupoController.
 */
public class CrearGrupoController {

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The btn guardar. */
    @FXML
    private Button btnGuardar;

    /** The txt nombre grupo. */
    @FXML
    private TextField txtNombreGrupo;

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
     * Accion guardar.
     *
     * @param event the event
     */
    @FXML
    void accionGuardar(ActionEvent event) {
    	String grupo=txtNombreGrupo.getText();
    	String error="";
    	Alert al=new Alert(AlertType.ERROR);
    	al.setHeaderText(null);
    	if(grupo.isBlank()) {
    		error+="El nombre del grupo no puede estar vacio\n";
    	}
    	if(error.isEmpty()) {
    		try {
				MainApp.getApi().createGroup(grupo);
				al.setAlertType(AlertType.INFORMATION);
				error="Grupo creado correctamente";
			} catch (ApiException e) {
				e.printStackTrace();
				error+="Error al crear el grupo";
			} finally {
				al.setContentText(error);
				al.showAndWait();
				if(error.equals("Grupo creado correctamente")) {
					Navegador.cargarVista("PestaniaPrincipal", null);
				}
			}
    	}
    }

}
