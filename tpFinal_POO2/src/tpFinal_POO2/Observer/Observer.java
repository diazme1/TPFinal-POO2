package tpFinal_POO2.Observer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import tpFinal_POO2.Inmueble.Inmueble;

public class Observer {
	private static Observer instance;
	//la instancia
	private HashMap<Inmueble, Set<ListenerReserva>> mapReserva = new HashMap<Inmueble, Set<ListenerReserva>>(); 
	//Map de subscriptores a Reservas
	private HashMap<Inmueble, Set<ListenerBajaPrecio>> mapBajaPrecio= new HashMap<Inmueble, Set<ListenerBajaPrecio>>(); 
	//Map de subscriptores a BajaPrecio
	private HashMap<Inmueble, Set<ListenerCancelacion>> mapCancelacion= new HashMap<Inmueble, Set<ListenerCancelacion>>(); 
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
			Set<ListenerBajaPrecio> nuevoSet = new HashSet<ListenerBajaPrecio>();
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
			Set<ListenerCancelacion> nuevoSet = new HashSet<ListenerCancelacion>();
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
		if (mapReserva.containsKey(observable)) {
			mapReserva.putIfAbsent(observable, new HashSet<ListenerReserva>());
			mapReserva.get(observable).forEach(sub -> sub.notifyReserva(observable));
		}
	};
	
	public void notifyCancelacion(Inmueble observable) {
		//notifica a cada uno de los subscriptores
		if (mapCancelacion.containsKey(observable)) {
			mapCancelacion.putIfAbsent(observable, new HashSet<ListenerCancelacion>());
			mapCancelacion.get(observable).forEach(sub -> sub.popUp(observable));
		}

	};
	
	public void notifyBajaPrecio(Inmueble observable) {
		//notifica a cada uno de los subscriptores
		if (mapBajaPrecio.containsKey(observable)) {
			mapBajaPrecio.putIfAbsent(observable, new HashSet<ListenerBajaPrecio>());
			mapBajaPrecio.get(observable).forEach(sub -> sub.publish(observable));
		}
	};
	
	
	
}
