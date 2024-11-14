package tpFinal_POO2.Inmueble;



import java.time.LocalDate;
import java.util.List;

import tpFinal_POO2.Reserva.Reserva;
import tpFinal_POO2.SitioWeb.Servicio;
import tpFinal_POO2.SitioWeb.TipoDeInmueble;
import tpFinal_POO2.Usuario.Usuario;
import tpFinal_POO2.Valoracion.Valoracion;

public class Inmueble {

	public Usuario getDueño() {
		return null;
	}

	public int getValorDeFecha(LocalDate fechaActual) {
		// TODO Auto-generated method stub
		return 100;
	}

	public Integer cantidadDeReservas() {
		// TODO Auto-generated method stub
		return null;
	}

	public void agregarValoracion(Valoracion val) {
		// TODO Auto-generated method stub
		
	}

	public String getCiudad() {
		// TODO Auto-generated method stub
		return null;
	}

	public void reservaCancelada(Reserva r, LocalDate diaHecho) {
		// TODO Auto-generated method stub
		
	}

	public int getMontoTotal(LocalDate checkIn, LocalDate checkOut) {
		int valFinal = 0;
		LocalDate fechaActual = checkIn;
		while(!fechaActual.isAfter(checkOut)) {
			valFinal+=this.getValorDeFecha(fechaActual);
			fechaActual =fechaActual.plusDays(1);
		}
		return valFinal;
	}

	public String getTipoDeInmueble() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getServicios() {
		// TODO Auto-generated method stub
		return null;
	}


}
