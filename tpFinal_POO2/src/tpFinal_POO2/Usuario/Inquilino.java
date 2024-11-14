package tpFinal_POO2.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public interface Inquilino {
	public double promedioValoracionInquilino();
	public ArrayList<Valoracion> getValoracionesInquilino();
	public ArrayList <Reserva> getReservas();
	public ArrayList <Reserva> reservasFuturas(Date hoy);
	public ArrayList <Reserva> reservasEnCiudad(String ciudad);
	public HashSet<String> ciudadesReservadas();
	public void añadirReserva(Reserva reserva);
	public void abonarMonto(Double monto);
}
