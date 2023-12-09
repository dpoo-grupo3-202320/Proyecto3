package interfaz.menuempleado;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.Reserva;
import clases.SistemaAlquiler;
import interfaz.Navegador;
import interfaz.componentes.TButton;
import interfaz.componentes.TCombo;

public class FormalizarAlquiler extends JPanel {
  private final Navegador nav;
  private final SistemaAlquiler sistemaAlquiler;

  public FormalizarAlquiler(Navegador nav, SistemaAlquiler sistemaAlquiler) {
    this.nav = nav;
    this.sistemaAlquiler = sistemaAlquiler;

    ArrayList<Reserva> reservas = sistemaAlquiler.getReservas();
    ArrayList<Reserva> sinFormalizar = new ArrayList<Reserva>();
    ArrayList<String> sinFormalizarDesc = new ArrayList<String>();
    for (Reserva r : reservas) {
      if (r.getVehiculo() == null) {
        sinFormalizar.add(r);
        sinFormalizarDesc.add("cliente: " + r.getCliente().getNombres() + ", id: " + r.getId() + ", categoria: "
            + r.getCategoriaSolicitada() + ", ");
      }
    }
    String[] reservasString = Arrays.copyOf(sinFormalizarDesc.toArray(), sinFormalizarDesc.size(), String[].class);

    setLayout(new GridLayout(0, 1));

    add(new TButton("Atras", () -> {
      nav.paginaAnterior();
      return null;
    }));
    // reserva
    add(new JLabel("Seleccionar Reserva:"));
    TCombo reserva = new TCombo(reservasString, false);
    add(reserva);
    // reserva
    add(new TButton("Asignar Vehiculo", () -> {
      try {
        this.sistemaAlquiler.formalizarAlquiler(reservas.get(reserva.getSelectedIndex()).getId());
        this.nav.paginaAnterior();
        this.nav.mensajeCliente("Reserva formalizada exitosamente", 1500);
      } catch (Exception e) {
        e.printStackTrace();
        this.nav.mensajeCliente("No se pudo formalizar reserva, error: " + e, 3000);
      }
      return null;
    }));

  }
}
