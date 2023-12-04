package clases;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventario implements Serializable {
	private static final long serialVersionUID = 5150589531983728391L;
	/**
	 * 
	 */
	public static final List<String> prioridadCategoria = Arrays
			.asList(new String[] { "Pequeños", "SUV", "Vans", "Lujo", "Otros" });
	// TODO: sedes?
	public static final String[] categorias = new String[] { "Pequeños", "SUV", "Vans", "Lujo", "Otros" };
	public static final String[] sedes = new String[] { "SedeA", "SedeB",
			"SedeC", "SedeD" };
	public static final String[] seguros = new String[] { "Seguro 1", "Seguro 2",
			"Seguro 3", "Seguro 4" };
	public static final Map<String, Tarifa> tarifas = new HashMap<String, Tarifa>() {
		{
			put("Pequeños", new Tarifa(25000L, 50000L, 25000L));
			put("SUV", new Tarifa(25000L, 50000L, 25000L));
			put("Vans", new Tarifa(25000L, 50000L, 25000L));
			put("Lujo", new Tarifa(25000L, 50000L, 25000L));
			put("Otros", new Tarifa(25000L, 50000L, 25000L));
		}
	};
	private Map<String, Vehiculo> vehiculos;
	private Map<String, Reserva> reservas;
	private Map<String, List<Range<LocalDateTime>>> calendario;

	public Inventario() {
		this.vehiculos = new HashMap<String, Vehiculo>();
		this.reservas = new HashMap<String, Reserva>();
		this.calendario = new HashMap<String, List<Range<LocalDateTime>>>();
	}

	public Inventario(Map<String, Vehiculo> vehiculos, Map<String, Reserva> reservas,
			Map<String, List<Range<LocalDateTime>>> calendario) {
		this.vehiculos = vehiculos;
		this.reservas = reservas;
		this.calendario = calendario;
	}

	/*
	 * getters
	 */
	public Map<String, Vehiculo> getVehiculo() {
		return this.vehiculos;
	}

	public Map<String, Reserva> getReserva() {
		return this.reservas;
	}

	public Map<String, List<Range<LocalDateTime>>> getCalendario() {
		return this.calendario;
	}

	/*
	 * setters
	 */
	public void setVehiculo(Map<String, Vehiculo> Vehi) {
		this.vehiculos = Vehi;
	}

	public void setReserva(Map<String, Reserva> reser) {
		this.reservas = reser;
	}

	public void getCalendario(Map<String, List<Range<LocalDateTime>>> calen) {
		this.calendario = calen;
	}
}
