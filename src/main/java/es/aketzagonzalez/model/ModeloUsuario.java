package es.aketzagonzalez.model;

public class ModeloUsuario {

	private String nombre;
	private int id;
	
	public ModeloUsuario(String nombre, int id) {
		super();
		this.nombre = nombre; 
		this.id=id;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getId() {
		return id;
	}
	
}
