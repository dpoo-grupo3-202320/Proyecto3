package pagos;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class PayPal extends PasarelaPagos {

	public void realizarPago() throws ErrorPago {
		Iterator<String> paramsIter = params.iterator();
		String numeroTarjeta = paramsIter.next();
		String fechaExpiracion = paramsIter.next();
		String cvv = paramsIter.next();
		String nombreUsuario = paramsIter.next();
		String contraseñaPerfil = paramsIter.next();
		/**
		 * ACA LLAMADA A API DE PAYPAL PARA REALIZAR PAGO
		 */
		// escribir a archivo
		try {
			Path path = Paths.get("./src/pagos/historial/PayPal.log");
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			// numeroTarjeta;fechaExpiracion;cvv;nombreUsuario;Contraseña
			lines.add(String.join(";", numeroTarjeta, fechaExpiracion, cvv, nombreUsuario, contraseñaPerfil));
			Files.write(path, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String[] getCampos() {
		return new String[] { "Numero Tarjeta", "Fecha Expiracion", "cvv", "Nombre Usuario", "Clave Usuario", };
	}
}
