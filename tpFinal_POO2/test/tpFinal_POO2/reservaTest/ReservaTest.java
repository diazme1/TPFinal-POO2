package tpFinal_POO2.reservaTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.Reserva.FormaDePago;
import tpFinal_POO2.Reserva.MailSender;
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
	private MailSender imailMock;
	
	@BeforeEach
	void setUp() throws Exception {
		this.checkIn= LocalDate.of(2022, 1, 10);
		this.checkOut= LocalDate.of(2022, 1, 13);
		this.formaPago= FormaDePago.EFECTIVO;
		this.inqMock= mock(Usuario.class);
		this.propMock= mock(Usuario.class);
		this.inmueble1= mock(Inmueble.class);
		this.valoracionMock = mock(Valoracion.class);
		this.imailMock = mock(MailSender.class);
		
		when(inmueble1.getMontoTotalPara(checkIn,checkOut)).thenReturn(400.00);
		when(inmueble1.getDueño()).thenReturn(propMock);
		when(inqMock.getEmail()).thenReturn("email");
		when(propMock.getEmail()).thenReturn("email");
		when(inmueble1.getCiudad()).thenReturn("BuenosAires");
		
		this.reserva= new Reserva(inmueble1,formaPago,checkIn,checkOut,inqMock);
		this.reserva.setMailSender(imailMock);
	}

	@Test
	void seApruebaUnaReserva() {
		reserva.aprobarReserva();
		assertTrue(this.reserva.estaAprobada());
		verify(imailMock,times(1)).sendEmail("email","Reserva Aprobada!", "Se confirmo su reserva, gracias.");
		assertFalse(reserva.estaCancelada());
		assertFalse(reserva.estaFinalizada());
		assertFalse(reserva.sePuedeValorar());
	}
	
	@Test
	void seCancelaUnaReserva() {
		reserva.aprobarReserva();
		this.reserva.cancelarReserva(LocalDate.of(2022, 10, 10));
		assertTrue(this.reserva.estaCancelada());
		verify(imailMock,times(1)).sendEmail("email", "Reserva Cancelada!", "Se cancelo la reserva ;(");
		assertFalse(reserva.estaAprobada());
	}
	
	@Test
	void seChequeaLosCostos() {
		assertEquals(400, reserva.getMontoTotal());
		verify(inmueble1,times(1)).getMontoTotalPara(checkIn,checkOut);
	}
	
	@Test
	void seChequeaInformacionDeLaReserva() {
		assertEquals(inqMock, reserva.getInfoPosibleInquilino());
		assertEquals("BuenosAires", reserva.getCiudad());
		assertEquals(LocalDate.of(2022, 1, 13), reserva.getCheckOut());
		assertEquals(LocalDate.of(2022, 1, 10), reserva.getCheckIn());
		assertFalse(reserva.estaFinalizada());
		verify(inmueble1,times(1)).getCiudad();
		assertFalse(reserva.haySolapamiento(LocalDate.of(2023, 1, 10), LocalDate.of(2023, 1, 13)));
		assertTrue(reserva.haySolapamiento(LocalDate.of(2022, 1, 12), LocalDate.of(2022, 1, 11)));
		assertFalse(reserva.haySolapamiento(LocalDate.of(2023, 1, 10), LocalDate.of(2023, 1, 13)));
		assertFalse(reserva.haySolapamiento(LocalDate.of(2020, 1, 10), LocalDate.of(2020, 1, 13)));
	}
	
	@Test
	void seChequeaLaRealizacionDelCheckOut() {
		reserva.aprobarReserva();
		this.reserva.realizarCheckOut(LocalDate.of(2022, 1, 13));
		// si se puede rankear es porque esta finalizada
		assertTrue(this.reserva.sePuedeValorar());
		assertTrue(reserva.estaFinalizada());
	}
	
	@Test
	void seChequeaLaNORealizacionDelCheckOut() {
		reserva.aprobarReserva();
		this.reserva.realizarCheckOut(LocalDate.of(2022, 1, 15));
		// si se puede rankear es porque esta finalizada
		assertFalse(reserva.estaFinalizada());
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
	