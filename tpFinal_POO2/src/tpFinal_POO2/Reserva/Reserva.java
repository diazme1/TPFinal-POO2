package tpFinal_POO2.Reserva;

import java.time.LocalDate;

import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.Usuario.Usuario;
import tpFinal_POO2.Valoracion.Valoracion;

public class Reserva {
	
	private Inmueble inmueble;
	private FormaDePago formaDePago;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private Usuario inquilino;
	private EstadoReserva estado;
	
	public Reserva(Inmueble inmueble, FormaDePago formaDePago, LocalDate checkIn, LocalDate checkOut, Usuario inquilino) {
		this.inmueble = inmueble;
		this.formaDePago = formaDePago;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.inquilino=inquilino;
		this.estado= new EstadoSolicitada();
		
	}
	
	public void aprobarReserva() {
			this.estado.aprobarReserva(this);
	}
	
	public void realizarCheckOut() {
		if(LocalDate.now().equals(this.checkOut)) {
			this.estado.siguienteEstado(this);
		}
	}
	
	public Usuario getInfoPosibleInquilino() {
		return this.inquilino;
	}
	
	public float getMontoTotal() {
		float valFinal = 0;
		LocalDate fechaActual = this.checkIn;
		while(!fechaActual.isAfter(this.checkOut)) {
			valFinal+=this.inmueble.getValorDeFecha(fechaActual);
			fechaActual.plusDays(1);
		}
		return valFinal;
	}
	
	public void cancelarReserva() {
		this.estado.cancelarReserva(this);
	}
	
	public Usuario getPropietario() {
		return this.inmueble.getDueño();
	}
	
	public Usuario getInquilino() {
		return this.inquilino;
	}
	
	public Inmueble getInmueble() {
		return this.inmueble;
	}
	
	public void rankearInmueble(Valoracion val) {
		this.estado.rankearInmueble(this,val);
	}
	
	public void rankearInquilino(Valoracion val) {
		this.estado.rankearInquilino(this,val);
	}
	
	public void rankearPropietario(Valoracion val) {
		this.estado.rankearPropietario(this,val);
	}
	
	public void cambiaEstadoA(EstadoReserva nuevoEstado) {
		this.estado=nuevoEstado;
	}
	
	public boolean estaAprobada(){
		return this.estado.esAprobada(this);
	}
	
	public boolean puedenRankear(){
		return this.estado.puedenRankear(this);
	}
}