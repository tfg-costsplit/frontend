package es.aketzagonzalez.model;

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
		return this.descripcion+" "+this.cantidad;
	}
	
}
