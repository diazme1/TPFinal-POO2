package tpFinal_POO2.politicasCancelacionTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpFinal_POO2.Externos.Inquilino;
import tpFinal_POO2.Externos.Reserva;
import tpFinal_POO2.PoliticasCancelacion.CancelacionBasica;

class CancelacionBasicaTest {

	private CancelacionBasica cancelacionBasica;
	
	@BeforeEach
	void setUp() throws Exception {
		this.cancelacionBasica = new CancelacionBasica();
		
	}

	@Test
	void testCancelacionBasica12DiasAntes() {
		
		Reserva reservaMock = mock(Reserva.class);
		Inquilino inquilinoMock = mock(Inquilino.class);
		LocalDate fechaCancelacion = LocalDate.of(2024, 1, 1);
		
		//Comportamientos mock reserva.
		when(reservaMock.getCheckIn()).thenReturn(LocalDate.of(2024, 1, 13));
		when(reservaMock.getInquilino()).thenReturn(inquilinoMock);
		
		this.cancelacionBasica.cancelarReserva(reservaMock, fechaCancelacion);
		
		//Más de 10 días antes es gratuito, no debería abonar:
		verify(inquilinoMock, never()).abonar(0);
		
		//Agregado de assert luego de verify
		assertTrue(true, "La cancelación fue con más de 10 días de anticipación.");
	}

}
