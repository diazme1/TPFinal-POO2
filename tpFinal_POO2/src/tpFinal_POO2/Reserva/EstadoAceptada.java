package tpFinal_POO2.Reserva;

public class EstadoAceptada implements EstadoReserva {

	public EstadoAceptada() {}
		
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
		r.cambiaEstadoA(new ReservaFinalizada());
	}

	@Override
	public boolean esPosibleRankear() {
		return false;
	}

	@Override
	public boolean puedeSerAceptado() {
		return false;
	}

}
