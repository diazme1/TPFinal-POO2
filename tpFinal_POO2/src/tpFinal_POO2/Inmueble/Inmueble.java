package tpFinal_POO2.Inmueble;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tpFinal_POO2.Externos.FormaDePago;
import tpFinal_POO2.Externos.Inquilino;
import tpFinal_POO2.Externos.Propietario;
import tpFinal_POO2.Externos.Reserva;
import tpFinal_POO2.Externos.Servicio;
import tpFinal_POO2.Externos.Valoracion;
import tpFinal_POO2.PoliticasCancelacion.PoliticaCancelacion;
import tpFinal_POO2.PoliticasCancelacion.SinCancelacion;

//Clase abstracta para imports, ELIMINAR cuando se haya desarrollado el inmueble.
public class Inmueble {

	private Propietario dueño;
	private TipoInmueble tipoInmueble;
	private double metrosCuadrados;
	private String pais;
	private String ciudad;
	private String direccion;
	private List<Servicio> servicios;
	private int cantHuespedes;
	private Time checkIn;
	private Time checkOut;
	private double precioDefault;
	private Set<Periodo> periodosExtraordinarios;
	private List<Valoracion> valoraciones;
	private Set<Reserva> reservas;
	private Set<Reserva> reservasEnCola;
	private Set<FormaDePago> formasDePagoAdmitidas;
	private PoliticaCancelacion politicaCancelacion;
	
	
	public Inmueble(TipoInmueble tipo, double metros, String pais, String ciudad, String dir, List<Servicio> servicios, int cantHuespedes, Time checkIn, Time checkOut, double precio) {
		super();
		this.tipoInmueble = tipo;
		this.metrosCuadrados = metros;
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = dir;
		this.servicios = servicios;
		this.cantHuespedes = cantHuespedes;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.precioDefault = precio;
		//Se inicializa sin periodos extraordinarios definidos, deben agregarse. 
		this.periodosExtraordinarios = new HashSet<Periodo>();
		//Se inicializa sin reservas.
		this.reservas = new HashSet<Reserva>();
		this.reservasEnCola = new HashSet<Reserva>();
		//Se inicializa sin valoraciones:
		this.valoraciones = new ArrayList<Valoracion>();
		//Inicialmente no hay formas de pago definidas. 
		this.formasDePagoAdmitidas = new HashSet<FormaDePago>();
		this.politicaCancelacion = new SinCancelacion();
	}
	
	//Getters:
	public Propietario getDueño() {
		return this.dueño;
	};
	
	public List<Servicio> getServicios(){
		return this.servicios;
	};
	
	public TipoInmueble getTipoInmueble() {
		return this.tipoInmueble; 
	};
	
	public List<Valoracion> getValoracionesRecibidas(){
		return this.valoraciones;
	};
	
	public List<Reserva> getReservasAceptadas(){
		return this.reservas.stream().filter(r -> r.estaAceptada()).toList();
	};
	
	public Set<Reserva> getReservasCondicionales() {
		return this.reservasEnCola;
	}
	
	public double getMontoTotalPara(LocalDate fechaIni, LocalDate fechaFin) {
		
		LocalDate fechaActual = fechaIni;
		double valorAcumulado = 0.00;
		
		while (!fechaActual.isAfter(fechaFin)) {
			valorAcumulado += this.getValorDeFecha(fechaActual);
			fechaActual = fechaActual.plusDays(1);
			}
		return valorAcumulado;
	};
	
	protected double getValorDeFecha(LocalDate fecha) {
		
		double valor = 0.00;
		
		for (Periodo p : this.periodosExtraordinarios) {
			
			if (p.incluidoEnPeriodo(fecha)) {
				valor = this.precioDefault + p.getIncremento();
			} else {
				valor = this.precioDefault;
			}
		}
		
		return valor;
	};
	
	//Setters:
	public void setPoliticaCancelacion(PoliticaCancelacion newPolitica) {
		this.politicaCancelacion = newPolitica;
	}

	//Agregar valoración:
	public void agregarValoracion(Valoracion val) {
		this.valoraciones.add(val);
	};
	
	//Agregar periodo extraordinadrio:
	public void agregarPeriodoExtraordinario(Periodo periodo) {
		this.periodosExtraordinarios.add(periodo);
	};
	
	//Agregar forma de pago admitida.
	public void agregarFormaDePago(FormaDePago metodo) {
		this.formasDePagoAdmitidas.add(metodo);
	};
	
	//Agregar reserva:
	public void agregarReserva(Reserva reserva) {
		this.reservas.add(reserva);
	};
	
	//Agregar reserva condicional:
	public void agregarReservaCondicional(Reserva reserva) {
		this.reservasEnCola.add(reserva);
	};
	
	//Eliminar reserva de cola de reservas condicionales:
	protected void eliminarReservaDeCola(Reserva r) {
		this.reservasEnCola.remove(r);
	}
	
	//Veces alquilado = cantidad de reservas finalizadas. 
	public int vecesAlquilado() {
		return (int) this.reservas.stream().filter(r -> r.estaFinalizada()).count();
	};
	
	//Disponibilidad del inmueble en ciertas fechas dadas, dada por no tener reservas aceptadas en esas fechas:
	public boolean estaDisponible(LocalDate checkIn, LocalDate checkOut) {
		
		List<Reserva> reservasAceptadas = this.getReservasAceptadas();
		
		for (Reserva r : reservasAceptadas) {
			if (r.haySolapamiento(checkIn, checkOut)) {
				return false;
			}
		}
		return true;
		
	}
	
	//Generar una reserva para el inmueble dado.
	public void generarReserva(Inquilino inquilino, LocalDate checkIn, LocalDate checkOut, FormaDePago formaDePago) {
		Reserva newReserva = new Reserva(this, formaDePago, checkIn, checkOut, inquilino);
		
		if (this.estaDisponible(checkIn, checkOut)) {
			this.agregarReserva(newReserva);
		} else {
			this.agregarReservaCondicional(newReserva);
		}
	}
	
	//Administrar cola de reservas condicionales:
	public void reservaCancelada(Reserva reservaCancelada, LocalDate diaHecho) {
		
		LocalDate checkInCancelado = reservaCancelada.getCheckIn();
		LocalDate checkOutCancelado = reservaCancelada.getCheckOut();
		
		//Gestion de reservas en cola:
		for (Reserva r : this.getReservasCondicionales()) {
			if (r.haySolapamiento(checkInCancelado, checkOutCancelado)) {
				this.agregarReserva(r);
				this.eliminarReservaDeCola(r);
				break;
			}
		}
		
		this.politicaCancelacion.cancelarReserva(reservaCancelada, diaHecho);
	}
	
}
