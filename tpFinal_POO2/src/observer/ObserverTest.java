package observer;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class ObserverTest {
	private Observer observer = new Observer();
	private Observable obs = mock(Observable.class);
	
	
	@Test
	void ReservasTest() {
		ListenerReserva lReserva = mock(ListenerReserva.class);
		ListenerReserva lReserva2 = mock(ListenerReserva.class);
		this.observer.attachReserva(obs, lReserva);
		this.observer.attachReserva(obs, lReserva2);
		this.observer.notifyReserva(obs);
		verify(lReserva).notifyReserva("Se ha reservado el inmueble");
		verify(lReserva2).notifyReserva("Se ha reservado el inmueble");
		this.observer.removeReserva(obs, lReserva);
		this.observer.notifyReserva(obs);
		verify(lReserva,times(1)).notifyReserva("Se ha reservado el inmueble");
		verify(lReserva2,times(2)).notifyReserva("Se ha reservado el inmueble");
		}
	@Test
	void BajaPrecioTest() {
		Observable obs = mock(Observable.class);
		ListenerBajaPrecio lBajaPrecio = mock(ListenerBajaPrecio.class);
		ListenerBajaPrecio lBajaPrecio2 = mock(ListenerBajaPrecio.class);
		when(obs.getTipoInmueble()).thenReturn("habitacion");
		when(obs.getPrecio()).thenReturn(12000.00);
		this.observer.attachBajaPrecio(obs, lBajaPrecio);
		this.observer.attachBajaPrecio(obs, lBajaPrecio2);
		this.observer.notifyBajaPrecio(obs);
		verify(lBajaPrecio).publish("Un "+ "habitacion" + " a tan solo " + 12000.00 + " pesos");
		verify(lBajaPrecio2).publish("Un "+ "habitacion" + " a tan solo " + 12000.00 + " pesos");
		this.observer.removeBajaPrecio(obs, lBajaPrecio);
		this.observer.notifyBajaPrecio(obs);
		verify(lBajaPrecio,times(1)).publish("Un "+ "habitacion" + " a tan solo " + 12000.00 + " pesos");
		verify(lBajaPrecio2,times(2)).publish("Un "+ "habitacion" + " a tan solo " + 12000.00 + " pesos");
		};
	@Test
	void CancelacionTest() {
		Observable obs = mock(Observable.class);
		when(obs.getTipoInmueble()).thenReturn("quincho");
		ListenerCancelacion lCancelacion = mock(ListenerCancelacion.class);
		ListenerCancelacion lCancelacion2 = mock(ListenerCancelacion.class);
		this.observer.attachCancelacion(obs, lCancelacion);
		this.observer.attachCancelacion(obs, lCancelacion2);
		this.observer.notifyCancelacion(obs);
		verify(lCancelacion).popUp("El "+"quincho"+" que te interesa se ha liberado! Corre a reservarlo!");
		verify(lCancelacion2).popUp("El "+"quincho"+" que te interesa se ha liberado! Corre a reservarlo!");
		this.observer.removeCancelacion(obs, lCancelacion);
		this.observer.notifyCancelacion(obs);
		verify(lCancelacion,times(1)).popUp("El "+"quincho"+" que te interesa se ha liberado! Corre a reservarlo!");
		verify(lCancelacion2,times(2)).popUp("El "+"quincho"+" que te interesa se ha liberado! Corre a reservarlo!");
		}

}
