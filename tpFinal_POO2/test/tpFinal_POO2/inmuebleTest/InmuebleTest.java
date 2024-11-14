package tpFinal_POO2.inmuebleTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpFinal_POO2.Reserva.FormaDePago;
import tpFinal_POO2.Usuario.Inquilino;
import tpFinal_POO2.Usuario.Propietario;
import tpFinal_POO2.Reserva.Reserva;
import tpFinal_POO2.Valoracion.Valoracion;
import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.Inmueble.Periodo;
import tpFinal_POO2.PoliticasCancelacion.CancelacionIntermedia;
import tpFinal_POO2.Observer.Observer;

class InmuebleTest {
	
	private Inmueble inmueble;
	private Propietario propietarioMock;
	

	@BeforeEach
	void setUp() throws Exception {
		
		this.propietarioMock = mock(Propietario.class);
		this.inmueble = new Inmueble(
				propietarioMock,			//Dueño
				"Casa",						//Tipo inmueble
				50.00,						//Metros cuadrados
				"Argentina",				//Pais
				"CABA",						//Ciudad
				"Av. Libertador 2000",		//Direccion
				new ArrayList<String>(),	//Servicios
				5,							//Cantidad huespedes
				LocalTime.of(15, 0),		//Check in
				LocalTime.of(10, 0),		//Check out
				100.00						//Precio default
				);
				
	}

	@Test
	void testSeInicializaUnInmuebleConTodosLosValoresCorrespondientes() {
		
		assertEquals(this.propietarioMock, this.inmueble.getDueño());
		assertTrue(this.inmueble.getServicios().isEmpty());
		assertTrue(this.inmueble.getValoracionesRecibidas().isEmpty());
		assertTrue(this.inmueble.getReservasAceptadas().isEmpty());
		assertTrue(this.inmueble.getReservasCondicionales().isEmpty());
		assertEquals(5, this.inmueble.getCantHuespedes());
		assertEquals("CABA", this.inmueble.getCiudad());
		assertTrue(this.inmueble.getFormasDePagoAdmitidas().isEmpty());
		assertEquals("Casa", this.inmueble.getTipoInmueble());

	}
	
	@Test
	void testSeAgregaUnServicioAlInmueble() {
		
		this.inmueble.agregarServicio("Agua");
		
		assertTrue(this.inmueble.getServicios().contains("Agua"));
		assertEquals(1, this.inmueble.getServicios().size());
	}
	
	@Test
	void testAgregarValoracionCategoriaValida() {
		Valoracion valoracionMock = mock(Valoracion.class);
		when(valoracionMock.getCategoria()).thenReturn("Vacacionar");
		when(this.propietarioMock.validarCategoriaInmueble("Vacacionar")).thenReturn(true);
		
		this.inmueble.agregarValoracion(valoracionMock);
		
		assertTrue(this.inmueble.getValoracionesRecibidas().contains(valoracionMock));
		assertEquals(1, this.inmueble.getValoracionesRecibidas().size());
		
	}
	
	@Test
	void testAgregarValoracionCategoriaNoValida() {
		Valoracion valoracionMock = mock(Valoracion.class);
		when(valoracionMock.getCategoria()).thenReturn("Vacacionar");
		when(this.propietarioMock.validarCategoriaInmueble("Vacacionar")).thenReturn(false);
		
		this.inmueble.agregarValoracion(valoracionMock);
		
		assertFalse(this.inmueble.getValoracionesRecibidas().contains(valoracionMock));
		assertTrue(this.inmueble.getValoracionesRecibidas().isEmpty());
		
	}
	
	@Test
	void testAgregarPeriodoExtraordinario() {
		Periodo periodoMock = mock(Periodo.class);
		LocalDate fechaTest = LocalDate.of(2024, 1, 5);
		
		when(periodoMock.incluidoEnPeriodo(fechaTest)).thenReturn(true);
		when(periodoMock.getIncremento()).thenReturn(50.00);
		
		this.inmueble.agregarPeriodoExtraordinario(periodoMock);
		
		assertEquals(150.00, this.inmueble.getValorDeFecha(fechaTest));
	}
	
	@Test
	void testAgregarFormaDePagoAdmitida() {
		
		FormaDePago efectivo = FormaDePago.EFECTIVO;
		
		this.inmueble.agregarFormaDePago(efectivo);
		
		assertTrue(this.inmueble.getFormasDePagoAdmitidas().contains(efectivo));
		
	}
	
	@Test
	void testBajarPrecioInmueble() {
		//No hay periodos extraordinarios, se usa fecha random para verificar precio:
		LocalDate fechaTest = LocalDate.of(2024, 1, 5);
		
		//Assert de precio inicial 100.00:
		assertEquals(100.00, this.inmueble.getValorDeFecha(fechaTest));
		
		this.inmueble.bajarPrecio(70.00);
		
		assertEquals(70.00, this.inmueble.getValorDeFecha(fechaTest));
	}
	
	@Test
	void testGetMontoTotalParaFechasConPrecioDefault() {
		LocalDate fechaInicio = LocalDate.of(2024, 1, 1);
		LocalDate fechaFin = LocalDate.of(2024, 1, 5);
		
		assertEquals(500.00, this.inmueble.getMontoTotalPara(fechaInicio, fechaFin));
	}
	
	@Test
	void testGetMontoTotalParaTodasFechasPertenecientesAPeriodoExtraordinario() {
		LocalDate fechaInicio = LocalDate.of(2024, 1, 1);
		LocalDate fechaFin = LocalDate.of(2024, 1, 3);
		
		Periodo periodoMock = mock(Periodo.class);
		when(periodoMock.getIncremento()).thenReturn(50.00);
		when(periodoMock.incluidoEnPeriodo(fechaInicio)).thenReturn(true);
		when(periodoMock.incluidoEnPeriodo(fechaInicio.plusDays(1))).thenReturn(true);
		when(periodoMock.incluidoEnPeriodo(fechaFin)).thenReturn(true);
		
		this.inmueble.agregarPeriodoExtraordinario(periodoMock);
		
		assertEquals(450.00, this.inmueble.getMontoTotalPara(fechaInicio, fechaFin));
	}
	
	@Test
	void testGetMontoTotalParaFechasDePeriodoExtraordinarioYDefault() {
		LocalDate fechaInicio = LocalDate.of(2024, 1, 1);
		LocalDate fechaFin = LocalDate.of(2024, 1, 3);
		
		Periodo periodoMock = mock(Periodo.class);
		when(periodoMock.getIncremento()).thenReturn(50.00);
		when(periodoMock.incluidoEnPeriodo(fechaInicio)).thenReturn(true);
		when(periodoMock.incluidoEnPeriodo(fechaInicio.plusDays(1))).thenReturn(true);
		when(periodoMock.incluidoEnPeriodo(fechaFin)).thenReturn(false);
		
		this.inmueble.agregarPeriodoExtraordinario(periodoMock);
		
		assertEquals(400.00, this.inmueble.getMontoTotalPara(fechaInicio, fechaFin));
	}
	
	
	@Test
	void testGenerarReservaParaFechasDisponibles() {
		Inquilino inquilinoMock = mock(Inquilino.class);
		LocalDate checkIn = LocalDate.of(2024, 1, 1);
		LocalDate checkOut = LocalDate.of(2024, 1, 3);
		
		this.inmueble.generarReserva(inquilinoMock, checkIn, checkOut, FormaDePago.EFECTIVO);
		
		assertTrue(!this.inmueble.getReservas().isEmpty());
		assertEquals(1, this.inmueble.getReservas().size());
		
	}
	
	@Test
	void testGenerarReservaParaFechasOcupadas_SeAgregaAReservasCondicionales() {
		Inquilino inquilinoMock = mock(Inquilino.class);
		LocalDate checkIn_1 = LocalDate.of(2024, 1, 1);
		LocalDate checkOut_1 = LocalDate.of(2024, 1, 3);
		
		//Se genera reserva para mockear reserva aceptada, en fechas dadas:
		Reserva reservaMock = mock(Reserva.class);
		when(reservaMock.estaAprobada()).thenReturn(true);
		when(reservaMock.haySolapamiento(checkIn_1, checkOut_1)).thenReturn(true);
		//Se agrega la reserva mock:
		this.inmueble.agregarReserva(reservaMock);
		
		assertTrue(this.inmueble.getReservasAceptadas().contains(reservaMock));
		
		this.inmueble.generarReserva(inquilinoMock, checkIn_1, checkOut_1, FormaDePago.EFECTIVO);
		
		assertTrue(!this.inmueble.getReservas().isEmpty());
		assertTrue(!this.inmueble.getReservasCondicionales().isEmpty());
		//Reservas en cola:
		assertEquals(1, this.inmueble.getReservasCondicionales().size());
		//Reservas no en cola:
		assertEquals(1, this.inmueble.getReservas().size());
		
	}
	
	@Test
	void testSetPoliticaCancelacionIntermedia() {
		//Fecha cancelacion:
		LocalDate fechaCancelacion = LocalDate.of(2024, 1, 1);
		//Mock politica cancelacion:
		CancelacionIntermedia polCancelacionMock = mock(CancelacionIntermedia.class);
		//Se genera una reserva mock aprobada para poder cancelarla:
		Reserva reservaMock = mock(Reserva.class);
		when(reservaMock.estaAprobada()).thenReturn(true);
		
		this.inmueble.agregarReserva(reservaMock);
		
		//Set de politica de cancelacion:
		this.inmueble.setPoliticaCancelacion(polCancelacionMock);
		this.inmueble.reservaCancelada(reservaMock, fechaCancelacion);
		
		verify(polCancelacionMock, times(1)).cancelarReserva(reservaMock, fechaCancelacion);
		
		assertTrue(true, "La politica de cancelacion recibió correctamente el mensaje");
				
	}
	
	@Test
	void testVecesAlquiladoInmueble_Inicialmente0(){
		
		assertEquals(0, this.inmueble.vecesAlquilado());	
	}
	
	@Test
	void testVecesAlquiladoInmueble_2Veces() {
		Reserva reservaMock_1 = mock(Reserva.class);
		when(reservaMock_1.estaFinalizada()).thenReturn(true);
		Reserva reservaMock_2 = mock(Reserva.class);
		when(reservaMock_2.estaFinalizada()).thenReturn(true);
		
		//Agregamos las reservas al inmueble:
		this.inmueble.agregarReserva(reservaMock_1);
		this.inmueble.agregarReserva(reservaMock_2);
		
		//Asserts:
		assertEquals(2, this.inmueble.vecesAlquilado());
	}
	
	@Test
	void testSeCancelaUnaReservaQueHabilitaUnaReservaEnCola() {
		LocalDate fechaCancelacion = LocalDate.of(2024, 1, 1);
		
		//Reserva del 10/1/24 al 15/1/24:
		LocalDate checkIn_1 = LocalDate.of(2024, 1, 10);
		LocalDate checkOut_1 = LocalDate.of(2024, 1, 15);
		Reserva reservaMock = mock(Reserva.class);
		when(reservaMock.getCheckIn()).thenReturn(checkIn_1);
		when(reservaMock.getCheckOut()).thenReturn(checkOut_1);
		
		//Reserva condicional que se solapa para fechas anteriores:
		Reserva reservaCondicionalMock = mock(Reserva.class);
		when(reservaCondicionalMock.haySolapamiento(checkIn_1, checkOut_1)).thenReturn(true);
		
		//Agrego las reservas a sus respectivas colas:
		this.inmueble.agregarReserva(reservaMock);
		this.inmueble.agregarReservaCondicional(reservaCondicionalMock);
		
		//Assert de que tengo una reserva en cola y una reserva en reservas:
		assertFalse(this.inmueble.getReservasCondicionales().isEmpty());
		assertFalse(this.inmueble.getReservas().isEmpty());
		
		//Cancelo la primer reserva:
		this.inmueble.reservaCancelada(reservaMock, fechaCancelacion);
		
		//Asserts: No debería tener más reservas en cola
		//Se mantienen dos reservas, porque el getReservas también contempla reservas canceladas.
		assertEquals(2, this.inmueble.getReservas().size());
		assertTrue(this.inmueble.getReservas().contains(reservaCondicionalMock));
		assertTrue(this.inmueble.getReservasCondicionales().isEmpty());
	}
	
	@Test
	void testPromedioPuntajeTotalYCategoria0_SinValoraciones() {
		
		assertEquals(0.00, this.inmueble.promedioPuntajeTotal());
		assertEquals(0.00, this.inmueble.promedioPuntajeCategoria("Vacacionar"));
		
	}
	
	@Test
	void testPromedioPuntajeTotal_2Valoraciones() {
		//Valoraciones mock:
		Valoracion valoracion1 = mock(Valoracion.class);
		when(valoracion1.getPuntaje()).thenReturn(10);
		when(valoracion1.getCategoria()).thenReturn("Vacacionar");
		Valoracion valoracion2 = mock(Valoracion.class);
		when(valoracion2.getPuntaje()).thenReturn(15);
		when(valoracion2.getCategoria()).thenReturn("Vacacionar");
		
		//Mock de categorias validas:
		when(this.propietarioMock.validarCategoriaInmueble("Vacacionar")).thenReturn(true);
		
		//Agrego las valoraciones mock al inmueble:
		this.inmueble.agregarValoracion(valoracion1);
		this.inmueble.agregarValoracion(valoracion2);
		
		//Asserts:
		assertTrue(this.inmueble.getValoracionesRecibidas().contains(valoracion1));
		assertTrue(this.inmueble.getValoracionesRecibidas().contains(valoracion2));
		
		assertEquals(12.5, this.inmueble.promedioPuntajeTotal());
	}
	
	@Test
	void testPromedioPuntajeCategoriaVacacionar_2Valoraciones() {
		//Valoraciones mock:
		Valoracion valoracion1 = mock(Valoracion.class);
		when(valoracion1.getPuntaje()).thenReturn(10);
		when(valoracion1.getCategoria()).thenReturn("Vacacionar");
		Valoracion valoracion2 = mock(Valoracion.class);
		when(valoracion2.getPuntaje()).thenReturn(15);
		when(valoracion2.getCategoria()).thenReturn("Vacacionar");
		
		//Mock de categorias validas:
		when(this.propietarioMock.validarCategoriaInmueble("Vacacionar")).thenReturn(true);
		
		//Agrego las valoraciones mock al inmueble:
		this.inmueble.agregarValoracion(valoracion1);
		this.inmueble.agregarValoracion(valoracion2);
		
		//Asserts:
		assertTrue(this.inmueble.getValoracionesRecibidas().contains(valoracion1));
		assertTrue(this.inmueble.getValoracionesRecibidas().contains(valoracion2));
		
		assertEquals(12.5, this.inmueble.promedioPuntajeCategoria("Vacacionar"));
	}
	

}


















