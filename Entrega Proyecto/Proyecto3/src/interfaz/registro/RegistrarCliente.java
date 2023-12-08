package interfaz.registro;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

	public RegistrarCliente(Navegador nav, SistemaAlquiler sistemaAlquiler) {
		this.nav = nav;
		this.sistemaAlquiler = sistemaAlquiler;
		// panel izquierdo
		JPanel pi = new JPanel();
		TText usuario = new TText("", true);
		TText clave = new TText("", true);
		TText nombre = new TText("Nombre y Apellido", true);
		TText numero = new TText("Numero Telefonico", true);
		TText fechaNacimiento = new TText("Numero Telefonico", true);
		TText direccion = new TText("Direccion", true);
		TText nacionalidad = new TText("Correo", true);
		TText licencia = new TText("Licencia", true);
		TText cedula = new TText("Cedula", true);
		pi.setLayout(new BoxLayout(pi, BoxLayout.Y_AXIS));
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
		// licencia
		pi.add(new JLabel("Licencia"));
		pi.add(licencia);
		// Cedula
		pi.add(new JLabel("Cedula"));
		pi.add(cedula);
		// panel derecho
		JPanel pd = new JPanel();
		pd.setLayout(new BorderLayout());
		pd.add(new TButton("Atras", () -> {
			nav.paginaAnterior();
			return null;
		}), BorderLayout.NORTH);
		pd.add(new MostrarYEscogerImagen("Escoger Cedula"), BorderLayout.CENTER);
		JPanel box = new JPanel();
		box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
		pd.add(box, BorderLayout.SOUTH);
		box.add(new TButton("Tarjeta de Credito", () -> {
			obtenerTarjeta = new ObtenerTarjeta(() -> {
				tarjeta = solicitarInfo();
				if (tarjeta == null) {
					// TODO: mostrar error
				} else {
					cerrarObtenerTarjeta();
				}
				return null;
			});
			obtenerTarjeta.setSize(new Dimension(400, 400));
			obtenerTarjeta.setResizable(false);
			obtenerTarjeta.setAlwaysOnTop(true);
			obtenerTarjeta.setVisible(true);
			return null;
		}));
		box.add(new TButton("Finalizar", () -> {
			sistemaAlquiler.registroCliente(usuario.getText(), clave.getText(), nombre.getText(), numero.getText(),
					direccion.getText(), fechaNacimiento.getText(), nacionalidad.getText(), "", "", "", "", "",
					tarjeta.getNumero(), tarjeta.getFechaVencimiento(), tarjeta.getCvv());
			return null;
		}));
		// organizar
		setLayout(new GridLayout(1, 2));
		add(pi);
		add(pd);
	}

	TarjetaDeCredito solicitarInfo() {
		return obtenerTarjeta.solicitarInfo();
	}

	private void cerrarObtenerTarjeta() {
		obtenerTarjeta.dispatchEvent(new WindowEvent(obtenerTarjeta, WindowEvent.WINDOW_CLOSING));
	}
}

class PanelIzquierda extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	PanelIzquierda() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// nombre
		add(new TText("Nombre y Apellido", false));
		add(new TText("Nombre y Apellido", true));
		// numero
		add(new TText("Numero Telefonico", false));
		add(new TText("Numero Telefonico", true));
		// direccion
		add(new TText("Direccion", false));
		add(new TText("Direccion", true));
		// correo
		add(new TText("Correo", false));
		add(new TText("Correo", true));
		// licencia
		add(new TText("Licencia", false));
		add(new TText("Licencia", true));
		// Cedula
		add(new TText("Cedula", false));
		add(new TText("Cedula", true));
	}
}