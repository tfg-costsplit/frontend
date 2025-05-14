package es.aketzagonzalez.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.aketzagonzalez.costsplitFrontend.MainApp;
import es.aketzagonzalez.model.ModeloPago;
import es.aketzagonzalez.utilidad.Navegador;
import io.github.costsplit.api.invoker.ApiException;
import io.github.costsplit.api.model.PayEntry;
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
    
    @FXML
    public void initialize() {
    	Map<String, PayEntry> mapaPagosTuyos=new HashMap<String, PayEntry>();
    	List<Integer> grupos=IniciarSesionController.getToken().getGroups();
    	for(Integer i:grupos) {
    		try {
				List<Integer> compras=MainApp.getApi().getGroupData(i).getPurchases();
				System.out.println(compras);
				for(Integer j:compras) {
					System.out.println(j);
					Map<String, PayEntry> mapaPagosTodos=MainApp.getApi().getPurchaseData(j).getPayments();
					for (Map.Entry<String, PayEntry> entry : mapaPagosTodos.entrySet()) {
						int idUsuario=Integer.parseInt(entry.getKey());
						if(idUsuario==IniciarSesionController.getToken().getId()) {
							mapaPagosTuyos.put(idUsuario+"", entry.getValue());
						}
					}
				}
			} catch (ApiException e) {
				e.printStackTrace();
			}
    	}
    	
    	for (Map.Entry<String, PayEntry> entry : mapaPagosTuyos.entrySet()) {
    		lstGastos.getItems().add(new ModeloPago(entry.getKey(),(double)entry.getValue().getShouldPay()/100));
    	}
    }

}
