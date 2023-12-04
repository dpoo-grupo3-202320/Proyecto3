package clases;

import java.io.Serializable;

public abstract class Seguro implements Serializable {
	private static final long serialVersionUID = 1975402684445611620L;
	/**
	 * 
	 */
	protected String nombre;
	protected float costoDiario;


	/*
	 * getters
	 */
	public String getNombre() {
		return this.nombre;
	}

	public float getCostoDiario() {
		return this.costoDiario;
	}
	
	/*
	 * setters
	 */

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setContraseña(float costoDiario) {
		this.costoDiario = costoDiario;
	}
	
}

