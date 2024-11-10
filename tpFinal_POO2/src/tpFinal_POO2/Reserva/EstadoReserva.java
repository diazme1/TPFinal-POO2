package tpFinal_POO2.Reserva;

public abstract class EstadoReserva {
	
	public void cancelarReserva(Reserva r) {};
	
	public void siguienteEstado(Reserva r) {};
	
	public void aprobarReserva(Reserva r) {};
	
	public void rankearPropietario(Reserva r,Valoracion val) {};
	
	public void rankearInquilino(Reserva r,Valoracion val) {};
	
	public void rankearInmueble(Reserva r,Valoracion val) {};
	
}
