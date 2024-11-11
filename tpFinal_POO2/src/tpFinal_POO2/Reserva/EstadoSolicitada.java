package tpFinal_POO2.Reserva;

public class EstadoSolicitada extends EstadoReserva{
	
	public EstadoSolicitada() {}

	@Override
	public void siguienteEstado(Reserva r) {
		r.cambiaEstadoA(new EstadoAceptada());
	}

	@Override
	public void aprobarReserva(Reserva r) {
		this.siguienteEstado(r);
		r.enviarMailConfirmacion();
	}
	
}
