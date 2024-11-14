package tpFinal_POO2.usuarioTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.Reserva.Reserva;
import tpFinal_POO2.SitioWeb.SitioWeb;
import tpFinal_POO2.Usuario.Usuario;
import tpFinal_POO2.Usuario.Inquilino;
import tpFinal_POO2.Usuario.Propietario;
import tpFinal_POO2.Valoracion.Valoracion;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

class UsuarioTest {
	private LocalDate hoy = LocalDate.of(2024, 10, 10);
	private SitioWeb sitio = mock(SitioWeb.class); 
	private Usuario user = new Usuario("Daniel","correo","tlfn", hoy, sitio); 
	private Inquilino inquilino = user;
	private Propietario propietario = user;
	
	
	@Test
	void gettersTest(){
		assertEquals("Daniel", user.getNombre());
		assertEquals("correo", user.getEmail());
		assertEquals("tlfn", user.getTelefono());
	}
	
	@Test 
	void inmuebleValoracionTest(){
		Valoracion valInm = mock(Valoracion.class);
		when(valInm.getCategoria()).thenReturn("Inmueble");
		//No se deberia añadir una categoria que no le pertenece
		user.agregarValoracionInquilino(valInm);
		user.agregarValoracionPropietario(valInm);
		assertTrue(user.getValoracionesInquilino().isEmpty());
		assertTrue(user.getValoracionesPropietario().isEmpty());
	}
	@Test
	void inquilinoValoracionTest() {
		when(sitio.esValidaCategoriaInquilino("Inquilino")).thenReturn(true);
		when(sitio.esValidaCategoriaPropietario("Inquilino")).thenReturn(false);
		when(sitio.esValidaCategoriaInquilino("Propietario")).thenReturn(false);
		when(sitio.esValidaCategoriaPropietario("Propietario")).thenReturn(true);
		//aseguro que el promedio inicializa en 0
		assertEquals(0.0, inquilino.promedioValoracionInquilino());
		assertEquals(0,0, inquilino.promedioValoracionCategoria("Inquilino"));
		Valoracion val =mock(Valoracion.class);
		when(val.getCategoria()).thenReturn("Propietario");
		when(val.getPuntaje()).thenReturn(3);
		//añadimos una valoracion que no le importe a propietario
		inquilino.agregarValoracionInquilino(val);
		//no deberia modificar su promedio
		assertEquals(0.0, inquilino.promedioValoracionInquilino());
		assertEquals(0,0, inquilino.promedioValoracionCategoria("Inquilino"));
		Valoracion val2 =mock(Valoracion.class);
		when(val2.getCategoria()).thenReturn("Inquilino");
		when(val2.getPuntaje()).thenReturn(4);
		//añado una valoracion a Inquilino
		inquilino.agregarValoracionInquilino(val2);
		//deberia cambiar su promedio
		assertEquals(4.0, inquilino.promedioValoracionInquilino());
		assertEquals(4,0, inquilino.promedioValoracionCategoria("Inquilino"));
		//las Valoraciones del inquilino no deben tener solo las que le son pertinentes
		assertTrue(inquilino.getValoracionesInquilino().contains(val2) && inquilino.getValoracionesInquilino().size() ==1);
	}
	
	@Test
	void PropietarioValoracionTest() {
		when(sitio.esValidaCategoriaInquilino("Inquilino")).thenReturn(true);
		when(sitio.esValidaCategoriaPropietario("Inquilino")).thenReturn(false);
		when(sitio.esValidaCategoriaInquilino("Propietario")).thenReturn(false);
		when(sitio.esValidaCategoriaPropietario("Propietario")).thenReturn(true);
		//aseguro que el promedio inicializa en 0
		assertEquals(0.0, propietario.promedioValoracionPropietario());
		assertEquals(0,0, propietario.promedioValoracionCategoria("Propietario"));
		Valoracion val =mock(Valoracion.class);
		when(val.getCategoria()).thenReturn("Propietario");
		when(val.getPuntaje()).thenReturn(3);
		//añadimos una valoracion 
		propietario.agregarValoracionPropietario(val);
		//deberia modificar el promedio
		assertEquals(3.0, propietario.promedioValoracionPropietario());
		assertEquals(3,0, propietario.promedioValoracionCategoria("Propietario"));
		Valoracion val2 =mock(Valoracion.class);
		when(val2.getCategoria()).thenReturn("Inquilino");
		when(val2.getPuntaje()).thenReturn(4);
		//añado una valoracion a Inquilino
		propietario.agregarValoracionPropietario(val2);
		//no deberia cambiar el promedio
		assertEquals(3.0, propietario.promedioValoracionPropietario());
		assertEquals(3,0, propietario.promedioValoracionCategoria("Propietario"));
		//las Valoraciones del propietario no deben tener solo las que le son pertinentes
		assertTrue(propietario.getValoracionesPropietario().contains(val) && propietario.getValoracionesPropietario().size() ==1);
	}
	
	@Test
	void inquilinoTest(){
		Reserva reservaBA = mock(Reserva.class);
		Reserva reservaSA = mock(Reserva.class);
		when(reservaBA.getCiudad()).thenReturn("BA");
		when(reservaSA.getCiudad()).thenReturn("SA");
		//agregamos 2 reservas en distintas ciudades
		inquilino.agregarReserva(reservaBA);
		inquilino.agregarReserva(reservaSA);
		//debe contener ambos
		assertTrue(inquilino.getReservas().size() == 2 && inquilino.getReservas().contains(reservaBA) && inquilino.getReservas().contains(reservaSA));
		//deben ser las 2 ciudades
		assertTrue(inquilino.ciudadesReservadas().size() == 2 && inquilino.ciudadesReservadas().contains("BA") && inquilino.ciudadesReservadas().contains("SA"));
		//las reservas en cada ciudad deberian ser solo 1
		assertTrue(inquilino.reservasEnCiudad("BA").size() == 1 && inquilino.reservasEnCiudad("BA").contains(reservaBA));
		assertTrue(inquilino.reservasEnCiudad("SA").size() == 1 && inquilino.reservasEnCiudad("SA").contains(reservaSA));
	}
	@Test
	void propietarioTest() {
		//setup de los inmuebles
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
		when(inmuebleAlquilado.getReservas()).thenReturn(reservas);
		//se intentan añadir 2 inmuebles, uno de ellos no es valido
		propietario.darDeAltaInmueble(inmuebleValido);
		propietario.darDeAltaInmueble(inmuebleInvalido);
		//solo deberia tener el valido
		assertTrue(propietario.getInmuebles().contains(inmuebleValido) && propietario.getInmuebles().size() == 1);
		//añadimos un inmueble mas para comprobar que los que no esten alquilados contabilicen para los mensajes pertinentes
		propietario.darDeAltaInmueble(inmuebleAlquilado);
		//solo deberian contar aquellos inmuebles que fueron alquilados
		assertTrue(propietario.inmueblesAlquilados().size() == 1 && propietario.inmueblesAlquilados().contains(inmuebleAlquilado));
		assertEquals(1, propietario.vecesAlquilado());
		assertTrue(propietario.reservasDeInmuebles().size() == 1 && propietario.reservasDeInmuebles().contains(reserva));
	}
	
	@Test
	void valorarInmuebleTest() {
		when(sitio.esValidaCategoriaInmueble("valida")).thenReturn(true);
		when(sitio.esValidaCategoriaInmueble("invalida")).thenReturn(false);
		//el sitio deberia de poder indicarle al propietario si el inmueble es valido
		assertTrue(propietario.validarCategoriaInmueble("valida"));
		assertFalse(propietario.validarCategoriaInmueble("invalida"));
	}
	
	@Test
	void getReservasFuturasTest() {
		LocalDate hoy = LocalDate.of(2024, 1, 1);
		
		//Mocks reservas:
		Reserva reserva1 = mock(Reserva.class);
		when(reserva1.getCheckIn()).thenReturn(LocalDate.of(2024, 1, 20));
		Reserva reserva2 = mock(Reserva.class);
		when(reserva2.getCheckIn()).thenReturn(LocalDate.of(2024, 1, 27));
		
		//Agrego reservas a usuario:
		this.user.agregarReserva(reserva1);
		this.user.agregarReserva(reserva2);
		
		//Asserts:
		assertTrue(this.user.reservasFuturas(hoy).contains(reserva1));
		assertTrue(this.user.reservasFuturas(hoy).contains(reserva2));
		
		assertEquals(2, this.user.reservasFuturas(hoy).size());
	}
	
	@Test
	void getAntiguedadUsuarioTest() {
		//El usuario se habría registrado en 2024.10.10:
		LocalDate unAnioDespues = LocalDate.of(2025, 10, 10);
		
		assertEquals(365, this.user.getAntiguedadUsuario(unAnioDespues));
	}
	
	@Test
	void getCantidadReservasFinalizadasInquilino() {
		//Mocks reservas:
		Reserva reserva1 = mock(Reserva.class);
		when(reserva1.estaFinalizada()).thenReturn(true);
		Reserva reserva2 = mock(Reserva.class);
		when(reserva2.estaFinalizada()).thenReturn(true);
		
		//Agrego reservas a usuario:
		this.user.agregarReserva(reserva1);
		this.user.agregarReserva(reserva2);
		
		//Asserts:
		assertEquals(2, this.user.cantReservasFinalizadasInquilino());
	}

}