package es.aketzagonzalez.model;

/**
 * The Class ModeloUsuario.
 */
public class ModeloUsuario {

	/** The nombre. */
	private String nombre;
	
	/** The id. */
	private int id;
	
	/**
	 * Instantiates a new modelo usuario.
	 *
	 * @param nombre the nombre
	 * @param id the id
	 */
	public ModeloUsuario(String nombre, int id) {
		super();
		this.nombre = nombre; 
		this.id=id;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return this.nombre;
	}
	
	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
}
