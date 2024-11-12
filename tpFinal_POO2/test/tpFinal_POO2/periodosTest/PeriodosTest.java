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
	void testMontoTotalPeriodo() {
		
		LocalDate fechaInicio = LocalDate.of(2024, 1, 8);
		LocalDate fechaFin = LocalDate.of(2024, 1, 17);
		
		assertEquals(900.00, this.periodo.montoExtraordinarioPara(100.00, fechaInicio, fechaFin));
	}
	
	@Test
	void testMontoParcialPeriodo() {
		
		LocalDate fechaInicio = LocalDate.of(2024, 1, 11);
		LocalDate fechaFin = LocalDate.of(2024, 1, 14);
		
		assertEquals(600.00, this.periodo.montoExtraordinarioPara(100.00, fechaInicio, fechaFin));
	}

}
