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
	
	public double montoExtraordinarioPara(double precioBase, LocalDate fechaEntrada, LocalDate fechaSalida) {
		
		double precioAcumulado = 0.00;
        LocalDate fechaActual = fechaEntrada;
        
        while(!fechaActual.isAfter(this.fechaFin) && !fechaActual.isAfter(fechaSalida)) {
        	
        	if (fechaActual.isAfter(this.fechaIni) || fechaActual.isEqual(this.fechaIni)) {
            	precioAcumulado += (precioBase + this.incremento);
                fechaActual = fechaActual.plusDays(1);
        	} else {
        		fechaActual = fechaActual.plusDays(1);
        	}
        }
        return precioAcumulado;
	}
		
}
