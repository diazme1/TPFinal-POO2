package tpFinal_POO2.filtrosTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpFinal_POO2.Inmueble;
import tpFinal_POO2.Filtros.FiltroCiudad;
import tpFinal_POO2.Filtros.FiltroCompuesto;

class FiltroCiudadTest {

	private FiltroCiudad filtro;
	private Inmueble inmueble;
	private FiltroCompuesto filtroCompuesto;
	
	@BeforeEach
	void setUp() throws Exception {
		this.filtroCompuesto = mock(FiltroCompuesto.class);
		this.inmueble = mock(Inmueble.class);
		this.filtro = new FiltroCiudad("Quilmes");
	}

	@Test
	void testFiltroCiudadCumpleParaInmueble() {
		when(this.inmueble.getCiudad()).thenReturn("Quilmes");
		assertEquals(true, this.filtro.cumplePara(this.inmueble, this.filtroCompuesto));
	}
	
	@Test
	void testFiltroCiudadNoCumpleParaInmueble() {
		when(this.inmueble.getCiudad()).thenReturn("La Plata");
		assertEquals(false, this.filtro.cumplePara(this.inmueble, this.filtroCompuesto));
	}

}
