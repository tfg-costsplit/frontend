package es.aketzagonzalez.model;

/**
 * The Class ModeloGrupo.
 */
public class ModeloGrupo {

	/** The nombre. */
	private String nombre;
	
	/** The id. */
	private int id;
	
	/**
	 * Instantiates a new modelo grupo.
	 *
	 * @param nombre the nombre
	 * @param id the id
	 */
	public ModeloGrupo(String nombre,int id) {
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
}
