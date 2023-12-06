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
import java.util.HashMap;
import java.util.Map;

import clases.CSVReader;
import clases.CSVWriter;

public class SistemaAlquiler {
	private CSVReader csv = new CSVReader(this);
	private Admin adminGeneral;
	private Usuario usuarioActual;
	private Inventario inventario = new Inventario();
	
	//llave es login 
	private HashMap<String, Cliente> clientes = new HashMap<String, Cliente>();
	private HashMap<String, Admin> admins = new HashMap<String, Admin>();
	private HashMap<String, Empleado> empleados =  new HashMap<String, Empleado>();
	
	//llave es nombre 
	private HashMap<String, Sede> sedes = new HashMap<String, Sede>();
	
	// llave es id reserva
	private HashMap<String, Reserva> reservas = new HashMap<String, Reserva>();
	
	private ArrayList<Seguro> seguros = new ArrayList<Seguro>();
	
	// se utiliza como id para las reservas
	private int contadorReservas = 0;
	
	//Builder 
	public SistemaAlquiler() throws FileNotFoundException, IOException, ClassNotFoundException 
	{
		cargarDatos();
		this.adminGeneral = new Admin("AdministradorGen", "SenecaDPOO");
		this.admins.put(adminGeneral.getNombreUsuario(), adminGeneral);
	}

	//Carga de datos 
	public void cargarDatos() throws IOException, ClassNotFoundException {
		csv.cargarDatos();	
	}
	
	//Guardar Datos
	
	public void guardarDatos() 
	{
		CSVWriter.guardarDatos(empleados, clientes, admins, sedes, inventario.getVehiculos());
	}
	
	
	//Getters individuales
	public Empleado getEmpleado(String usuario) {
		return empleados.get(usuario);
	}

	public Cliente getCliente(String usuario) {
		return clientes.get(usuario);
	}
	
	public Sede getSede(String nombre) {
		return sedes.get(nombre);
	}
	 
	public Usuario getUsuario(String usuario, String clave) 
	{
		Map<String, Usuario> usuarios = getUsuarios();
		Usuario usuarioInteres = usuarios.get(usuario);
		
		if (clave.equals(usuarioInteres.getContraseña())){
			return usuarioInteres;
		} else {
			return null;
		}
	}
	
	public Reserva getReserva(String id) {
		return reservas.get(id);
	}
	
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
	
	
	//Getters Estructuras
	
	//Getters estruturas
	public ArrayList<Sede> getSedes() {
		return (ArrayList<Sede>) sedes.values();
	}
	
	public Map<String, Usuario> getUsuarios() {
		Map<String, Usuario> usuarios = new HashMap<String, Usuario>();
		usuarios.putAll(admins);
		usuarios.putAll(empleados);
		usuarios.putAll(clientes);
		return usuarios;
	}
	
	public ArrayList<Seguro> getSeguros() {
		return seguros;
	}
	
	public ArrayList<Reserva> getReservas() {
		return new ArrayList<Reserva>(reservas.values());
	}
	
	
	
	//Metodos 
	
	//Registro de nuevos admins
	public void nuevoAdmin(Admin admin) throws Exception {
		if (adminExiste(admin.usuario))
			throw new Exception("Ya existe un admin con este usuario");
		admins.put(admin.usuario, admin);
	}
	
	public boolean adminExiste(String usuario) {
		return admins.containsKey(usuario);
	}
	
	public void registroAdmin(String usuario, String clave, String sede) throws Exception {
		if (adminExiste(usuario)) {
			throw new Exception("El nombre de usuario ya esta en uso. Intenta con otro");
		}
		// El admin no existe, agregarlo
		Admin nuevoAdmin = new Admin(usuario, clave, sede);
		nuevoAdmin(nuevoAdmin);
	}
	
	//Registro de nuevos empleados
	public boolean empleadoExiste(String usuario) {
		return empleados.containsKey(usuario);
	}

	public void nuevoEmpleado(Empleado empleado) throws Exception {
		if (empleadoExiste(empleado.usuario))
			throw new Exception("Ya existe un empleado con este usuario");
		this.empleados.put(empleado.usuario, empleado);
	}

	public void eliminarEmpleado(String usuario) throws Exception {
		if (!empleadoExiste(usuario)) {
			throw new Exception("El empleado seleccionado no existe");
		}
		this.empleados.remove(usuario);
	}
	
	public Empleado registroEmpleado(String usuario, String clave, Sede sede) throws Exception {
		if (empleadoExiste(usuario)) {
			throw new Exception("Ya existe un usuario con este nombre, intente con otro");
		}
		Empleado empleado = new Empleado(usuario, clave, sede);
		nuevoEmpleado(empleado);
		return empleado;
	}
	
	//Registro nuevos clientes
	public boolean clienteExiste(String usuario) {
		return clientes.containsKey(usuario);
	}

	public void nuevoCliente(Cliente cliente) throws Exception {
		if (clienteExiste(cliente.usuario)) {
			throw new Exception("El nombre de usuario ya esta en uso. Intenta con otro");
		}
		clientes.put(cliente.usuario, cliente);
	}
	
	public void registroCliente(String usuario, String clave, String nombres, String numeroTelefono, String direccion,
			String fechaNacimiento, String nacionalidad, String imagenDocumentoIdentidad, String numeroLicencia,
			String paisExpedicion, String fechaVencimientoLicencia, String imagen, String numeroTarjeta,
			String fechaVencimientoTarjeta, String cvv) throws Exception {
		if (clienteExiste(usuario)) {
			throw new Exception("El nombre de usuario ya esta en uso. Intenta con otro");
		}

		// El cliente no existe, agregarlo
		LicenciaDeConduccion licencia = new LicenciaDeConduccion(numeroLicencia, paisExpedicion,
				fechaVencimientoLicencia, imagen);
		TarjetaDeCredito tarjetaDeCredito = new TarjetaDeCredito(numeroTarjeta, fechaVencimientoTarjeta, cvv);
		Cliente nuevoCliente = new Cliente(usuario, clave, nombres, numeroTelefono, direccion, fechaNacimiento,
				nacionalidad, imagenDocumentoIdentidad, licencia, tarjetaDeCredito);
		nuevoCliente(nuevoCliente);
	}
	
	//Creacion y modificacion de sedes
	public boolean sedeExiste(String nombreSede) {
		return sedes.containsKey(nombreSede);
	}
	
	public void nuevaSede(Sede sede) throws Exception {
		if (sedeExiste(sede.getNombre())) {
			throw new Exception("Ya existe una sede con este nombre. Intenta con otro.");
		}
		sedes.put(sede.getNombre(), sede);
	}
	
	public void crearSede(String nomSede, String ubiSede, int hrsASede, int hrsCSede)
			throws IllegalArgumentException, Exception {
		Range<Integer> rangeHrs = new Range<Integer>(hrsASede, hrsCSede);

		HorarioDeAtencion hrs = new HorarioDeAtencion(rangeHrs);

		ArrayList<Empleado> empleados = new ArrayList<Empleado>();

		// La sede no existe, agregarla
		Sede nuevaSede = new Sede(nomSede, ubiSede, hrs, empleados);
		nuevaSede(nuevaSede);

	}
	
	public void modificarNombreSede(String nuevoNomSede, String actNomSede) throws Exception {
		if (sedeExiste(actNomSede)) {
			Sede sedeActual = getSede(actNomSede);
			sedes.remove(actNomSede);
			sedeActual.setNombre(nuevoNomSede);
			sedes.put(actNomSede, sedeActual);
		} else {
			throw new Exception("La sede ingresada no fue encontrada ");
		}
	}

	public void modificarHorarioSede(String nomSede, int hrsASede, int hrsCSede) throws Exception {
		if (sedes.containsKey(nomSede)) {
			Sede sedeActual = sedes.get(nomSede);
			Range<Integer> rangeHrs = new Range<Integer>(hrsASede, hrsCSede);
			HorarioDeAtencion hrs = new HorarioDeAtencion(rangeHrs);
			sedeActual.setHorariosDeAtencion(hrs);
		} else {
			throw new Exception("La sede ingresada no fue encontrada ");
		}
	}
	
	//Creacion de vehiculos
	
	//FIXME Todos estos metodos deben ser parte de Inventario
	
	public Vehiculo getVehiculo(String placa) {
		return inventario.getVehiculo(placa);
	}

	public ArrayList<Vehiculo> getVehiculos() {
		return inventario.getVehiculos();
	}
	
	
	public void agregarVehiculo(String placa, String marca, String color, String transmision, String categoria,
			String sede, String estado) throws Exception {
		inventario.agregarVehiculo(placa, marca, color, transmision, categoria, sede, estado);
	}
	
	public String consultarUbicacionVehiculo(String placa) throws Exception {
		return inventario.consultarUbicacionVehiculo(placa);
	}

	public ArrayList<Reserva> consultarHistorialVehiculo(String placa) throws Exception {
		return inventario.consultarHistorialVehiculo(placa);
	}
	
	//Creacion y modificación de reservas
	
	public boolean reservaExiste(String id) {
		return reservas.containsKey(id);
	}
	
	public String nuevoIdReservas() {
		String nuevoId = String.valueOf(this.contadorReservas);
		this.contadorReservas += 1;
		return nuevoId;
	}

	public void nuevaReserva(Reserva r) throws Exception {
		if (reservaExiste(r.getId())) {
			throw new Exception("El id de la reserva ya esta en uso.");
		}
		reservas.put(r.getId(), r);
	}

	public void crearReserva(String categoriaSolicitada, LocalDateTime fechaRecogida, String ubicacionRecogida,
			String ubicacionEntrega, Range<LocalDateTime> rangoEntrega, Cliente cliente,
			ArrayList<LicenciaDeConduccion> conductoresExtra) throws Exception {
		Tarifa tarifa = Inventario.tarifas.get(categoriaSolicitada);
		Reserva r = new Reserva(nuevoIdReservas(), categoriaSolicitada, fechaRecogida, ubicacionRecogida,
				ubicacionEntrega, rangoEntrega, cliente, null, conductoresExtra, tarifa);
		nuevaReserva(r);
	}
	
	public void setReserva( Map<String, Reserva> reser) {
		Map<String, Reserva> reservas= this.inventario.getReservaMap() ;
		reservas=reser;
	}

	public void modificarReserva(String idReserva, LocalDateTime fechaRecogida, Range<LocalDateTime> rangoEntrega)
			throws Exception {
		if (!reservaExiste(idReserva)) {
			throw new Exception("La reserva seleccionada no existe");
		}
		Reserva r = getReserva(idReserva);
		if (r.getVehiculo() != null) {
			throw new Exception("Esta reserva ya es un alquiler en curso, no se puede modificar");
		}
		r.setFechaRecogida(fechaRecogida);
		r.setRangoEntrega(rangoEntrega);
	}
	
	public ArrayList<Reserva> consultarHistorialVehiculoInventario(String placa) throws Exception {
		if (!inventario.vehiculoExiste(placa)) {
			throw new Exception("El vehiculo seleccionado no existe");
		}
		Vehiculo v = getVehiculo(placa);
		ArrayList<Reserva> historial = v.getHistorial();
		if (historial.isEmpty()) {
			throw new Exception("El vehiculo seleccionado no tiene historial");
		}
		return historial;
	}
	
	//Creacion Alquileres
	public void crearAlquiler(String categoriaSolicitada, LocalDateTime fechaRecogida, String ubicacionRecogida,
			String ubicacionEntrega, Range<LocalDateTime> rangoEntrega, Cliente cliente,
			ArrayList<LicenciaDeConduccion> conductoresExtra) throws Exception {

		Tarifa tarifa = Inventario.tarifas.get(categoriaSolicitada);
		Reserva r = new Reserva(nuevoIdReservas(), categoriaSolicitada, fechaRecogida, ubicacionRecogida,
				ubicacionEntrega, rangoEntrega, cliente, null, conductoresExtra, tarifa);
		nuevaReserva(r);
		formalizarAlquiler(r.getId());
	}

	public void formalizarAlquiler(String idReserva) throws Exception {
		if (!reservaExiste(idReserva)) {
			throw new Exception("La reserva seleccionada no existe");
		}
		Reserva r = getReserva(idReserva);
		// encontrar vehiculo disponible
		String categoria = r.getCategoriaSolicitada();
		Vehiculo vehiculoEncontrado = null;
		while (vehiculoEncontrado == null) {
			for (Vehiculo v : getVehiculos()) {
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
	
	
	//Inicio de sesion 
	public boolean sesionIniciada() {
		return usuarioActual != null;
	}

	public void establecerUsuario(Usuario u) {
		this.usuarioActual = u;
	}

	public void cerrarSesion() {
		this.usuarioActual = null;
	}
}
