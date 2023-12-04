package interfaz.registro;

import java.awt.GridLayout;
import java.util.concurrent.Callable;

import javax.swing.JFrame;
import javax.swing.JLabel;

import clases.TarjetaDeCredito;
import interfaz.componentes.Boton;
import interfaz.componentes.Texto;

public class ObtenerTarjeta extends JFrame {
  Texto numero;
  Texto fecha;
  Texto cvv;
  Boton aceptar;

  public ObtenerTarjeta(Callable<Void> callback) {
    setLayout(new GridLayout(0, 1));
    numero = new Texto("", true);
    fecha = new Texto("", true);
    cvv = new Texto("", true);
    aceptar = new Boton("aceptar", () -> {
      callback.call();
      return null;
    });
    add(new JLabel("Numero de Tarjeta:"));
    add(numero);
    add(new JLabel("Fecha de expiracion (MM/YY):"));
    add(fecha);
    add(new JLabel("CSV:"));
    add(cvv);
    add(aceptar);
  }

  public TarjetaDeCredito solicitarInfo() {
    return new TarjetaDeCredito(numero.getText(), fecha.getText(), cvv.getText());
  }
}
