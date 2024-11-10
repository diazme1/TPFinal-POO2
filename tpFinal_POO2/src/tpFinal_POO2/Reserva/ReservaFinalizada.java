package tpFinal_POO2.Reserva;

public class ReservaFinalizada implements EstadoReserva {

	@Override
	public void cancelarReserva(Reserva r) {
	}

	@Override
	public boolean esPosibleCancelar() {
		return false;
	}

	@Override
	public void siguienteEstado(Reserva r) {
	}

	@Override
	public boolean esPosibleRankear() {
		return true;
	}

	@Override
	public boolean puedeSerAceptado() {
		return false;
	}

}
