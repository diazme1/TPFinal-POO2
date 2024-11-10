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
		this.estado= new EstadoSolicitada();	// POR SINGLETON SIN EL NEW
		
	}
	
	public void aprobarReserva() {
		// SIGNIFICA QUE LA RESERVA ESTA EN ESTADO SOLICITADA Y LA APRUEBA EL PROPIETARIO 
		if(this.estado.puedeSerAceptado()) {
			this.estado.siguienteEstado(this);
			this.inquilino.recibeEmailDeConfirmacion(); // ESTA BIEN ASI O TENGO QUE MANDARLO A TRAVES DEL EMAIL? / MAIL SENDER
		}
	}
	
	public Inquilino getInfoPosibleInquilino() {
		return this.inquilino;
		// return "nombre:" ++ this.inquilino.getNombre() ++ ", numero de telefono:" ++ this.inquilino.getNumeroDeTelefono ++ ", email:" ++ this.inquilino.getEmail(); 
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
	
	public boolean puedeCancelarse() {
		return this.estado.esPosibleCancelar();
	} // va a servir para que la politica de cancelacion pueda chequear si se efectua la cancelacion
	
	/**
	 * 	public void cancelarReserva() {
			if(this.estado.esPosibleCancelar()) {
				this.estado= new EstadoCancelada(); // si cambio el estado desde aca rompo el state
			}
		}
	 */
	public void cancelarReserva() {
		this.estado.cancelarReserva(this);
	}
	
	public void rankearInmueble(Valoracion val) {
		if(this.estado.esPosibleRankear()) {
			this.inmueble.agregarValoracion(val);
		}
	}
	
	public void rankearInquilino(Valoracion val) {
		if(this.estado.esPosibleRankear()) {
			this.inquilino.agregarValoracion(val);
		}
	}
	
	public void rankearPropietario(Valoracion val) {
		if(this.estado.esPosibleRankear()) {
			this.inmueble.getDueño().agregarValoracion(val);
		}
	}

	public void cambiaEstadoA(EstadoReserva nuevoEstado) {
		this.estado=nuevoEstado;
	}
}