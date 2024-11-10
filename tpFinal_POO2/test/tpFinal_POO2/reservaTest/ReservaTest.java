package tpFinal_POO2.reservaTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpFinal_POO2.Reserva.FormaDePago;
import tpFinal_POO2.Reserva.Reserva;

import static org.mockito.Mockito.*;

class ReservaTest {
	
	private Reserva reserva;
	private Inmueble inmueble1;
	private Inquilino inq1;
	private Propietario prop1;
	
	@BeforeEach
	void setUp() throws Exception {
		this.reserva= new Reserva(inmueble1,FormaDePago.TARJETACREDITO,2-12-23,12-12-23,inq1);
	}
	@Test
	void testAprobacionReserva() {
		assertEquals(null, null);;
	}

}
