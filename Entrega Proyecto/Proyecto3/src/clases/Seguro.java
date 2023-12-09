package clases;

import java.io.Serializable;

public class Seguro implements Serializable {
	private static final long serialVersionUID = 1975402684445611620L;
	
	//Atributos
	private String nombre;
	private float costoDiario;

	//Builder 
	
	Seguro(String nomSeguro, float valor )
	{
		this.nombre = nomSeguro;
		this.costoDiario = valor;
	}
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

	public void setCostoDiario(float costoDiario) 
	{
		this.costoDiario = costoDiario;
	}
	
}

