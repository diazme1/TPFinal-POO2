package observer;

import java.util.HashMap;
import java.util.HashSet;

public class Observer {
	private HashMap<Observable, HashSet<ListenerReserva>> mapReserva = new HashMap<Observable, HashSet<ListenerReserva>>(); 
	//Map de subscriptores a Reservas
	private HashMap<Observable, HashSet<ListenerBajaPrecio>> mapBajaPrecio= new HashMap<Observable, HashSet<ListenerBajaPrecio>>(); 
	//Map de subscriptores a BajaPrecio
	private HashMap<Observable, HashSet<ListenerCancelacion>> mapCancelacion= new HashMap<Observable, HashSet<ListenerCancelacion>>(); 
	//Map de subscriptores a Cancelacion
	
	public void attachBajaPrecio(Observable observable, ListenerBajaPrecio listener) {
		//Añade un subscritor al map de BajaPrecio, si no esta como clave lo añade
		if(mapBajaPrecio.containsKey(observable)){
			mapBajaPrecio.get(observable).add(listener);
		}else {
			HashSet<ListenerBajaPrecio> nuevoSet = new HashSet<ListenerBajaPrecio>();
			nuevoSet.add(listener);
			mapBajaPrecio.put(observable,nuevoSet);
		}
	};
	
	public void removeBajaPrecio(Observable observable, ListenerBajaPrecio listener) {
		//elimina a un subscriptor de el map de BajaPrecio
		if(mapBajaPrecio.containsKey(observable)) {
			mapBajaPrecio.get(observable).remove(listener);
		};
	};
	public void attachCancelacion(Observable observable, ListenerCancelacion listener) {
		//Añade un subscritor al map de cancelacion, si no esta como clave lo añade
		if(mapCancelacion.containsKey(observable)){
			mapCancelacion.get(observable).add(listener);
		}else {
			HashSet<ListenerCancelacion> nuevoSet = new HashSet<ListenerCancelacion>();
			nuevoSet.add(listener);
			mapCancelacion.put(observable,nuevoSet);
		}
	};
	
	public void removeCancelacion(Observable observable, ListenerCancelacion listener) {
		//elimina a un subscriptor de el map de Cancelacion
		if(mapCancelacion.containsKey(observable)) {
			mapCancelacion.get(observable).remove(listener);
		};
	};
	public void attachReserva(Observable observable, ListenerReserva listener) {
		//Añade un subscritor al map de subcriptores, si no esta como clave lo añade
		if(mapReserva.containsKey(observable)){
			mapReserva.get(observable).add(listener);
		}else {
			HashSet<ListenerReserva> nuevoSet = new HashSet<ListenerReserva>();
			nuevoSet.add(listener);
			mapReserva.put(observable,nuevoSet);
		}
	};
	
	public void removeReserva(Observable observable, ListenerReserva listener) {
		//elimina a un subscriptor de el map de reservas
		if(mapReserva.containsKey(observable)) {
			mapReserva.get(observable).remove(listener);
		};
	};
	
	public void notifyReserva(Observable observable) {
		//notifica a cada uno de los subscriptores
		mapReserva.get(observable).forEach(sub -> sub.notifyReserva("Se ha reservado el inmueble"));
	};
	
	public void notifyCancelacion(Observable observable) {
		//notifica a cada uno de los subscriptores
		mapCancelacion.get(observable).forEach(sub -> sub.popUp("El "+observable.getTipoInmueble()+" que te interesa se ha liberado! Corre a reservarlo!"));
	};
	
	public void notifyBajaPrecio(Observable observable) {
		//notifica a cada uno de los subscriptores
		mapBajaPrecio.get(observable).forEach(sub -> sub.publish("Un "+ observable.getTipoInmueble() + " a tan solo " + observable.getPrecio() + " pesos"));
	};
	
	
	
}
