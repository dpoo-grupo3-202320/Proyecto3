package pagos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public abstract class PasarelaPagos {
	
	public final Iterator<String> params;
	
	public PasarelaPagos(String...params) {
		this.params = new ArrayList<String>(Arrays.asList(params)).iterator();
	}

	/**
	 * realiza pago basado en informacion establecida al crear objeto de pagos
	 * tambien agrega entrada a historial de pagos al realizar pago
	 * 
	 * @param params toda la informacion necesaria para realizar el pago
	 * @throws ErrorPago
	 */
	public abstract void realizarPago() throws ErrorPago;


	public class ErrorPago extends Exception {
		private static final long serialVersionUID = 6444727521657235239L;

		public ErrorPago(String mensaje) {
			super(mensaje);
		}
	}
}
