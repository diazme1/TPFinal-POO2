package tpFinal_POO2.Inmueble;

import java.time.LocalDate;

public class Periodo {

	private LocalDate fechaIni;
	private LocalDate fechaFin;
	private double incremento;
	
	public Periodo(LocalDate fechaInicio, LocalDate fechaFin, double precio) {
		super();
		this.fechaIni = fechaInicio;
		this.fechaFin = fechaFin;
		this.incremento = precio;
	};
	
	public double getIncremento() {
		return this.incremento;
	}
	
	public boolean incluidoEnPeriodo(LocalDate fecha) {
		return (fecha.isAfter(this.fechaIni) || fecha.isEqual(this.fechaIni)) && (fecha.isBefore(this.fechaFin) || fecha.isEqual(this.fechaFin));
	}
		
}
