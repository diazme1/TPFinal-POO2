package tpFinal_POO2.PoliticasCancelacion;

import java.time.LocalDate;

import tpFinal_POO2.Reserva.Reserva;

public interface PoliticaCancelacion {

	public void cancelarReserva(Reserva r, LocalDate fechaCancelacion);
}
