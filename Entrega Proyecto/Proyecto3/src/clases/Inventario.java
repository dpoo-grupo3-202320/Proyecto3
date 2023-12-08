package clases;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventario implements Serializable {
	private static final long serialVersionUID = 5150589531983728391L;
	/**
	 * 
	 */
	public static final List<String> prioridadCategoria = Arrays.asList(new String[] { "automovil", "moto", "atv",
			"bicicleta", "bicicleta electrica", "patineta electrica", "Pequeños", "SUV", "Vans", "Lujo", "Otros" });
	// TODO: sedes?
	public static final String[] sedes = new String[] { "SedeA", "SedeB", "SedeC", "SedeD", "SedeE", "SedeH" };
	public static final String[] categorias = new String[] { "automovil", "moto", "atv", "bicicleta",
			"bicicleta electrica", "patineta electrica", "Pequenos", "SUV", "Vans", "Lujo", "Otros" };

	public static final String[] seguros = new String[] { "Seguro 1", "Seguro 2", "Seguro 3", "Seguro 4" };
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
	public Map<String, Vehiculo> getVehiculoMap() {
		return this.vehiculos;
	}

	public Map<String, Reserva> getReservaMap() {
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

	// Metodos utiles

	public Vehiculo getVehiculo(String placa) {
		return vehiculos.get(placa);
	}

	public ArrayList<Vehiculo> getVehiculos() {
		return new ArrayList<Vehiculo>(vehiculos.values());
	}

	public boolean vehiculoExiste(String nombreVehiculo) {
		return vehiculos.containsKey(nombreVehiculo);
	}

	public void nuevoVehiculo(Vehiculo v) throws Exception {
		if (vehiculoExiste(v.getPlaca())) {
			throw new Exception("Ya existe un vehiculo con esta placa.");
		}
		vehiculos.put(v.getPlaca(), v);
	}

	public void agregarVehiculo(String placa, String marca, String color, String transmision, String categoria,
			String sede, String estado) throws Exception {

		LocalDateTime fechaDisponible = LocalDateTime.now();
		String comentarios = "vehiculo nuevo";
		ArrayList<Reserva> historial = null;

		Vehiculo nuevoVehiculo = new Vehiculo(placa, marca, color, transmision, categoria, sede, fechaDisponible,
				comentarios, estado, historial);
		nuevoVehiculo(nuevoVehiculo);
	}

	public String consultarUbicacionVehiculo(String placa) throws Exception {
		if (!vehiculoExiste(placa)) {
			throw new Exception("El vehiculo seleccionado no existe");
		}
		Vehiculo v = getVehiculo(placa);
		if (v.getUbicacion() == null) {
			throw new Exception("El vehiculo esta actualmente alquilado");
		}
		return v.getUbicacion();
	}

	public ArrayList<Reserva> consultarHistorialVehiculo(String placa) throws Exception {
		if (!vehiculoExiste(placa)) {
			throw new Exception("El vehiculo seleccionado no existe");
		}
		Vehiculo v = getVehiculo(placa);
		ArrayList<Reserva> historial = v.getHistorial();
		if (historial.isEmpty()) {
			throw new Exception("El vehiculo seleccionado no tiene historial");
		}
		return historial;
	}

	public boolean eliminarVehiculo(String placa) {

		// Vehiculo vehiculoInteres = getVehiculo(placa);
		if (vehiculoExiste(placa)) {
			vehiculos.remove(placa);
			return true;
		} else {
			return false;
		}

	}

}
