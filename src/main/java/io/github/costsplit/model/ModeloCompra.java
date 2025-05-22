package io.github.costsplit.model;

// TODO: Auto-generated Javadoc
/**
 * The Class ModeloCompra.
 */
public class ModeloCompra {

	/** The id. */
	private int id;
	
	/** The descripcion. */
	private String descripcion;
	
	/**
	 * Instantiates a new modelo compra.
	 *
	 * @param id the id
	 * @param nombre the nombre
	 */
	public ModeloCompra(int id, String nombre) {
		super();
		this.id = id;
		this.descripcion = nombre;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return this.descripcion;
	}
	
}
