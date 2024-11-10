package tpFinal_POO2.Reserva;

public interface EstadoReserva {
	
	public void cancelarReserva(Reserva r); // para camiarle el estado desde el propio estado y no romper el state

	public boolean esPosibleCancelar();
	
	public void siguienteEstado(Reserva r);
	
	public boolean esPosibleRankear();
	
	public boolean puedeSerAceptado();
	
}
