package tpFinal_POO2.observer;

import java.util.HashMap;
import java.util.HashSet;

public class Observer {
	private static Observer instance;
	//la instancia
	private HashMap<Inmueble, HashSet<ListenerReserva>> mapReserva = new HashMap<Inmueble, HashSet<ListenerReserva>>(); 
	//Map de subscriptores a Reservas
	private HashMap<Inmueble, HashSet<ListenerBajaPrecio>> mapBajaPrecio= new HashMap<Inmueble, HashSet<ListenerBajaPrecio>>(); 
	//Map de subscriptores a BajaPrecio
	private HashMap<Inmueble, HashSet<ListenerCancelacion>> mapCancelacion= new HashMap<Inmueble, HashSet<ListenerCancelacion>>(); 
	//Map de subscriptores a Cancelacion
	
	private Observer(){}
	//el constructor deberia ser privado para Singleton
	public static Observer getInstance() {
	//si no hay una instancia de Singleton, la crea, y si ya hay la da
		if (instance== null) {
			instance = new Observer();
		}
		return instance;
	}
	public void attachBajaPrecio(Inmueble observable, ListenerBajaPrecio listener) {
		//Añade un subscritor al map de BajaPrecio, si no esta como clave lo añade
		if(mapBajaPrecio.containsKey(observable)){
			mapBajaPrecio.get(observable).add(listener);
		}else {
			HashSet<ListenerBajaPrecio> nuevoSet = new HashSet<ListenerBajaPrecio>();
			nuevoSet.add(listener);
			mapBajaPrecio.put(observable,nuevoSet);
		}
	};
	
	public void removeBajaPrecio(Inmueble observable, ListenerBajaPrecio listener) {
		//elimina a un subscriptor de el map de BajaPrecio
		if(mapBajaPrecio.containsKey(observable)) {
			mapBajaPrecio.get(observable).remove(listener);
		};
	};
	public void attachCancelacion(Inmueble observable, ListenerCancelacion listener) {
		//Añade un subscritor al map de cancelacion, si no esta como clave lo añade
		if(mapCancelacion.containsKey(observable)){
			mapCancelacion.get(observable).add(listener);
		}else {
			HashSet<ListenerCancelacion> nuevoSet = new HashSet<ListenerCancelacion>();
			nuevoSet.add(listener);
			mapCancelacion.put(observable,nuevoSet);
		}
	};
	
	public void removeCancelacion(Inmueble observable, ListenerCancelacion listener) {
		//elimina a un subscriptor de el map de Cancelacion
		if(mapCancelacion.containsKey(observable)) {
			mapCancelacion.get(observable).remove(listener);
		};
	};
	public void attachReserva(Inmueble observable, ListenerReserva listener) {
		//Añade un subscritor al map de subcriptores, si no esta como clave lo añade
		if(mapReserva.containsKey(observable)){
			mapReserva.get(observable).add(listener);
		}else {
			HashSet<ListenerReserva> nuevoSet = new HashSet<ListenerReserva>();
			nuevoSet.add(listener);
			mapReserva.put(observable,nuevoSet);
		}
	};
	
	public void removeReserva(Inmueble observable, ListenerReserva listener) {
		//elimina a un subscriptor de el map de reservas
		if(mapReserva.containsKey(observable)) {
			mapReserva.get(observable).remove(listener);
		};
	};
	
	public void notifyReserva(Inmueble observable) {
		//notifica a cada uno de los subscriptores
		mapReserva.get(observable).forEach(sub -> sub.notifyReserva(observable));
	};
	
	public void notifyCancelacion(Inmueble observable) {
		//notifica a cada uno de los subscriptores
		mapCancelacion.get(observable).forEach(sub -> sub.popUp(observable));
	};
	
	public void notifyBajaPrecio(Inmueble observable) {
		//notifica a cada uno de los subscriptores
		mapBajaPrecio.get(observable).forEach(sub -> sub.publish(observable));
	};
	
	
	
}
