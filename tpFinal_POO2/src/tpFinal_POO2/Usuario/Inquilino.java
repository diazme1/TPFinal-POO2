package tpFinal_POO2.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import tpFinal_POO2.Valoracion.Valoracion;
import tpFinal_POO2.Reserva.Reserva;

public interface Inquilino {
	// COMPORTAMIENTO INQUILINO
	public double promedioValoracionInquilino();
	public List<Reserva> reservasFuturas(LocalDate hoy);
	public List<Reserva> reservasEnCiudad(String ciudad);
	public Set<String> ciudadesReservadas();
	public void agregarReserva(Reserva reserva);
	public void abonarMonto(Double monto);
	public void agregarValoracionInquilino(Valoracion valoracion);
	public int cantReservasFinalizadasInquilino();
	// GETTTERS
	public String getEmail();
	public String getNombre(); 
	public String getTelefono();
	public List<Reserva> getReservas();
	public List<Valoracion> getValoracionesInquilino();
	public int getAntiguedadUsuario(LocalDate hoy);
	public double promedioValoracionCategoria(String c);
	
}
