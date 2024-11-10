package tpFinal_POO2.Reserva;

import java.time.LocalDate;

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
	
	public Inquilino getInfoPosibleInquilino() {
		return this.inquilino;
	}
	
	public double getMontoTotal() {
		double valFinal = 0;
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
	
	public Propietario getPropietario() {
		return this.inmueble.getDueño();
	}
	
	public Inquilino getInquilino() {
		return this.inquilino;
	}
	
	public Inmueble getInmueble() {
		return this.inmueble;
	}
	
	public void rankearInmueble(Valoracion val) {
		this.estado.rankearInmueble(val,this);
	}
	
	public void rankearInquilino(Valoracion val) {
		this.estado.rankearInquilino(val,this);
	}
	
	public void rankearPropietario(Valoracion val) {
		this.estado.rankearPropietario(val,this);
	}
	
	public void cambiaEstadoA(EstadoReserva nuevoEstado) {
		this.estado=nuevoEstado;
	}
}