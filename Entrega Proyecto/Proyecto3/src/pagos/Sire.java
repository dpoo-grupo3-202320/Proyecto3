package pagos;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Sire extends PasarelaPagos {

	@Override
	public void realizarPago() throws ErrorPago {
		String numeroTarjeta = params.next();
		String fechaExpiracion = params.next();
		String cvv = params.next();
		String nombre = params.next();
		String pais = params.next();
		String correo = params.next();
		String apiKey = "API KEY ACA";
		/**
		 * ACA LLAMADA A API DE PAYU PARA REALIZAR PAGO
		 */
		String numeroTransaccion = "NUMERO TRANSACCION ACA";
		// escribir a archivo
		try {
			Path path = Paths.get("./src/pagos/historial/Sire.json");
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			// numeroTarjeta;fechaExpiracion;cvv;nombreUsuario;Contraseña
			lines.add(lines.size() - 1,
					"{numeroTarjeta:" + numeroTarjeta + ",fechaExpiracion:" + fechaExpiracion + ",cvv:" + cvv
							+ ",nombre:" + nombre + ",pais:" + pais + ",correo:" + correo + ",numeroTransaccion:"
							+ numeroTransaccion + "},");
			Files.write(path, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
