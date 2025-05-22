package io.github.costsplit.ctrl;

import java.util.Optional;

import io.github.costsplit.api.invoker.ApiException;
import io.github.costsplit.costsplitFrontend.MainApp;
import io.github.costsplit.model.ModeloGrupo;
import io.github.costsplit.utilidad.Navegador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

/**
 * The Class BorrarGrupoController.
 */
public class BorrarGrupoController {

    /** The btn borrar. */
    @FXML
    private Button btnBorrar;

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The cmb grupos. */
    @FXML
    private ComboBox<ModeloGrupo> cmbGrupos;

    /**
     * Accion borrar.
     *
     * @param event the event
     */
    @FXML
    void accionBorrar(ActionEvent event) {
    	Alert al=new Alert(AlertType.CONFIRMATION);
    	al.setHeaderText(null);
    	al.setContentText("¿Estás seguro de que deseas elimar el grupo \""+cmbGrupos.getSelectionModel().getSelectedItem()+"\"?\n"
    			+ "Esta opcion no se puede deshacer");
    	Optional<ButtonType> resp=al.showAndWait();
    	if(resp.get()==ButtonType.OK) {
    		try {
				MainApp.getApi().deleteGroup(cmbGrupos.getSelectionModel().getSelectedItem().getId());
				Navegador.cargarVista("PestaniaPrincipal", null);
			} catch (ApiException e) {
				al.setAlertType(AlertType.ERROR);
				al.setContentText("Error al borrar el grupo");
				al.showAndWait();
				e.printStackTrace();
			}
    	}
    }

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
     * Initialize.
     */
    @FXML
    public void initialize() {
    	 for(int i:IniciarSesionController.getToken().getGroups()) {
         	try {
 				cmbGrupos.getItems().add(new ModeloGrupo(MainApp.getApi().getGroupData(i).getName(), i));
 			} catch (ApiException e) {
 				e.printStackTrace();
 			}
         }
         if(cmbGrupos.getItems().size()>0) {
         	cmbGrupos.getSelectionModel().select(0);
         }else {
        	 btnBorrar.setDisable(true);
         }
    }

}
