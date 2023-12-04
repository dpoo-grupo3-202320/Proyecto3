package interfaz.menuempleado;

import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.Inventario;
import clases.SistemaAlquiler;
import clases.TarjetaDeCredito;
import interfaz.Navegador;
import interfaz.componentes.Boton;
import interfaz.componentes.Combo;
import interfaz.componentes.Texto;
import interfaz.registro.ObtenerTarjeta;

public class CrearAlquiler extends JPanel {

        private final Navegador nav;
        private final SistemaAlquiler sistemaAlquiler;

        private ObtenerTarjeta obtenerTarjeta;
        private TarjetaDeCredito tarjeta;

        public CrearAlquiler(
                        final Navegador navegador,
                        SistemaAlquiler sistemaAlquiler) {
                this.nav = navegador;
                this.sistemaAlquiler = sistemaAlquiler;

                setLayout(new GridLayout(0, 2));
                // categorias
                add(new JLabel("Categoria:"));
                Combo comboCategoria = new Combo(Inventario.categorias, false);
                add(comboCategoria);
                // sede de recogida
                add(new JLabel("Sede de recogida:"));
                Combo comboRecogida = new Combo((String[]) Inventario.sedes, false);
                add(comboRecogida);
                // sede de entrega
                add(new JLabel("Sede de entrega:"));
                Combo comboEntrega = new Combo((String[]) Inventario.sedes, false);
                add(comboEntrega);
                // sede de entrega
                add(new JLabel("Tarjeta de Credito:"));
                add(new Boton("Seleccionar", () -> {
                        obtenerTarjeta = new ObtenerTarjeta(() -> {
                                tarjeta = solicitarInfo();
                                if (tarjeta == null) {
                                        // TODO: mostrar error
                                } else {
                                        cerrarObtenerTarjeta();
                                }
                                return null;
                        });
                        return null;
                }));
                // fecha recogida
                add(new JLabel("Fecha de Recogida:"));
                Texto fechaRecogida = new Texto("", true);
                add(fechaRecogida);
                // fecha entrega
                add(new JLabel("Fecha de Entrega:"));
                Texto fechaEntrega = new Texto("", true);
                add(fechaEntrega);
                // usuario
                add(new JLabel("Cliente (ID):"));
                Texto idCliente = new Texto("", true);
                add(idCliente);
                // seguro
                add(new JLabel("Seguro:"));
                Combo seguro = new Combo(Inventario.seguros, true);
                add(seguro);
                // crear
                add(new Boton("Crear Reserva", () -> {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime entrega = LocalDateTime.parse(fechaEntrega.getText(), formatter);
                        LocalDateTime recogida = LocalDateTime.parse(fechaRecogida.getText(), formatter);
                        sistemaAlquiler.crearAlquiler(
                                        comboCategoria.getSelectedItem(),
                                        recogida,
                                        comboRecogida.getSelectedItem(),
                                        comboEntrega.getSelectedItem(),
                                        null,
                                        sistemaAlquiler.getCliente(idCliente.getText()),
                                        null);
                        return null;
                }));
                add(new Boton("Atras", () -> {
                        nav.paginaAnterior();
                        return null;
                }));
        }

        TarjetaDeCredito solicitarInfo() {
                return obtenerTarjeta.solicitarInfo();
        }

        private void cerrarObtenerTarjeta() {
                obtenerTarjeta.dispatchEvent(new WindowEvent(obtenerTarjeta, WindowEvent.WINDOW_CLOSING));
        }

}
