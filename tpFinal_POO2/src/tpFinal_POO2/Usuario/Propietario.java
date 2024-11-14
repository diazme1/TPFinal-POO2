package tpFinal_POO2.Usuario;

import java.util.List;

import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.Valoracion.Valoracion;
import tpFinal_POO2.Reserva.Reserva;

public interface Propietario {
	public Double promedioValoracionPropietario();
	public List<Valoracion> getValoracionesPropietario();
	public void darDeAltaInmueble(Inmueble inmu);
	public List<Inmueble> inmueblesAlquilados();
	public int vecesAlquilado();
	public List<Reserva> reservasDeInmuebles();
	public List<Inmueble> getInmuebles();
	public boolean validarCategoriaInmueble(String categoria);
	public String getEmail();
	public void agregarValoracion(Valoracion valoracion);
}
