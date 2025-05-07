package es.aketzagonzalez.model;

import java.util.ArrayList;

public class ModeloCompra {

	private int id;
	private ArrayList<ModeloPago> pagos;
	
	public ModeloCompra(int id, ArrayList<ModeloPago> pagos) {
		super();
		this.id = id;
		this.pagos = pagos;
	}
	
}