package tpFinal_POO2.SitioWebTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.Reserva.Reserva;
import tpFinal_POO2.SitioWeb.FiltroCompuesto;
import tpFinal_POO2.SitioWeb.Servicio;
import tpFinal_POO2.SitioWeb.SitioWeb;
import tpFinal_POO2.SitioWeb.TipoDeInmueble;
import tpFinal_POO2.Usuario.Usuario;

class SitioWebTest {
	
	private SitioWeb site;
	private Usuario inquilinoMock,inquilinoMock2;	// no me toma inquilino ni propietario como usuario
	private Usuario propietarioMock;
	private FiltroCompuesto filtroMock;
	private Inmueble inmuebleMock,inmuebleMock2,inmuebleMock3,inmuebleMock4;
	private Servicio servicioMock,servicioMock2;
	private Reserva reservaMock,reservaMock2;
	private TipoDeInmueble tipoInmuebleMock,tipoInmuebleMock2;
	private ArrayList<Inmueble> inmueblesMock,inmueblesMock2;
	private ArrayList<Reserva> reservasMock,reservasMock2;
	private List<Usuario>inquilinosTopTen;
	private List<Servicio>serviciosMock;
	
	
	@BeforeEach
	void setUp() throws Exception {
		this.site=new SitioWeb();
		
		this.filtroMock= mock(FiltroCompuesto.class);
		this.inmuebleMock=mock(Inmueble.class);
		this.inmuebleMock2=mock(Inmueble.class);
		this.inmuebleMock3=mock(Inmueble.class);
		this.inmuebleMock4=mock(Inmueble.class);
		this.servicioMock=mock(Servicio.class);
		this.servicioMock2=mock(Servicio.class);
		this.tipoInmuebleMock=mock(TipoDeInmueble.class);
		this.tipoInmuebleMock2=mock(TipoDeInmueble.class);
		this.inquilinoMock=mock(Usuario.class);
		this.inquilinoMock2=mock(Usuario.class);
		this.propietarioMock=mock(Usuario.class);
		this.reservaMock=mock(Reserva.class);
		this.reservaMock2=mock(Reserva.class);
		
		this.inmueblesMock = new ArrayList<Inmueble>();
		this.inmueblesMock2 = new ArrayList<Inmueble>();
		this.reservasMock = new ArrayList<Reserva>();	// 1 sola reserva
		this.reservasMock2 = new ArrayList<Reserva>();	// 2 reservas
		this.inquilinosTopTen=new ArrayList<Usuario>();
		this.serviciosMock= new ArrayList<Servicio>();
		this.serviciosMock.add(servicioMock);
		
		this.inmueblesMock.add(inmuebleMock);
		this.inmueblesMock.add(inmuebleMock2);
		this.inmueblesMock2.add(inmuebleMock3);
		this.inmueblesMock2.add(inmuebleMock4);
		this.inmueblesMock2.add(inmuebleMock);
		this.inmueblesMock2.add(inmuebleMock2);
		this.reservasMock.add(reservaMock); 
		this.reservasMock2.add(reservaMock);
		this.reservasMock2.add(reservaMock2);
		this.inquilinosTopTen.add(inquilinoMock2);
		this.inquilinosTopTen.add(inquilinoMock);
		
		this.site.addUsuario(inquilinoMock);
		this.site.addUsuario(inquilinoMock2);
		this.site.addUsuario(propietarioMock);
		this.site.darAltaServicios(servicioMock);
		this.site.darAltaTipoInmueble(tipoInmuebleMock);
		this.site.darDeAltaCategoriaInmueble("Amplio");
		this.site.darDeAltaCategoriaInquilino("Pulcro");
		this.site.darDeAltaCategoriaPropietario("Amigable");
		
		when(propietarioMock.getInmuebles()).thenReturn(inmueblesMock2);	// tiene 4 inmuebles
		when(propietarioMock.inmueblesAlquilados()).thenReturn(inmueblesMock);		// tiene 2 alquilados
		when(inmuebleMock.getServicios()).thenReturn(serviciosMock);
		when(inmuebleMock.getTipoDeInmueble()).thenReturn(tipoInmuebleMock);
		when(inmuebleMock3.getTipoDeInmueble()).thenReturn(tipoInmuebleMock2);
		when(inquilinoMock.getReservas()).thenReturn(reservasMock);
		when(inquilinoMock2.getReservas()).thenReturn(reservasMock2);
		when(inquilinoMock.cantReservasFinalizadasInquilino()).thenReturn(1);
		when(inquilinoMock2.cantReservasFinalizadasInquilino()).thenReturn(3);
		when(propietarioMock.cantReservasFinalizadasInquilino()).thenReturn(0);
	}
	
	
	@Test
	void seEvaluaElIngresoDeDatos() {
		assertEquals(3, site.getUsuarios().size());
		assertEquals(1, site.getServicios().size());
		assertEquals(1,site.getTipoDeInmuebles().size());
	}
	
	@Test
	void seChequeaLaFiltracionDeInmuebles() {
		site.filtrarPropiedadesPor(filtroMock);
		verify(filtroMock,times(1)).filtrar(inmueblesMock2);
	}
	
	@Test
	void seChequeaLaValidezDeUnInmueble() {
		assertTrue(site.esValidoInmueble(inmuebleMock));
		verify(inmuebleMock,times(1)).getServicios();
		verify(inmuebleMock,times(1)).getTipoDeInmueble();
		// LE AGREGO UN NUEVO SERVICIO PARA QUE NO SEA VALIDO Y ROMPA POR SERVICIO
		this.serviciosMock.add(servicioMock2);
		when(inmuebleMock2.getServicios()).thenReturn(serviciosMock);
		assertFalse(site.esValidoInmueble(inmuebleMock2));
		verify(inmuebleMock2,times(1)).getServicios();
		// NO LE LLEGA A PEDIR EL TIPO PORQUE YA FALLA CON LOS SERVICIOS
		verify(inmuebleMock2,times(0)).getTipoDeInmueble();
		// LE AGREGO UN NUEVO TIPO DE INMUEBLE A ESTE INMUEBLE PARA QUE ROMPA 
		assertFalse(site.esValidoInmueble(inmuebleMock3));
	}
	
	@Test
	void seChequeaLaTasaDeOcupacion() {
		// propietarioMock TIENE 2 INMUEBLES ALQUILADOS Y 4 INMUEBLES PUBLICADOS
		assertEquals(0.5 , site.getTasaDeOcupacion());
		verify(inquilinoMock,times(1)).inmueblesAlquilados();
		verify(propietarioMock,times(1)).getInmuebles();
	}
	
	@Test
	void seChequeaElTopTenInquilinos() {
		assertEquals(inquilinosTopTen, site.getTopTenInquilinos());
	}
	
	@Test
	void seTesteaLaValidezDeLasCategorias() {
		assertTrue(this.site.esValidaCategoriaInmueble("Amplio"));
		assertTrue(this.site.esValidaCategoriaInquilino("Pulcro"));
		assertTrue(this.site.esValidaCategoriaPropietario("Amigable"));
		assertFalse(this.site.esValidaCategoriaInmueble("Limpio"));
		assertFalse(this.site.esValidaCategoriaInquilino("Comprensivo"));
		assertFalse(this.site.esValidaCategoriaPropietario("Paciente"));
	}
	
	
	
}
