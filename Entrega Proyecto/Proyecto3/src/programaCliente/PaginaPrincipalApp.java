package programaCliente;

import interfaz.Navegador;
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
import java.util.ArrayList;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

import clases.Admin;
import clases.Cliente;
import clases.ContenedorDeDatos;
import clases.Empleado;
import clases.SistemaAlquiler;
import clases.Usuario;
import clases.Vehiculo;

/**
 * El login se hace aca
 */
public class PaginaPrincipalApp extends JFrame {

	private final NavegadorApp nav;
	private final GestorDatos gestor;
	
	public PaginaPrincipalApp() throws FileNotFoundException, ClassNotFoundException, IOException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		// rosado
		setBackground(new Color(255, 192, 203));
		setOpacity(1f);
		setSize(300, 200);
		
		
		
		
		this.gestor= GestorDatos.obtenerInstancia();
		nav = new NavegadorApp (gestor);
		add(nav, BorderLayout.CENTER);
	}

	@Override
	public void dispose() {
		gestor.guardarDatos();
		super.dispose();
	}
	
	
	public static void main(String[] args) {
		try {
			PaginaPrincipalApp pp = new PaginaPrincipalApp();
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
