package programaCliente;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import clases.SistemaAlquiler;
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
	
	public void nuevoCliente (Cliente cliente) {
		
		try {
			SA.nuevoCliente(cliente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
