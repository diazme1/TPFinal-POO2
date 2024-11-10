package tpFinal_POO2.Reserva;

public class EstadoAceptada extends EstadoReserva {

	public EstadoAceptada() {}

	@Override
	public void cancelarReserva(Reserva r) {
		r.cambiaEstadoA(new EstadoCancelada());
	} 

	@Override
	public void siguienteEstado(Reserva r) {
		r.cambiaEstadoA(new EstadoFinalizada());
	}
		

}
