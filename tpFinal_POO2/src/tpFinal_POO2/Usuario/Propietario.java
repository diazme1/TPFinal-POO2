package tpFinal_POO2.Usuario;

import java.util.ArrayList;
import java.util.List;

import tpFinal_POO2.Inmueble.Inmueble;

public interface Propietario {
	
	public ArrayList<Inmueble> getInmuebles();

	public List<Inmueble> inmueblesAlquilados();
}
