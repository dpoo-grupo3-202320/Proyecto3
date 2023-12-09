package interfaz.registro;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.Inventario;
import clases.SistemaAlquiler;
import clases.Usuario;
import interfaz.Navegador;
import interfaz.componentes.TButton;
import interfaz.componentes.TCombo;
import interfaz.componentes.TText;

@SuppressWarnings("serial")
public class RegistrarEmpleado extends JPanel {
	private final Navegador nav;
	private final SistemaAlquiler sistemaAlquiler;

	public RegistrarEmpleado(Navegador nav, SistemaAlquiler sistemaAlquiler) {
		this.nav = nav;
		this.sistemaAlquiler = sistemaAlquiler;
		setLayout(new GridLayout(0, 1));
		TButton devolver = new TButton("Atras", () -> {
			nav.paginaAnterior();
			return null;
		});
		TText usuario = new TText("", true);
		TText clave = new TText("", true);
		TCombo sede = new TCombo(Inventario.sedes, true);
		TText rol = new TText("rol", true);
		TButton aceptar = new TButton("Aceptar", () -> {
			try {
				Usuario u = this.sistemaAlquiler.registroEmpleado(usuario.getText(), clave.getText(), // rol.getText(),
						this.sistemaAlquiler.getSede(sede.getSelectedItem()));
				this.sistemaAlquiler.establecerUsuario(u);
				this.nav.paginaAnterior();
				this.nav.login();
				this.nav.mensajeCliente("Cuenta creada exitosamente, iniciando sesion.", 1500);
			} catch (Exception e) {
				// TODO: mostrar mensaje de error
				e.printStackTrace();
				this.nav.mensajeCliente("No se pudo crear la cuenta, error: " + e, 3000);
			}
			return null;
		});
		add(devolver);
		add(new JLabel("Usuario:"));
		add(usuario);
		add(new JLabel("Contraseña:"));
		add(clave);
		add(new JLabel("Sede:"));
		add(sede);
		add(new JLabel("Rol:"));
		add(rol);
		add(aceptar);
	}
}
