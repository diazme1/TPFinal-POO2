package tpFinal_POO2.Usuario;

import java.util.ArrayList;
import java.util.List;

import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.Valoracion.Valoracion;

public interface Propietario {
	public Double promedioValoracionPropietario();
	public List<Valoracion> getValoracionesPropietario();
	public void darDeAltaInmueble(Inmueble inmu);
	public List<Inmueble> inmueblesAlquilados();
	public int vecesAlquilado();
	public List<Reserva> reservasDeInmuebles();
	public List<Inmueble> getInmuebles();
	public boolean validarCategoriaInmueble(String categoria);
}
