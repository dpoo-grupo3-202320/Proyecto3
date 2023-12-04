package interfaz.registro;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.SistemaAlquiler;
import clases.TarjetaDeCredito;
import interfaz.Navegador;
import interfaz.componentes.Boton;
import interfaz.componentes.MostrarYEscogerImagen;
import interfaz.componentes.Texto;
import interfaz.menuempleado.MetodoDePago;

public class RegistrarCliente extends JPanel {
  private final Navegador nav;
  private final SistemaAlquiler sistemaAlquiler;

  private ObtenerTarjeta obtenerTarjeta;
  private TarjetaDeCredito tarjeta;

  public RegistrarCliente(Navegador nav, SistemaAlquiler sistemaAlquiler) {
    this.nav = nav;
    this.sistemaAlquiler = sistemaAlquiler;
    // panel izquierdo
    JPanel pi = new JPanel();
    Texto usuario = new Texto("", true);
    Texto clave = new Texto("", true);
    Texto nombre = new Texto("Nombre y Apellido", true);
    Texto numero = new Texto("Numero Telefonico", true);
    Texto fechaNacimiento = new Texto("Numero Telefonico", true);
    Texto direccion = new Texto("Direccion", true);
    Texto nacionalidad = new Texto("Correo", true);
    Texto licencia = new Texto("Licencia", true);
    Texto cedula = new Texto("Cedula", true);
    pi.setLayout(new BoxLayout(pi, BoxLayout.Y_AXIS));
    // usuario
    pi.add(new JLabel("Nombre de Usuario"));
    pi.add(usuario);
    // clave
    pi.add(new JLabel("Contraseña"));
    pi.add(clave);
    // nombre
    pi.add(new JLabel("Nombre y Apellido"));
    pi.add(nombre);
    // numero
    pi.add(new JLabel("Numero Telefonico"));
    pi.add(numero);
    // direccion
    pi.add(new JLabel("Direccion"));
    pi.add(direccion);
    // fecha nacimiento
    pi.add(new JLabel("Fecha de Nacimiento"));
    pi.add(fechaNacimiento);
    // nacionalidad
    pi.add(new JLabel("Nacionalidad"));
    pi.add(nacionalidad);
    // licencia
    pi.add(new JLabel("Licencia"));
    pi.add(licencia);
    // Cedula
    pi.add(new JLabel("Cedula"));
    pi.add(cedula);
    // panel derecho
    JPanel pd = new JPanel();
    pd.setLayout(new BorderLayout());
    pd.add(new Boton("Atras", () -> {
      nav.paginaAnterior();
      return null;
    }), BorderLayout.NORTH);
    pd.add(new MostrarYEscogerImagen(), BorderLayout.CENTER);
    JPanel box = new JPanel();
    box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
    pd.add(box, BorderLayout.SOUTH);
    box.add(new Boton("Tarjeta de Credito", () -> {
      obtenerTarjeta = new ObtenerTarjeta(() -> {
        tarjeta = solicitarInfo();
        if (tarjeta == null) {
          // TODO: mostrar error
        } else {
          cerrarObtenerTarjeta();
        }
        return null;
      });
      obtenerTarjeta.setSize(new Dimension(400, 400));
      obtenerTarjeta.setResizable(false);
      obtenerTarjeta.setAlwaysOnTop(true);
      obtenerTarjeta.setVisible(true);
      return null;
    }));
    box.add(new Boton("Finalizar", () -> {
      sistemaAlquiler.registroCliente(
          usuario.getText(),
          clave.getText(),
          nombre.getText(),
          numero.getText(),
          direccion.getText(),
          fechaNacimiento.getText(),
          nacionalidad.getText(),
          "",
          "",
          "",
          "",
          "",
          tarjeta.getNumero(),
          tarjeta.getFechaVencimiento(),
          tarjeta.getCvv());
      return null;
    }));
    // organizar
    setLayout(new GridLayout(1, 2));
    add(pi);
    add(pd);
  }

  TarjetaDeCredito solicitarInfo() {
    return obtenerTarjeta.solicitarInfo();
  }

  private void cerrarObtenerTarjeta() {
    obtenerTarjeta.dispatchEvent(new WindowEvent(obtenerTarjeta, WindowEvent.WINDOW_CLOSING));
  }
}

class PanelIzquierda extends JPanel {
  PanelIzquierda() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    // nombre
    add(new Texto("Nombre y Apellido", false));
    add(new Texto("Nombre y Apellido", true));
    // numero
    add(new Texto("Numero Telefonico", false));
    add(new Texto("Numero Telefonico", true));
    // direccion
    add(new Texto("Direccion", false));
    add(new Texto("Direccion", true));
    // correo
    add(new Texto("Correo", false));
    add(new Texto("Correo", true));
    // licencia
    add(new Texto("Licencia", false));
    add(new Texto("Licencia", true));
    // Cedula
    add(new Texto("Cedula", false));
    add(new Texto("Cedula", true));
  }
}