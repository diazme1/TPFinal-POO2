package tpFinal_POO2.Filtros;

import tpFinal_POO2.Inmueble;

public class FiltroCantHuespedes implements Filtro {

	private int minimoCantHuespedes;
	
	public FiltroCantHuespedes(int huespedes) {
		super();
		this.minimoCantHuespedes = huespedes;
	};
	
	public boolean cumplePara(Inmueble inmueble, FiltroCompuesto filtro){
		
		return inmueble.getCantHuespedes() >= this.minimoCantHuespedes;
	};
	
}
