package consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import clases.*;

public class MenuRegistro {

	private SistemaAlquiler sistemaAlquiler;

	public MenuRegistro(SistemaAlquiler SA) {
		this.sistemaAlquiler = SA;
	}

	public void mostrarMenu() throws FileNotFoundException, IOException {

		System.out.println("Menú registro \n");

		System.out.println("1. Resgistrar Administrador");
		System.out.println("2. Resgistrar cliente");

		int opcionSeleccionada = Integer.parseInt(input("Por favor seleccione una opcion"));
		ejecutarOpcion(opcionSeleccionada);
	}

	public void ejecutarOpcion(int opcionSeleccionada) throws FileNotFoundException, IOException {
		try {
			if (opcionSeleccionada == 1) {
				String claveIN = input("Ingrese la clave del sistema");
				if (claveIN.equals("RegisterAdmin")) // Se define una contraseña arbitraria para que solo quienes la
				// conozcan puedan registrar administradores
				{
					ejecutarRegistroAdmin(sistemaAlquiler);
					sistemaAlquiler.guardarDatos();
				} else {
					System.out.println(
							"La clave del sistema no es valida. El proceso de registro de administrador ha sido cancelado");
				}
			} else if (opcionSeleccionada == 2) {
				ejecutarRegistroCliente(sistemaAlquiler);
				sistemaAlquiler.guardarDatos();
			} else {
				System.out.println("Por favor seleccione una opción valida.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Debe seleccionar uno de los numeros de las opciones.");
		}

	}

	public void ejecutarRegistroAdmin(SistemaAlquiler sistemaAlquiler) {
		String usuario = input("ingrese el Usuario");
		String clave = input("Ingrese la clave");
		ArrayList<Sede> listaSedes = sistemaAlquiler.getSedes();
		for (int i = 0; i < listaSedes.size(); i++) {
			String elemento = listaSedes.get(i).getNombre();
			System.out.println(i + " " + elemento + "\n");
		}
		int sedeInt = Integer.parseInt(input("Ingrese el numero de la sede a la cual se asignara el administrador"));
		Sede SedeOb = listaSedes.get(sedeInt);
		String sede = SedeOb.getNombre();
		try {
			sistemaAlquiler.registroAdmin(usuario, clave, sede);
			System.out.println("Admin registrado");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void ejecutarRegistroCliente(SistemaAlquiler sistemaAlquiler) {
		System.out.println("Para registrar un cliente nuevo por favor ingrese los siguientes datos: \n");
		String usuario = input("Usuario");
		String clave = input("Clave");
		String nombres = input("Nombre(s) completo(s)");
		String numeroTelefono = input("Numero de teléfono");
		String direccion = input("Dirección");
		String fechaNacimiento = input("Fecha de nacimiento");
		String nacionalidad = input("Nacionalidad");
		String imagenDocumentoIdentidad = input("Imagen documento identidad");
		String numeroLicencia = input("Numero de su licencia de conducción");
		String paisExpedicion = input("País de expedición");
		String fechaVencimientoLicencia = input("Fecha de vencimiento de su licencia");
		String imagen = input("Imagen licencia de conducción");
		String numeroTarjeta = input("Número tarjeta de crédito");
		String fechaVencimientoTarjeta = input("Fecha de vencimiento de su tarjeta de crédito");
		String cvv = input("cvv");

		try {
			sistemaAlquiler.registroCliente(usuario, clave, nombres, numeroTelefono, direccion, fechaNacimiento,
					nacionalidad, imagenDocumentoIdentidad, numeroLicencia, paisExpedicion, fechaVencimientoLicencia,
					imagen, numeroTarjeta, fechaVencimientoTarjeta, cvv);
			System.out.println("Cliente registrado");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public String input(String mensaje) {
		try {
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		} catch (IOException e) {
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

}
