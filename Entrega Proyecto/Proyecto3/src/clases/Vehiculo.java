package clases;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Vehiculo implements Serializable {
	private static final long serialVersionUID = -4570371102516863918L;
	private String placa;
	private String marca;
	private String color;
	private String transmision;
	private String categoria;
	private String ubicacion;
	private LocalDateTime fechaDisponible;
	private String comentarios;
	//
	private String estado;
	private ArrayList<Reserva> historial;

	public Vehiculo(String placa, String marca, String color, String transmision, String categoria, String ubicacion,
			LocalDateTime fechaDisponible, String comentarios, String estado,
			ArrayList<Reserva> historial) {
		this.placa = placa;
		this.marca = marca;
		this.color = color;
		this.transmision = transmision;
		this.categoria = categoria;
		this.ubicacion = ubicacion;
		this.fechaDisponible = fechaDisponible;
		this.comentarios = comentarios;
		this.estado = estado;
		this.historial = historial;
	}

	/*
	 * getters
	 */

	public String getPlaca() {
		return placa;
	}

	public String getMarca() {
		return marca;
	}

	public String getColor() {
		return color;
	}

	public String getTransmision() {
		return transmision;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public LocalDateTime getFechaDisponible() {
		return fechaDisponible;
	}

	public String getComentarios() {
		return comentarios;
	}

	public String getEstado() {
		return estado;
	}

	public ArrayList<Reserva> getHistorial() {
		return historial;
	}
	
	public String getDescripcion() {
		String descripcion="";
		descripcion+=this.marca+"\n";
		descripcion+=this.color+"\n";
		descripcion+=this.categoria+"\n";
		descripcion+=this.transmision+"\n";
		
		return descripcion;
	}

	/*
	 * setters
	 */

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setTransmision(String transmision) {
		this.transmision = transmision;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public void setFechaDisponible(LocalDateTime fechaDisponible) {
		this.fechaDisponible = fechaDisponible;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setHistorial(ArrayList<Reserva> historial) {
		this.historial = historial;
	}

}