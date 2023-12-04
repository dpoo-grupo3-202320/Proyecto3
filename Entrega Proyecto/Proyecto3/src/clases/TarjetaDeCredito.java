package clases;

import java.io.Serializable;

public class TarjetaDeCredito implements Serializable {
	private static final long serialVersionUID = 2599490462876986299L;
	/**
	 * 
	 */
	private String numero;
	private String fechaVencimiento;
	private String cvv;

	public TarjetaDeCredito(String numero, String fechaVencimiento, String cvv) {
		this.numero = numero;
		this.fechaVencimiento = fechaVencimiento;
		this.cvv = cvv;
	}

	/**
	 * getters
	 */

	public String getNumero() {
		return numero;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public String getCvv() {
		return cvv;
	}

	/**
	 * setters
	 */

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
}
