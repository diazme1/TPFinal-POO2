package tpFinal_POO2.Reserva;

import java.time.LocalDate;

import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.Usuario.Inquilino;
import tpFinal_POO2.Usuario.Propietario;
import tpFinal_POO2.Valoracion.Valoracion;

public class Reserva{
	
	private Inmueble inmueble;
	private FormaDePago formaDePago;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private Inquilino inquilino;
	private EstadoReserva estado;
	private MailSender mailSender;
	
	public Reserva(Inmueble inmueble, FormaDePago formaDePago, LocalDate checkIn, LocalDate checkOut, Inquilino inquilino) {
		this.inmueble = inmueble;
		this.formaDePago = formaDePago;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.inquilino=inquilino;
		this.estado= new EstadoSolicitada();
	}
	
	// CHEQUEA SI LAS FECHAS DE LA NUEVA RESERVA SOLICITADA EN INMUEBLE SE SOLAPAN CON ESTA
	public boolean haySolapamiento(LocalDate checkIn, LocalDate checkOut) {
        return checkIn.isBefore(this.getCheckOut()) && checkOut.isAfter(this.getCheckIn());
    }
	
	// GETTERS
	public LocalDate getCheckIn() {
		return this.checkIn;
	}

	public LocalDate getCheckOut() {
		return this.checkOut;
	}
	
	public String getCiudad() {
		return this.inmueble.getCiudad();
	}
	
	public boolean estaFinalizada() {
		return this.estado.esFinalizada();
	}
	
	public Inquilino getInfoPosibleInquilino() {
		return this.inquilino;
	}
	
	public Inquilino getInquilino() {
		return this.inquilino;
	}
	
	public Inmueble getInmueble() {
		return this.inmueble;
	}
	
	public FormaDePago getFormaDePago() {
		return this.formaDePago;
	}
	
	// FUNCIONES MANEJADAS POR EL ESTADO DE LA RESERVA - SU EFECTIVIDAD ES RELATIVA DEL ESTADO EN QUE SE ENCUENTRA
	public void aprobarReserva() {
			this.estado.aprobarReserva(this);
	}
	
	public void cancelarReserva(LocalDate diaHecho) {
		this.estado.cancelarReserva(this,diaHecho);
	}	
	
	public void realizarCheckOut(LocalDate diaHecho) {
		if(diaHecho.equals(this.checkOut)) {
			this.estado.siguienteEstado(this);
		}
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
	
	public boolean estaAprobada(){
		return this.estado.esAprobada(this);
	}
	
	public boolean estaCancelada(){
		return this.estado.esCancelada(this);
	}

	public boolean sePuedeValorar() {
		return this.estado.puedeValorar(this);
	}
	
	// FUNCIONES MANEJADAS POR EL INMUEBLE
	public double getMontoTotal() {
		return this.inmueble.getMontoTotalPara(checkIn,checkOut);
	}
	
	public Propietario getPropietario() {
		return this.inmueble.getDueño();
	}

	// FUNCIONES SOLICITADAS POR LOS ESTADOS - IMPLEMENTACION SOLICITADA DE PATRON STATE
	public void cambiaEstadoA(EstadoReserva nuevoEstado) {
		this.estado=nuevoEstado;
	}

	public void enviarMailConfirmacion() {
		mailSender.sendEmail(inquilino.getEmail(), "Reserva Aprobada!", "Se confirmo su reserva, gracias.");
	}

	public MailSender setMailSender(MailSender mailSender) {
		return this.mailSender=mailSender;
	}

	public void enviarMailCancelacion() {
		mailSender.sendEmail(this.getPropietario().getEmail(), "Reserva Cancelada!", "Se cancelo la reserva ;(");
	}

}