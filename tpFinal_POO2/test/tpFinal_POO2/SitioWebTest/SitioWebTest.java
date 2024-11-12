package tpFinal_POO2.SitioWebTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.SitioWeb.FiltroCompuesto;
import tpFinal_POO2.SitioWeb.Servicio;
import tpFinal_POO2.SitioWeb.SitioWeb;
import tpFinal_POO2.SitioWeb.TipoDeInmueble;
import tpFinal_POO2.Usuario.Usuario;

class SitioWebTest {
	
	private SitioWeb site;
	private Usuario inquilinoMock;	// no me toma inquilino ni propietario como usuario
	private Usuario propietarioMock;
	private FiltroCompuesto filtroMock;
	private Inmueble inmuebleMock;
	private Servicio servicioMock;
	private TipoDeInmueble tipoInmuebleMock;
	private ArrayList<Inmueble> inmueblesMock;
	private Set<TipoDeInmueble> tiposInmuebleMock;
	private Set<Servicio> serviciosMock;
	
	
	@BeforeEach
	void setUp() throws Exception {
		this.site=new SitioWeb();
		
		this.filtroMock= mock(FiltroCompuesto.class);
		this.inmuebleMock=mock(Inmueble.class);
		this.servicioMock=mock(Servicio.class);
		this.tipoInmuebleMock=mock(TipoDeInmueble.class);
		this.inquilinoMock=mock(Usuario.class);
		this.propietarioMock=mock(Usuario.class);
		this.inmueblesMock = new ArrayList<Inmueble>();
		this.tiposInmuebleMock=new HashSet<TipoDeInmueble>();
		this.serviciosMock=new HashSet<Servicio>();
		this.inmueblesMock.add(inmuebleMock);
		
		this.site.addUsuario(inquilinoMock);
		this.site.addUsuario(propietarioMock);
		this.site.darAltaServicios(servicioMock);
		this.site.darAltaTipoInmueble(tipoInmuebleMock);
		
		when(propietarioMock.getInmuebles()).thenReturn(inmueblesMock);
	}
	
	
	@Test
	void seEvaluaElIngresoDeDatos() {
		assertEquals(2, site.getUsuarios().size());
		assertEquals(1, site.getServicios().size());
		assertEquals(1,site.getTipoDeInmuebles().size());
	}
	
	@Test
	void seChequeaLaFiltracionDeInmuebles() {
		site.filtrarPropiedadesPor(filtroMock);
		verify(filtroMock,times(1)).filtrar(inmueblesMock);
	}
	
	@Test
	void seChequeaLaValidezDeUnInmueble() {
		
	}
	
	
	
	
	
}
