package clases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CSVReader {
	
	String archivoAdmins;
	String archivoEmpleados;
	String archivoClientes;
	//archivo carga sedes
	String archivoSedes;
	//archivo carga vehiculos
	String archivoVehiculos;
	//archivo carga seguros
	String archivoSeguros;
	//archivo carga Reservas
	String archivoReservas;
	
	SistemaAlquiler sa; 

	
	public CSVReader(SistemaAlquiler sistemaAlquiler) throws FileNotFoundException, IOException 
	{
		//archivos carga usuarios
		archivoAdmins = "./Persistencia/Administradores.csv";
		archivoEmpleados = "./Persistencia/Empleados.csv";
		archivoClientes = "./Persistencia/clientes.csv";
		//archivo carga sedes
		archivoSedes = "./Persistencia/Sedes.csv";
		//archivo carga vehiculos
		archivoVehiculos = "./Persistencia/Vehiculo.csv";
		//archivo carga Seguros
		archivoSeguros = "./Persistencia/Seguros.csv";
		//archivos carga Reservas´
		archivoReservas = "./Persistencia/Reservas.csv";
		
		sa = sistemaAlquiler; 
		
	}
	
	public void cargarDatos()
	{
		try 
		{
			cargarVehiculos();
			cargarClientes();
			cargarSedes();
			cargarAdmins();
			cargarEmpleados();
			System.out.println("Datos Cargados");
		}
		catch (Exception e)
		{
			// FIXME: que hacer si falla?
			System.out.println("Carga de datos inicial fallida");
			e.printStackTrace();
		}
	}

	private void cargarEmpleados() throws FileNotFoundException, IOException
	{
		try (BufferedReader br = new BufferedReader(new FileReader(archivoEmpleados))) 
		{
			String line;
			while ((line = br.readLine()) != null) 
			{
				String[] info = line.split(";");
				try 
				{
					sa.registroEmpleado(info[0], info[1], sa.getSede(info[2]));
					Empleado elEmpleado = sa.getEmpleado(info[0]);
					sa.getSede(info[2]).agregarEmpleado(elEmpleado);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
				
	private void cargarAdmins() throws Exception 
	{
		try (BufferedReader br = new BufferedReader(new FileReader(archivoAdmins))) 
		{
			String line;
			while ((line = br.readLine()) != null) 
			{
				String[] info = line.split(";");
				try 
				{
					sa.registroAdmin(info[0], info[1], info[2]);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
	}

	private void cargarSedes() throws NumberFormatException, IllegalArgumentException, Exception 
	{
		try (BufferedReader br = new BufferedReader(new FileReader(archivoSedes))) {
			String line;
			while ((line = br.readLine()) != null) 
			{
				String[] info = line.split(";");

				String nombre = info[0];
				String ubicacion = info[1];
				String[] horario = info[2].split(",");

				try 
				{
					sa.crearSede(nombre, ubicacion, Integer.parseInt(horario[0]), Integer.parseInt(horario[1]));
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
				/*
				String[] empleados = info[3].split("\\|");

				for (String empleado : empleados) 
				{
					String[] data = empleado.split(",");
					try 
					{
						sa.registroEmpleado(data[0], data[1], sa.getSede(nombre));
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
				*/
			}
		}

	}

	private void cargarClientes() throws Exception 
	{
		try (BufferedReader br = new BufferedReader(new FileReader(archivoClientes))) {
			String line;
			while ((line = br.readLine()) != null) 
			{
				String[] info = line.split(";");

				String usuario = info[0];
				String contraseña = info[1];
				String nombre = info[2];
				String numero = info[3];
				String direccion = info[4];
				String fechaNacimiento = info[5];
				String nacionalidad = info[6];
				String documento = info[7];
				LicenciaDeConduccion licencia = new LicenciaDeConduccion(info[8], info[9], info[10], info[11]);
				TarjetaDeCredito tarjeta = new TarjetaDeCredito(info[12], info[13], info[14]);

				Cliente nuevo = new Cliente(usuario, contraseña, nombre, numero, direccion, fechaNacimiento, nacionalidad,
						documento, licencia, tarjeta);
				try 
				{
					sa.nuevoCliente(nuevo);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
	}

	private void cargarVehiculos() throws Exception 
	{
		try (BufferedReader br = new BufferedReader(new FileReader(archivoVehiculos))) {
			String line;
			while ((line = br.readLine()) != null) 
			{
				String[] info = line.split(";");

				String placa = info[0];
				String marca = info[1];
				String color = info[2];
				String transmision = info[3];
				String clasificacion = info[4];
				String ubicacion = info[5];
				LocalDateTime fechadisponible = LocalDateTime.parse(info[6]);
				String comentarios = info[7];
				String estado = info[8];

				try 
				{
					sa.agregarVehiculo(placa, marca, color, transmision, clasificacion, ubicacion, estado);
					Vehiculo elVehiculo = sa.getVehiculo(placa);
					elVehiculo.setFechaDisponible(fechadisponible);
					elVehiculo.setComentarios(comentarios);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	private void cargarSeguros() throws FileNotFoundException, IOException
	{
		try (BufferedReader br = new BufferedReader(new FileReader(archivoSeguros))) 
		{
			String line;
			while ((line = br.readLine()) != null) 
			{
				String[] info = line.split(";");
				try 
				{
					sa.registroEmpleado(info[0], info[1], sa.getSede(info[2]));
					Empleado elEmpleado = sa.getEmpleado(info[0]);
					sa.getSede(info[2]).agregarEmpleado(elEmpleado);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	
	private void cargarReservas() throws FileNotFoundException, IOException
	{
		try (BufferedReader br = new BufferedReader(new FileReader(archivoReservas))) 
		{
			String line;
			while ((line = br.readLine()) != null) 
			{
				String[] info = line.split(";");
				try 
				{
					//TODO Falta implementar logica de carga
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
	}

}
