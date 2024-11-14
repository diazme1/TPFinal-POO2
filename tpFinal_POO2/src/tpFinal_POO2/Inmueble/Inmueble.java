package tpFinal_POO2.Inmueble;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;

import tpFinal_POO2.Reserva.FormaDePago;
import tpFinal_POO2.Usuario.Inquilino;
import tpFinal_POO2.Observer.Observer;
import tpFinal_POO2.Usuario.Propietario;
import tpFinal_POO2.Reserva.Reserva;
import tpFinal_POO2.Valoracion.Valoracion;
import tpFinal_POO2.PoliticasCancelacion.PoliticaCancelacion;
import tpFinal_POO2.PoliticasCancelacion.SinCancelacion;

//Clase abstracta para imports, ELIMINAR cuando se haya desarrollado el inmueble.
public class Inmueble {

	private Propietario dueño;
	private String tipoInmueble;
	private double metrosCuadrados;
	private String pais;
	private String ciudad;
	private String direccion;
	private List<String> servicios;
	private int cantHuespedes;
	private LocalTime checkIn;
	private LocalTime checkOut;
	private double precioDefault;
	private Set<Periodo> periodosExtraordinarios;
	private List<Valoracion> valoraciones;
	private Set<Reserva> reservas;
	private Set<Reserva> reservasEnCola;
	private Set<FormaDePago> formasDePagoAdmitidas;
	private PoliticaCancelacion politicaCancelacion;
	private Observer observer;

	
	public Inmueble(Propietario propietario, String tipo, double metros, String pais, String ciudad, String dir, List<String> servicios, int cantHuespedes, LocalTime checkIn, LocalTime checkOut, double precio) {
		super();
		this.dueño = propietario;
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
		//Por defecto no hay politica de cancelación consignada.
		this.politicaCancelacion = new SinCancelacion();
		//Inicializar observer:
		this.observer = Observer.getInstance();
	}
	
	//Getters:
	public Propietario getDueño() {
		return this.dueño;
	};
	
	public List<String> getServicios(){
		return this.servicios;
	};
	
	public String getTipoInmueble() {
		return this.tipoInmueble; 
	};
	
	public List<Valoracion> getValoracionesRecibidas(){
		return this.valoraciones;
	};
	
	public List<Reserva> getReservasAceptadas(){
		return this.reservas.stream().filter(r -> r.estaAprobada()).toList();
	};
	
	public Set<Reserva> getReservasCondicionales() {
		return this.reservasEnCola;
	};
	
	public Set<Reserva> getReservas(){
		return this.reservas;
	};
	
	public int getCantHuespedes() {
		return this.cantHuespedes;
	};
	
	public String getCiudad() {
		return this.ciudad;
	};
	
	public Set<FormaDePago> getFormasDePagoAdmitidas() {
		return this.formasDePagoAdmitidas;
	};
	
	public double getMontoTotalPara(LocalDate fechaIni, LocalDate fechaFin) {
		
		LocalDate fechaActual = fechaIni;
		double valorAcumulado = 0.00;
		
		while (!fechaActual.isAfter(fechaFin)) {
			valorAcumulado += this.getValorDeFecha(fechaActual);
			fechaActual = fechaActual.plusDays(1);
			}
		return valorAcumulado;
	};
	
	public double getValorDeFecha(LocalDate fecha) {
		
		double valor = this.precioDefault;
		
		for (Periodo p : this.periodosExtraordinarios) {
			
			if (p.incluidoEnPeriodo(fecha)) {
				return valor + p.getIncremento();
			}
		}
		
		return valor;
	};
	
	public double promedioPuntajeTotal() {
		
		OptionalDouble promedio = this.getValoracionesRecibidas().stream().mapToInt(v -> v.getPuntaje()).average();
		
		if (promedio.isPresent()) {
			return promedio.getAsDouble();
		} else {
			return 0.00;
		}
	}
	
	public double promedioPuntajeCategoria(String categoria) {
		
		OptionalDouble promedio = this.getValoracionesRecibidas().stream().
																	filter(v -> v.getCategoria().equals(categoria)).
																	mapToInt(v -> v.getPuntaje()).average();
		
		if (promedio.isPresent()) {
			return promedio.getAsDouble();
		} else {
			return 0.00;
		}
	}
	
	//Setters:
	public void setPoliticaCancelacion(PoliticaCancelacion newPolitica) {
		this.politicaCancelacion = newPolitica;
	}

	//Agregar valoración:
	public void agregarValoracion(Valoracion val) {
		
		if(this.dueño.validarCategoriaInmueble(val.getCategoria())) {
			this.valoraciones.add(val);
		}
	};
	
	//Agregar periodo extraordinadrio:
	public void agregarPeriodoExtraordinario(Periodo periodo) {
		this.periodosExtraordinarios.add(periodo);
	};
	
	//Agregar forma de pago admitida.
	public void agregarFormaDePago(FormaDePago metodo) {
		this.formasDePagoAdmitidas.add(metodo);
	};
	
	//Agregar servicio:
	public void agregarServicio(String servicio) {
		this.servicios.add(servicio);
	};
	
	//Agregar reserva:
	//Debería ser protected, es public por testeo:
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
	
	//Bajar precio default de inmueble:
	public void bajarPrecio(double newPrecio) {
		if (newPrecio < this.precioDefault) {
			this.precioDefault = newPrecio;
			this.observer.notifyBajaPrecio(this);
		}
	};
	
	//Disponibilidad del inmueble en ciertas fechas dadas, dada por no tener reservas aceptadas en esas fechas:
	public boolean estaDisponible(LocalDate checkIn, LocalDate checkOut) {
		
		List<Reserva> reservasAceptadas = this.getReservasAceptadas();
		
		return !reservasAceptadas.stream().anyMatch(r -> r.haySolapamiento(checkIn, checkOut));
	}
	
	//Generar una reserva para el inmueble dado.
	public void generarReserva(Inquilino inquilino, LocalDate checkIn, LocalDate checkOut, FormaDePago formaDePago) {
		Reserva newReserva = new Reserva(this, formaDePago, checkIn, checkOut, inquilino);
		
		if (this.estaDisponible(checkIn, checkOut)) {
			this.agregarReserva(newReserva);
			inquilino.agregarReserva(newReserva);
			this.observer.notifyReserva(this);
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
				this.observer.notifyCancelacion(this);
				break;
			}
		}
		
		this.politicaCancelacion.cancelarReserva(reservaCancelada, diaHecho);
	}

	public double getPrecio() {
		return this.precioDefault;
	}
	
	
}
