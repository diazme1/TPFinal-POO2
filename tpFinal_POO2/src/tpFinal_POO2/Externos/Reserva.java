package tpFinal_POO2.Externos;

import java.time.LocalDate;

import tpFinal_POO2.Inmueble.Inmueble;

public class Reserva {
	
	public  Reserva(Inmueble i, FormaDePago f, LocalDate cin, LocalDate cout, Inquilino inq) {};
	public  boolean estaFinalizada() {return true;};
	public  boolean estaAceptada() {return true;};
	public  LocalDate getCheckIn() {return LocalDate.of(2024, 1, 1);};
	public  LocalDate getCheckOut() {return LocalDate.of(2024, 1, 1);};
	
	public Inquilino getInquilino() {};
	public Inmueble getInmueble() {};
	
	public double getMontoTotal() {return 0.00;};
	
	public boolean haySolapamiento(LocalDate checkIn, LocalDate checkOut) {
		return checkIn.isBefore(this.getCheckOut()) && checkOut.isAfter(this.getCheckIn());
	}
	
}
