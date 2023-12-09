package interfaz.registro;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.SistemaAlquiler;
import clases.Usuario;
import interfaz.Navegador;
import interfaz.componentes.TButton;
import interfaz.componentes.TText;

public class IniciarSesion extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4605148485347732702L;
	private final Navegador nav;
	private final SistemaAlquiler sistemaAlquiler;

	public IniciarSesion(Navegador nav, SistemaAlquiler sistemaAlquiler) {
		this.nav = nav;
		this.sistemaAlquiler = sistemaAlquiler;
		setLayout(new GridLayout(0, 1, 25, 25));
		TButton devolver = new TButton("Atras", () -> {
			nav.paginaAnterior();
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

			Usuario u = sistemaAlquiler.getUsuario(nombreUsuario.getText(), clave.getText());
			sistemaAlquiler.establecerUsuario(u);
			if (u == null) {
				// TODO: mostrar error, clave incorrecta
			} else {
				nav.paginaAnterior();
				nav.login();
			}
			return null;
		}));
	}
}
