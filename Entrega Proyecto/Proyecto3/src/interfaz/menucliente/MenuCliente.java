package interfaz.menucliente;

import java.awt.GridLayout;
import javax.swing.JPanel;

import clases.SistemaAlquiler;
import interfaz.Navegador;
import interfaz.componentes.TButton;

public class MenuCliente extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3355054884650790372L;
	
	private final Navegador nav;
	private final SistemaAlquiler sistemaAlquiler;

	public MenuCliente(Navegador nav, SistemaAlquiler sistemaAlquiler) {
		this.nav = nav;
		this.sistemaAlquiler = sistemaAlquiler;
		
		setLayout(new GridLayout(0, 1));

		TButton crear = new TButton("Crear Reserva", () -> {
			nav.agregarPagina(new CrearReservaPanel(nav, this.sistemaAlquiler));
			return null;
		});
		add(crear);

		TButton modificar = new TButton("Modificar Reserva", () -> {
			nav.agregarPagina(new ModificarReservaPanel(nav, this.sistemaAlquiler));
			return null;
		});
		add(modificar);

		TButton salir = new TButton("Cerrar Sesion", () -> {
			this.nav.paginaAnterior();
			return null;
		});
		add(salir);
	}
}
