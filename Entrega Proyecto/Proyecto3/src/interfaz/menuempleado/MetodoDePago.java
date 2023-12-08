package interfaz.menuempleado;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;

import clases.Inventario;
import interfaz.componentes.TCombo;
import interfaz.componentes.TText;

public class MetodoDePago extends JFrame {
  public MetodoDePago() {
    setDefaultCloseOperation(ABORT);
    setLayout(new GridBagLayout());
    // categorias
    add(new TText("Numero de tarjeta:", true),
        new GridBagConstraints(
            0, 0,
            1, 1,
            0, 0,
            10, 0,
            new Insets(0, 0, 0, 0),
            0, 0));
    TCombo comboCategoria = new TCombo((String[]) Inventario.prioridadCategoria.toArray(), false);
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