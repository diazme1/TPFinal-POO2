package tpFinal_POO2.Reserva;

import java.time.LocalDate;

import tpFinal_POO2.Valoracion.Valoracion;

public abstract class EstadoReserva {
	
	public void cancelarReserva(Reserva r, LocalDate diaHecho) {};
	
	public void siguienteEstado(Reserva r) {};
	
	public void aprobarReserva(Reserva r) {};
	
	public void rankearPropietario(Reserva r,Valoracion val) {};
	
	public void rankearInquilino(Reserva r,Valoracion val) {};
	
	public void rankearInmueble(Reserva r,Valoracion val) {};

	public boolean esAprobada(Reserva reserva){return false;};

	public boolean puedeValorar(Reserva reserva) {return false;}

	public boolean esCancelada(Reserva reserva) {return false;}

	public boolean esFinalizada() {return false;}
	
}
