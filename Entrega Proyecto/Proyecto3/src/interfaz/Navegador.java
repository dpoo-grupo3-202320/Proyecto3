package interfaz;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import clases.Admin;
import clases.Cliente;
import clases.Empleado;
import clases.SistemaAlquiler;
import clases.Usuario;
import interfaz.menucliente.MenuCliente;
import interfaz.menuadmin.MenuAdmin;
import interfaz.menuempleado.MenuEmpleado;
import interfaz.registro.LandingPage;
import interfaz.componentes.TMensajeUsuario;;

/**
 * se encarga de agregar y quitar paginas
 */
@SuppressWarnings("serial")
public class Navegador extends JPanel {
	private final SistemaAlquiler sistemaAlquiler;

	private ArrayList<JPanel> paneles;
	private CardLayout card;

	public Navegador(SistemaAlquiler sistemaAlquiler) {

		this.sistemaAlquiler = sistemaAlquiler;
		paneles = new ArrayList<JPanel>();

		// amarillo
		setBackground(new Color(255, 255, 0));
		setOpaque(true);
		card = new CardLayout();
		setLayout(card);
		setSize(new Dimension(25, 25));

		add(new LandingPage(this, sistemaAlquiler));
		card.last(this);
		// login();
		// if (modoPruebaActivado) {
		// // TODO: aca agregar paginas que se van a probar
		// agregarPagina(new MenuEmpleado(this, sistemaAlquiler, null));
		// }
	}

	public void agregarPagina(JPanel panel) {
		paneles.add(panel);
		add(panel);
		card.last(this);
	}

	public void paginaAnterior() {
		if (paneles.size() <= 0) {
			System.out.println("no hay paginas anteriores");
			return;
		}
		remove(paneles.remove(paneles.size() - 1));
		card.last(this);
	}

	public void mensajeCliente(String mensaje, int millis) {
		new TMensajeUsuario(this, mensaje, millis);
	}

	public void cerrarSesion() {
		paginaAnterior();
		sistemaAlquiler.guardarDatos();
		sistemaAlquiler.cerrarSesion();
	}

	public void login() {
		Usuario usuario = sistemaAlquiler.getUsuarioActual();
		if (usuario != null) {
			System.out.println("sesion iniciada abriendo menu correspondiente");
			if (usuario instanceof Admin) {
				agregarPagina(new MenuAdmin(this, sistemaAlquiler, (Admin) usuario));
			} else if (usuario instanceof Empleado) {
				agregarPagina(new MenuEmpleado(this, sistemaAlquiler));
			} else if (usuario instanceof Cliente) {
				agregarPagina(new MenuCliente(this, sistemaAlquiler));
			}
		} else {
			System.out.println("sesion no iniciada");
		}
	}
}