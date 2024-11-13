package tpFinal_POO2.Usuario;

import java.util.ArrayList;
import java.util.List;

import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.Reserva.Reserva;
import tpFinal_POO2.Valoracion.Valoracion;

public class Usuario implements Inquilino,Propietario{

	public void agregarValoracion(Valoracion val) {
	}

	public String getEmail() {
		return "email";
	}

	public List<Reserva> getReservas() {
		return null;
	}

	public List<Inmueble> inmueblesAlquilados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Inmueble> getInmuebles() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object esInquilino() {
		// TODO Auto-generated method stub
		return null;
	}

	public int promedioRankingInquilino() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int cantReservasFinalizadasInquilino() {
		// TODO Auto-generated method stub
		return null;
	}

}
