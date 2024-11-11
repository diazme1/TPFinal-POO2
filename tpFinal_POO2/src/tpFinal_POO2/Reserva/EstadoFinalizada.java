package tpFinal_POO2.Reserva;

import tpFinal_POO2.Valoracion.Valoracion;

public class EstadoFinalizada extends EstadoReserva {

	public EstadoFinalizada(){};
	
	@Override
	public void rankearPropietario(Reserva r,Valoracion val) {
		r.getPropietario().agregarValoracion(val);
	};

	@Override
	public void rankearInquilino(Reserva r,Valoracion val) {
		r.getInquilino().agregarValoracion(val);
	};
	
	@Override
	public void rankearInmueble(Reserva r,Valoracion val) {
		r.getInmueble().agregarValoracion(val);
	};
	
	@Override
	public boolean puedeValorar(Reserva reserva) {return true;};
}
