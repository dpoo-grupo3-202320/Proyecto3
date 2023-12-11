package programaCliente;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.SistemaAlquiler;
import clases.Usuario;
import interfaz.Navegador;
import interfaz.componentes.TButton;
import interfaz.componentes.TText;

public class IniciarSesionCliente extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4605148485347732702L;
	private final NavegadorApp nav;
	private final GestorDatos gestor;

	public IniciarSesionCliente(NavegadorApp nav2, GestorDatos gestor) {
		this.nav = nav2;
		this.gestor=gestor;
		setLayout(new GridLayout(0, 1, 25, 25));
		TButton devolver = new TButton("Atras", () -> {
			nav2.paginaAnterior();
			return null;
		});
		TText nombreUsuario = new TText("", true);
		TText clave = new TText("", true);
		add(devolver);
		add(new JLabel("Nombre Usuario"));
		add(nombreUsuario);
		add(new JLabel("Contraseña"));
		add(clave);
		add(new TButton("Aceptar", () -> {

			Usuario u = gestor.getUsuario(nombreUsuario.getText(), clave.getText());
			this.gestor.establecerUsuario(u);
			if (u == null) {
				this.nav.mensajeCliente("Clave o usuario incorrectos", 2000);
				// TODO: mostrar error, clave incorrecta
			} else {
				this.nav.paginaAnterior();
				this.nav.login();
				this.nav.mensajeCliente("Sesion iniciada exitosamente", 1500);
			}
			return null;
		}));
	}
}
