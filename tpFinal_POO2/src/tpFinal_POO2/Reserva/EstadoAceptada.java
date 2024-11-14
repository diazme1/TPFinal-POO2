package tpFinal_POO2.Reserva;

import java.time.LocalDate;

public class EstadoAceptada extends EstadoReserva {

	public EstadoAceptada() {}

	@Override
	public void cancelarReserva(Reserva r, LocalDate diaHecho) {
		r.cambiaEstadoA(new EstadoCancelada());
		r.enviarMailCancelacion();
		r.getInmueble().reservaCancelada(r,diaHecho);
	} 

	@Override
	public void siguienteEstado(Reserva r) {
		r.cambiaEstadoA(new EstadoFinalizada());
	}
	
	@Override
	public boolean esAprobada(Reserva reserva){return true;};

}
