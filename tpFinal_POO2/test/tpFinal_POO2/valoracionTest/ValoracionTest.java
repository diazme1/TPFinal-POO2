package tpFinal_POO2.valoracionTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpFinal_POO2.Valoracion.Valoracion;

class ValoracionTest {
	
	private Valoracion valoracion;

	@BeforeEach
	void setUp() throws Exception {
		this.valoracion = new Valoracion("Vacacionar", 3, "Lejos de la playa");
		
	}

	@Test
	void testGetters() {
		assertEquals("Vacacionar", this.valoracion.getCategoria());
		assertEquals("Lejos de la playa", this.valoracion.getComentario());
		assertEquals(3, this.valoracion.getPuntaje());
	}
	
	@Test
	void testInstanciarConPuntajeMenorA1() {
		Valoracion valoracion1 = new Valoracion("Co-working", 0, "Malisimo");
		
		//Si se inicializa con menor a 1, se asigna 1:
		assertEquals(1, valoracion1.getPuntaje());
	}
	
	@Test
	void testInstanciarConPuntajeMayorA5() {
		Valoracion valoracion2 = new Valoracion("Co-working", 7, "Increible");
		
		//Si se inicializa con mayor a 5, se limita a 5:
		assertEquals(5, valoracion2.getPuntaje());
	}

}
