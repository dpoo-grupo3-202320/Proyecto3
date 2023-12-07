package interfaz.menuempleado;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import clases.Empleado;
import clases.SistemaAlquiler;
import interfaz.Navegador;
import interfaz.componentes.TButton;

public class MenuEmpleado extends JPanel {
  private final Navegador nav;
  private final SistemaAlquiler sistemaAlquiler;
  private final Empleado empleado;

  public MenuEmpleado(Navegador nav, SistemaAlquiler sistemaAlquiler, Empleado empleado) {
    this.nav = nav;
    this.empleado = empleado;
    this.sistemaAlquiler = sistemaAlquiler;
    setLayout(new GridLayout(0, 1));
    // rojo
    setBackground(new Color(255, 0, 0));
    add(new TButton("Formalizar Alquiler", () -> {
      nav.agregarPagina(new FormalizarAlquiler(nav, sistemaAlquiler));
      return null;
    }));
    add(new TButton("Crear Alquiler", () -> {
      nav.agregarPagina(new CrearAlquiler(nav, sistemaAlquiler));
      return null;
    }));
    add(new TButton("Cerrar Sesion", () -> {
      nav.cerrarSesion();
      return null;
    }));
  }
}
