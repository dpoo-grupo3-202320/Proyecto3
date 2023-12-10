package pagos;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PayPal extends PasarelaPagos {

	@Override
	public void realizarPago() throws ErrorPago {
		String numeroTarjeta = params.next();
		String fechaExpiracion = params.next();
		String cvv = params.next();
		String nombreUsuario = params.next();
		String contraseñaPerfil = params.next();
		/**
		 * ACA LLAMADA A API DE PAYPAL PARA REALIZAR PAGO
		 */
		// escribir a archivo
		try {
			Path path = Paths.get("./src/pagos/historial/PayPal.log");
			List<String> lines = Files.readAllLines(path,
					StandardCharsets.UTF_8);
			// numeroTarjeta;fechaExpiracion;cvv;nombreUsuario;Contraseña
			lines.add(String.join(";", numeroTarjeta, fechaExpiracion, cvv, nombreUsuario, contraseñaPerfil));
			Files.write(path, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
