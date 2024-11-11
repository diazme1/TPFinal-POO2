package tpFinal_POO2.Reserva;

public class EstadoAceptada extends EstadoReserva {

	public EstadoAceptada() {}

	@Override
	public void cancelarReserva(Reserva r) {
		r.cambiaEstadoA(new EstadoCancelada());
		r.enviarMailCancelacion();
		r.getInmueble().reservaCancelada(r);
	} 

	@Override
	public void siguienteEstado(Reserva r) {
		r.cambiaEstadoA(new EstadoFinalizada());
	}
	
	@Override
	public boolean esAprobada(Reserva reserva){return true;};

}
