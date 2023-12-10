package pagos;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class Sire extends PasarelaPagos {

	@Override
	public void realizarPago() throws ErrorPago {
		Iterator<String> paramsIter = params.iterator();
		String numeroTarjeta = paramsIter.next();
		String fechaExpiracion = paramsIter.next();
		String cvv = paramsIter.next();
		String nombre = paramsIter.next();
		String pais = paramsIter.next();
		String correo = paramsIter.next();
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
					"{\"numeroTarjeta\":\"" + numeroTarjeta + "\",\"fechaExpiracion\":\"" + fechaExpiracion
							+ "\",\"cvv\":\"" + cvv + "\",\"nombre\":\"" + nombre + "\",\"pais\":\"" + pais
							+ "\",\"correo\":\"" + correo + "\",\"numeroTransaccion\":\"" + numeroTransaccion + "\"},");
			Files.write(path, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String[] getCampos() {
		return new String[] { "Numero Tarjeta", "Fecha Expiracion", "cvv", "Nombre", "Pais", "Correo", };
	}

}
