package consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import clases.*;

public class MenuEmpleado {
	private SistemaAlquiler sistemaAlquiler;
	private Empleado empleadoActual;

	public MenuEmpleado(SistemaAlquiler SA, Empleado EA) {
		this.sistemaAlquiler = SA;
		this.empleadoActual = EA;

	}

	public void mostrarMenu() throws FileNotFoundException, IOException, ClassNotFoundException {

		System.out.println("1. Formalizar alquiler");
		System.out.println("2. Crear alquiler");
		System.out.println("3. Cerrar sesión");

		int opcionSeleccionada = Integer.parseInt(input("Por favor seleccione una opcion"));
		ejecutarOpcion(opcionSeleccionada);
	}

	public void ejecutarOpcion(int opcionSeleccionada) throws FileNotFoundException, IOException, ClassNotFoundException 
	{
		boolean continuar = true;
		while (continuar) 
		{
			try {
				if (opcionSeleccionada == 1) 
				{
					System.out.println("Para formalizar un alquiler ingrese la siguiente informacion: ");
					String idReserva = input("idReserva");
					try 
					{
						sistemaAlquiler.formalizarAlquiler(idReserva);
						System.out.println("Alquiler formalizado, se asigno un vehiculo");
					} catch (Exception e) 
					{
						System.out.println(e.getMessage());
					}
					opcionSeleccionada = 0;
				} 
				else if (opcionSeleccionada == 2) 
				{
					System.out.println("Para crear un alquiler ingrese la siguiente informacion: ");
					String categoriaSolicitada = input("Categoria solicitada");
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
					String recogida = input("Fecha recogida (yyyy-MM-dd HH:mm) (Formato 24 Hrs)");
					LocalDateTime fechaRecogida = LocalDateTime.parse(recogida, formatter);
					String ubicacionRecogida = input("Ubicacion recogida");
					String ubicacionEntrega = input("Ubicacion entrega");
					String entregaTemprano = input("fecha entrega por temprano (yyyy-MM-dd HH:mm) (Formato 24 Hrs)");
					String entregaTarde = input("fecha entrega por tarde (yyyy-MM-dd HH:mm) (Formato 24 Hrs)");
					LocalDateTime fechaEntregaTemprano = LocalDateTime.parse(entregaTemprano, formatter);
					LocalDateTime fechaEntregaTarde = LocalDateTime.parse(entregaTarde, formatter);
					Range<LocalDateTime> rangoEntrega = new Range<LocalDateTime>(fechaEntregaTemprano,
							fechaEntregaTarde);
					String usuario = input("Usuario cliente");
					String contraseñaCliente = input("Contraseña cliente");
					Cliente cliente = (Cliente) sistemaAlquiler.getUsuario(usuario, contraseñaCliente);
					while (cliente == null) {
						System.out.println("El cliente seleccionado no existe, volver a ingresar usuario y contraseña");
						usuario = input("Usuario cliente");
						contraseñaCliente = input("Contraseña cliente");
						cliente = (Cliente) sistemaAlquiler.getUsuario(usuario, contraseñaCliente);
					}
					ArrayList<LicenciaDeConduccion> conductoresExtra = new ArrayList<LicenciaDeConduccion>();
					boolean agregarMas = input("Agregar conductores extra? (si/no)").equals("si");
					while (agregarMas) {
						String usuarioConductorExtra = input("Usuario de conductor extra");
						String claveConductorExtra = input("Contraseña de conductor extra");
						Cliente conductorExtra = (Cliente) sistemaAlquiler.getUsuario(usuarioConductorExtra,claveConductorExtra);
						if (conductorExtra != null) {
							conductoresExtra.add(conductorExtra.getLicenciaDeConduccion());
						} else {
							System.out.println("El cliente solicitado no existe");
						}
						agregarMas = input("Agregar mas conductores extra? (si/no)").equals("si");
					}
					try {
						sistemaAlquiler.crearAlquiler(categoriaSolicitada, fechaRecogida, ubicacionRecogida,
								ubicacionEntrega, rangoEntrega, cliente, conductoresExtra);
						System.out.println("Alquiler creado");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					opcionSeleccionada = 0;
				} 
				else if (opcionSeleccionada == 3) 
				{
					// cerrar sesion
					System.out.println("Cerrando sesión ...");
					this.empleadoActual = null;
					continuar = false;
					sistemaAlquiler.guardarDatos();
				} 
				else 
				{
					System.out.println("Por favor seleccione una opción valida.");
				}
			} 
			catch (NumberFormatException e) 
			{
				System.out.println("Debe seleccionar uno de los numeros de las opciones.");
			}
		}
	}
	// Input -------------------------------------------------------------------

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