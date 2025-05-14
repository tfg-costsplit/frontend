package es.aketzagonzalez.ctrl;

import es.aketzagonzalez.model.ModeloPago;
import es.aketzagonzalez.utilidad.Navegador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;

/**
 * The Class PestaniaPrincipalController.
 */
public class PestaniaPrincipalController {

    /** The lst gastos. */
    @FXML
    private ListView<ModeloPago> lstGastos;

    /**
     * Aniadir compra.
     *
     * @param event the event
     */
    @FXML
    void aniadirCompra(ActionEvent event) {
    	Navegador.cargarVista("AniadirPago", null);
    }

    /**
     * Crear grupo.
     *
     * @param event the event
     */
    @FXML
    void crearGrupo(ActionEvent event) {
    	Navegador.cargarVista("CrearGrupo", null);
    }
    
    /**
     * Unirse A grupo.
     *
     * @param event the event
     */
    @FXML
    void unirseAGrupo(ActionEvent event) {
    	Navegador.cargarVista("UnirseAGrupo", null);
    }

    /**
     * Ver credenciales.
     *
     * @param event the event
     */
    @FXML
    void verCredenciales(ActionEvent event) {
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText("Desarrolladores:");
    	al.setContentText("Aimar Ibarra\nAketza González");
    	al.showAndWait();
    }

}
