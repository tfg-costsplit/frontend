package io.github.costsplit.ctrl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.github.costsplit.api.invoker.ApiException;
import io.github.costsplit.api.model.PayEntry;
import io.github.costsplit.api.model.PurchaseData;
import io.github.costsplit.costsplitFrontend.MainApp;
import io.github.costsplit.model.ModeloCompra;
import io.github.costsplit.utilidad.Navegador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;

public class BorrarPagoController {

    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnCancelar;

    @FXML
    private ComboBox<ModeloCompra> cmbPagos;

    @FXML
    void accionBorrar(ActionEvent event) {
    	Alert al=new Alert(AlertType.CONFIRMATION);
    	al.setHeaderText(null);
    	al.setContentText("¿Estás seguro de que deseas elimar el pago \""+cmbPagos.getSelectionModel().getSelectedItem()+"\"?\n"
    			+ "Esta opcion no se puede deshacer");
    	Optional<ButtonType> resp=al.showAndWait();
    	if(resp.get()==ButtonType.OK) {
    		//TODO cambiar el print por la funcion de borrar
    		MainApp.getApi().deletePurchase(cmbPagos.getSelectionModel().getSelectedItem().getId());
    		System.out.println("BORRADO");
    		Navegador.cargarVista("PestaniaPrincipal", null);
    	}
    }

    @FXML
    void accionCancelar(ActionEvent event) {
    	Navegador.cargarVista("PestaniaPrincipal", null);
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
						if(Integer.parseInt(entry.getKey())==IniciarSesionController.getToken().getId()) {
							cmbPagos.getItems().add(new ModeloCompra(datosCompra.getId(), datosCompra.getDescription()));
						}
					}
				}
    		}catch (ApiException e) {
				e.printStackTrace();
			}
    	}
         if(cmbPagos.getItems().size()>0) {
        	 cmbPagos.getSelectionModel().select(0);
         }else {
        	 btnBorrar.setDisable(true);
         }
    }

}
