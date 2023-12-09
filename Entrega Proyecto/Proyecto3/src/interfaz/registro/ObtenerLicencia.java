package interfaz.registro;

import java.awt.GridLayout;
import java.util.concurrent.Callable;

import javax.swing.JFrame;
import javax.swing.JLabel;

import clases.LicenciaDeConduccion;
import interfaz.componentes.TButton;
import interfaz.componentes.TText;

public class ObtenerLicencia extends JFrame {
	/**
	* 
	*/
	private static final long serialVersionUID = -9166861740552394697L;

	TText numero;
	TText pais;
	TText fecha;
	TButton aceptar;

	public ObtenerLicencia(Callable<Void> callback) {
		setLayout(new GridLayout(0, 1));
		numero = new TText("", true);
		pais = new TText("", true);
		fecha = new TText("", true);
		aceptar = new TButton("aceptar", () -> {
			callback.call();
			return null;
		});
		add(new JLabel("Numero de Licencia:"));
		add(numero);
		add(new JLabel("Pais de expedicion"));
		add(pais);
		add(new JLabel("Fecha de expiracion (MM/YY):"));
		add(fecha);
		add(aceptar);
	}

	public LicenciaDeConduccion solicitarInfo() {
		return new LicenciaDeConduccion(numero.getText(), pais.getText(), fecha.getText(), null);
	}
}
