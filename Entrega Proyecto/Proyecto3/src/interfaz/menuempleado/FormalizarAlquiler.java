package interfaz.menuempleado;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.Reserva;
import clases.SistemaAlquiler;
import interfaz.Navegador;
import interfaz.componentes.Boton;
import interfaz.componentes.Combo;

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

    add(new Boton("Atras", () -> {
      nav.paginaAnterior();
      return null;
    }));
    // reserva
    add(new JLabel("Seleccionar Reserva:"));
    Combo reserva = new Combo(reservasString, false);
    add(reserva);
    // reserva
    add(new Boton("Asignar Vehiculo", () -> {
      try {
        sistemaAlquiler.formalizarAlquiler(reservas.get(reserva.getSelectedIndex()).getId());
        System.out.println("Reserva Formalizada exitosamente");
        nav.paginaAnterior();
      } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
      }
      return null;
    }));

  }
}
