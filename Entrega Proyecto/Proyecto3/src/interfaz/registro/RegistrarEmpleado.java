package interfaz.registro;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.Inventario;
import clases.SistemaAlquiler;
import clases.Usuario;
import interfaz.Navegador;
import interfaz.componentes.Boton;
import interfaz.componentes.Combo;
import interfaz.componentes.Texto;

public class RegistrarEmpleado extends JPanel {
  private final Navegador nav;
  private final SistemaAlquiler sistemaAlquiler;

  public RegistrarEmpleado(Navegador nav, SistemaAlquiler sistemaAlquiler) {
    this.nav = nav;
    this.sistemaAlquiler = sistemaAlquiler;
    setLayout(new GridLayout(0, 1));
    Boton devolver = new Boton("Atras", () -> {
      nav.paginaAnterior();
      return null;
    });
    Texto usuario = new Texto("", true);
    Texto clave = new Texto("", true);
    Combo sede = new Combo(Inventario.sedes, true);
    Texto rol = new Texto("rol", true);
    Boton aceptar = new Boton("Aceptar", () -> {
      try {
        Usuario u = sistemaAlquiler.registroEmpleado(usuario.getText(), clave.getText(), rol.getText(),
            sistemaAlquiler.getSede(sede.getSelectedItem()));
        sistemaAlquiler.establecerUsuario(u);
        nav.paginaAnterior();
        nav.login();
      } catch (Exception e) {
        // TODO: mostrar mensaje de error
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
