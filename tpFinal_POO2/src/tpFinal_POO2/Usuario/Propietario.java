package tpFinal_POO2.Usuario;

import java.time.LocalDate;
import java.util.List;

import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.Valoracion.Valoracion;
import tpFinal_POO2.Reserva.Reserva;

public interface Propietario {
	// COMPORTAMIENTO PROPIETARIO
	public Double promedioValoracionPropietario();
	public void darDeAltaInmueble(Inmueble inmu);
	public List<Inmueble> inmueblesAlquilados();
	public int vecesAlquilado();
	public List<Reserva> reservasDeInmuebles();
	public boolean validarCategoriaInmueble(String categoria);
	public void agregarValoracionPropietario(Valoracion val);
	// GETTTERS
	public String getEmail();
	public String getNombre(); 
	public String getTelefono();
	public List<Inmueble> getInmuebles();
	public List<Valoracion> getValoracionesPropietario();
	public int getAntiguedadUsuario(LocalDate hoy);
	public double promedioValoracionCategoria(String c);
	
}
