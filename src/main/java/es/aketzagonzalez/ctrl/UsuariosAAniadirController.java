package es.aketzagonzalez.ctrl;

import es.aketzagonzalez.costsplitFrontend.MainApp;
import es.aketzagonzalez.model.ModeloUsuario;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UsuariosAAniadirController {

    @FXML
    private ListView<ModeloUsuario> lstMiembrosPosibles;
    
    private ModeloUsuario usuario;

    @FXML
    void seleccionarUsuario(MouseEvent event) {
    	if(event.getClickCount()==2) {
    		if(lstMiembrosPosibles.getSelectionModel().getSelectedItem()!=null) {
    			usuario=lstMiembrosPosibles.getSelectionModel().getSelectedItem();
    			((Stage) lstMiembrosPosibles.getScene().getWindow()).close();
    		}
    	}
    }
    
    public ModeloUsuario getUsuario() {
		return usuario;
	}
    
    @FXML
    private void initialize() {
    	lstMiembrosPosibles=new ListView<ModeloUsuario>();
    	
    }

}
