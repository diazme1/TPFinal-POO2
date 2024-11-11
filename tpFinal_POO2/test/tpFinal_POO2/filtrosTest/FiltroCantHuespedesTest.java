package tpFinal_POO2.filtrosTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpFinal_POO2.Inmueble;
import tpFinal_POO2.Filtros.FiltroCantHuespedes;
import tpFinal_POO2.Filtros.FiltroCompuesto;

class FiltroCantHuespedesTest {

	private FiltroCantHuespedes filtro;
	private Inmueble inmueble;
	private FiltroCompuesto filtroCompuesto;
	
	@BeforeEach
	void setUp() throws Exception {
		this.filtro = new FiltroCantHuespedes(5);
		this.inmueble = mock(Inmueble.class);
		this.filtroCompuesto = mock(FiltroCompuesto.class);
		
	}

	@Test
	void testFiltroCantHuespedesCumpleParaInmueble_MismaCantHuespedes() {
		when(this.inmueble.getCantHuespedes()).thenReturn(5);
		assertEquals(true, this.filtro.cumplePara(this.inmueble, this.filtroCompuesto));
	}
	
	@Test
	void testFiltroCantHuespedesCumpleParaInmueble_MasCantHuespedes() {
		when(this.inmueble.getCantHuespedes()).thenReturn(7);
		assertEquals(true, this.filtro.cumplePara(this.inmueble, this.filtroCompuesto));
	}
	
	@Test
	void testFiltroCantHuespedesNoCumpleParaInmueble() {
		when(this.inmueble.getCantHuespedes()).thenReturn(4);
		assertEquals(false, this.filtro.cumplePara(this.inmueble, this.filtroCompuesto));
	}

}
