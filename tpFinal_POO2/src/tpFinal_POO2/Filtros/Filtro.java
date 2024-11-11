package tpFinal_POO2.Filtros;
import tpFinal_POO2.Inmueble.Inmueble;

public interface Filtro {

	public boolean cumplePara(Inmueble inmueble, FiltroCompuesto f);
	
}
