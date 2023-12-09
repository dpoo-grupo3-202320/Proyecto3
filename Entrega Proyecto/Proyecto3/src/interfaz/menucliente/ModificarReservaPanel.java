package interfaz.menucliente;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.JPanel;
import javax.swing.JTextField;

import clases.Cliente;
import clases.Range;
import clases.Reserva;
import clases.SistemaAlquiler;
import interfaz.Navegador;
import interfaz.componentes.TButton;
import interfaz.componentes.TCombo;
import interfaz.componentes.TLabel;
import interfaz.componentes.TText;

public class ModificarReservaPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1130925823047717927L;

	private final Navegador nav;
	private final SistemaAlquiler sistemaAlquiler;

	private Reserva reservaSeleccionada;

	private final TCombo combo;
	private TText fechaR;
	private TText fechaE;
	private TText entregaTarde;
	private TButton reservar;

	public ModificarReservaPanel(Navegador nav, SistemaAlquiler sistemaAlquiler) {
		this.nav = nav;
		this.sistemaAlquiler = sistemaAlquiler;

		final List<Reserva> reservas = sistemaAlquiler.getReservas();
		final String[] reservaIds = (String[]) reservas.stream().map((Reserva r) -> {
			return "reserva id: " + r.getId() + ", cliente: " + r.getCliente().getUsuario();
		}).toArray();

		combo = new TCombo(reservaIds, true);
		combo.addActionListener(e -> {
			int i = combo.getSelectedIndex();
			setReservaSeleccionada(reservas.get(i));
		});

		setLayout(new GridLayout(0, 1));
		setName("Modificar Reserva");

		add(new TButton("ATRAS", () -> {
			this.nav.paginaAnterior();
			return null;
		}));

		add(new TLabel("Reserva a modificar"));
		add(combo);

		add(new TLabel("Fecha recogida (yyyy-MM-dd HH:mm)"));
		this.fechaR = new TText("", true);
		add(fechaR);

		add(new TLabel("Fecha Entrega (yyyy-MM-dd HH:mm)"));
		this.fechaE = new TText("", true);
		add(fechaE);

		add(new TLabel("A más tardar (yyyy-MM-dd HH:mm)"));
		this.entregaTarde = new TText("", true);
		add(entregaTarde);

		this.reservar = new TButton("RESERVAR", () -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime fechaRecogida = LocalDateTime.parse(fechaR.getText(), formatter);
			LocalDateTime fechaEntregaTemprano = LocalDateTime.parse((CharSequence) fechaE, formatter);
			LocalDateTime fechaEntregaTarde = LocalDateTime.parse((CharSequence) entregaTarde, formatter);
			Range<LocalDateTime> rangoEntrega = new Range<LocalDateTime>(fechaEntregaTemprano, fechaEntregaTarde);

			try {
				this.sistemaAlquiler.modificarReserva(reservaSeleccionada.getId(), fechaRecogida, rangoEntrega);
				this.nav.paginaAnterior();
				this.nav.mensajeCliente("Reserva modificada exitosamente", 2000);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				this.nav.mensajeCliente("No se pudo modificar la reserva, error: " + e1, 3000);
			}
			return null;
		});
		add(reservar);
	}

	private void setReservaSeleccionada(Reserva r) {
		this.reservaSeleccionada = r;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		this.fechaR.setText(r.getFechaRecogida().format(formatter));
		this.fechaE.setText(r.getRangoEntrega().getLow().format(formatter));
		this.entregaTarde.setText(r.getRangoEntrega().getHigh().format(formatter));
	}

}
