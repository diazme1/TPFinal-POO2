package tpFinal_POO2.observer;

import java.util.ArrayList;

import tpFinal_POO2.usuario.Reserva;

public interface Inmueble {
	public void update();

	public String getTipoInmueble();
	
	public double getPrecio();

	public int vecesAlquilado();
	
	public ArrayList<Reserva> getReservas();
}
