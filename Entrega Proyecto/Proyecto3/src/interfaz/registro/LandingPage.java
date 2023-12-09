package interfaz.registro;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import clases.SistemaAlquiler;
import interfaz.Navegador;
import interfaz.componentes.TButton;

@SuppressWarnings("serial")
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
			this.nav.agregarPagina(new IniciarSesion(this.nav, this.sistemaAlquiler));
			return null;
		});
		TButton registrarCliente = new TButton("Registrar Cliente", () -> {
			this.nav.agregarPagina(new RegistrarCliente(this.nav, this.sistemaAlquiler));
			return null;
		});
		TButton registrarEmpleado = new TButton("Registrar Empleado", () -> {
			this.nav.agregarPagina(new RegistrarEmpleado(this.nav, this.sistemaAlquiler));
			return null;
		});
		// iniciarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(iniciarSesion);
		add(registrarCliente);
		add(registrarEmpleado);
	}
}
