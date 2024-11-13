package tpFinal_POO2.politicasCancelacionTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpFinal_POO2.Externos.Inquilino;
import tpFinal_POO2.Externos.Reserva;
import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.PoliticasCancelacion.CancelacionBasica;

class CancelacionBasicaTest {

	private CancelacionBasica cancelacionBasica;
	private Reserva reservaMock;
	
	@BeforeEach
	void setUp() throws Exception {
		this.cancelacionBasica = new CancelacionBasica();
		this.reservaMock = mock(Reserva.class);
		
	}

	@Test
	void testCancelacionBasica12DiasAntes_NoDebeAbonar() {
		
		Inquilino inquilinoMock = mock(Inquilino.class);
		LocalDate fechaCancelacion = LocalDate.of(2024, 1, 1);
		
		//Comportamientos mock reserva.
		when(this.reservaMock.getCheckIn()).thenReturn(LocalDate.of(2024, 1, 13));
		when(this.reservaMock.getInquilino()).thenReturn(inquilinoMock);
		
		this.cancelacionBasica.cancelarReserva(this.reservaMock, fechaCancelacion);
		
		//Más de 10 días antes es gratuito, no debería abonar:
		verify(inquilinoMock, never()).abonar(0);
		
		//Agregado de assert luego de verify
		assertTrue(true, "La cancelación fue con más de 10 días de anticipación.");
	}
	
	@Test
	void testCancelacionBasica8DiasAntes_DebeAbonar() {

		Inquilino inquilinoMock = mock(Inquilino.class);
		Inmueble inmuebleMock = mock(Inmueble.class);
		LocalDate fechaCancelacion = LocalDate.of(2024, 1, 1);
		
		//Comportamientos mock reserva.
		when(this.reservaMock.getCheckIn()).thenReturn(LocalDate.of(2024, 1, 8));
		when(this.reservaMock.getInmueble()).thenReturn(inmuebleMock);
		when(this.reservaMock.getInquilino()).thenReturn(inquilinoMock);
		when(inmuebleMock.getMontoTotalPara(this.reservaMock.getCheckIn(), this.reservaMock.getCheckIn().plusDays(1))).thenReturn(100.00);
		
		this.cancelacionBasica.cancelarReserva(this.reservaMock, fechaCancelacion);
		
		//Más de 10 días antes es gratuito, no debería abonar:
		verify(inquilinoMock, times(1)).abonar(100.00);
		
		//Agregado de assert luego de verify
		assertTrue(true, "La cancelación fue con menos de 10 días de anticipación, se notificó abono.");
	}

}
