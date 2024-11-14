package tpFinal_POO2.usuarioTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.Reserva.Reserva;
import tpFinal_POO2.SitioWeb.SitioWeb;
import tpFinal_POO2.Usuario.Usuario;
import tpFinal_POO2.Valoracion.Valoracion;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;



class UsuarioTest {
	private LocalDate hoy = LocalDate.of(2024, 10, 10);
	private SitioWeb sitio = mock(SitioWeb.class); 
	private Usuario user =new Usuario("Daniel","correo","tlfn", hoy, sitio); 
	
	@Test
	void gettersTest(){
		//falta el getter de Date porque me falla el LocalDate
		assertEquals("Daniel", user.getNombre());
		assertEquals("correo", user.getEmail());
		assertEquals("tlfn", user.getTelefono());
	}
	@Test
	void UsuarioValoracionTest() {
		Valoracion valInm = mock(Valoracion.class);
		when(valInm.getCategoria()).thenReturn("Inmueble");
		user.agregarValoracion(valInm);
		when(sitio.esValidaCategoriaInquilino("Inquilino")).thenReturn(true);
		when(sitio.esValidaCategoriaPropietario("Inquilino")).thenReturn(false);
		when(sitio.esValidaCategoriaInquilino("Propietario")).thenReturn(false);
		when(sitio.esValidaCategoriaPropietario("Propietario")).thenReturn(true);
		assertEquals(0.0, user.promedioValoracionInquilino());
		assertEquals(0,0, user.promedioValoracionCategoria("Inquilino"));
		assertEquals(0.0, user.promedioValoracionPropietario());
		assertEquals(0,0, user.promedioValoracionCategoria("Propietario"));
		Valoracion val =mock(Valoracion.class);
		when(val.getCategoria()).thenReturn("Propietario");
		when(val.getPuntaje()).thenReturn(3);
		user.agregarValoracion(val);
		assertEquals(0.0, user.promedioValoracionInquilino());
		assertEquals(0,0, user.promedioValoracionCategoria("Inquilino"));
		assertEquals(3.0, user.promedioValoracionPropietario());
		assertEquals(3,0, user.promedioValoracionCategoria("Propietario"));
		Valoracion val2 =mock(Valoracion.class);
		when(val2.getCategoria()).thenReturn("Inquilino");
		when(val2.getPuntaje()).thenReturn(4);
		user.agregarValoracion(val2);
		assertEquals(4.0, user.promedioValoracionInquilino());
		assertEquals(4,0, user.promedioValoracionCategoria("Inquilino"));
		assertEquals(3.0, user.promedioValoracionPropietario());
		assertEquals(3,0, user.promedioValoracionCategoria("Propietario"));
		assertTrue(user.getValoracionesInquilino().contains(val2) && user.getValoracionesInquilino().size() ==1);
		assertTrue(user.getValoracionesPropietario().contains(val) && user.getValoracionesPropietario().size() ==1);
	}
	
	@Test
	void inquilinoTest(){
		//falta reservas futuras por el problema con LocalDate
		Reserva reservaBA = mock(Reserva.class);
		Reserva reservaSA = mock(Reserva.class);
		when(reservaBA.getCiudad()).thenReturn("BA");
		when(reservaSA.getCiudad()).thenReturn("SA");
		user.agregarReserva(reservaBA);
		user.agregarReserva(reservaSA);
		assertTrue(user.getReservas().size() == 2 && user.getReservas().contains(reservaBA) && user.getReservas().contains(reservaSA));
		assertTrue(user.ciudadesReservadas().size() == 2 && user.ciudadesReservadas().contains("BA") && user.ciudadesReservadas().contains("SA"));
		assertTrue(user.reservasEnCiudad("BA").size() == 1 && user.reservasEnCiudad("BA").contains(reservaBA));
		assertTrue(user.reservasEnCiudad("SA").size() == 1 && user.reservasEnCiudad("SA").contains(reservaSA));
	}
	@Test
	void propietarioTest() {
		Inmueble inmuebleValido = mock(Inmueble.class);
		Inmueble inmuebleInvalido = mock(Inmueble.class);
		Inmueble inmuebleAlquilado = mock(Inmueble.class);
		when(inmuebleAlquilado.vecesAlquilado()).thenReturn(1);
		Reserva reserva = mock(Reserva.class);
		Set<Reserva> reservas =new HashSet<Reserva>();
		reservas.add(reserva);
		when(sitio.esValidoInmueble(inmuebleValido)).thenReturn(true);
		when(sitio.esValidoInmueble(inmuebleAlquilado)).thenReturn(true);
		when(sitio.esValidoInmueble(inmuebleInvalido)).thenReturn(false);
		user.darDeAltaInmueble(inmuebleValido);
		user.darDeAltaInmueble(inmuebleInvalido);
		assertTrue(user.getInmuebles().contains(inmuebleValido) && user.getInmuebles().size() == 1);
		when(inmuebleAlquilado.getReservas()).thenReturn(reservas);
		user.darDeAltaInmueble(inmuebleAlquilado);
		assertTrue(user.inmueblesAlquilados().size() == 1 && user.inmueblesAlquilados().contains(inmuebleAlquilado));
		assertEquals(1, user.vecesAlquilado());
		assertTrue(user.reservasDeInmuebles().size() == 1 && user.reservasDeInmuebles().contains(reserva));
		
	}
	
	@Test
	void valorarInmuebleTest() {
		when(sitio.esValidaCategoriaInmueble("valida")).thenReturn(true);
		when(sitio.esValidaCategoriaInmueble("invalida")).thenReturn(false);
		assertTrue(user.validarCategoriaInmueble("valida"));
		assertFalse(user.validarCategoriaInmueble("invalida"));
	}
	

}
