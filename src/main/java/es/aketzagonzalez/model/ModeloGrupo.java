package es.aketzagonzalez.model;

import java.util.ArrayList;

public class ModeloGrupo {

	private String nombre;
	private ArrayList<ModeloUsuario> lstUsuarios;
	
	public ModeloGrupo(String nombre, ArrayList<ModeloUsuario> lstUsuarios) {
		super();
		this.nombre = nombre;
		this.lstUsuarios = lstUsuarios;
	} 
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
}
