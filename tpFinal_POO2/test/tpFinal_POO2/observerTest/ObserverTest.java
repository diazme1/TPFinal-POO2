package tpFinal_POO2.observerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import tpFinal_POO2.Observer.Observer;

import org.junit.jupiter.api.Test;

import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.Observer.ListenerBajaPrecio;
import tpFinal_POO2.Observer.ListenerCancelacion;
import tpFinal_POO2.Observer.ListenerReserva;

class ObserverTest {
	private Observer observer = Observer.getInstance();
	private Inmueble obs = mock(Inmueble.class);
	
	
	@Test
	void SingletonTest() {
		Observer singleton = Observer.getInstance();
		assertEquals(observer,singleton);
	}
	
	@Test
	void ReservasTest() {
		ListenerReserva lReserva = mock(ListenerReserva.class);
		ListenerReserva lReserva2 = mock(ListenerReserva.class);
		this.observer.attachReserva(obs, lReserva);
		this.observer.attachReserva(obs, lReserva2);
		this.observer.notifyReserva(obs);
		verify(lReserva).notifyReserva(obs);
		verify(lReserva2).notifyReserva(obs);
		this.observer.removeReserva(obs, lReserva);
		this.observer.notifyReserva(obs);
		verify(lReserva,times(1)).notifyReserva(obs);
		verify(lReserva2,times(2)).notifyReserva(obs);
		}
	@Test
	void BajaPrecioTest() {
		Inmueble obs = mock(Inmueble.class);
		ListenerBajaPrecio lBajaPrecio = mock(ListenerBajaPrecio.class);
		ListenerBajaPrecio lBajaPrecio2 = mock(ListenerBajaPrecio.class);
		this.observer.attachBajaPrecio(obs, lBajaPrecio);
		this.observer.attachBajaPrecio(obs, lBajaPrecio2);
		this.observer.notifyBajaPrecio(obs);
		verify(lBajaPrecio).publish(obs);
		verify(lBajaPrecio2).publish(obs);
		this.observer.removeBajaPrecio(obs, lBajaPrecio);
		this.observer.notifyBajaPrecio(obs);
		verify(lBajaPrecio,times(1)).publish(obs);
		verify(lBajaPrecio2,times(2)).publish(obs);
		};
	@Test
	void CancelacionTest() {
		Inmueble obs = mock(Inmueble.class);
		ListenerCancelacion lCancelacion = mock(ListenerCancelacion.class);
		ListenerCancelacion lCancelacion2 = mock(ListenerCancelacion.class);
		this.observer.attachCancelacion(obs, lCancelacion);
		this.observer.attachCancelacion(obs, lCancelacion2);
		this.observer.notifyCancelacion(obs);
		verify(lCancelacion).popUp(obs);
		verify(lCancelacion2).popUp(obs);
		this.observer.removeCancelacion(obs, lCancelacion);
		this.observer.notifyCancelacion(obs);
		verify(lCancelacion,times(1)).popUp(obs);
		verify(lCancelacion2,times(2)).popUp(obs);
		}

}
