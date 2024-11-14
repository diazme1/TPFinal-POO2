package tpFinal_POO2.PoliticasCancelacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import tpFinal_POO2.Externos.Inquilino;
import tpFinal_POO2.Externos.Reserva;

public class CancelacionIntermedia implements PoliticaCancelacion {

	@Override
	public void cancelarReserva(Reserva r, LocalDate fechaCancelacion) {
		
		long diasPrevios = ChronoUnit.DAYS.between(fechaCancelacion, r.getCheckIn());
		Inquilino inq = r.getInquilino();
		
		if (19 >= diasPrevios && diasPrevios >= 10) {
			
			double multaCancelacion = r.getMontoTotal() * 0.50;
			inq.abonar(multaCancelacion);
			
		} else if (diasPrevios < 10) {

			double multaCancelacion = r.getMontoTotal();
			inq.abonar(multaCancelacion);
			
		}

	}

}