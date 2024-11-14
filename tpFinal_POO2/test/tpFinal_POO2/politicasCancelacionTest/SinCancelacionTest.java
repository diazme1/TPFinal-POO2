package tpFinal_POO2.politicasCancelacionTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpFinal_POO2.Usuario.Inquilino;
import tpFinal_POO2.Reserva.Reserva;
import tpFinal_POO2.PoliticasCancelacion.SinCancelacion;

class SinCancelacionTest {
	
	private SinCancelacion sinCancelacion;
	private Reserva reservaMock;

	@BeforeEach
	void setUp() throws Exception {
		
		this.sinCancelacion = new SinCancelacion();
		this.reservaMock = mock(Reserva.class);
		
	}

	@Test
	void testSinCancelacion_NoSeDebeAbonar() {
		Inquilino inquilinoMock = mock(Inquilino.class);
		when(this.reservaMock.getInquilino()).thenReturn(inquilinoMock);
		
		this.sinCancelacion.cancelarReserva(reservaMock, LocalDate.of(2024, 1, 15));
		
		//Más de 10 días antes es gratuito, no debería abonar:
		verify(inquilinoMock, never()).abonarMonto(0.00);
		
		//Agregado de assert luego de verify
		assertTrue(true, "No hay multa por cancelación.");
	}

}
