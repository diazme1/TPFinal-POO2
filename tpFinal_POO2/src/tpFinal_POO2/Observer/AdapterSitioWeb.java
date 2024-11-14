package tpFinal_POO2.Observer;

import tpFinal_POO2.Inmueble.Inmueble;

public class AdapterSitioWeb implements ListenerBajaPrecio{
	private HomePagePublisher adaptee;
	
	public AdapterSitioWeb(HomePagePublisher adaptee){
		this.adaptee = adaptee;
	}
	@Override
	public void publish(Inmueble im) {
		this.adaptee.publish("Un Inmueble tipo " + im.getTipoInmueble() + " a tan solo " + im.getPrecio() + " pesos!" );
	}

}
