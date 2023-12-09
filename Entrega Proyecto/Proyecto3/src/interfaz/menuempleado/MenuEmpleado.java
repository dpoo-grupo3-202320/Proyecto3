package interfaz.menuempleado;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import clases.SistemaAlquiler;
import interfaz.Navegador;
import interfaz.componentes.TButton;

public class MenuEmpleado extends JPanel {
  private final Navegador nav;
  private final SistemaAlquiler sistemaAlquiler;

  public MenuEmpleado(Navegador nav, SistemaAlquiler sistemaAlquiler) {
    this.nav = nav;
    this.sistemaAlquiler = sistemaAlquiler;
    setLayout(new GridLayout(0, 1));
    // rojo
    setBackground(new Color(255, 0, 0));
    add(new TButton("Formalizar Alquiler", () -> {
      this.nav.agregarPagina(new FormalizarAlquiler(nav, this.sistemaAlquiler));
      return null;
    }));
    add(new TButton("Crear Alquiler", () -> {
      this.nav.agregarPagina(new CrearAlquiler(nav, this.sistemaAlquiler));
      return null;
    }));
    add(new TButton("Cerrar Sesion", () -> {
      this.nav.cerrarSesion();
      return null;
    }));
  }
}
