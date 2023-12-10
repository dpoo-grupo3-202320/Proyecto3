package programaCliente;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import clases.SistemaAlquiler;
import clases.Usuario;
import clases.Vehiculo;
import clases.CSVReader;
import clases.CSVWriter;
import clases.Cliente;
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
			String cedula, String licencia, String numerolin,String paisExped,String fechaVen, String license, String numTarjeta, String tarvence,
			String cvv) {
		
		try {
			Cliente cliente= SA.registroCliente(license, fechaVen, nombre, numero, direccion, nacimiento, nacionalidad, tarvence, numerolin, paisExped, nacimiento, licencia, numTarjeta, fechaVen, cvv);
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
	
	public void nuevaReserva(Reserva reserva) {
		
		try {
			SA.nuevaReserva(reserva);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
