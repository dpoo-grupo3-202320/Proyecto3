package interfaz.pagos;

import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import javax.swing.JPanel;

import interfaz.Navegador;
import interfaz.componentes.TButton;
import interfaz.componentes.TLabel;
import interfaz.componentes.TText;
import pagos.PasarelaPagos;

@SuppressWarnings("serial")
public class MetodoPago extends JPanel {

	private Navegador nav;

	private final ArrayList<TText> fields;

	private final PasarelaPagos obj;

	protected TButton establecerInfo = new TButton("Establecer Datos", () -> {
		try {

			nav.paginaAnterior();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	});

	public MetodoPago(String nombreClasePasarela, String nombrePasarela, Navegador navegador, Callable<Void> callback)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		this.nav = navegador;
		setName(nombrePasarela);

		Class<?> clase = Class.forName(nombreClasePasarela);
		this.obj = (PasarelaPagos) clase.getDeclaredConstructor(null).newInstance(null);

		setLayout(new GridLayout(0, 1));

		TLabel label = new TLabel("Pago con " + nombrePasarela);
		label.setHorizontalAlignment(TLabel.CENTER);
		add(label);

		this.fields = new ArrayList<TText>();
		for (String s : obj.getCampos()) {
			TText n = new TText("", true);
			add(new TLabel(s));
			add(n);
			fields.add(n);
		}
	}

	public PasarelaPagos getObj() {
		obj.setParams(getParams());
		return obj;
	}

	private String[] getParams() {
		ArrayList<String> params = new ArrayList<String>();
		for (TText t : this.fields) {
			params.add(t.getText());
		}
		return params.toArray(new String[0]);
	}
}
