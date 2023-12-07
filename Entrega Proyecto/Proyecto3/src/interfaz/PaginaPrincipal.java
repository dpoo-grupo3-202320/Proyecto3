package interfaz;

import interfaz.menuadmin.*;
import interfaz.menucliente.*;
import interfaz.menuempleado.*;
import interfaz.registro.LandingPage;
import interfaz.registro.RegistrarCliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import clases.Admin;
import clases.Cliente;
import clases.ContenedorDeDatos;
import clases.Empleado;
import clases.SistemaAlquiler;
import clases.Usuario;

/**
 * El login se hace aca
 */
public class PaginaPrincipal extends JFrame {

	private final Navegador nav;
	private final SistemaAlquiler sistemaAlquiler;
	private final ContenedorDeDatos contenedorDatos;

	public PaginaPrincipal() throws FileNotFoundException, ClassNotFoundException, IOException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		// rosado
		setBackground(new Color(255, 192, 203));
		setOpacity(1f);
		sistemaAlquiler = new SistemaAlquiler();
		contenedorDatos = new ContenedorDeDatos();

		sistemaAlquiler.cargarDatos();
		nav = new Navegador(sistemaAlquiler, contenedorDatos);
		add(nav, BorderLayout.CENTER);
	}

	@Override
	public void dispose() {
		sistemaAlquiler.guardarDatos();
		super.dispose();
	}

	public static void main(String[] args) {
		try {
			PaginaPrincipal pp = new PaginaPrincipal();
			// pp.setExtendedState(JFrame.MAXIMIZED_BOTH);
			pp.setResizable(true);
			pp.setSize(new Dimension(500, 500));
			pp.setVisible(true);
			// pp.setUndecorated(true);
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Carga de datos fallida");
			e.printStackTrace();
		}
	}
}
