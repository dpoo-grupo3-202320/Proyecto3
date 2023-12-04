package clases;

import java.io.Serializable;

public class LicenciaDeConduccion implements Serializable {
	private static final long serialVersionUID = -4977841391704972753L;
	/**
	 * 
	 */
	private String numero;
	private String paisExpedicion;
	private String fechaVencimiento;
	private String imagen;
	
	/**
	 * 
	 * @param numero numero documento
	 * @param paisExpedicion
	 * @param fechaVencimiento
	 * @param imagen path a archivo
	 */
	
	public LicenciaDeConduccion(String numero, String paisExpedicion, String fechaVencimiento, String imagen) {
		this.numero = numero;
		this.paisExpedicion = paisExpedicion;
		this.fechaVencimiento = fechaVencimiento;
		this.imagen = imagen;
	}
	
	public String getNumero() {
		return this.numero;
	}
	
	public String getPaisExpedicion() {
		return this.paisExpedicion;
	}
	
	public String getFechaVencimiento() {
		return this.fechaVencimiento;
	}
	
	public String getImagen() {
		return this.imagen;
	}
}
