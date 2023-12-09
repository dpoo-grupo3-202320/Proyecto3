package interfaz.registro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.Cliente;
import clases.LicenciaDeConduccion;
import clases.SistemaAlquiler;
import clases.TarjetaDeCredito;
import interfaz.Navegador;
import interfaz.componentes.TButton;
import interfaz.componentes.MostrarYEscogerImagen;
import interfaz.componentes.TText;

public class RegistrarCliente extends JPanel {

	/**
		 * 
		 */
	private static final long serialVersionUID = -1203696558748837174L;
	private final Navegador nav;
	private final SistemaAlquiler sistemaAlquiler;

	private ObtenerTarjeta obtenerTarjeta;
	private TarjetaDeCredito tarjeta;
	private ObtenerLicencia obtenerLicencia;
	private LicenciaDeConduccion licenciaDeConduccion;

	private TButton seleccionarTarjetaDeCredito;
	private TButton seleccionarLicencia;

	private TText usuario;
	private TText clave;
	private TText nombre;
	private TText numero;
	private TText fechaNacimiento;
	private TText direccion;
	private TText nacionalidad;
//	private TText licencia = new TText("Licencia", true);
	private TText cedula;
	private MostrarYEscogerImagen escogerImagenCedula;
	private MostrarYEscogerImagen escogerImagenLicencia;

	public RegistrarCliente(Navegador nav, SistemaAlquiler sistemaAlquiler) {
		this.nav = nav;
		this.sistemaAlquiler = sistemaAlquiler;
		// componentes
		usuario = new TText("", true);
		clave = new TText("", true);
		nombre = new TText("", true);
		numero = new TText("", true);
		fechaNacimiento = new TText("", true);
		direccion = new TText("", true);
		nacionalidad = new TText("", true);
//		licencia = new TText("Licencia", true);
		cedula = new TText("", true);
		escogerImagenCedula = new MostrarYEscogerImagen("Foto Cedula");
		escogerImagenLicencia = new MostrarYEscogerImagen("Foto Licencia");
		//
		seleccionarTarjetaDeCredito = new TButton("Tarjeta de Credito", () -> {
			obtenerTarjeta = new ObtenerTarjeta(() -> {
				tarjeta = solicitarInfoTarjeta();
				if (tarjeta == null) {
					// TODO: mostrar error
				} else {
					cerrarObtenerTarjeta();
				}
				establecerColorBotonTarjeta();
				return null;
			});
			obtenerTarjeta.setSize(new Dimension(400, 400));
			obtenerTarjeta.setResizable(false);
			obtenerTarjeta.setAlwaysOnTop(true);
			obtenerTarjeta.setVisible(true);
			return null;
		});
		seleccionarLicencia = new TButton("Licencia", () -> {
			obtenerLicencia = new ObtenerLicencia(() -> {
				licenciaDeConduccion = solicitarInfoLicencia();
				if (licenciaDeConduccion == null) {
					// TODO: mostrar error
				} else {
					cerrarObtenerLicencia();
				}
				establecerColorBotonLicencia();
				return null;
			});
			obtenerLicencia.setSize(new Dimension(400, 400));
			obtenerLicencia.setResizable(false);
			obtenerLicencia.setAlwaysOnTop(true);
			obtenerLicencia.setVisible(true);
			return null;
		});
		// actualizar
		establecerColorBotonTarjeta();
		establecerColorBotonLicencia();
		// panel izquierdo
		JPanel pi = panelIzquierda();
		// panel centro
		JPanel pc = panelCentro();
		// panel derecha
		JPanel pd = panelDerecha();
		// organizar
		setLayout(new GridLayout(1, 3));
		add(pi);
		add(pc);
		add(pd);
	}

	TarjetaDeCredito solicitarInfoTarjeta() {
		return obtenerTarjeta.solicitarInfo();
	}

	LicenciaDeConduccion solicitarInfoLicencia() {
		return obtenerLicencia.solicitarInfo();
	}

	private void cerrarObtenerTarjeta() {
		obtenerTarjeta.dispatchEvent(new WindowEvent(obtenerTarjeta, WindowEvent.WINDOW_CLOSING));
	}

	private void cerrarObtenerLicencia() {
		obtenerLicencia.dispatchEvent(new WindowEvent(obtenerLicencia, WindowEvent.WINDOW_CLOSING));
	}

	/**
	 * Panel izquierda se encarga de datos escritos
	 * 
	 * @return
	 */
	private JPanel panelIzquierda() {
		JPanel pi = new JPanel();
		pi.setLayout(new GridLayout(0, 1));
		// usuario
		pi.add(new JLabel("Nombre de Usuario"));
		pi.add(usuario);
		// clave
		pi.add(new JLabel("Contraseña"));
		pi.add(clave);
		// nombre
		pi.add(new JLabel("Nombre y Apellido"));
		pi.add(nombre);
		// numero
		pi.add(new JLabel("Numero Telefonico"));
		pi.add(numero);
		// direccion
		pi.add(new JLabel("Direccion"));
		pi.add(direccion);
		// fecha nacimiento
		pi.add(new JLabel("Fecha de Nacimiento"));
		pi.add(fechaNacimiento);
		// nacionalidad
		pi.add(new JLabel("Nacionalidad"));
		pi.add(nacionalidad);
//		// licencia
//		pi.add(new JLabel("Licencia"));
//		pi.add(licencia);
		// Cedula
		pi.add(new JLabel("Cedula"));
		pi.add(cedula);
		return pi;
	}

	/**
	 * panel centro se encarga de obtener licencia y una foto
	 * 
	 * @return
	 */
	private JPanel panelCentro() {
		JPanel pd = new JPanel();
		pd.setLayout(new BorderLayout());
		pd.add(escogerImagenLicencia, BorderLayout.CENTER);
		JPanel box = new JPanel();
		box.setLayout(new GridLayout(0, 1));
		pd.add(box, BorderLayout.SOUTH);
		box.add(seleccionarLicencia);
		box.add(new TButton("Atras", () -> {
			nav.paginaAnterior();
			return null;
		}));
		return pd;
	}

	/**
	 * Panel Derecha se encarga de pedir foto cedula y tarjeta de credito
	 * 
	 * @return
	 */
	private JPanel panelDerecha() {
		JPanel pc = new JPanel();
		pc.setLayout(new BorderLayout());
		pc.add(escogerImagenCedula, BorderLayout.CENTER);
		JPanel box = new JPanel();
		box.setLayout(new GridLayout(0, 1));
		pc.add(box, BorderLayout.SOUTH);
		box.add(seleccionarTarjetaDeCredito);
		box.add(new TButton("Finalizar", () -> {
			if (tarjeta == null) {
				throw new Exception("Tarjeta de credito no ha sido seleccionada");
			} else if (licenciaDeConduccion == null) {
				throw new Exception("Licencia de conduccion no ha sido seleccionada");
			}
			// mover imagen cedula a ./Peristencia/ImagenesCedula
			File imagenCedula = escogerImagenCedula.getImage();
			String pathImagenCedula = "./Persistencia/ImagenesCedulas/" + usuario.getText() + "."
					+ (fileNameExtension(imagenCedula.getName()));
			Path outCedula = new File(pathImagenCedula).toPath();
			// mover imagen licencia a ./Peristencia/ImagenesCedula
			File imagenLicencia = escogerImagenCedula.getImage();
			String pathImagenLicencia = "./Persistencia/ImagenesLicencias/" + usuario.getText() + "."
					+ (fileNameExtension(imagenLicencia.getName()));
			Path outLicencia = new File(pathImagenLicencia).toPath();
			// registrar cliente
			Cliente nuevoCliente = sistemaAlquiler.registroCliente(usuario.getText(), clave.getText(), nombre.getText(),
					numero.getText(), direccion.getText(), fechaNacimiento.getText(), nacionalidad.getText(),
					pathImagenCedula, licenciaDeConduccion.getNumero(), licenciaDeConduccion.getPaisExpedicion(),
					licenciaDeConduccion.getFechaVencimiento(), pathImagenLicencia, tarjeta.getNumero(),
					tarjeta.getFechaVencimiento(), tarjeta.getCvv());
			// mover imagenes a almacenamiento
			try {
				Files.copy(imagenCedula.toPath(), outCedula, StandardCopyOption.REPLACE_EXISTING);
				Files.copy(imagenLicencia.toPath(), outLicencia, StandardCopyOption.REPLACE_EXISTING);
			} catch (Exception e) {
				System.out.println(
						"Cliente creado pero intento de subir imagenes fallido. Iniciando sesion de todas maneras.");
				e.printStackTrace();
			}
			sistemaAlquiler.establecerUsuario(nuevoCliente);
			nav.paginaAnterior();
			nav.login();
			return null;
		}));
		return pc;
	}

	private void establecerColorBotonTarjeta() {
		if (tarjeta == null) {
			// si imagen no escogida, rojo
			seleccionarTarjetaDeCredito.setBackground(new Color(237, 154, 154));
		} else {
			// si imagen escogida, verde
			seleccionarTarjetaDeCredito.setBackground(new Color(203, 230, 201));
		}
	}

	private void establecerColorBotonLicencia() {
		if (licenciaDeConduccion == null) {
			// si imagen no escogida, rojo
			seleccionarLicencia.setBackground(new Color(237, 154, 154));
		} else {
			// si imagen escogida, verde
			seleccionarLicencia.setBackground(new Color(203, 230, 201));
		}
	}

	/**
	 * 
	 * @param filename
	 * @return extension de archivo, .jpeg por defecto
	 */
	private String fileNameExtension(String filename) {
		String ext = Optional.ofNullable(filename).filter(f -> f.contains("."))
				.map(f -> f.substring(filename.lastIndexOf(".") + 1)).get();
		return ext == null ? ext : "jpeg";

	}
}