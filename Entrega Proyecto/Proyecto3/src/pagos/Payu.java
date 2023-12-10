package pagos;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Payu extends PasarelaPagos {

	@Override
	public void realizarPago() throws ErrorPago {
		String numeroTarjeta = params.next();
		String fechaExpiracion = params.next();
		String cvv = params.next();
		String nombre = params.next();
		String direccion = params.next();
		String telefono = params.next();
		String apiKey = "API KEY ACA";
		/**
		 * ACA LLAMADA A API DE PAYU PARA REALIZAR PAGO
		 */
		String numeroTransaccion = "NUMERO TRANSACCION ACA";
		// escribir a archivo
		try {
			Path path = Paths.get("./src/pagos/historial/Payu.txt");
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			// numeroTarjeta;fechaExpiracion;cvv;nombreUsuario;Contraseña
			lines.add(String.join(";", numeroTarjeta, fechaExpiracion, cvv, nombre, direccion, telefono,
					numeroTransaccion));
			Files.write(path, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
