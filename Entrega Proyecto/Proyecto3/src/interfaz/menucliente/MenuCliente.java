package interfaz.menucliente;

import java.awt.GridLayout;
import javax.swing.JPanel;

import clases.SistemaAlquiler;
import interfaz.Navegador;
import interfaz.componentes.TButton;

public class MenuCliente extends JPanel{

<<<<<<< HEAD
	public MenuCliente(SistemaAlquiler SA, ContenedorDeDatos CD) {
=======
	/**
	 * 
	 */
	private static final long serialVersionUID = -3355054884650790372L;
	
	private final Navegador nav;
	private final SistemaAlquiler sistemaAlquiler;
>>>>>>> branch 'main' of https://github.com/dpoo-grupo3-202320/Proyecto3.git

	public MenuCliente(Navegador nav, SistemaAlquiler sistemaAlquiler) {
		this.nav = nav;
		this.sistemaAlquiler = sistemaAlquiler;
		
		setLayout(new GridLayout(0, 1));

<<<<<<< HEAD
		JPanel buttonPanel = new JPanel();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JButton inicio;
		JButton registro;
		JButton salir;
		add(inicio = new JButton("Iniciar Sesion"));
		add(registro = new JButton("Registrarse"));
		add(salir = new JButton("Salir"));

		inicio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//
				SesionCliente sesion = new SesionCliente(CD , SA);
			}
=======
		TButton crear = new TButton("Crear Reserva", () -> {
			nav.agregarPagina(new CrearReservaPanel(nav, this.sistemaAlquiler));
			return null;
>>>>>>> branch 'main' of https://github.com/dpoo-grupo3-202320/Proyecto3.git
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
