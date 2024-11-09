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
		this.estado= EstadoSolicitada();	// POR SINGLETON SIN EL NEW
		
	}
	
	public void aprobarReserva() {
		// SIGNIFICA QUE LA RESERVA ESTA EN ESTADO SOLICITADA Y LA APRUEBA EL PROPIETARIO 
		if(this.estado.puedeSerAceptado()) {
			this.estado.siguienteEstado();
		}
		this.inquilino.recibeEmailDeConfirmacion(); // ESTA BIEN ASI O TENGO QUE MANDARLO A TRAVES DEL EMAIL?
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
	}	// podria intentarlo con streams?
	
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
}