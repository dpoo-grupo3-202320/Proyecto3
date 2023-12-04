package clases;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

public class Sede implements Serializable {
	private static final long serialVersionUID = -7007424474250561109L;
	/**
	 * 
	 */
	private String nombre;
	private String ubicacion;
	private HorarioDeAtencion horariosDeAtencion;
	private ArrayList<Empleado> empleados;

	public Sede(String nombre, String ubicacion, HorarioDeAtencion horariosDeAtencion,
			ArrayList<Empleado> empleados) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.horariosDeAtencion = horariosDeAtencion;
		this.empleados = empleados;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public void setHorariosDeAtencion(HorarioDeAtencion horariosDeAtencion) {
		this.horariosDeAtencion = horariosDeAtencion;
	}

	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.empleados = empleados;
	}

	public void agregarEmpleado(Empleado e) {
		this.empleados.add(e);
	}

	public boolean removerEmpleado(Empleado e) {
		return this.empleados.remove(e);
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public HorarioDeAtencion getHorariosDeAtencion() {
		return this.horariosDeAtencion;
	}

	public ArrayList<Empleado> getEmpleados() {
		return this.empleados;
	}
}