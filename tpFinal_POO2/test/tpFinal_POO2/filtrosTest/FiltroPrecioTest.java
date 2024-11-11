package tpFinal_POO2.filtrosTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpFinal_POO2.Inmueble;
import tpFinal_POO2.Filtros.FiltroCompuesto;
import tpFinal_POO2.Filtros.FiltroPrecio;

class FiltroPrecioTest {

	private FiltroPrecio filtro;
	private Inmueble inmueble;
	private FiltroCompuesto filtroCompuesto;
	
	@BeforeEach
	void setUp() throws Exception {
		this.filtro = new FiltroPrecio(5000.00, 7000.00);
		this.filtroCompuesto = mock(FiltroCompuesto.class);
		this.inmueble = mock(Inmueble.class);
	}

	@Test
	void testFiltroPrecioCumpleParaInmueble() {
		when(this.filtroCompuesto.getFechaInicio()).thenReturn(LocalDate.of(2024, 1, 1));
		when(this.filtroCompuesto.getFechaFin()).thenReturn(LocalDate.of(2024, 2, 1));
		when(this.inmueble.getMontoTotal(this.filtroCompuesto.getFechaInicio(), this.filtroCompuesto.getFechaFin())).thenReturn(6000.00);
		assertEquals(true, this.filtro.cumplePara(this.inmueble, this.filtroCompuesto));
	}

	@Test
	void testFiltroPrecioNoCumpleParaInmueble_MayorAPrecioMaximo() {
		when(this.filtroCompuesto.getFechaInicio()).thenReturn(LocalDate.of(2024, 1, 1));
		when(this.filtroCompuesto.getFechaFin()).thenReturn(LocalDate.of(2024, 2, 1));
		when(this.inmueble.getMontoTotal(this.filtroCompuesto.getFechaInicio(), this.filtroCompuesto.getFechaFin())).thenReturn(8000.00);
		assertEquals(false, this.filtro.cumplePara(this.inmueble, this.filtroCompuesto));
	}
	
	@Test
	void testFiltroPrecioNoCumpleParaInmueble_MenorAPrecioMinimo() {
		when(this.filtroCompuesto.getFechaInicio()).thenReturn(LocalDate.of(2024, 1, 1));
		when(this.filtroCompuesto.getFechaFin()).thenReturn(LocalDate.of(2024, 2, 1));
		when(this.inmueble.getMontoTotal(this.filtroCompuesto.getFechaInicio(), this.filtroCompuesto.getFechaFin())).thenReturn(4000.00);
		assertEquals(false, this.filtro.cumplePara(this.inmueble, this.filtroCompuesto));
	}
	
}
