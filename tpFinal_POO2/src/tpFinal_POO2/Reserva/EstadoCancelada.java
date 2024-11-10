package tpFinal_POO2.Reserva;

public class EstadoCancelada implements EstadoReserva{

	public EstadoCancelada() {}
	
	@Override
	public boolean esPosibleCancelar() {
		return false;
	}

	@Override
	public void cancelarReserva(Reserva r) {
	}

	@Override
	public void siguienteEstado(Reserva r) {
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
