package interfaz.pagos;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.swing.JFrame;
import javax.swing.JPanel;

import interfaz.Navegador;
import interfaz.componentes.TButton;
import interfaz.componentes.TCombo;
import pagos.PasarelaPagos;

@SuppressWarnings("serial")
public class PaginaPago extends JFrame {

	private final Navegador nav;

	private final TCombo combo;
	private final ArrayList<MetodoPago> panels;
	private final CardLayout cardLayout;
	private MetodoPago selected;
	private final Map<String, MetodoPago> metodosPago = new HashMap<String, MetodoPago>();

	public PaginaPago(Navegador navegador, Callable<Void> callback) {
		this.nav = navegador;

		setLayout(new BorderLayout());

		cardLayout = new CardLayout();
		JPanel cards = new JPanel(cardLayout);

		combo = new TCombo(new String[] {}, false);
		combo.addItemListener((e) -> {
			cardLayout.show(cards, (String) e.getItem());
			selected = metodosPago.get(e.getItem());
		});
		add(combo, BorderLayout.NORTH);

		panels = new ArrayList<MetodoPago>();
		for (String[] ip : PasarelaPagos.getPasarelas()) {
			try {
				// ip[0] es nombre clase completo, ip[1] es nombre pasarela
				MetodoPago paymentPanel = new MetodoPago(ip[0], ip[1], nav, callback);
				metodosPago.put(ip[1], paymentPanel);
				combo.addItem(ip[1]);
				if (selected == null) {
					selected = paymentPanel;
					cardLayout.show(cards, ip[1]);
				}
				panels.add(paymentPanel);
				cards.add(paymentPanel, ip[1]);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
				nav.paginaAnterior();
				nav.mensajeCliente("No fue posible realizar pago, error: " + e, 5000);
				return;
			}
		}
		combo.setSelectedIndex(0);
		add(cards, BorderLayout.CENTER);

		add(new TButton("Finalizar", () -> {
			callback.call();
			return null;
		}), BorderLayout.SOUTH);
	}

	public PasarelaPagos getObj() {
		return selected.getObj();
	}

}
