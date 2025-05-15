package es.aketzagonzalez.ctrl;

import java.util.List;
import java.util.Map;

import es.aketzagonzalez.costsplitFrontend.MainApp;
import es.aketzagonzalez.model.ModeloPago;
import es.aketzagonzalez.utilidad.Navegador;
import io.github.costsplit.api.invoker.ApiException;
import io.github.costsplit.api.model.PayEntry;
import io.github.costsplit.api.model.PurchaseData;
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
     * Pagar.
     *
     * @param event the event
     */
    @FXML
    void pagar(ActionEvent event) {
    	Navegador.cargarVista("Pagar", null);
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
    
    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
    	List<Integer> grupos=IniciarSesionController.getToken().getGroups();
    	for(Integer i:grupos) {
    		try {
    			List<PurchaseData> compras=MainApp.getApi().getAllGroupData(i).getPurchases();
				for(PurchaseData datosCompra:compras) {
					for (Map.Entry<String, PayEntry> entry : datosCompra.getPayments().entrySet()) {
						if(Integer.parseInt(entry.getKey())==IniciarSesionController.getToken().getId()&&
								datosCompra.getPayments().get(entry.getKey()).getShouldPay()-
								datosCompra.getPayments().get(entry.getKey()).getPaid()!=0) {
							lstGastos.getItems().add(new ModeloPago(entry.getKey(),datosCompra.getDescription(),
									((double)(entry.getValue().getShouldPay()-entry.getValue().getPaid()))/100));
						}
					}
				}
			} catch (ApiException e) {
				e.printStackTrace();
			}
    	}
    }

}
