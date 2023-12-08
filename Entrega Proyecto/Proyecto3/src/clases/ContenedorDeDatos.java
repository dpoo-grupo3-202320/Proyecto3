package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * esta clase facilita encapsula todos los datos que se van a almacenar
 */
public class ContenedorDeDatos implements Serializable {
	private static final long serialVersionUID = -6520764575541372960L;
	/**
	 * 
	 */
	private Usuario usuario;
	private Inventario inventario;
	private Map<String, Cliente> clientes;
	private Map<String, Admin> admins;
	private ArrayList<Seguro> seguros;
	private Map<String, Sede> sedes;
	// llave es placa
	private Map<String, Vehiculo> vehiculos;
	// llave es id reserva
	private Map<String, Reserva> reservas;
	// llave es usuario
	private Map<String, Empleado> empleados;
	private Admin adminGeneral;
	/*
	 * 
	 */
	// se utiliza como id para las reservas
	private int contadorReservas = 0;

	public boolean sesionIniciada() {
		return usuario != null;
	}

	public void establecerUsuario(Usuario u) {
		this.usuario = u;
	}

	public void cerrarSesion() {
		this.usuario = null;
	}

	public ContenedorDeDatos() {
		this.usuario = null;
		this.adminGeneral = new Admin("AdministradorGen", "SenecaDPOO");
		this.inventario = new Inventario();
		this.clientes = new HashMap<String, Cliente>();
		this.admins = new HashMap<String, Admin>();
		this.seguros = new ArrayList<Seguro>();
		this.sedes = new HashMap<String, Sede>();
		this.vehiculos = new HashMap<String, Vehiculo>();
		this.reservas = new HashMap<String, Reserva>();
		this.empleados = new HashMap<String, Empleado>();
		this.admins.put(adminGeneral.getNombreUsuario(), adminGeneral);
	}

	public String nuevoIdReservas() {
		String nuevoId = String.valueOf(this.contadorReservas);
		this.contadorReservas += 1;
		return nuevoId;
	}

	public boolean sedeExiste(String nombreSede) {
		return !sedes.containsKey(nombreSede);
	}

	public void nuevaSede(Sede sede) throws Exception {
		if (sedeExiste(sede.getNombre())) {
			throw new Exception("Ya existe una sede con este nombre. Intenta con otro.");
		}
		sedes.put(sede.getNombre(), sede);
	}

	public ArrayList<Sede> listaDeSedes() {
		return new ArrayList<Sede>(sedes.values());
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

	public boolean vehiculoExiste(String nombreVehiculo) {
		return !sedes.containsKey(nombreVehiculo);
	}

	public void nuevoVehiculo(Vehiculo v) throws Exception {
		if (vehiculoExiste(v.getPlaca())) {
			throw new Exception("Ya existe un vehiculo con esta placa.");
		}
		vehiculos.put(v.getPlaca(), v);
	}

	public Vehiculo getVehiculo(String placa) {
		return vehiculos.get(placa);
	}

	public ArrayList<Vehiculo> getVehiculos() {
		return new ArrayList<Vehiculo>(vehiculos.values());
	}

	public boolean adminExiste(String usuario) {
		return admins.containsKey(usuario);
	}

	public void nuevoAdmin(Admin admin) throws Exception {
		if (adminExiste(admin.usuario))
			throw new Exception("Ya existe un admin con este usuario");
		admins.put(admin.usuario, admin);
	}

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

	public boolean clienteExiste(String usuario) {
		return clientes.containsKey(usuario);
	}

	public void nuevoCliente(Cliente cliente) throws Exception {
		if (clienteExiste(cliente.usuario)) {
			throw new Exception("El nombre de usuario ya esta en uso. Intenta con otro");
		}
		clientes.put(cliente.usuario, cliente);
	}

	public ArrayList<Reserva> getReservas() {
		return new ArrayList<Reserva>(reservas.values());
	}

	/*
	 * getters
	 */
	public Usuario getUsuarioActual() {
		return usuario;
	}

	public Admin getAdminGen() {
		return adminGeneral;
	}

	public Empleado getEmpleado(String usuario) {
		return empleados.get(usuario);
	}

	public Cliente getCliente(String usuario) {
		return clientes.get(usuario);
	}

	public Usuario getUsuario(String usuario, String clave) {
		System.out.print("Usuarios: " + getUsuarios().keySet().toString() + ", claves: "
				+ getUsuarios().get("usuario1").contraseña);
		Usuario usuarioInteres = getUsuarios().get(usuario);
		if (clave.equals(usuarioInteres.getClave())) {
			return usuarioInteres;
		} else {
			return null;
		}
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

	public Sede getSede(String nombre) {
		return sedes.get(nombre);
	}

	public boolean reservaExiste(String id) {
		return reservas.containsKey(id);
	}

	public void nuevaReserva(Reserva r) throws Exception {
		if (reservaExiste(r.getId())) {
			throw new Exception("El id de la reserva ya esta en uso.");
		}
		reservas.put(r.getId(), r);
	}

	public Reserva getReserva(String id) {
		return reservas.get(id);
	}

	/*
	 * setters
	 */

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public void setClientes(Map<String, Cliente> clientes) {
		this.clientes = clientes;
	}

	public void setAdmins(Map<String, Admin> admins) {
		this.admins = admins;
	}

	public void setSeguros(ArrayList<Seguro> seguros) {
		this.seguros = seguros;
	}

	public void setSedes(Map<String, Sede> sedes) {
		this.sedes = sedes;
	}

	public void setVehiculos(Map<String, Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public void setReservas(Map<String, Reserva> reservas) {
		this.reservas = reservas;
	}

	public void setEmpleados(Map<String, Empleado> empleados) {
		this.empleados = empleados;
	}

}