package interfaz.menuempleado;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

import clases.Reserva;
import clases.SistemaAlquiler;
import interfaz.Navegador;
import interfaz.componentes.TButton;
import interfaz.componentes.TCombo;
import interfaz.componentes.TLabel;

@SuppressWarnings("serial")
public class FormalizarAlquiler extends JPanel {
	private final Navegador nav;
	private final SistemaAlquiler sistemaAlquiler;

	public FormalizarAlquiler(Navegador nav, SistemaAlquiler sistemaAlquiler) {
		this.nav = nav;
		this.sistemaAlquiler = sistemaAlquiler;

//    ArrayList<Reserva> reservas = sistemaAlquiler.getReservas();
		ArrayList<Reserva> sinFormalizar = sistemaAlquiler.getReservas();
		ArrayList<String> sinFormalizarDesc = new ArrayList<String>(sinFormalizar.stream().map((Reserva r) -> {
			return "cliente: " + r.getCliente().getNombres() + ", id: " + r.getId() + ", categoria: "
					+ r.getCategoriaSolicitada() + ", ";
		}).toList());
		String[] reservasString = Arrays.copyOf(sinFormalizarDesc.toArray(), sinFormalizarDesc.size(), String[].class);

		setLayout(new GridLayout(0, 1));

		add(new TButton("Atras", () -> {
			nav.paginaAnterior();
			return null;
		}));
		// seleccionar reserva
		add(new TLabel("Seleccionar Reserva:"));
		TCombo reserva = new TCombo(reservasString, false);
		add(reserva);
		// formalizar
		add(new TButton("Asignar Vehiculo", () -> {
			try {
				this.sistemaAlquiler.formalizarReserva(sinFormalizar.get(reserva.getSelectedIndex()).getId());
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
