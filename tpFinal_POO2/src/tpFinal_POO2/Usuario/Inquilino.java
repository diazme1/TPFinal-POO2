package tpFinal_POO2.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import tpFinal_POO2.Valoracion.Valoracion;
import tpFinal_POO2.Reserva.Reserva;

public interface Inquilino {
	public double promedioValoracionInquilino();
	public List<Valoracion> getValoracionesInquilino();
	public List<Reserva> getReservas();
	public List<Reserva> reservasFuturas(LocalDate hoy);
	public List<Reserva> reservasEnCiudad(String ciudad);
	public Set<String> ciudadesReservadas();
	public void agregarReserva(Reserva reserva);
	public void abonarMonto(Double monto);
	public String getEmail();
	public void agregarValoracion(Valoracion valoracion);
	public int cantReservasFinalizadasInquilino();
}
