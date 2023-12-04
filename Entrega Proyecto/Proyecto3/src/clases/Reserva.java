package clases;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reserva implements Serializable {
	private static final long serialVersionUID = 7767627061899038385L;
	/*
	 * 
	 */
	private String id;
	private String categoriaSolicitada;
	private LocalDateTime fechaRecogida;
	private String ubicacionRecogida;
	private String ubicacionEntrega;
	private Range<LocalDateTime> rangoEntrega;
	private Cliente cliente;
	private Vehiculo vehiculo;
	private ArrayList<LicenciaDeConduccion> conductoresExtra;
	private Tarifa tarifa;

	public Reserva(String id, String categoriaSolicitada, LocalDateTime fechaRecogida, String ubicacionRecogida,
			String ubicacionEntrega, Range<LocalDateTime> rangoEntrega, Cliente cliente, Vehiculo vehiculo,
			ArrayList<LicenciaDeConduccion> conductoresExtra, Tarifa tarifa) {
		this.id = id;
		this.categoriaSolicitada = categoriaSolicitada;
		this.fechaRecogida = fechaRecogida;
		this.ubicacionRecogida = ubicacionRecogida;
		this.ubicacionEntrega = ubicacionEntrega;
		this.rangoEntrega = rangoEntrega;
		this.cliente = cliente;
		this.vehiculo = vehiculo;
		this.conductoresExtra = conductoresExtra;
		this.tarifa = tarifa;
	}

	/**
	 * Calcula costo de reserva / alquiler
	 * 
	 * @return costo de reserva / alquiler
	 */
	public Double calcularCosto() {
		Double costoSedeDiferente = (double) (ubicacionEntrega.equals(ubicacionRecogida) ? 0D
				: tarifa.getPrecioSedeDiferente());
		Double costoConductoresExtra = (double) (tarifa.getPrecioConductorExtra() * conductoresExtra.size());

		return tarifa.getPrecioCategoria() + costoSedeDiferente + costoConductoresExtra;
	}

	/*
	 * getters
	 */
	public String getId() {
		return id;
	}

	public String getCategoriaSolicitada() {
		return categoriaSolicitada;
	}

	public LocalDateTime getFechaRecogida() {
		return fechaRecogida;
	}

	public String getUbicacionRecogida() {
		return ubicacionRecogida;
	}

	public String getUbicacionEntrega() {
		return ubicacionEntrega;
	}

	public Range<LocalDateTime> getRangoEntrega() {
		return rangoEntrega;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public ArrayList<LicenciaDeConduccion> getConductoresExtra() {
		return conductoresExtra;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	/*
	 * setters
	 */
	public void setId(String id) {
		this.id = id;
	}

	public void setCategoriaSolicitada(String categoriaSolicitada) {
		this.categoriaSolicitada = categoriaSolicitada;
	}

	public void setFechaRecogida(LocalDateTime fechaRecogida) {
		this.fechaRecogida = fechaRecogida;
	}

	public void setUbicacionRecogida(String ubicacionRecogida) {
		this.ubicacionRecogida = ubicacionRecogida;
	}

	public void setUbicacionEntrega(String ubicacionEntrega) {
		this.ubicacionEntrega = ubicacionEntrega;
	}

	public void setRangoEntrega(Range<LocalDateTime> rangoEntrega) {
		this.rangoEntrega = rangoEntrega;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public void setConductoresExtra(ArrayList<LicenciaDeConduccion> conductoresExtra) {
		this.conductoresExtra = conductoresExtra;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}
}
