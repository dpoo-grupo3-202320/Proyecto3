package clases;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SistemaAlquiler {
	private ContenedorDeDatos datos;

	// FIXME: no deberia haber accesso directo al contenedor
	// de datos
	public ContenedorDeDatos getContenedorDeDatos() {
		return this.datos;
	}

	public ArrayList<Reserva> getReservas() {
		return datos.getReservas();
	}

	public SistemaAlquiler() throws FileNotFoundException, IOException, ClassNotFoundException {
		cargarDatos();
	}

	public boolean sesionIniciada() {
		return datos.sesionIniciada();
	}

	public void establecerUsuario(Usuario u) {
		datos.establecerUsuario(u);
	}

	public void cerrarSesion() {
		datos.cerrarSesion();
	}

	public Usuario getUsuarioActual() {
		return datos.getUsuarioActual();
	}

	/*
	 * funciones
	 */
	/**
	 * si se lanza error, significa que no se pudieron cargar los datos los datos se
	 * cargan automaticamente, si no existe un archivo de
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void cargarDatos() throws IOException, ClassNotFoundException {
		// cargar bytes de archivo
		File archivoDatos = new File("./Datos/datos");
		if (archivoDatos.exists()) {
			byte[] bytes = Files.readAllBytes(archivoDatos.toPath());
			// convertir bytes a objeto
			ByteArrayInputStream bs = new ByteArrayInputStream(bytes); // bytes es el byte[]
			ObjectInputStream is = new ObjectInputStream(bs);
			datos = (ContenedorDeDatos) is.readObject();
			is.close();
		} else {
			datos = new ContenedorDeDatos();
			// CSVReader r = new CSVReader(this, datos);
		}
		CSVReader r = new CSVReader(this, datos);

	}

	/**
	 * si lanza un error, significa que no se pudieron guardar datos
	 * 
	 * @throws IOException
	 */
	public void guardarDatos() throws IOException {
		// convertir objeto de datos a bytes
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(bs);
		os.writeObject(datos);
		os.close();
		byte[] bytes = bs.toByteArray();
		// guardar bytes en archivo
		File archivoDatos = new File("./Datos/datos");
		if (!archivoDatos.exists()) {
			if (!archivoDatos.createNewFile())
				throw new FileNotFoundException("no se pudo crear el archivo");
		}
		try (FileOutputStream outputStream = new FileOutputStream(archivoDatos)) {
			outputStream.write(bytes);
			System.out.println("datos guardados");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Empleado getEmpleado(String usuario) {
		return datos.getEmpleado(usuario);
	}

	public Cliente getCliente(String usuario) {
		return datos.getCliente(usuario);
	}

	public Usuario getUsuario(String usuario, String clave) {
		Usuario usuarioInteres = datos.getUsuario(usuario, clave);
		return usuarioInteres;
	}

	public ArrayList<Sede> getSedes() {
		return datos.listaDeSedes();
	}

	public Sede getSede(String nombre) {
		return datos.getSede(nombre);
	}

	public void registroAdmin(String usuario, String clave, String sede) throws Exception {
		if (!datos.adminExiste(usuario)) {
			throw new Exception("El nombre de usuario ya esta en uso. Intenta con otro");
		}
		// El admin no existe, agregarlo
		Admin nuevoAdmin = new Admin(usuario, clave, sede);
		datos.nuevoAdmin(nuevoAdmin);
	}

	public Empleado registroEmpleado(String usuario, String clave, String rol, Sede sede) throws Exception {
		if (datos.empleadoExiste(usuario)) {
			throw new Exception("Ya existe un usuario con este nombre, intente con otro");
		}
		Empleado empleado = new Empleado(usuario, clave, rol, sede);
		datos.nuevoEmpleado(empleado);
		return empleado;
	}

	public void eliminarEmpleado(String usuario) throws Exception {
		datos.eliminarEmpleado(usuario);
	}

	public void registroCliente(String usuario, String clave, String nombres, String numeroTelefono, String direccion,
			String fechaNacimiento, String nacionalidad, String imagenDocumentoIdentidad, String numeroLicencia,
			String paisExpedicion, String fechaVencimientoLicencia, String imagen, String numeroTarjeta,
			String fechaVencimientoTarjeta, String cvv) throws Exception {
		if (datos.clienteExiste(usuario)) {
			throw new Exception("El nombre de usuario ya esta en uso. Intenta con otro");
		}

		// El cliente no existe, agregarlo
		LicenciaDeConduccion licencia = new LicenciaDeConduccion(numeroLicencia, paisExpedicion,
				fechaVencimientoLicencia, imagen);
		TarjetaDeCredito tarjetaDeCredito = new TarjetaDeCredito(numeroTarjeta, fechaVencimientoTarjeta, cvv);
		Cliente nuevoCliente = new Cliente(usuario, clave, nombres, numeroTelefono, direccion, fechaNacimiento,
				nacionalidad, imagenDocumentoIdentidad, licencia, tarjetaDeCredito);
		datos.nuevoCliente(nuevoCliente);
	}

	public void crearSede(String nomSede, String ubiSede, int hrsASede, int hrsCSede)
			throws IllegalArgumentException, Exception {
		Range<Integer> rangeHrs = new Range<Integer>(hrsASede, hrsCSede);

		HorarioDeAtencion hrs = new HorarioDeAtencion(rangeHrs);

		ArrayList<Empleado> empleados = new ArrayList<Empleado>();

		// La sede no existe, agregarla
		Sede nuevaSede = new Sede(nomSede, ubiSede, hrs, empleados);
		datos.nuevaSede(nuevaSede);

	}

	public void modificarNombreSede(String nuevoNomSede, String actNomSede) throws Exception {
		datos.modificarNombreSede(nuevoNomSede, actNomSede);
	}

	public void modificarHorarioSede(String nomSede, int hrsASede, int hrsCSede) throws Exception {
		datos.modificarHorarioSede(nomSede, hrsASede, hrsCSede);
	}

	public String consultarUbicacionVehiculo(String placa) throws Exception {
		if (!datos.vehiculoExiste(placa)) {
			throw new Exception("El vehiculo seleccionado no existe");
		}
		Vehiculo v = datos.getVehiculo(placa);
		if (v.getUbicacion() == null) {
			throw new Exception("El vehiculo esta actualmente alquilado");
		}
		return v.getUbicacion();
	}

	public ArrayList<Reserva> consultarHistorialVehiculo(String placa) throws Exception {
		if (!datos.vehiculoExiste(placa)) {
			throw new Exception("El vehiculo seleccionado no existe");
		}
		Vehiculo v = datos.getVehiculo(placa);
		ArrayList<Reserva> historial = v.getHistorial();
		if (historial.isEmpty()) {
			throw new Exception("El vehiculo seleccionado no tiene historial");
		}
		return historial;
	}

	public void crearReserva(String categoriaSolicitada, LocalDateTime fechaRecogida, String ubicacionRecogida,
			String ubicacionEntrega, Range<LocalDateTime> rangoEntrega, Cliente cliente,
			ArrayList<LicenciaDeConduccion> conductoresExtra) throws Exception {
		Tarifa tarifa = Inventario.tarifas.get(categoriaSolicitada);
		Reserva r = new Reserva(datos.nuevoIdReservas(), categoriaSolicitada, fechaRecogida, ubicacionRecogida,
				ubicacionEntrega, rangoEntrega, cliente, null, conductoresExtra, tarifa);
		datos.nuevaReserva(r);
	}

	public void modificarReserva(String idReserva, LocalDateTime fechaRecogida, Range<LocalDateTime> rangoEntrega)
			throws Exception {
		if (!datos.reservaExiste(idReserva)) {
			throw new Exception("La reserva seleccionada no existe");
		}
		Reserva r = datos.getReserva(idReserva);
		if (r.getVehiculo() != null) {
			throw new Exception("Esta reserva ya es un alquiler en curso, no se puede modificar");
		}
		r.setFechaRecogida(fechaRecogida);
		r.setRangoEntrega(rangoEntrega);
	}

	public void crearAlquiler(String categoriaSolicitada, LocalDateTime fechaRecogida, String ubicacionRecogida,
			String ubicacionEntrega, Range<LocalDateTime> rangoEntrega, Cliente cliente,
			ArrayList<LicenciaDeConduccion> conductoresExtra) throws Exception {

		Tarifa tarifa = Inventario.tarifas.get(categoriaSolicitada);
		Reserva r = new Reserva(datos.nuevoIdReservas(), categoriaSolicitada, fechaRecogida, ubicacionRecogida,
				ubicacionEntrega, rangoEntrega, cliente, null, conductoresExtra, tarifa);
		datos.nuevaReserva(r);
		formalizarAlquiler(r.getId());
	}

	public void formalizarAlquiler(String idReserva) throws Exception {
		if (!datos.reservaExiste(idReserva)) {
			throw new Exception("La reserva seleccionada no existe");
		}
		Reserva r = datos.getReserva(idReserva);
		// encontrar vehiculo disponible
		String categoria = r.getCategoriaSolicitada();
		Vehiculo vehiculoEncontrado = null;
		while (vehiculoEncontrado == null) {
			for (Vehiculo v : datos.getVehiculos()) {
				if (v.getCategoria() == categoria
						&& (v.getFechaDisponible().compareTo(r.getRangoEntrega().getLow()) <= 0)) {
					// actualizar vehiculo
					v.setSede(null);
					v.setEstado("alquilado");
					v.setFechaDisponible(r.getFechaRecogida());
					r.setVehiculo(v);
					return;
				}
			}
			int i = Inventario.prioridadCategoria.indexOf(categoria);
			i += 1;
			if (i >= Inventario.prioridadCategoria.size()) {
				throw new Exception("No existen vehiculos disponibles en este momento");
			}
			categoria = Inventario.prioridadCategoria.get(i);
		}
	}

	public void agregarVehiculo(String placa, String marca, String color, String transmision, String categoria,
			String sede, String estado) throws Exception {
		// La sede no existe, agregarla
		LocalDateTime fechaDisponible = null;
		String comentarios = "vehiculo nuevo";
		ArrayList<Reserva> historial = null;

		Vehiculo nuevoVehiculo = new Vehiculo(placa, marca, color, transmision, categoria, sede, fechaDisponible,
				comentarios, estado, historial);
		datos.nuevoVehiculo(nuevoVehiculo);

	}
}
