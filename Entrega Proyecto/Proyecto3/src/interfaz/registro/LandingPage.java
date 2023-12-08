package interfaz.registro;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import clases.SistemaAlquiler;
import interfaz.Navegador;
import interfaz.componentes.TButton;

public class LandingPage extends JPanel {
	private final Navegador nav;
	private final SistemaAlquiler sistemaAlquiler;

	public LandingPage(Navegador nav, SistemaAlquiler sistemaAlquiler) {
		this.nav = nav;
		this.sistemaAlquiler = sistemaAlquiler;
		// morado
		setBackground(new Color(255, 255, 255));
		setOpaque(true);
		setLayout(new GridLayout(3, 1, 25, 25));
		TButton iniciarSesion = new TButton("Iniciar Sesion", () -> {
			nav.agregarPagina(new IniciarSesion(nav, sistemaAlquiler));
			return null;
		});
		TButton registrarCliente = new TButton("Registrar Cliente", () -> {
			nav.agregarPagina(new RegistrarCliente(nav, sistemaAlquiler));
			return null;
		});
		TButton registrarEmpleado = new TButton("Registrar Empleado", () -> {
			nav.agregarPagina(new RegistrarEmpleado(nav, sistemaAlquiler));
			return null;
		});
		// iniciarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(iniciarSesion);
		add(registrarCliente);
		add(registrarEmpleado);
	}
}
