package tpFinal_POO2.periodosTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpFinal_POO2.Inmueble.Periodo;

class PeriodosTest {
	
	private Periodo periodo;

	@BeforeEach
	void setUp() throws Exception {
		LocalDate fechaInicio = LocalDate.of(2024, 1, 10);
		LocalDate fechaFin = LocalDate.of(2024, 1, 15);
		this.periodo = new Periodo(fechaInicio, fechaFin, 50.00);
	}
	
	@Test
	void testGetIncremento() {
		assertEquals(50.00, periodo.getIncremento());
	}
	
	@Test
	void testFechaIncluidaEnPeriodo() {
		LocalDate fecha = LocalDate.of(2024, 1, 12);
		assertTrue(periodo.incluidoEnPeriodo(fecha));
	}
	
	@Test
	void testFechaNoEstaIncluidaEnPeriodo() {
		LocalDate fecha = LocalDate.of(2024, 1, 5);
		assertFalse(periodo.incluidoEnPeriodo(fecha));
	}

}
