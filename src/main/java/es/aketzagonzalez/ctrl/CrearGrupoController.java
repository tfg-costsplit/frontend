package es.aketzagonzalez.ctrl;

import java.io.IOException;

import es.aketzagonzalez.costsplitFrontend.MainApp;
import es.aketzagonzalez.model.ModeloUsuario;
import es.aketzagonzalez.utilidad.Navegador;
import io.github.costsplit.api.invoker.ApiException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    
    private Stage s;
    

    /**
     * Aniadir miembro.
     *
     * @param event the event
     */
    @FXML
    void AniadirMiembro(ActionEvent event) {
    	try {
    		s=new Stage();
            FXMLLoader loader = new FXMLLoader(Navegador.class.getResource("/fxml/UsuariosAAniadir.fxml"), null);
            UsuariosAAniadirController controller = loader.getController();
            Scene nuevaEscena = new Scene(loader.load());
            s.setScene(nuevaEscena); // Cambia la escena
            s.setResizable(false);
            s.initModality(Modality.APPLICATION_MODAL);
            s.show();
            ModeloUsuario usuarioSeleccionado = controller.getUsuario();
            if (usuarioSeleccionado != null && !listaMienbros.getItems().contains(usuarioSeleccionado)) {
                listaMienbros.getItems().add(usuarioSeleccionado);
            }
        } catch (IOException e) {
            e.printStackTrace();
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
     * Accion guardar.
     *
     * @param event the event
     */
    @FXML
    void accionGuardar(ActionEvent event) {
    	//TODO acabar el metodo de guardar
    	String grupo=txtNombreGrupo.getText();
    	String error="";
    	if(grupo.isBlank()) {
    		error+="El nombre del grupo no puede estar vacio\n";
    	}
    	if(error.isEmpty()) {
    		try {
				MainApp.getApi().createGroup(grupo);
			} catch (ApiException e) {
				e.printStackTrace();
			}
    	}
    }

}
