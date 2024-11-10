package tpFinal_POO2.Reserva;

public class EstadoSolicitada implements EstadoReserva{
	
	public EstadoSolicitada() {}

	@Override
	public void cancelarReserva(Reserva r) {
		r.cambiaEstadoA(new EstadoCancelada());
	}

	@Override
	public boolean esPosibleCancelar() {
		return true;
	}

	@Override
	public void siguienteEstado(Reserva r) {
		r.cambiaEstadoA(new EstadoAceptada());
	}

	@Override
	public boolean esPosibleRankear() {
		return false;
	}

	@Override
	public boolean puedeSerAceptado() {
		return true;
	}
	
	
}
