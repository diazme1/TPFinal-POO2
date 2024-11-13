package tpFinal_POO2.Filtros;

import java.time.LocalDate;

import tpFinal_POO2.Inmueble.Inmueble;

public class FiltroFecha implements Filtro {

	private LocalDate checkIn;
	private LocalDate checkOut;
	
	public FiltroFecha(LocalDate checkIn, LocalDate checkOut) {
		super();
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	};
	
	public boolean cumplePara(Inmueble inmueble, FiltroCompuesto filtro){
		
		return inmueble.estaDisponible(this.checkIn, this.checkOut);
	};
	
}
