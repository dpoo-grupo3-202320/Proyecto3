package programaCliente;

import java.awt.GridLayout;
import javax.swing.JPanel;

import clases.SistemaAlquiler;
import interfaz.Navegador;
import interfaz.componentes.TButton;

public class MenuClienteApp extends JPanel {

	private final NavegadorApp nav;
	private final GestorDatos gestor;

	public MenuClienteApp(NavegadorApp nav, GestorDatos gestor) {
		this.nav = nav;
		this.gestor=gestor;

		setLayout(new GridLayout(0, 1));

		TButton crear = new TButton("Crear Reserva", () -> {
			nav.agregarPagina(new CrearReservaApp(nav, this.gestor));
			return null;
		});
		add(crear);

		TButton modificar = new TButton("Modificar Reserva", () -> {
			nav.agregarPagina(new ModificarReservaApp(nav, this.gestor));
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