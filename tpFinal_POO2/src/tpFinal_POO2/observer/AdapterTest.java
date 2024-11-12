package tpFinal_POO2.observer;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

class AdapterTest {
	private PopUpWindow mockPopUp = mock(PopUpWindow.class);
	private HomePagePublisher mockSite = mock(HomePagePublisher.class);
	private AdapterAppMovil appMovil= new AdapterAppMovil(mockPopUp);
	private AdapterSitioWeb sitioWeb= new AdapterSitioWeb(mockSite);
	private Inmueble obs = mock(Inmueble.class);
	
	@Test
	void testAdapter() {
		when(obs.getTipoInmueble()).thenReturn("Quincho");
		when(obs.getPrecio()).thenReturn(12000.00);
		appMovil.popUp(obs);
		sitioWeb.publish(obs);
		verify(mockPopUp).popUp("El/la "+obs.getTipoInmueble()+" que te interesaba se ha liberado! Corre a reservarlo!", "Black", 17);
		verify(mockSite).publish("Un Inmueble tipo " + obs.getTipoInmueble() + " a tan solo " + obs.getPrecio() + " pesos!");
	}

}
