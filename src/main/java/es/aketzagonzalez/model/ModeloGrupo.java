package es.aketzagonzalez.model;

public class ModeloGrupo {

	private String nombre;
	private int id;
	
	public ModeloGrupo(String nombre,int id) {
		super();
		this.nombre = nombre;
		this.id=id;
	} 
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
	public int getId() {
		return id;
	}
	
}
