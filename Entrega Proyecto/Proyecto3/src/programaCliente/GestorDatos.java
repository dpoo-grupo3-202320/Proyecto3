package programaCliente;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import clases.SistemaAlquiler;
import clases.Usuario;
import clases.Vehiculo;
import clases.CSVReader;
import clases.CSVWriter;
import clases.Cliente;
import clases.LicenciaDeConduccion;
import clases.Range;
import clases.Reserva;


public class GestorDatos {
	
	private SistemaAlquiler SA;
	private Calendario calendario;
	
	private static volatile GestorDatos instancia = null;

	
	private GestorDatos() throws FileNotFoundException, ClassNotFoundException, IOException {
		
		this.SA=new SistemaAlquiler();
		this.calendario= new Calendario(SA.getVehiculos());
			
	}
	
	public Cliente nuevoCliente (String user, String contraseña, String nombre,String numero,String direccion, String nacimiento,String nacionalidad,
			String cedula, String numerolin,String paisExped,String fechaVen, String licencia, String numTarjeta, String tarvence,
			String cvv) {
		
		try {
			Cliente cliente= SA.registroCliente(user, contraseña, nombre, numero, direccion, nacimiento, nacionalidad, cedula, numerolin, paisExped, fechaVen, licencia, numTarjeta, tarvence, cvv);
			return cliente;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();;
		}
		return null;
		
		
	}
	
	public void establecerUsuario(Cliente cliente) {
		this.SA.establecerUsuario(cliente);
	}
	
	public Reserva crearReserva(String categoriaSolicitada, LocalDateTime fechaRecogida, String ubicacionRecogida,
			String ubicacionEntrega, Range<LocalDateTime> rangoEntrega, Cliente cliente,
			ArrayList<LicenciaDeConduccion> conductoresExtra) throws Exception {
		Reserva nueva= this.SA.crearReserva(categoriaSolicitada, fechaRecogida, ubicacionRecogida, ubicacionEntrega, rangoEntrega, cliente, conductoresExtra);
		return nueva;
		
	}
	
	public void nuevaReserva(Reserva reserva) {
		
		try {
			SA.nuevaReserva(reserva);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Reserva modificarReserva(String idReserva, LocalDateTime fechaRecogida, Range<LocalDateTime> rangoEntrega) {
		try {
			Reserva modificar= this.SA.modificarReserva(idReserva, fechaRecogida, rangoEntrega);
			return modificar;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Reserva> getReservas(){
		return this.SA.getReservas();
	}
	
	public ArrayList<Vehiculo> getInventario(){
		return this.SA.getVehiculos();
	} 
	
	public Usuario getUsuario(String usuario,String contraseña) {
		return this.getUsuario(usuario, contraseña);
	}
	
	public void establecerUsuario(Usuario user) {
		this.SA.establecerUsuario(user);
	}
	
	public Usuario getUsuarioActual() {
		return this.SA.getUsuarioActual();
	}
	
	public void guardarDatos() {
		this.SA.guardarDatos();
	}
	
	public void cerrarSesion() {
		this.SA.cerrarSesion();
	}
	
	 public static GestorDatos obtenerInstancia() throws FileNotFoundException, ClassNotFoundException, IOException {
	        if (instancia == null) {
	            synchronized (GestorDatos.class) {
	                if (instancia == null) {
	                    instancia = new GestorDatos();
	                }
	            }
	        }
	        return instancia;
	    }
	
	

}
