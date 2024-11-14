package tpFinal_POO2.Reserva;

public class EstadoCancelada extends EstadoReserva{

	public EstadoCancelada() {}
	
	@Override
	public boolean esCancelada(Reserva reserva) {return true;};
	
}
