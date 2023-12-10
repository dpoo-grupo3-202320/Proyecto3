package pagos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class PasarelaPagos {

	protected List<String> params;

	public PasarelaPagos(String... params) {
		setParams(params);
	}

	public void setParams(String... params) {
		this.params = new ArrayList<String>(Arrays.asList(params));
	}

	/**
	 * realiza pago basado en informacion establecida al crear objeto de pagos
	 * tambien agrega entrada a historial de pagos al realizar pago
	 * 
	 * @param params toda la informacion necesaria para realizar el pago
	 * @throws ErrorPago
	 */
	public abstract void realizarPago() throws ErrorPago;

	public abstract String[] getCampos();

	private static String[][] nombresClasePasarelas;

	public static String[][] getPasarelas() {
		if (nombresClasePasarelas == null) {
			try {
				List<String> lineas = Files.readAllLines(new File("./src/pagos/config.txt").toPath());
				List<String[]> pasarelas = new ArrayList<String[]>();
				for (String l : lineas) {
					String[] c = l.split(";");
					pasarelas.add(new String[] { c[0], c[1] });
				}
				nombresClasePasarelas = pasarelas.toArray(new String[0][0]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return nombresClasePasarelas;
	}

	public class ErrorPago extends Exception {
		private static final long serialVersionUID = 6444727521657235239L;

		public ErrorPago(String mensaje) {
			super(mensaje);
		}
	}

}
