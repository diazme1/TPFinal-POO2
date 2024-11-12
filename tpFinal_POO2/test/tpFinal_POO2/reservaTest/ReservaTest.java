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
		
		when(inmueble1.getMontoTotal(checkIn,checkOut)).thenReturn(400);
		when(inmueble1.getDueño()).thenReturn(propMock);
		when(inqMock.getEmail()).thenReturn("email");
		when(propMock.getEmail()).thenReturn("email");
		when(inmueble1.getCiudad()).thenReturn("BuenosAires");
		
		this.reserva= new Reserva(inmueble1,formaPago,checkIn,checkOut,inqMock,imailMock);
	}

	@Test
	void seApruebaUnaReserva() {
		reserva.aprobarReserva();
		assertTrue(this.reserva.estaAprobada());
		verify(imailMock,times(1)).sendEmail("email","Reserva Aprobada!", "Se confirmo su reserva, gracias.");
	}
	
	@Test
	void seCancelaUnaReserva() {
		reserva.aprobarReserva();
		this.reserva.cancelarReserva();
		assertTrue(this.reserva.estaCancelada());
		verify(imailMock,times(1)).sendEmail("email", "Reserva Cancelada!", "Se cancelo la reserva ;(");
	}
	
	@Test
	void seChequeaLosCostos() {
		assertEquals(400, reserva.getMontoTotal());
		verify(inmueble1,times(1)).getMontoTotal(checkIn,checkOut);
	}
	
	@Test
	void seChequeaInformacionDeLaReserva() {
		assertEquals(inqMock, reserva.getInfoPosibleInquilino());
		assertEquals("BuenosAires", reserva.getCiudad());
		verify(inmueble1,times(1)).getCiudad();
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
	