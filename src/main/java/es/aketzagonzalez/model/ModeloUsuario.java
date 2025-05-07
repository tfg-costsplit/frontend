package es.aketzagonzalez.model;

public class ModeloUsuario {

	private String nombre;
	private String email;
	private String contrasenia;
	
	public ModeloUsuario(String nombre, String email, String contrasenia) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.contrasenia = contrasenia;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
}
