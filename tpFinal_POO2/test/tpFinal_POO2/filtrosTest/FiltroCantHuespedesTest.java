package tpFinal_POO2.filtrosTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpFinal_POO2.Filtros.FiltroCantHuespedes;
import tpFinal_POO2.Filtros.FiltroCompuesto;
import tpFinal_POO2.Inmueble.Inmueble;

class FiltroCantHuespedesTest {

	private FiltroCantHuespedes filtro;
	private FiltroCompuesto filtroCompuesto;
	
	@BeforeEach
	void setUp() throws Exception {
		this.filtro = new FiltroCantHuespedes(5);
		this.filtroCompuesto = mock(FiltroCompuesto.class);
		
	}

	@Test
	void testFiltroCantHuespedesCumpleParaInmueble_MismaCantHuespedes() {
		Inmueble inmueble = mock(Inmueble.class);
		when(inmueble.getCantHuespedes()).thenReturn(5);
		assertEquals(true, this.filtro.cumplePara(inmueble, this.filtroCompuesto));
	}
	
	@Test
	void testFiltroCantHuespedesCumpleParaInmueble_MasCantHuespedes() {
		Inmueble inmueble = mock(Inmueble.class);
		when(inmueble.getCantHuespedes()).thenReturn(7);
		assertEquals(true, this.filtro.cumplePara(inmueble, this.filtroCompuesto));
	}
	
	@Test
	void testFiltroCantHuespedesNoCumpleParaInmueble() {
		Inmueble inmueble = mock(Inmueble.class);
		when(inmueble.getCantHuespedes()).thenReturn(4);
		assertEquals(false, this.filtro.cumplePara(inmueble, this.filtroCompuesto));
	}

}
