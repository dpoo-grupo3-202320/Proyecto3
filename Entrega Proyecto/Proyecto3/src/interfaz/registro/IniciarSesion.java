package interfaz.registro;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.SistemaAlquiler;
import clases.Usuario;
import interfaz.Navegador;
import interfaz.componentes.Boton;
import interfaz.componentes.Texto;

public class IniciarSesion extends JPanel {
  private final Navegador nav;
  private final SistemaAlquiler sistemaAlquiler;

  public IniciarSesion(Navegador nav, SistemaAlquiler sistemaAlquiler) {
    this.nav = nav;
    this.sistemaAlquiler = sistemaAlquiler;
    setLayout(new GridLayout(0, 1, 25, 25));
    Boton devolver = new Boton("Atras", () -> {
      nav.paginaAnterior();
      return null;
    });
    Texto nombreUsuario = new Texto("Nombre Usuario", true);
    Texto clave = new Texto("Contraseña", true);
    add(devolver);
    add(new JLabel("Nombre Usuario"));
    add(nombreUsuario);
    add(new JLabel("Contraseña"));
    add(clave);
    add(new Boton("Aceptar", () -> {

      Usuario u = sistemaAlquiler.getUsuario(nombreUsuario.getText(), clave.getText());
      sistemaAlquiler.establecerUsuario(u);
      if (u == null) {
        // TODO: mostrar error, clave incorrecta
      } else {
        nav.login();
      }
      return null;
    }));
  }
}
