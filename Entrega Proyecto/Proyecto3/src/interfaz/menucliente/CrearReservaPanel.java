package interfaz.menucliente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JPanel;
import javax.swing.JTextField;

import clases.Cliente;
import clases.Range;
import clases.Reserva;
import clases.SistemaAlquiler;
import interfaz.Navegador;
import interfaz.componentes.TButton;
import interfaz.componentes.TLabel;
import interfaz.pagos.PaginaPago;
import pagos.PasarelaPagos;
import pdf.GeneradorPdf;

public class CrearReservaPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1130925823047717927L;

	private final Navegador nav;
	private final SistemaAlquiler sistemaAlquiler;

	private final TButton botonPago;
	private final PaginaPago paginaPago;
	private PasarelaPagos objPago;

	public CrearReservaPanel(Navegador nav, SistemaAlquiler sistemaAlquiler) {
		this.nav = nav;
		this.sistemaAlquiler = sistemaAlquiler;

		setLayout(new GridLayout(0, 1));
		setName("Reserva");

		add(new TButton("ATRAS", () -> {
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

		paginaPago = new PaginaPago(nav, () -> {
			obtenerObj();
			if (objPago == null) {
				// TODO: mostrar error
			} else {
				cerrarPaginaPago();
			}
			establecerColorBotonPago();
			return null;
		});

		botonPago = new TButton("Establecer Metodo De Pago", () -> {
			paginaPago.setSize(new Dimension(600, 600));
			paginaPago.setResizable(false);
			paginaPago.setAlwaysOnTop(true);
			paginaPago.setVisible(true);
			return null;
		});
		add(botonPago);
		establecerColorBotonPago();

		TButton reservar = new TButton("RESERVAR", () -> {
			if (this.objPago == null) {
				this.nav.mensajeCliente("Establecer metodo de pago primero.", 3000);
				return null;
			}
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime fechaRecogida = LocalDateTime.parse(fechaR.getText(), formatter);
			LocalDateTime fechaEntregaTemprano = LocalDateTime.parse((CharSequence) fechaE, formatter);
			LocalDateTime fechaEntregaTarde = LocalDateTime.parse((CharSequence) entregaTarde, formatter);
			Range<LocalDateTime> rangoEntrega = new Range<LocalDateTime>(fechaEntregaTemprano, fechaEntregaTarde);

			try {
				Reserva r = this.sistemaAlquiler.crearReserva(categoria.getText(), fechaRecogida, ubicacionR.getText(),
						ubicacionE.getText(), rangoEntrega, (Cliente) sistemaAlquiler.getUsuarioActual(), null);
				objPago.realizarPago();
				try {
					GeneradorPdf.guardarPdf("id", r.getDatosRecibo());
					nav.mensajeCliente("Impresion de PDF exitosa.", 1000);
				} catch (Exception e) {
					nav.mensajeCliente("Impresion de pdf fallida, error: " + e, 2500);
				}
				this.nav.paginaAnterior();
				this.nav.mensajeCliente("Reserva creada por cliente exitosamente", 2000);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				this.nav.mensajeCliente("La reserva no se pudo crear, error: " + e1, 3000);
			}
			return null;
		});
		add(reservar);
	}

	private void cerrarPaginaPago() {
		paginaPago.dispatchEvent(new WindowEvent(paginaPago, WindowEvent.WINDOW_CLOSING));
	}

	private void obtenerObj() {
		this.objPago = paginaPago.getObj();
	}

	private void establecerColorBotonPago() {
		if (objPago == null) {
			// si imagen no escogida, rojo
			botonPago.setBackground(new Color(237, 154, 154));
			botonPago.setText("Establecer Metodo De Pago");
		} else {
			// si imagen escogida, verde
			botonPago.setBackground(new Color(203, 230, 201));
			botonPago.setText("Metodo De Pago Establecido");
		}
	}

}
