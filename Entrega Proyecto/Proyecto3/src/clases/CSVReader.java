package clases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class CSVReader {

	private SistemaAlquiler SA;
	private ContenedorDeDatos CD;

	public CSVReader(SistemaAlquiler SA, ContenedorDeDatos CD) throws FileNotFoundException, IOException {
		this.SA = SA;
		this.CD = CD;

		try {
			cargarVehiculos();
			cargarClientes();
			cargarSedes();
			cargarAdmins();
			cargarEmpleados();
		} catch (Exception e) {
			// FIXME: que hacer si falla?
			System.out.println("Carga de datos inicial fallida");
			e.printStackTrace();
		}
	}

	private void cargarEmpleados() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(
				"./Persistencia/Empleados.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] info = line.split(";");
				try {
					SA.registroEmpleado(info[0], info[1], info[2], SA.getSede(info[3]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void cargarAdmins() throws Exception {
		try (BufferedReader br = new BufferedReader(new FileReader(
				"./Persistencia/Administradores.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] info = line.split(";");

				try {
					SA.registroAdmin(info[0], info[1], info[2]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void cargarSedes() throws NumberFormatException, IllegalArgumentException, Exception {
		try (BufferedReader br = new BufferedReader(new FileReader(
				"./Persistencia/Sedes.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] info = line.split(";");

				String nombre = info[0];
				String ubicacion = info[1];
				String[] horario = info[2].split(",");

				try {
					SA.crearSede(nombre, ubicacion, Integer.parseInt(horario[0]), Integer.parseInt(horario[1]));
				} catch (Exception e) {
					e.printStackTrace();
				}
				String[] empleados = info[3].split("|");

				for (String empleado : empleados) {
					String[] data = empleado.split(",");
					try {
						SA.registroEmpleado(data[1], data[2], data[3], SA.getSede(nombre));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	private void cargarClientes() throws Exception {

		try (BufferedReader br = new BufferedReader(new FileReader(
				"./Persistencia/clientes.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
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
				TarjetaDeCredito tarjeta = new TarjetaDeCredito(info[12], info[13], "134");

				Cliente nuevo = new Cliente(usuario, contraseña, nombre, numero, direccion, fechaNacimiento, nacionalidad,
						documento, licencia, tarjeta);
				try {
					CD.nuevoCliente(nuevo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void cargarVehiculos() throws Exception {

		try (BufferedReader br = new BufferedReader(new FileReader(
				"./Persistencia/Vehiculo.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] info = line.split(";");

				String placa = info[0];
				String marca = info[1];
				String color = info[2];
				String transmision = info[3];
				String clasificacion = info[4];
				String sede = info[5];
				LocalDateTime fechadisponible = LocalDateTime.parse(info[6]);
				String comentarios = info[7];
				String estado = info[8];

				try {
					SA.agregarVehiculo(placa, marca, color, transmision, clasificacion, sede, estado);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
