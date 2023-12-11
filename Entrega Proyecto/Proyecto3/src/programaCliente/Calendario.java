package programaCliente;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import clases.Vehiculo;

public class Calendario {
	
	HashMap<String,HashMap<LocalDateTime,ArrayList<Vehiculo>>> reservas = new HashMap<String,HashMap<LocalDateTime,ArrayList<Vehiculo>>>();
	
	public Calendario(ArrayList<Vehiculo> vehiculos) {
		
		for (Vehiculo i:vehiculos) {
			String sede=i.getUbicacion();
			if (sede != null) {
				if(!reservas.containsKey(sede)) {
					HashMap<LocalDateTime, ArrayList<Vehiculo>> mapa= new HashMap<LocalDateTime,ArrayList<Vehiculo>>();
					ArrayList<Vehiculo> disponibles= new ArrayList<Vehiculo>();
					disponibles.add(i);
					mapa.put(i.getFechaDisponible(), disponibles);
					reservas.put(sede, mapa);
				}else {
					HashMap<LocalDateTime,ArrayList<Vehiculo>> mapa=reservas.get(vehiculos);
					if(!mapa.containsKey(i.getFechaDisponible())) {
						ArrayList<Vehiculo> disponibles= new ArrayList<Vehiculo>();
						disponibles.add(i);
						mapa.put(i.getFechaDisponible(), disponibles);
					}else {
						ArrayList<Vehiculo> disponibles=mapa.get(i.getFechaDisponible());
						disponibles.add(i);
					}
				}
			}
		}
		
	}
	
	public ArrayList<Vehiculo> GetDisponibilidad(String sede, LocalDateTime fecha) throws Exception{
		
		ArrayList<Vehiculo> disponibles= null;
		
		if (reservas.containsKey(sede)) {
			HashMap<LocalDateTime,ArrayList<Vehiculo>> mapa= reservas.get(sede);
			if(mapa.containsKey(fecha)) {
				disponibles=mapa.get(fecha);
			}else {
				throw new Exception("No se encontró disponibilidad para la fecha");
			}
			
		}else {
			throw new Exception("No se encontró la sede");
		}
		
		return disponibles;
		
	}

}
