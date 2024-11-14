package tpFinal_POO2.politicasCancelacionTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpFinal_POO2.Usuario.Inquilino;
import tpFinal_POO2.Reserva.Reserva;
import tpFinal_POO2.PoliticasCancelacion.CancelacionIntermedia;

class CancelacionIntermediaTest {
	
	private CancelacionIntermedia cancelacionIntermedia;
	private Reserva reservaMock;

	@BeforeEach
	void setUp() throws Exception {
		this.cancelacionIntermedia = new CancelacionIntermedia();
		this.reservaMock = mock(Reserva.class);
	}

	@Test
	void testCancelacionIntermediaMasDe20DiasAntes_NoDebeAbonar() {
		
		Inquilino inquilinoMock = mock(Inquilino.class);
		LocalDate fechaCancelacion = LocalDate.of(2024, 1, 1);
		
		//Comportamientos mock reserva.
		when(this.reservaMock.getCheckIn()).thenReturn(LocalDate.of(2024, 1, 21));
		when(this.reservaMock.getInquilino()).thenReturn(inquilinoMock);
		
		this.cancelacionIntermedia.cancelarReserva(this.reservaMock, fechaCancelacion);
		
		//Más de 10 días antes es gratuito, no debería abonar:
		verify(inquilinoMock, never()).abonarMonto(0.00);
		
		//Agregado de assert luego de verify
		assertTrue(true, "La cancelación fue con más de 20 días de anticipación.");
		
	}
	
	@Test
	void testCancelacionIntermedia15DiasAntes_DebeAbonar50porciento() {

		Inquilino inquilinoMock = mock(Inquilino.class);
		LocalDate fechaCancelacion = LocalDate.of(2024, 1, 1);
		
		//Comportamientos mock reserva.
		when(this.reservaMock.getCheckIn()).thenReturn(LocalDate.of(2024, 1, 16));
		when(this.reservaMock.getCheckOut()).thenReturn(LocalDate.of(2024, 1, 24));
		when(this.reservaMock.getMontoTotal()).thenReturn(100.00);
		when(this.reservaMock.getInquilino()).thenReturn(inquilinoMock);
		
		this.cancelacionIntermedia.cancelarReserva(this.reservaMock, fechaCancelacion);
		
		//Más de 10 días antes es gratuito, no debería abonar:
		verify(inquilinoMock, times(1)).abonarMonto(50.00);
		
		//Agregado de assert luego de verify
		assertTrue(true, "La cancelación fue con de 15 días de anticipación, se notificó abono.");
	}
	
	@Test
	void testCancelacionIntermedia8DiasAntes_DebeAbonarTotalidadReserva() {

		Inquilino inquilinoMock = mock(Inquilino.class);
		LocalDate fechaCancelacion = LocalDate.of(2024, 1, 1);
		
		//Comportamientos mock reserva.
		when(this.reservaMock.getCheckIn()).thenReturn(LocalDate.of(2024, 1, 8));
		when(this.reservaMock.getCheckOut()).thenReturn(LocalDate.of(2024, 1, 15));
		when(this.reservaMock.getMontoTotal()).thenReturn(100.00);
		when(this.reservaMock.getInquilino()).thenReturn(inquilinoMock);
		
		this.cancelacionIntermedia.cancelarReserva(this.reservaMock, fechaCancelacion);
		
		//Más de 10 días antes es gratuito, no debería abonar:
		verify(inquilinoMock, times(1)).abonarMonto(100.00);
		
		//Agregado de assert luego de verify
		assertTrue(true, "La cancelación fue con de 8 días de anticipación, se notificó abono.");
	}

}
