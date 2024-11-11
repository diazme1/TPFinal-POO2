package tpFinal_POO2.Usuario;

import java.util.List;

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

}
