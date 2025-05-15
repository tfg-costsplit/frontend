package io.github.costsplit.ctrl;

import io.github.costsplit.utilidad.Navegador;
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
    private ListView<?> lstGastos;

    /**
     * Aniadir compra.
     *
     * @param event the event
     */
    @FXML
    void aniadirCompra(ActionEvent event) {

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
     * Ver credenciales.
     *
     * @param event the event
     */
    @FXML
    void verCredenciales(ActionEvent event) {
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText("Desarrolladores:");
    	al.setContentText("Aketza González\nAimar Ibarra");
    	al.showAndWait();
    }

}
