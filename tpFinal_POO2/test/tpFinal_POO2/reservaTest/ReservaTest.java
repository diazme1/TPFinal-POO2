package tpFinal_POO2.reservaTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.Reserva.FormaDePago;
import tpFinal_POO2.Reserva.Reserva;
import tpFinal_POO2.Usuario.Usuario;
import tpFinal_POO2.Valoracion.Valoracion;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

class ReservaTest {
	private Reserva reserva;
	private Inmueble inmueble1;
	private Usuario inqMock;
	private Usuario propMock;
	private LocalDate checkIn, checkOut;
	private FormaDePago formaPago;
	private Valoracion valoracionMock;
	
	@BeforeEach
	void setUp() throws Exception {
		this.checkIn= LocalDate.of(2022, 1, 10);
		this.checkOut= LocalDate.of(2022, 1, 13);
		this.formaPago= FormaDePago.EFECTIVO;
		this.inqMock= mock(Usuario.class);
		this.propMock= mock(Usuario.class);
		this.inmueble1= mock(Inmueble.class);
		this.valoracionMock = mock(Valoracion.class);
		
		when(inmueble1.getValorDeFecha(any(LocalDate.class))).thenReturn(100);
		when(inmueble1.getDueño()).thenReturn(propMock);

		
		this.reserva= new Reserva(inmueble1,formaPago,checkIn,checkOut,inqMock);
	}
	@Test
	void seApruebaUnaReserva() {
		reserva.aprobarReserva();
		assertTrue(this.reserva.estaAprobada());
	}
	
	@Test
	void seCancelaUnaReserva() {
		this.reserva.cancelarReserva();
		assertFalse(this.reserva.sePuedeValorar());
	}
	
	@Test
	void seChequeaLosCostos() {
		assertEquals(400, reserva.getMontoTotal());
		verify(inmueble1,times(4)).getValorDeFecha(any(LocalDate.class));
	}
	
	@Test
	void seChequeaLaRealizacionDelCheckOut() {
		reserva.aprobarReserva();
		this.reserva.realizarCheckOut(LocalDate.of(2022, 1, 13));
		// si se puede rankear es porque esta finalizada
		assertTrue(this.reserva.sePuedeValorar());
	}
	
	@Test
	void seChequeaLaRealizacionDeLaValoracionAPropietario() {
		reserva.aprobarReserva();
		this.reserva.realizarCheckOut(LocalDate.of(2022, 1, 13));
		this.reserva.rankearPropietario(valoracionMock);
		verify(propMock,times(1)).agregarValoracion(valoracionMock);
	}
	
	@Test
	void seChequeaLaRealizacionDeLaValoracionAInquilino() {
		reserva.aprobarReserva();
		this.reserva.realizarCheckOut(LocalDate.of(2022, 1, 13));
		this.reserva.rankearInquilino(valoracionMock);
		verify(inqMock,times(1)).agregarValoracion(valoracionMock);
	}
	
	@Test
	void seChequeaLaRealizacionDeLaValoracionAInmueble() {
		reserva.aprobarReserva();
		this.reserva.realizarCheckOut(LocalDate.of(2022, 1, 13));
		this.reserva.rankearInmueble(valoracionMock);
		verify(inmueble1,times(1)).agregarValoracion(valoracionMock);
	}


}
	