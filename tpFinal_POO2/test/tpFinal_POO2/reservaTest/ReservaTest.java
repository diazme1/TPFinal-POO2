package tpFinal_POO2.reservaTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.Reserva.FormaDePago;
import tpFinal_POO2.Reserva.Reserva;
import tpFinal_POO2.Usuario.Usuario;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

class ReservaTest {
	private Reserva reserva;
	private Inmueble inmueble1;
	private Usuario inq1;
	private Usuario prop1;
	private LocalDate checkIn, checkOut;
	private FormaDePago formaPago;
	
	@BeforeEach
	void setUp() throws Exception {
		this.checkIn= LocalDate.of(2022, 1, 10);
		this.checkOut= LocalDate.of(2022, 1, 13);
		this.formaPago= FormaDePago.EFECTIVO;
		this.inq1= mock(Usuario.class);
		this.inmueble1= mock(Inmueble.class);
		when(inmueble1.getValorDeFecha(any(LocalDate.class))).thenReturn(100);
		this.reserva= new Reserva(inmueble1,formaPago,checkIn,checkOut,inq1);
	}
	@Test
	void seApruebaUnaReserva() {
		reserva.aprobarReserva();
		assertTrue(this.reserva.estaAprobada());
	}
	
	@Test
	void seChequeaLosCostos() {
		assertEquals(400, reserva.getMontoTotal());
	}
	
}
	