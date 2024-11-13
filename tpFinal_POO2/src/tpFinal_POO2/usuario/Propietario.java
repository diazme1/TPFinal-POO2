package tpFinal_POO2.usuario;

import java.util.ArrayList;

import tpFinal_POO2.observer.Inmueble;

public interface Propietario {
	public Double promedioValoracionPropietario();
	public ArrayList<Valoracion> getValoracionesPropietario();
	public void darDeAltaInmueble(Inmueble inmu);
	public ArrayList<Inmueble> inmueblesAlquilados();
	public int vecesAlquilado();
	public ArrayList<Reserva> reservasDeInmuebles();
	public ArrayList<Inmueble> getInmuebles();
	public boolean validarCategoriaInmueble(String categoria);
}
