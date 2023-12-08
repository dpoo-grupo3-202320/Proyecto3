package clases;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

public class CSVWriter {
	
			//archivos carga usuarios
	static String archivoAdmins = "./Persistencia/Administradores.csv";
	static String archivoEmpleados = "./Persistencia/Empleados.csv";
	static String archivoClientes = "./Persistencia/clientes.csv";
			//archivo carga sedes
	static String archivoSedes = "./Persistencia/Sedes.csv";
			//archivo carga vehiculos
	static String archivoVehiculos = "./Persistencia/Vehiculo.csv";		 
	
	private static void guardarEmpleados(Map<String, Empleado> empleados) {
        

        try (PrintWriter writer = new PrintWriter(new FileWriter(archivoEmpleados))) {
            for (Map.Entry<String, Empleado> entry : empleados.entrySet()) {
                Empleado empleado = entry.getValue();
                String linea = String.format("%s;%s;%s", empleado.getUsuario(), empleado.getClave(), empleado.getSede().getNombre());
                writer.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private static void guardarClientes(Map<String, Cliente> clientes) {
        String rutaArchivo = archivoClientes;

        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            for (Map.Entry<String, Cliente> entry : clientes.entrySet()) {
                Cliente cliente = entry.getValue();
                String linea = String.format(
                        "%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s",
                        cliente.getUsuario(), cliente.getClave(), cliente.getNombres(),
                        cliente.getNumeroTelefono(), cliente.getDireccion(), cliente.getFechaNacimiento(),
                        cliente.getNacionalidad(), cliente.getImagenDocumentoIdentidad(), cliente.getLicenciaDeConduccion().getNumero(),
                        cliente.getLicenciaDeConduccion().getPaisExpedicion(), cliente.getLicenciaDeConduccion().getFechaVencimiento(),
                        cliente.getLicenciaDeConduccion().getImagen(), cliente.getTarjetaDeCredito().getNumero(),
                        cliente.getTarjetaDeCredito().getFechaVencimiento(),cliente.getTarjetaDeCredito().getCvv()
                );
                writer.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	private static void guardarAdmins(Map<String, Admin> admins) {
		
        String rutaArchivo = archivoAdmins;

        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            for (Map.Entry<String, Admin> entry : admins.entrySet()) {
                Admin admin = entry.getValue();
                if (admin.getNombreUsuario() != "AdministradorGen")
                {
	                String linea = String.format(
	                        "%s;%s;%s",
	                        admin.getUsuario(), admin.getClave(), admin.getSede()
	                );
	                writer.println(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	private static void guardarSedes(Map<String, Sede> sedes) {
	        String rutaArchivo = archivoSedes;

	        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
	            for (Map.Entry<String, Sede> entry : sedes.entrySet()) {
	                Sede sede = entry.getValue();
	                String horario = String.format("%s,%s", sede.getHorariosDeAtencion().getHoras().getLow().toString(), sede.getHorariosDeAtencion().getHoras().getHigh().toString());

	                StringBuilder empleadosStr = new StringBuilder();
	                for (Empleado empleado : sede.getEmpleados()) {
	                    empleadosStr.append(String.format("%s,%s|", empleado.getUsuario(), empleado.getClave()));
	                }

	                String linea = String.format(
	                        "%s;%s;%s;%s",
	                        sede.getNombre(), sede.getUbicacion(), horario, empleadosStr.toString().replaceAll("\\|$", "")
	                );
	                writer.println(linea);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }

	private static void guardarVehiculos(ArrayList<Vehiculo> vehiculos) {
        String rutaArchivo = archivoVehiculos;

        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            for (Vehiculo vehiculo : vehiculos) {
                String fechaFormateada = vehiculo.getFechaDisponible().toString();
                String linea = String.format(
                        "%s;%s;%s;%s;%s;%s;%s;%s;%s",
                        vehiculo.getPlaca(), vehiculo.getMarca(), vehiculo.getColor(),
                        vehiculo.getTransmision(), vehiculo.getCategoria(),vehiculo.getUbicacion(), fechaFormateada,
                        vehiculo.getComentarios(), vehiculo.getEstado()
                );
                writer.println(linea);
        		//System.out.println("Datos Guardados");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static void guardarDatos(
			Map<String, Empleado> empleados,
            Map<String, Cliente> clientes,
            Map<String, Admin> admins,
            Map<String, Sede> sedes,
            ArrayList<Vehiculo> vehiculos
    ) {
        guardarClientes(clientes);
        guardarAdmins(admins);
        guardarSedes(sedes);
        guardarVehiculos(vehiculos);
        guardarEmpleados(empleados);
    }
}
