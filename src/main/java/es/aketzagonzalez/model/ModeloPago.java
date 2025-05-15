package es.aketzagonzalez.model;

import java.util.Objects;

/**
 * The Class ModeloPago.
 */
public class ModeloPago {

	/** The id usuario. */
	private String idUsuario;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The cantidad. */
	private Double cantidad;
	
	/**
	 * Instantiates a new modelo pago.
	 *
	 * @param idUsuario the id usuario
	 * @param descripcion the descripcion
	 * @param cantidad the cantidad
	 */
	public ModeloPago(String idUsuario,String descripcion, Double cantidad) {
		super();
		this.idUsuario = idUsuario;
		this.descripcion=descripcion;
		this.cantidad = cantidad;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return this.descripcion+" "+this.cantidad+"€";
	}
	
	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad the new cantidad
	 */
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	
	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public Double getCantidad() {
		return cantidad;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(cantidad, descripcion, idUsuario);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModeloPago other = (ModeloPago) obj;
		return Objects.equals(cantidad, other.cantidad) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(idUsuario, other.idUsuario);
	}
	
}
