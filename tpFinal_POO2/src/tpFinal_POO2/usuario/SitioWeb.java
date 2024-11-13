package tpFinal_POO2.usuario;

import tpFinal_POO2.observer.Inmueble;

public interface SitioWeb {
	public boolean esValidoInmueble(Inmueble im);
	public boolean esValidaCategoriaInquilino(String categoria);
	public boolean esValidaCategoriaPropietario(String categoria);
	public boolean esValidaCategoriaInmueble(String categoria);
}
