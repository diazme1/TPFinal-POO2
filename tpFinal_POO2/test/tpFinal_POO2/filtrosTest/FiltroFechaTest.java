package tpFinal_POO2.filtrosTest;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpFinal_POO2.Filtros.FiltroCompuesto;
import tpFinal_POO2.Filtros.FiltroFecha;
import tpFinal_POO2.Inmueble.Inmueble;

class FiltroFechaTest {
	
	private Inmueble inmueble;
	private FiltroFecha filtro;
	private FiltroCompuesto filtroCompuesto;

	@BeforeEach
	void setUp() throws Exception {
		this.filtro = new FiltroFecha(LocalDate.of(2024, 1, 5), LocalDate.of(2024, 1, 6));
		this.inmueble = mock(Inmueble.class);
		this.filtroCompuesto = mock(FiltroCompuesto.class);
	}

	@Test
	void testCumpleParaInmueble() {
		LocalDate fechaIni = LocalDate.of(2024, 1, 5);
		LocalDate fechaFin = LocalDate.of(2024, 1, 6);
		when(inmueble.estaDisponibleEn(fechaIni, fechaFin)).thenReturn(true);
		
		assertEquals(true, this.filtro.cumplePara(this.inmueble, this.filtroCompuesto));
		
	}
	
	@Test
	void testNoCumpleParaInmueble() {
		LocalDate fechaIni = LocalDate.of(2024, 1, 5);
		LocalDate fechaFin = LocalDate.of(2024, 1, 6);
		when(inmueble.estaDisponibleEn(fechaIni, fechaFin)).thenReturn(false);
		
		assertEquals(false, this.filtro.cumplePara(this.inmueble, this.filtroCompuesto));
	}

}
