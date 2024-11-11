package tpFinal_POO2.Inmueble;

import java.time.LocalDate;

//Clase abstracta para imports, ELIMINAR cuando se haya desarrollado el inmueble.
public abstract class Inmueble {

	public abstract boolean estaDisponibleEn(LocalDate checkIn, LocalDate checkOut);

	public abstract double getMontoTotal(LocalDate fechaInicio, LocalDate fechaFin);

	public abstract String getCiudad();

	public abstract int getCantHuespedes();

}
