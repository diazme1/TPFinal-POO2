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
		//Mocks de listeners reserva:
		ListenerReserva lReserva = mock(ListenerReserva.class);
		ListenerReserva lReserva2 = mock(ListenerReserva.class);
		
		//Attach de listeners reservas al mapReservas:
		this.observer.attachReserva(obs, lReserva);
		this.observer.attachReserva(obs, lReserva2);
		
		//Notify de reserva generada:
		this.observer.notifyReserva(obs);
		
		//Verify de notificaciones:
		verify(lReserva).notifyReserva(obs);
		verify(lReserva2).notifyReserva(obs);
		
		//Remove de un listener de reserva:
		this.observer.removeReserva(obs, lReserva);
		//Notify de reserva generada:
		this.observer.notifyReserva(obs);
		
		//Verify de notifys a listeners:
		verify(lReserva,times(1)).notifyReserva(obs);
		verify(lReserva2,times(2)).notifyReserva(obs);
		}
	
	
	@Test
	void BajaPrecioTest() {
		//Mock de inmueble y listeners baja precio:
		Inmueble obs = mock(Inmueble.class);
		ListenerBajaPrecio lBajaPrecio = mock(ListenerBajaPrecio.class);
		ListenerBajaPrecio lBajaPrecio2 = mock(ListenerBajaPrecio.class);
		
		//Attach de listener de baja de precio:
		this.observer.attachBajaPrecio(obs, lBajaPrecio);
		this.observer.attachBajaPrecio(obs, lBajaPrecio2);
		
		//Notify de baja de precio:
		this.observer.notifyBajaPrecio(obs);
		
		//Verify de mensaje publish listener baja precio:
		verify(lBajaPrecio).publish(obs);
		verify(lBajaPrecio2).publish(obs);
		
		//Remove listener baja precio:
		this.observer.removeBajaPrecio(obs, lBajaPrecio);
		
		//Notify baja precio:
		this.observer.notifyBajaPrecio(obs);
		
		//Verify interacciones:
		verify(lBajaPrecio,times(1)).publish(obs);
		verify(lBajaPrecio2,times(2)).publish(obs);
		
		};
		
		
	@Test
	void CancelacionTest() {
		//Mock de inmueble y listener cancelacion:
		Inmueble obs = mock(Inmueble.class);
		ListenerCancelacion lCancelacion = mock(ListenerCancelacion.class);
		ListenerCancelacion lCancelacion2 = mock(ListenerCancelacion.class);
		
		//Attach listeners cancelacion:
		this.observer.attachCancelacion(obs, lCancelacion);
		this.observer.attachCancelacion(obs, lCancelacion2);
		
		//Noitfy cancelacion:
		this.observer.notifyCancelacion(obs);
		
		//Verify de mensaje popUp listener cancelacion:
		verify(lCancelacion).popUp(obs);
		verify(lCancelacion2).popUp(obs);
		
		//Remove listener cancelacion:
		this.observer.removeCancelacion(obs, lCancelacion);
		
		//Notify cancelacion:
		this.observer.notifyCancelacion(obs);
		
		//Verify interacciones con listeners:
		verify(lCancelacion,times(1)).popUp(obs);
		verify(lCancelacion2,times(2)).popUp(obs);
		
		}

}
