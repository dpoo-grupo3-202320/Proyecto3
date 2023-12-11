import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clases.Cliente;
import clases.Inventario;
import clases.LicenciaDeConduccion;
import clases.Range;
import clases.Reserva;
import clases.Seguro;
import clases.SistemaAlquiler;

class SistemaAlquilerTest {

	private SistemaAlquiler sistemaAlquiler;

	@BeforeEach
	void setUp() throws Exception {
		sistemaAlquiler = new SistemaAlquiler();
	}

	@Test
	void testCrearReserva() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String categoria = "Lujo";
		LocalDateTime fechaRecogida = LocalDateTime.parse("2023-12-01 11:00", formatter);
		String ubicacionRecogida = "tu casa xd";
		String ubicacionEntrega = "SedeA";
		Range<LocalDateTime> rangoEntrega = new Range<LocalDateTime>(LocalDateTime.parse("2024-06-04 09:00", formatter),
				LocalDateTime.parse("2024-06-04 13:34", formatter));
		// Lionel Messi che
		Cliente cliente = sistemaAlquiler.getCliente("lionel");
		ArrayList<LicenciaDeConduccion> conductoresExtra = null;
		Reserva r;
		try {
			r = sistemaAlquiler.crearReserva(categoria, fechaRecogida, ubicacionRecogida, ubicacionEntrega,
					rangoEntrega, cliente, conductoresExtra);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		assertNull(r.getVehiculo());
	}

	@Test
	void testModificarReserva() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String categoria = "Lujo";
		LocalDateTime fechaRecogida = LocalDateTime.parse("2023-12-01 11:00", formatter);
		String ubicacionRecogida = "tu casa xd";
		String ubicacionEntrega = "SedeA";
		Range<LocalDateTime> rangoEntrega = new Range<LocalDateTime>(LocalDateTime.parse("2024-06-04 09:00", formatter),
				LocalDateTime.parse("2024-06-04 13:34", formatter));
		// Lionel Messi che
		Cliente cliente = sistemaAlquiler.getCliente("lionel");
		ArrayList<LicenciaDeConduccion> conductoresExtra = null;
		Reserva r;
		try {
			r = sistemaAlquiler.crearReserva(categoria, fechaRecogida, ubicacionRecogida, ubicacionEntrega,
					rangoEntrega, cliente, conductoresExtra);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		assertNull(r.getVehiculo());
		LocalDateTime fechaRecogida2 = LocalDateTime.parse("2023-12-01 11:00", formatter);
		Range<LocalDateTime> rangoEntrega2 = new Range<LocalDateTime>(
				LocalDateTime.parse("2024-06-04 09:00", formatter), LocalDateTime.parse("2024-06-04 13:34", formatter));
		Reserva r2;
		try {
			r2 = sistemaAlquiler.modificarReserva(r.getId(), fechaRecogida2, rangoEntrega2);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
			return;
		}
		assertEquals(fechaRecogida2, r2.getFechaRecogida());
		assertEquals(rangoEntrega2, r2.getRangoEntrega());
	}

	@Test
	void testCrearAlquiler() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String categoria = "Lujo";
		LocalDateTime fechaRecogida = LocalDateTime.parse("2023-12-01 11:00", formatter);
		String ubicacionRecogida = "tu casa xd";
		String ubicacionEntrega = "SedeA";
		Range<LocalDateTime> rangoEntrega = new Range<LocalDateTime>(LocalDateTime.parse("2024-06-04 09:00", formatter),
				LocalDateTime.parse("2024-06-04 13:34", formatter));
		// Lionel Messi che
		Cliente cliente = sistemaAlquiler.getCliente("lionel");
		ArrayList<LicenciaDeConduccion> conductoresExtra = null;
		ArrayList<Seguro> seguros = new ArrayList<Seguro>(
				Arrays.asList(new Seguro[] { sistemaAlquiler.getSeguro(Inventario.seguros[0]) }));
		Reserva r;
		try {
			r = sistemaAlquiler.crearAlquiler(categoria, fechaRecogida, ubicacionRecogida, ubicacionEntrega,
					rangoEntrega, cliente, conductoresExtra, seguros);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
			return;
		}
		assertNotNull(r.getVehiculo());
	}

	@Test
	void testFormalizarAlquiler() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String categoria = "Lujo";
		LocalDateTime fechaRecogida = LocalDateTime.parse("2023-12-01 11:00", formatter);
		String ubicacionRecogida = "tu casa xd";
		String ubicacionEntrega = "SedeA";
		Range<LocalDateTime> rangoEntrega = new Range<LocalDateTime>(LocalDateTime.parse("2024-06-04 09:00", formatter),
				LocalDateTime.parse("2024-06-04 13:34", formatter));
		// Lionel Messi che
		Cliente cliente = sistemaAlquiler.getCliente("lionel");
		ArrayList<LicenciaDeConduccion> conductoresExtra = null;
		Reserva r;
		try {
			r = sistemaAlquiler.crearReserva(categoria, fechaRecogida, ubicacionRecogida, ubicacionEntrega,
					rangoEntrega, cliente, conductoresExtra);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		assertNull(r.getVehiculo());
		try {
			sistemaAlquiler.formalizarReserva(r.getId());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e);
			return;
		}
		assertNotNull(r.getVehiculo());
	}

}
