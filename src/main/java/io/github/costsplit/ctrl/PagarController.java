package io.github.costsplit.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

import io.github.costsplit.costsplitFrontend.MainApp;
import io.github.costsplit.model.ModeloPago;
import io.github.costsplit.utilidad.Navegador;
import io.github.costsplit.api.invoker.ApiException;
import io.github.costsplit.api.model.PayEntry;
import io.github.costsplit.api.model.PurchaseData;
import io.github.costsplit.api.model.UpdatePurchase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 * The Class PagarController.
 */
public class PagarController {

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The btn pagar. */
    @FXML
    private Button btnPagar;

    /** The cmb pagos A realizar. */
    @FXML
    private ComboBox<ModeloPago> cmbPagosARealizar;

    /** The txt cantidad A pagar. */
    @FXML
    private TextField txtCantidadAPagar;
    
    /** The lbl cantidad. */
    @FXML
    private Label lblCantidad;
    
    /** The cantidad total. */
    private double cantidadTotal = -1.0;

    /** The cantidad ahora. */
    private double cantidadAhora = 0.0;
    
    /** The filter. */
    private UnaryOperator<TextFormatter.Change> filter;

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
     * Accion pagar.
     *
     * @param event the event
     */
    @FXML
    void accionPagar(ActionEvent event) {
    	Alert al=new Alert(AlertType.ERROR);
		al.setHeaderText(null);
    	if(cantidadAhora<=cantidadTotal&&cantidadAhora!=0) {
    		for(int i:IniciarSesionController.getToken().getGroups()) {
	    		List<PurchaseData> listaPagos;
	    		UpdatePurchase actualizacion=new UpdatePurchase();
	    		actualizacion.setPayments(new HashMap<String, PayEntry>());
				try {
					listaPagos = MainApp.getApi().getAllGroupData(i).getPurchases();
					for(PurchaseData j:listaPagos) {
		        		for (Map.Entry<String, PayEntry> entry : j.getPayments().entrySet()) {
		        			actualizacion.getPayments().put(entry.getKey(), entry.getValue());
		        			if(entry.getKey().equals(IniciarSesionController.getToken().getId()+"")&&
		        					entry.getValue().getPaid()<entry.getValue().getShouldPay()&&
		        					cmbPagosARealizar.getSelectionModel().getSelectedItem().equals(
		        							new ModeloPago(IniciarSesionController.getToken().getId()+"",
			        						j.getDescription(),((double)(entry.getValue().getShouldPay()-
			        								entry.getValue().getPaid()))/100))) {
		        				cantidadAhora*=100;
		        				PayEntry pago=new PayEntry();
		        				pago.setShouldPay((long)(cantidadTotal*100));
		        				pago.setPaid((long)(cantidadAhora));
		        				actualizacion.getPayments().put(entry.getKey(), pago);
		        				MainApp.getApi().updatePurchase(j.getId(), actualizacion);
		        				al.setAlertType(AlertType.INFORMATION);
		        				al.setContentText("Pago realizado correctamente");
		        				al.showAndWait();
		        				Navegador.cargarVista("PestaniaPrincipal", null);
		        			}
		        		}
					}
				}catch (ApiException e) {
					e.printStackTrace();
				}
    		}
    	}else {
    		if(cantidadAhora!=0) {
    			al.setContentText("No puedes pagar más de lo que debes");
    		}else {
    			al.setContentText("No puedes pagar 0");
    		}
    		al.showAndWait();
    	}
    }

    /**
     * Seleccionar pago.
     *
     * @param event the event
     */
    @FXML
    void seleccionarPago(ActionEvent event) {
    	actualizarCantidad();
    }
    
    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
    	filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*([.,]\\d{0,2})?")) {
                return change;
            }
            return null;
        };
        txtCantidadAPagar.setTextFormatter(new TextFormatter<>(filter));
        
        txtCantidadAPagar.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
            	cantidadAhora = Double.parseDouble(newVal.replace(",", "."));
            } catch (NumberFormatException e) {
            	cantidadAhora = 0.0;
            }
            actualizarCantidad();
        });
        
        for(int i:IniciarSesionController.getToken().getGroups()) {
        	List<PurchaseData> listaPagos;
			try {
				listaPagos = MainApp.getApi().getAllGroupData(i).getPurchases();
				for(PurchaseData j:listaPagos) {
	        		for (Map.Entry<String, PayEntry> entry : j.getPayments().entrySet()) {
	        			if(entry.getKey().equals(IniciarSesionController.getToken().getId()+"")&&
	        					entry.getValue().getPaid()<entry.getValue().getShouldPay()) {
	        				cmbPagosARealizar.getItems().add(new ModeloPago(IniciarSesionController.getToken().getId()+"",
	        						j.getDescription(),((double)(entry.getValue().getShouldPay()-entry.getValue().getPaid()))/100));
	        			}
	        		}
	        	}
			} catch (ApiException e) {
				e.printStackTrace();
			}
        	
        }
        
        if(cmbPagosARealizar.getItems().size()>0) {
        	cmbPagosARealizar.getSelectionModel().select(0);
        	cantidadTotal=cmbPagosARealizar.getSelectionModel().getSelectedItem().getCantidad();
        	actualizarCantidad();
        }
    }
    
    /**
     * Actualizar cantidad.
     */
    private void actualizarCantidad() {
    	cantidadAhora=0;
    	cantidadTotal=cmbPagosARealizar.getSelectionModel().getSelectedItem().getCantidad();
            String texto = txtCantidadAPagar.getText();
            if (texto != null && !texto.isEmpty()) {
                try {
                    cantidadAhora += Double.parseDouble(texto.replace(",", "."));
                } catch (NumberFormatException e) {
                    // Ignorar si no es número válido
                }
            }
        lblCantidad.setText(String.format("%.2f/%.2f", cantidadAhora, cantidadTotal));
    }
    
}
