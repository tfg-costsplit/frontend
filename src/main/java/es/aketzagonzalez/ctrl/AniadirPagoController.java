package es.aketzagonzalez.ctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

import es.aketzagonzalez.costsplitFrontend.MainApp;
import es.aketzagonzalez.model.ModeloGrupo;
import es.aketzagonzalez.model.ModeloUsuario;
import es.aketzagonzalez.utilidad.Navegador;
import io.github.costsplit.api.invoker.ApiException;
import io.github.costsplit.api.model.AddPurchase;
import io.github.costsplit.api.model.PayEntry;
import io.github.costsplit.api.model.UserInfo;

public class AniadirPagoController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnFijarCantidad;
    
    @FXML
    private ComboBox<ModeloGrupo> cmbGrupos;

    @FXML
    private VBox contenedorUsuarios;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label lblCantidad;

    @FXML
    private TextField txtCantidadTotal;
    
    @FXML
    private TextField txtDescripcion;

    private List<ModeloUsuario> usuarios;

    private double cantidadTotal = -1.0;

    private double cantidadAhora = -9.0;

    private List<TextField> camposCantidadUsuarios = new ArrayList<>();
    
    private UnaryOperator<TextFormatter.Change> filter;
    
    private Map<String, PayEntry> mapaPagos=new HashMap<String, PayEntry>();
    
    private double alturaOriginal=Navegador.getStage().getHeight();

    @FXML
    void accionCancelar(ActionEvent event) {
        Navegador.cargarVista("PestaniaPrincipal", null);
    }

    @FXML
    void accionGuardar(ActionEvent event) {
    	Alert al=new Alert(AlertType.ERROR);
    	al.setHeaderText(null);
    	if(txtDescripcion.getText().isBlank()) {
    		al.setContentText("La descripcion no puede estar vacia");
    		al.showAndWait();
    	}else {
	    	if(cantidadAhora==cantidadTotal) {
	    		
	    		for (int i = 0; i < usuarios.size(); i++) {
	                ModeloUsuario usuario = usuarios.get(i);
	                TextField campo = camposCantidadUsuarios.get(i);
	                String texto = campo.getText();
                    try {
                        double cantidad = Double.parseDouble(texto.replace(",", "."));
						PayEntry entry=new PayEntry();
						entry.setPaid(0l);
						entry.setShouldPay((long)cantidad*100);
                        mapaPagos.put(usuario.getId()+"", entry);
                    } catch (NumberFormatException e) {
                        al.showAndWait();
                        return;
                    }
	            }
	    		AddPurchase purchase=new AddPurchase();
	    		purchase.setDescription(txtDescripcion.getText());
	    		purchase.setGroupId(cmbGrupos.getSelectionModel().getSelectedItem().getId());
	    		purchase.setPayments(mapaPagos);
	    		purchase.setCost((long)cantidadTotal*100);
	    		try {
					MainApp.getApi().createPurchase(purchase);
					al.setContentText("Factura registrada correctamente");
					al.setAlertType(AlertType.INFORMATION);
					al.showAndWait();
					Navegador.cargarVista("PestaniaPrincipal", null);
				} catch (ApiException e) {
					e.printStackTrace();
				}
	    	}else {
	    		al.setContentText("La suma de lo que debe pagar cada persona debe ser igaul al total de la factura");
	    		al.showAndWait();
	    	}
    	}
    }

    @FXML
    void seleccionarGrupo(ActionEvent event) {
    	cargarUsuarios(filter,cmbGrupos.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    public void initialize() {
        // Permitir números con , o . como separador decimal
        filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*([.,]\\d{0,2})?")) {
                return change;
            }
            return null;
        };
        txtCantidadTotal.setTextFormatter(new TextFormatter<>(filter));

        // Listener para actualizar cantidadTotal automáticamente
        txtCantidadTotal.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                cantidadTotal = Double.parseDouble(newVal.replace(",", "."));
            } catch (NumberFormatException e) {
                cantidadTotal = 0.0;
            }
            actualizarCantidad();
        });
        
        //Cargar el combo
        for(int i:IniciarSesionController.getToken().getGroups()) {
        	try {
				cmbGrupos.getItems().add(new ModeloGrupo(MainApp.getApi().getGroupData(i).getName(), i));
			} catch (ApiException e) {
				e.printStackTrace();
			}
        }
        if(cmbGrupos.getItems().size()>0) {
        	cmbGrupos.getSelectionModel().select(0);
        	cargarUsuarios(filter, cmbGrupos.getSelectionModel().getSelectedItem().getId());
        }
        
    }

    private void cargarUsuarios(UnaryOperator<TextFormatter.Change> filter,int idGrupo) {
    	try {
    		usuarios = new ArrayList<ModeloUsuario>();
    		contenedorUsuarios.getChildren().clear(); // Limpia antes de volver a cargar
            camposCantidadUsuarios.clear(); // Limpia campos anteriores
			for(UserInfo i:MainApp.getApi().getAllGroupData(idGrupo).getUsers()) {
				usuarios.add(new ModeloUsuario(i.getName(), i.getId()));
			}
			 int margenVertical = 10;
		        int alturaPorUsuario = 40; // Ajusta si tus filas son más altas
		        int numUsuarios = usuarios.size();

		        int alturaMinima = (alturaPorUsuario * 5) + margenVertical;
		        int alturaReal = (alturaPorUsuario * numUsuarios) + margenVertical;

		        scrollPane.setMinHeight(Math.min(alturaReal, alturaMinima));
		        scrollPane.setPrefHeight(Math.min(alturaReal, alturaMinima));
		        double nuevaAltura = Math.min(alturaReal, alturaMinima)+alturaOriginal-alturaPorUsuario;
		        Navegador.getStage().setHeight(nuevaAltura);
	        for (ModeloUsuario usuario : usuarios) {
	            HBox fila = new HBox(10);
	            fila.setAlignment(Pos.CENTER);
	            
	            Label labelNombre = new Label(usuario.getNombre());
	            labelNombre.setMinWidth(100);

	            TextField campoNumero = new TextField();
	            campoNumero.setPromptText("Cantidad a pagar");
	            campoNumero.setPrefWidth(100);
	            campoNumero.setTextFormatter(new TextFormatter<>(filter));

	            campoNumero.textProperty().addListener((obs, oldVal, newVal) -> actualizarCantidad());

	            camposCantidadUsuarios.add(campoNumero);

	            fila.getChildren().addAll(labelNombre, campoNumero);
	            contenedorUsuarios.getChildren().add(fila);
	        }
		} catch (ApiException e) {
			e.printStackTrace();
		}
        
    }

    private void actualizarCantidad() {
        cantidadAhora = 0.0;

        for (TextField campo : camposCantidadUsuarios) {
            String texto = campo.getText();
            if (texto != null && !texto.isEmpty()) {
                try {
                    cantidadAhora += Double.parseDouble(texto.replace(",", "."));
                } catch (NumberFormatException e) {
                    // Ignorar si no es número válido
                }
            }
        }

        lblCantidad.setText(String.format("%.2f/%.2f", cantidadAhora, cantidadTotal));
    }
}
