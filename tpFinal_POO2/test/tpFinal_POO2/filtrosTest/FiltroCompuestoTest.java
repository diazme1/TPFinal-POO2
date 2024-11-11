package tpFinal_POO2.filtrosTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpFinal_POO2.Inmueble;
import tpFinal_POO2.Filtros.Filtro;
import tpFinal_POO2.Filtros.FiltroCantHuespedes;
import tpFinal_POO2.Filtros.FiltroCompuesto;
import tpFinal_POO2.Filtros.FiltroPrecio;

class FiltroCompuestoTest {
	
	private FiltroCompuesto filtro;
	private List<Inmueble> inmuebles;
	

	@BeforeEach
	void setUp() throws Exception {
		LocalDate fechaIni = LocalDate.of(2024, 1, 1);
		LocalDate fechaFin = LocalDate.of(2024, 2, 1);
		this.filtro = new FiltroCompuesto(fechaIni, fechaFin, "Quilmes");
		this.inmuebles = new ArrayList<Inmueble>();
	}

	@Test
	void testInstanciacionFiltroCompuestoConFiltroObligatorios() {
		
		assertEquals(2, this.filtro.getFiltros().size());
	}
	
	@Test
	void testAgregadoFiltroOpcional(){
		Filtro filtroOpcional = mock(Filtro.class);
		this.filtro.agregarFiltro(filtroOpcional);
		
		assertEquals(3, this.filtro.getFiltros().size());
	}
	
	@Test
	void testGetFechaInicio_FechaFin() {
		
		LocalDate fechaIni = LocalDate.of(2024, 1, 1);
		LocalDate fechaFin = LocalDate.of(2024, 2, 1);
		
		assertEquals(fechaIni, this.filtro.getFechaInicio());
		assertEquals(fechaFin, this.filtro.getFechaFin());
	}
	
	@Test
	void testFiltradoInmueblesConFiltrosObligatorios_TodosValidos() {
		
		LocalDate fechaIni = LocalDate.of(2024, 1, 1);
		LocalDate fechaFin = LocalDate.of(2024, 2, 1);
		Inmueble inmueble1 = mock(Inmueble.class);
		Inmueble inmueble2 = mock(Inmueble.class);
		this.inmuebles.add(inmueble1);
		this.inmuebles.add(inmueble2);
		//Comportamiento mocks para disponibilidad de fechas true.
		when(inmueble1.estaDisponibleEn(fechaIni, fechaFin)).thenReturn(true);
		when(inmueble2.estaDisponibleEn(fechaIni, fechaFin)).thenReturn(true);
		//Comportamiento mocks para filtro ciudad true.
		when(inmueble1.getCiudad()).thenReturn("Quilmes");
		when(inmueble2.getCiudad()).thenReturn("Quilmes");
		
		assertEquals(2, this.filtro.filtrar(this.inmuebles).size());
	}
	
	@Test
	void testFiltradoInmueblesConFiltrosObligatorios_AlgunosValidos() {
		
		LocalDate fechaIni = LocalDate.of(2024, 1, 1);
		LocalDate fechaFin = LocalDate.of(2024, 2, 1);
		Inmueble inmueble1 = mock(Inmueble.class);
		Inmueble inmueble2 = mock(Inmueble.class);
		this.inmuebles.add(inmueble1);
		this.inmuebles.add(inmueble2);
		//Comportamiento mocks para disponibilidad de fechas true.
		when(inmueble1.estaDisponibleEn(fechaIni, fechaFin)).thenReturn(true);
		when(inmueble2.estaDisponibleEn(fechaIni, fechaFin)).thenReturn(false);
		//Comportamiento mocks para filtro ciudad true.
		when(inmueble1.getCiudad()).thenReturn("Quilmes");
		when(inmueble2.getCiudad()).thenReturn("La Plata");
		
		assertEquals(1, this.filtro.filtrar(this.inmuebles).size());
	}
	
	@Test
	void testFiltradoInmueblesConFiltrosObligatorios_NingunoValido() {
		
		LocalDate fechaIni = LocalDate.of(2024, 1, 1);
		LocalDate fechaFin = LocalDate.of(2024, 2, 1);
		Inmueble inmueble1 = mock(Inmueble.class);
		Inmueble inmueble2 = mock(Inmueble.class);
		this.inmuebles.add(inmueble1);
		this.inmuebles.add(inmueble2);
		//Comportamiento mocks para disponibilidad de fechas true.
		when(inmueble1.estaDisponibleEn(fechaIni, fechaFin)).thenReturn(false);
		when(inmueble2.estaDisponibleEn(fechaIni, fechaFin)).thenReturn(true);
		//Comportamiento mocks para filtro ciudad true.
		when(inmueble1.getCiudad()).thenReturn("Quilmes");
		when(inmueble2.getCiudad()).thenReturn("La Plata");
		
		assertEquals(0, this.filtro.filtrar(this.inmuebles).size());
	}
	
	@Test
	void testFiltradoInmueblesConTodosLosFiltros_TodosValidos() {
		
		LocalDate fechaIni = LocalDate.of(2024, 1, 1);
		LocalDate fechaFin = LocalDate.of(2024, 2, 1);
		//Mock de filtros adicionales.
		FiltroPrecio filtroPrecio = mock(FiltroPrecio.class);
		FiltroCantHuespedes filtroHuespedes = mock(FiltroCantHuespedes.class);
		//Agregado de filtros adicionales a filtro compuesto.
		this.filtro.agregarFiltro(filtroHuespedes);
		this.filtro.agregarFiltro(filtroPrecio);
		//Mock de inmuebles a filtrar.
		Inmueble inmueble1 = mock(Inmueble.class);
		Inmueble inmueble2 = mock(Inmueble.class);
		this.inmuebles.add(inmueble1);
		this.inmuebles.add(inmueble2);
		//Comportamiento mocks filtros adicionales para ambos inmuebles true.
		when(filtroPrecio.cumplePara(inmueble1, this.filtro)).thenReturn(true);
		when(filtroPrecio.cumplePara(inmueble2, this.filtro)).thenReturn(true);
		when(filtroHuespedes.cumplePara(inmueble1, this.filtro)).thenReturn(true);
		when(filtroHuespedes.cumplePara(inmueble2, this.filtro)).thenReturn(true);
		//Comportamiento mocks para disponibilidad de fechas true.
		when(inmueble1.estaDisponibleEn(fechaIni, fechaFin)).thenReturn(true);
		when(inmueble2.estaDisponibleEn(fechaIni, fechaFin)).thenReturn(true);
		//Comportamiento mocks para filtro ciudad true.
		when(inmueble1.getCiudad()).thenReturn("Quilmes");
		when(inmueble2.getCiudad()).thenReturn("Quilmes");
		
		assertEquals(2, this.filtro.filtrar(this.inmuebles).size());
	}
	
	@Test
	void testFiltradoInmueblesConTodosLosFiltros_AlgunosValidos() {
		
		LocalDate fechaIni = LocalDate.of(2024, 1, 1);
		LocalDate fechaFin = LocalDate.of(2024, 2, 1);
		//Mock de filtros adicionales.
		FiltroPrecio filtroPrecio = mock(FiltroPrecio.class);
		FiltroCantHuespedes filtroHuespedes = mock(FiltroCantHuespedes.class);
		//Agregado de filtros adicionales a filtro compuesto.
		this.filtro.agregarFiltro(filtroHuespedes);
		this.filtro.agregarFiltro(filtroPrecio);
		//Mock de inmuebles a filtrar.
		Inmueble inmueble1 = mock(Inmueble.class);
		Inmueble inmueble2 = mock(Inmueble.class);
		this.inmuebles.add(inmueble1);
		this.inmuebles.add(inmueble2);
		//Comportamiento mocks filtros adicionales para ambos inmuebles true.
		when(filtroPrecio.cumplePara(inmueble1, this.filtro)).thenReturn(true);
		when(filtroPrecio.cumplePara(inmueble2, this.filtro)).thenReturn(false);
		when(filtroHuespedes.cumplePara(inmueble1, this.filtro)).thenReturn(true);
		when(filtroHuespedes.cumplePara(inmueble2, this.filtro)).thenReturn(false);
		//Comportamiento mocks para disponibilidad de fechas true.
		when(inmueble1.estaDisponibleEn(fechaIni, fechaFin)).thenReturn(true);
		when(inmueble2.estaDisponibleEn(fechaIni, fechaFin)).thenReturn(true);
		//Comportamiento mocks para filtro ciudad true.
		when(inmueble1.getCiudad()).thenReturn("Quilmes");
		when(inmueble2.getCiudad()).thenReturn("Quilmes");
		
		assertEquals(1, this.filtro.filtrar(this.inmuebles).size());
	}
	
	@Test
	void testFiltradoInmueblesConTodosLosFiltros_NingunoValido() {
		
		LocalDate fechaIni = LocalDate.of(2024, 1, 1);
		LocalDate fechaFin = LocalDate.of(2024, 2, 1);
		//Mock de filtros adicionales.
		FiltroPrecio filtroPrecio = mock(FiltroPrecio.class);
		FiltroCantHuespedes filtroHuespedes = mock(FiltroCantHuespedes.class);
		//Agregado de filtros adicionales a filtro compuesto.
		this.filtro.agregarFiltro(filtroHuespedes);
		this.filtro.agregarFiltro(filtroPrecio);
		//Mock de inmuebles a filtrar.
		Inmueble inmueble1 = mock(Inmueble.class);
		Inmueble inmueble2 = mock(Inmueble.class);
		this.inmuebles.add(inmueble1);
		this.inmuebles.add(inmueble2);
		//Comportamiento mocks filtros adicionales para ambos inmuebles true.
		when(filtroPrecio.cumplePara(inmueble1, this.filtro)).thenReturn(false);
		when(filtroPrecio.cumplePara(inmueble2, this.filtro)).thenReturn(true);
		when(filtroHuespedes.cumplePara(inmueble1, this.filtro)).thenReturn(true);
		when(filtroHuespedes.cumplePara(inmueble2, this.filtro)).thenReturn(false);
		//Comportamiento mocks para disponibilidad de fechas true.
		when(inmueble1.estaDisponibleEn(fechaIni, fechaFin)).thenReturn(false);
		when(inmueble2.estaDisponibleEn(fechaIni, fechaFin)).thenReturn(true);
		//Comportamiento mocks para filtro ciudad true.
		when(inmueble1.getCiudad()).thenReturn("Quilmes");
		when(inmueble2.getCiudad()).thenReturn("CABA");
		
		assertEquals(0, this.filtro.filtrar(this.inmuebles).size());
	}
	
}
