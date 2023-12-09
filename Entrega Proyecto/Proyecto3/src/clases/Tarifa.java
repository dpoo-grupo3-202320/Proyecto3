package clases;

import java.io.Serializable;

public class Tarifa implements Serializable {
	private static final long serialVersionUID = 6857521008207905238L;
	/**
	 * 
	 */
	private Long precioCategoria;
	private Long precioSedeDiferente;
	private Long precioConductorExtra;

	public Tarifa(Long precioCategoria, Long precioSedeDiferente, Long precioConductorExtra) {
		this.precioCategoria = precioCategoria;
		this.precioSedeDiferente = precioSedeDiferente;
		this.precioConductorExtra = precioConductorExtra;
	}

	/*
	 * getters
	 */

	public Long getPrecioCategoria() {
		return precioCategoria;
	}

	public Long getPrecioSedeDiferente() {
		return precioSedeDiferente;
	}

	public Long getPrecioConductorExtra() {
		return precioConductorExtra;
	}

	/*
	 * setters
	 */

	public void setPrecioCategoria(Long precioCategoria) {
		this.precioCategoria = precioCategoria;
	}

	public void setPrecioSedeDiferente(Long precioSedeDiferente) {
		this.precioSedeDiferente = precioSedeDiferente;
	}

	public void setPrecioConductorExtra(Long precioConductorExtra) {
		this.precioConductorExtra = precioConductorExtra;
	}
}
