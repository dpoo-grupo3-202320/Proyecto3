package interfaz.menuempleado;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;

import clases.Inventario;
import interfaz.componentes.Combo;
import interfaz.componentes.Texto;

public class MetodoDePago extends JFrame {
  public MetodoDePago() {
    setDefaultCloseOperation(ABORT);
    setLayout(new GridBagLayout());
    // categorias
    add(new Texto("Numero de tarjeta:", true),
        new GridBagConstraints(
            0, 0,
            1, 1,
            0, 0,
            10, 0,
            new Insets(0, 0, 0, 0),
            0, 0));
    Combo comboCategoria = new Combo((String[]) Inventario.prioridadCategoria.toArray(), false);
    add(comboCategoria,
        new GridBagConstraints(
            0, 1,
            1, 1,
            0, 0,
            10, 0,
            new Insets(0, 0, 0, 0),
            0, 0));
  }
}