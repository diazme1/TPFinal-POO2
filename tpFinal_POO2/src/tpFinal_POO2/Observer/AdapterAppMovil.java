package tpFinal_POO2.Observer;

import tpFinal_POO2.Inmueble.Inmueble;

public class AdapterAppMovil implements ListenerCancelacion{
	private PopUpWindow adaptee;
	
	public AdapterAppMovil(PopUpWindow adaptee) {
		this.adaptee = adaptee;
	}
		
	public void popUp(Inmueble im) {
		adaptee.popUp("El/la "+im.getTipoInmueble()+" que te interesaba se ha liberado! Corre a reservarlo!", "Black", 17);
	}
}
