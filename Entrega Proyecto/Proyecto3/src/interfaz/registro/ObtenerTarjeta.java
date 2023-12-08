package interfaz.registro;

import java.awt.GridLayout;
import java.util.concurrent.Callable;

import javax.swing.JFrame;
import javax.swing.JLabel;

import clases.TarjetaDeCredito;
import interfaz.componentes.TButton;
import interfaz.componentes.TText;

public class ObtenerTarjeta extends JFrame {
  TText numero;
  TText fecha;
  TText cvv;
  TButton aceptar;

  public ObtenerTarjeta(Callable<Void> callback) {
    setLayout(new GridLayout(0, 1));
    numero = new TText("", true);
    fecha = new TText("", true);
    cvv = new TText("", true);
    aceptar = new TButton("aceptar", () -> {
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
