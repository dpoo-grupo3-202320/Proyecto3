package interfaz.pagos;

import java.awt.GridLayout;

import javax.swing.JPanel;

import clases.SistemaAlquiler;
import clases.TarjetaDeCredito;
import interfaz.Navegador;
import interfaz.componentes.TButton;
import interfaz.componentes.TLabel;
import interfaz.componentes.TText;
import pagos.PasarelaPagos;

@SuppressWarnings("serial")
public class PayPalPanel extends MetodoPago {

	private SistemaAlquiler sa;
	private Navegador nav;
	private TarjetaDeCredito tarjeta;

	private final TText nombreUsuario;
	private final TText claveUsuario;

	public PayPalPanel(SistemaAlquiler sistemaAlquiler, Navegador navegador, TarjetaDeCredito tarjetaDeCredito) {
		super(sistemaAlquiler,navegador);
		this.tarjeta = tarjetaDeCredito;

		this.nombreUsuario = new TText("", true);
		this.claveUsuario = new TText("", true);

		setLayout(new GridLayout(0, 1));

		add(new TButton("ATRAS", () -> {
			nav.paginaAnterior();
			return null;
		}));

		add(new TLabel("Nombre usuario PayPal:"));
		add(nombreUsuario);

		add(new TLabel("Clave usuario PayPal:"));
		add(claveUsuario);

		add(new TButton("REALIZAR PAGO", () -> {
			try {
				PasarelaPagos.realizarPago("PayPal", tarjeta.getNumero(), tarjeta.getFechaVencimiento(),
						tarjeta.getCvv(), nombreUsuario.getText(), claveUsuario.getText());
				nav.paginaAnterior();
			} catch (Exception e) {
				
			}
			return null;
		}));
	}

}
