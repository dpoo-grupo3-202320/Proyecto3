package interfaz.menuempleado;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JPanel;

import clases.Inventario;
import clases.Range;
import clases.Seguro;
import clases.SistemaAlquiler;
import clases.TarjetaDeCredito;
import interfaz.Navegador;
import interfaz.componentes.TButton;
import interfaz.componentes.TCombo;
import interfaz.componentes.TLabel;
import interfaz.componentes.TText;
import interfaz.registro.ObtenerTarjeta;

public class CrearAlquiler extends JPanel {

	private final Navegador nav;
	private final SistemaAlquiler sistemaAlquiler;

	private ObtenerTarjeta obtenerTarjeta;
	private TarjetaDeCredito tarjeta;
	private TButton establecerTarjeta;

	public CrearAlquiler(final Navegador navegador, SistemaAlquiler sistemaAlquiler) {
		this.nav = navegador;
		this.sistemaAlquiler = sistemaAlquiler;

		setLayout(new GridLayout(0, 2));
		// categorias
		add(new TLabel("Categoria:"));
		TCombo comboCategoria = new TCombo(Inventario.categorias, false);
		add(comboCategoria);
		// sede de recogida
		add(new TLabel("Sede de recogida:"));
		TCombo comboRecogida = new TCombo((String[]) Inventario.sedes, false);
		add(comboRecogida);
		// sede de entrega
		add(new TLabel("Sede de entrega:"));
		TCombo comboEntrega = new TCombo((String[]) Inventario.sedes, false);
		add(comboEntrega);
		// sede de entrega
		add(new TLabel("Tarjeta de Credito:"));
		establecerTarjeta = new TButton(tarjeta == null ? "Seleccionar" : "Cambiar", () -> {
			obtenerTarjeta = new ObtenerTarjeta(() -> {
				tarjeta = solicitarInfo();
				if (tarjeta == null) {
					// TODO: mostrar error
					System.out.println("tarjeta == null");
				} else {
					cerrarObtenerTarjeta();
					System.out.println("ObtenerTarjeta cerrada");
				}
				return null;
			});
			obtenerTarjeta.setResizable(true);
			obtenerTarjeta.setSize(new Dimension(500, 500));
			obtenerTarjeta.setVisible(true);
			return null;
		});
		add(establecerTarjeta);
		// fecha recogida
		add(new TLabel("Fecha de Recogida (yyyy-MM-dd HH:mm):"));
		TText fechaRecogida = new TText("", true);
		add(fechaRecogida);
		// fecha entrega
		add(new TLabel("Fecha de Entrega (yyyy-MM-dd HH:mm):"));
		TText fechaEntrega = new TText("", true);
		add(fechaEntrega);
		// usuario
		add(new TLabel("Cliente (ID):"));
		TText idCliente = new TText("", true);
		add(idCliente);
		// seguro
		add(new TLabel("Seguro:"));
		TCombo seguro = new TCombo(Inventario.seguros, true);
		add(seguro);
		// crear
		add(new TButton("Crear Reserva", () -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime entrega = LocalDateTime.parse(fechaEntrega.getText(), formatter);
			LocalDateTime recogida = LocalDateTime.parse(fechaRecogida.getText(), formatter);
			System.out.println("entrega str: " + fechaEntrega.getText() + ", datetime: " + entrega);
			System.out.println("recogida str: " + fechaRecogida.getText() + ", datetime: " + recogida);
			try {
				ArrayList<Seguro> seguros = new ArrayList<>();
				seguros.add(sistemaAlquiler.getSeguro(seguro.getSelectedItem()));
				this.sistemaAlquiler.crearAlquiler(comboCategoria.getSelectedItem(), recogida, comboRecogida.getSelectedItem(),
						comboEntrega.getSelectedItem(), new Range<LocalDateTime>(entrega, entrega),
						sistemaAlquiler.getCliente(idCliente.getText()), null, seguros);
				System.out.println("Alquiler Creado");
				nav.paginaAnterior();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}));
		add(new TButton("Atras", () -> {
			nav.paginaAnterior();
			return null;
		}));
	}

	TarjetaDeCredito solicitarInfo() {
		return obtenerTarjeta.solicitarInfo();
	}

	private void cerrarObtenerTarjeta() {
		obtenerTarjeta.dispatchEvent(new WindowEvent(obtenerTarjeta, WindowEvent.WINDOW_CLOSING));
		establecerTarjeta.setText(tarjeta == null ? "Seleccionar" : "Cambiar");
	}

}
