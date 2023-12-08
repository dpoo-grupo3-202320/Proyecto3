package interfaz.menucliente;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JPanel;
import javax.swing.JTextField;

import clases.Cliente;
import clases.Range;
import clases.SistemaAlquiler;
import interfaz.Navegador;
import interfaz.componentes.TButton;
import interfaz.componentes.TLabel;

public class CrearReservaPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1130925823047717927L;
	
	private final Navegador nav;
	private final SistemaAlquiler sistemaAlquiler;

	public CrearReservaPanel(Navegador nav, SistemaAlquiler sistemaAlquiler) {
		this.nav = nav;
		this.sistemaAlquiler = sistemaAlquiler;

		setLayout(new GridLayout(0, 1));
		setName("Reserva");
		
		add(new TButton("ATRAS", () ->{
			nav.paginaAnterior();
			return null;
		}));

		add(new TLabel("Categoria"));
		JTextField categoria = new JTextField();
		add(categoria);

		add(new TLabel("Fecha recogida"));
		JTextField fechaR = new JTextField("yyyy-MM-dd HH:mm");
		add(fechaR);

		add(new TLabel("Ubicacion recogida"));
		JTextField ubicacionR = new JTextField();
		add(ubicacionR);

		add(new TLabel("Ubicacion entrega"));
		JTextField ubicacionE = new JTextField();
		add(ubicacionE);

		add(new TLabel("Fecha Entrega"));
		JTextField fechaE = new JTextField("yyyy-MM-dd HH:mm");
		add(fechaE);
		add(new TLabel("A más tardar"));
		JTextField entregaTarde = new JTextField("yyyy-MM-dd HH:mm");
		add(entregaTarde);

		JPanel panelReserva = new JPanel();
		panelReserva.setLayout(new BorderLayout());

		TButton reservar = new TButton("RESERVAR", () -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime fechaRecogida = LocalDateTime.parse(fechaR.getText(), formatter);
			LocalDateTime fechaEntregaTemprano = LocalDateTime.parse((CharSequence) fechaE, formatter);
			LocalDateTime fechaEntregaTarde = LocalDateTime.parse((CharSequence) entregaTarde, formatter);
			Range<LocalDateTime> rangoEntrega = new Range<LocalDateTime>(fechaEntregaTemprano, fechaEntregaTarde);

			try {
				sistemaAlquiler.crearReserva(categoria.getText(), fechaRecogida, ubicacionR.getText(),
						ubicacionE.getText(), rangoEntrega, (Cliente) sistemaAlquiler.getUsuarioActual(), null);
				System.out.println("Reserva creada por cliente exitosamente");
				nav.paginaAnterior();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		});
		add(reservar);

	}

}
