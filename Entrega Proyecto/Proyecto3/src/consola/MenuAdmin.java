package consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import clases.Admin;
import clases.Reserva;
import clases.Sede;
import clases.SistemaAlquiler;

public class MenuAdmin {

	private SistemaAlquiler sistemaAlquiler;
	private Admin adminActual;
	private String sedeAdmin;

	public MenuAdmin(SistemaAlquiler SA, Admin AA) {
		this.sistemaAlquiler = SA;
		this.adminActual = AA;
		this.sedeAdmin = adminActual.getSede();

	}

	public void mostrarMenu() throws FileNotFoundException, IOException, ClassNotFoundException {

		if (sedeAdmin == null) {
			// Cuando la sede es null se tiene un administrador general
			mostrarMenuGeneral();

		} else {
			// cuando sede != null, es un administrador local
			mostrarMenuLocal();
		}
		System.out.println("5. Consultar ubicacion vehiculo");
		System.out.println("6. Consultar historial vehiculo");
		System.out.println("7. Cerrar sesión");

		int opcionSeleccionada = Integer.parseInt(input("Por favor seleccione una opcion"));
		ejecutarOpcion(opcionSeleccionada);
	}

	private void mostrarMenuGeneral() {
		System.out.println("Menú administrador general \n");

		System.out.println("1. Agregar Carro");
		System.out.println("2. Eliminar Carro");
		System.out.println("3. Crear Sede");
		System.out.println("4. Modificar Sede");

	}

	private void mostrarMenuLocal() {
		System.out.println("Menú administrador local \n");

		System.out.println("1. Registrar empleado");
		System.out.println("2. Eliminar empleado");
	}

	public void ejecutarOpcion(int opcionSeleccionada) throws FileNotFoundException, IOException, ClassNotFoundException {
		boolean continuar = true;
		while (continuar) {
			try {
				//Menu Admin General
				
				//Crear Vehiculo
				if (opcionSeleccionada == 1 && sedeAdmin == null) {
					System.out.println("Para agregar un vehiculo nuevo ingrese la siguiente informacion: ");

					String placa = input("Placa");
					String marca = input("Marca");
					String color = input("Color");
					String transmision = input("Transmision");
					System.out.println("1) pequeños");
					System.out.println("2) SUV");
					System.out.println("3) Lujo");
					System.out.println("4) Otros");
					int eleccion = Integer.parseInt(input("Categoria"));

					String categoria = "";
					if (eleccion == 1) {
						categoria = "Pequeños";
					} else if (eleccion == 2) {
						categoria = "SUV";
					} else if (eleccion == 3) {
						categoria = "Lujo";
					} else {
						categoria = "Otros";
					}

					ArrayList<Sede> listaSedes = sistemaAlquiler.getSedes();
					for (int i = 0; i < listaSedes.size(); i++) {
						String elemento = listaSedes.get(i).getNombre();
						System.out.println(i + " " + elemento + "\n");
					}
					int sedeInt = Integer.parseInt(input("Ingrese el numero de la sede a la cual se asignara el vehiculo"));
					Sede SedeOb = listaSedes.get(sedeInt);
					String sede = SedeOb.getNombre();
					String estado = "disponible";

					try {
						sistemaAlquiler.agregarVehiculo(placa, marca, color, transmision, categoria, sede, estado);
						System.out.println("Nuevo vehiculo creado");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}

					opcionSeleccionada = 0;
				}
				//Eliminar Vehiculo
				else if (opcionSeleccionada == 2 && sedeAdmin == null) {
					System.out.println("Para dar de baja un vehiculo nuevo ingrese la siguiente informacion: ");
					// ToDo call eliminar carro
					String placaElim = input("Placa del vehiculo que desea eliminar");
					boolean ans = sistemaAlquiler.eliminarVehiculo(placaElim);
					if(ans == true) {
						System.out.println("Vehiculo eliminado exitosamente");
					}
					else {
						System.out.println("No se pudo eliminar el vehiculo");
						
					}
					opcionSeleccionada = 0;
				} 
				//Agregar Sede
				else if (opcionSeleccionada == 3 && sedeAdmin == null) {
					System.out.println("Para crear una sede nueva ingrese la siguiente informacion: ");
					String nomSede = input("Nombre de la sede");
					String ubiSede = input("Ubicación de la sede");
					int hrsASede = Integer.parseInt(input("Horario de apertura de la sede"));
					int hrsCSede = Integer.parseInt(input("Horario de cierre de la sede"));

					try {
						sistemaAlquiler.crearSede(nomSede, ubiSede, hrsASede, hrsCSede);
						System.out.println("Nueva sede creada");
					} catch (Exception e) {
						if (e instanceof IllegalArgumentException) {
							System.out.println("Rango no válido: " + e.getMessage());
						} else {
							System.out.println(e.getMessage());
						}
					}

					opcionSeleccionada = 0;
				} 
				//Modificar Sede
				else if (opcionSeleccionada == 4 && sedeAdmin == null) {
					System.out.println("Para modificar una sede ingrese la siguiente informacion: ");

					// Mostrar Sedes existentes y pedir una
					ArrayList<Sede> listaSedes = sistemaAlquiler.getSedes();
					for (int i = 0; i < listaSedes.size(); i++) {
						String elemento = listaSedes.get(i).getNombre();
						System.out.println(i + " " + elemento + "\n");
					}
					int sedeInt = Integer.parseInt(input("Ingrese el numero de la sede a la cual se modificará"));
					Sede SedeOb = listaSedes.get(sedeInt); // Se omitió el manejo del error en el cual el usuario elige
					// un numero fuera del rango permitido.
					String nomSede = SedeOb.getNombre();

					//
					System.out.println("Parametro que desea modificar: ");
					System.out.println("1) Nombre");
					System.out.println("2) Horario de Atención");
					int parametro = Integer.parseInt(input("Opción"));
					try {
						if (parametro == 1) {
							String nuevoNom = input("Ingrese el nuevo nombre");
							try {
								sistemaAlquiler.modificarNombreSede(nuevoNom, nomSede);
								System.out.println("Nombre sede modificado");
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						} else if (parametro == 2) {
							int hrsASede = Integer.parseInt(input("Nuevo horario de apertura de la sede"));
							int hrsCSede = Integer.parseInt(input("Nuevo horario de cierre de la sede"));
							try {
								sistemaAlquiler.modificarHorarioSede(nomSede, hrsASede, hrsCSede);
								System.out.println("Horarios de atencion establecidos");
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						} else {
							System.out.println("Por favor seleccione una opción valida.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Debe seleccionar uno de los numeros de las opciones.");
					}

					opcionSeleccionada = 0;
					
				
				} 
				//Menu admin local
				
				// registrar empleado
				else if (opcionSeleccionada == 1 && sedeAdmin != null) {
					System.out.println("Para agregar un empleado ingrese la siguiente informacion: ");
					String usuario = input("Nombre usuario");
					String constraseña = input("Clave usuario");
					try {
						sistemaAlquiler.registroEmpleado(usuario, constraseña, sistemaAlquiler.getSede(sedeAdmin));
						System.out.println("Empleado registrado a la sede: " + sedeAdmin);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					opcionSeleccionada = 0;
				} 
				
				//eliminar Empleado
				else if (opcionSeleccionada == 2 && sedeAdmin != null) {
					// eliminar empleado
					System.out.println("Para remover un empleado ingrese la siguiente informacion: ");
					String usuario = input("Nombre usuario");
					try {
						sistemaAlquiler.eliminarEmpleado(usuario);
						System.out.println("Empleado eliminado");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					opcionSeleccionada = 0;
				} 
				//Opciones para ambos
				else if (opcionSeleccionada == 5) {
					// call consultar ubicacion vehiculo (pedir parametros y llamar funcion)
					System.out.println("Para consultar la ubicacion de un vehiculo ingrese la siguiente informacion: ");
					String placa = input("Placa");
					try {
						String ubicacion = sistemaAlquiler.consultarUbicacionVehiculo(placa);
						System.out.println("Ubicacion vehiculo: " + ubicacion);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					opcionSeleccionada = 0;
				} else if (opcionSeleccionada == 6) {
					// call consultar historial vehiculo (pedir parametros y llamar funcion)
					System.out.println("Para consultar el historial de un vehiculo ingrese la siguiente informacion: ");
					String placa = input("Placa");
					try {
						ArrayList<Reserva> historial = sistemaAlquiler.consultarHistorialVehiculo(placa);
						System.out.println("Historial vehiculo:");
						for (Reserva r : historial) {
							System.out.println(String.format("ID reserva: %d, fecha inicio: %s, fecha final: %s", r.getId(),
									r.getRangoEntrega().getLow().toLocalDate().toString(),
									r.getFechaRecogida().toLocalDate().toString()));
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					opcionSeleccionada = 0;
				}

				else if (opcionSeleccionada == 7) {
					// cerrar sesion
					System.out.println("Cerrando sesión ...");
					this.adminActual = null;
					this.sedeAdmin = null;
					continuar = false;
					sistemaAlquiler.guardarDatos();

				} else if (opcionSeleccionada == 0) {

					if (sedeAdmin == null) {
						// Cuando la sede es null se tiene un administrador general
						mostrarMenuGeneral();

					} else {
						// cuando sede != null, es un administrador local
						mostrarMenuLocal();
					}
					System.out.println("5. Consultar ubicacion vehiculo");
					System.out.println("6. Consultar historial vehiculo");
					System.out.println("7. Cerrar sesión");
					opcionSeleccionada = Integer.parseInt(input("\nPor favor seleccione una opcion"));
				} else {
					System.out.println("Por favor seleccione una opción valida.");
				}
			} catch (NumberFormatException e) {
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
