package tpFinal_POO2.sitioWebTest;

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

import tpFinal_POO2.Filtros.FiltroCompuesto;
import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.Reserva.Reserva;
import tpFinal_POO2.SitioWeb.SitioWeb;
import tpFinal_POO2.Usuario.Usuario;

class SitioWebTest {
	
	private SitioWeb site;
	private Usuario inquilinoMock;
	private Usuario propietarioMock;
	private FiltroCompuesto filtroMock;
	private Inmueble inmuebleMock,inmuebleMock3,inmuebleMock2;
	private Reserva reservaMock;
	private ArrayList<Inmueble> inmueblesMock;
	private ArrayList<Reserva> reservasMock;
	private List<Usuario>inquilinosTopTen;
	private List<String>serviciosMock;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		this.site=new SitioWeb();
		
		// SE INICIALIZAN LOS MOCK
		this.filtroMock= mock(FiltroCompuesto.class);
		this.inmuebleMock=mock(Inmueble.class);
		this.inmuebleMock3=mock(Inmueble.class);
		this.inmuebleMock2=mock(Inmueble.class);
		this.inquilinoMock=mock(Usuario.class);
		this.propietarioMock=mock(Usuario.class);
		this.reservaMock=mock(Reserva.class);
		
		// SE LE CARGAN LOS DATOS AL SITIO
		this.site.addUsuario(inquilinoMock);
		this.site.addUsuario(propietarioMock);
		this.site.darAltaServicios("WIFI");
		this.site.darAltaTipoInmueble("Casa");
		this.site.darDeAltaCategoriaInmueble("Amplio");
		this.site.darDeAltaCategoriaInquilino("Pulcro");
		this.site.darDeAltaCategoriaPropietario("Amigable");
		
		// SE INICIALIZAN LAS LISTAS DE MOCKS UTILIZADAS
		this.inmueblesMock = new ArrayList<Inmueble>();
		this.reservasMock = new ArrayList<Reserva>();
		this.inquilinosTopTen=new ArrayList<Usuario>();
		this.serviciosMock=new ArrayList<String>();
		
		this.reservasMock.add(reservaMock);
	}
	
	
	@Test
	void seEvaluaElIngresoDeDatos() {
		// DATOS CARGADOS EN EL SET UP
		assertEquals(2, site.getUsuarios().size());
		assertEquals(1, site.getServicios().size());
		assertEquals(1,site.getTipoDeInmuebles().size());
	}
	
	@Test
	void seChequeaLaFiltracionDeInmuebles() {
		when(propietarioMock.getInmuebles()).thenReturn(inmueblesMock);
		
		site.filtrarPropiedadesPor(filtroMock);
		
		verify(filtroMock,times(1)).filtrar(inmueblesMock);
	}
	
	@Test
	void seChequeaLaValidezDeUnInmueble() {
		
		this.serviciosMock.add("WIFI");
		when(inmuebleMock.getTipoInmueble()).thenReturn("Casa");
		when(inmuebleMock.getServicios()).thenReturn(serviciosMock);
		
		// ASSERTS
		assertTrue(site.esValidoInmueble(inmuebleMock));
		verify(inmuebleMock,times(1)).getServicios();
		verify(inmuebleMock,times(1)).getTipoInmueble();
		
		// LE AGREGO UN NUEVO SERVICIO PARA QUE NO SEA VALIDO Y ROMPA POR SERVICIO
		this.serviciosMock.add("GAS");
		assertFalse(site.esValidoInmueble(inmuebleMock));
	}
	
	@Test
	void seChequeaLaTasaDeOcupacion() {
		
		// SE CONFIGURA EL ARRAYMOCK DE INMUEBLES
		this.inmueblesMock.add(inmuebleMock3);
		this.inmueblesMock.add(inmuebleMock2);
		this.inmueblesMock.add(inmuebleMock);
		when(propietarioMock.getInmuebles()).thenReturn(inmueblesMock);
		when(propietarioMock.inmueblesAlquilados()).thenReturn(inmueblesMock);
		
		// ASSERTS
		assertEquals(1, site.getTasaDeOcupacion());
		// LLAMA 1 VEZ PARA VER QUE SEA PEOPIETARIO Y OTRA VEZ PARA VER LOS INMUEBLES ALQUILADOS
		verify(propietarioMock,times(2)).inmueblesAlquilados();
	}
	
	@Test
	void seChequeaElTopTenInquilinos() {
		
		this.inquilinosTopTen.add(inquilinoMock);
		when(inquilinoMock.cantReservasFinalizadasInquilino()).thenReturn(3);
		
		// ASSERTS
		assertEquals(inquilinosTopTen, site.getTopTenInquilinos());
		verify(inquilinoMock,times(1)).cantReservasFinalizadasInquilino();
	}
	
	@Test
	void seTesteaLaValidezDeLasCategorias() {
		
		// TODO CARGADO DESDE EL SET UP
		assertTrue(this.site.esValidaCategoriaInmueble("Amplio"));
		assertTrue(this.site.esValidaCategoriaInquilino("Pulcro"));
		assertTrue(this.site.esValidaCategoriaPropietario("Amigable"));
		assertFalse(this.site.esValidaCategoriaInmueble("Limpio"));
		assertFalse(this.site.esValidaCategoriaInquilino("Comprensivo"));
		assertFalse(this.site.esValidaCategoriaPropietario("Paciente"));
		
	}
	
	
	
}
