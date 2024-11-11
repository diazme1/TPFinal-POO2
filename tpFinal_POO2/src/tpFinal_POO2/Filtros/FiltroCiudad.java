package tpFinal_POO2.Filtros;

import tpFinal_POO2.Inmueble;

public class FiltroCiudad implements Filtro {

	private String ciudadFiltro;
	
	public FiltroCiudad(String ciudad) {
		super();
		this.ciudadFiltro = ciudad;
	};
	
	public boolean cumplePara(Inmueble inmueble, FiltroCompuesto f){

		return inmueble.getCiudad() == this.ciudadFiltro;
	};
}
