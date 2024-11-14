package tpFinal_POO2.PoliticasCancelacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import tpFinal_POO2.Usuario.Inquilino;
import tpFinal_POO2.Reserva.Reserva;

public class CancelacionBasica implements PoliticaCancelacion {

	@Override
	public void cancelarReserva(Reserva r, LocalDate fechaCancelacion) {
		
		long diasPrevios = ChronoUnit.DAYS.between(fechaCancelacion, r.getCheckIn());
			
		if (diasPrevios < 10) {
			Inquilino inq = r.getInquilino();
			double multaCancelacion = r.getInmueble().getMontoTotalPara(r.getCheckIn(), r.getCheckIn().plusDays(1));
			inq.abonarMonto(multaCancelacion);
		}

	}

}
