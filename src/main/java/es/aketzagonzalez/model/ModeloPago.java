package es.aketzagonzalez.model;

import java.util.Objects;

public class ModeloPago {

	private String idUsuario;
	private String descripcion;
	private Double cantidad;
	
	public ModeloPago(String idUsuario,String descripcion, Double cantidad) {
		super();
		this.idUsuario = idUsuario;
		this.descripcion=descripcion;
		this.cantidad = cantidad;
	}
	
	@Override
	public String toString() {
		return this.descripcion+" "+this.cantidad+"€";
	}
	
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	
	public Double getCantidad() {
		return cantidad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cantidad, descripcion, idUsuario);
	}

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
