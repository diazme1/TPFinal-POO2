package tpFinal_POO2.usuario;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import tpFinal_POO2.observer.Inmueble;

public class Usuario implements Inquilino,Propietario{
	private String nombre;
	private String email;
	private String telefono;
	private ArrayList<Inmueble> inmuebles = new ArrayList<Inmueble>();
	private ArrayList<Valoracion> valoraciones = new ArrayList<Valoracion>();
	private Date fechaIngreso;
	private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
	private SitioWeb sitio;
	
	public Usuario(String newNombre, String newEmail, String tlfn, Date hoy,SitioWeb sitio) {
		//asumo que la fecha en el parametro es el dia de hoy entregado por la interfaz grafica
		this.nombre = newNombre;
		this.email = newEmail;
		this.telefono = tlfn;
		this.fechaIngreso = hoy;
		this.sitio = sitio;
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getEmail() {
		return email;
	}
	

	public String getTelefono() {
		return telefono;
	}
	
	private boolean esValidaCategoriaPropietario(String categoria){
		return this.sitio.esValidaCategoriaPropietario(categoria);
	}

	@Override
	public Double promedioValoracionPropietario() {
		OptionalDouble promedioProp = this.valoraciones.stream().filter(v->this.esValidaCategoriaPropietario(v.getCategoria())).mapToInt(v->v.getVal()).average();
		if (promedioProp.isPresent()) {
			return promedioProp.getAsDouble();
		}else {
			return 0.0;
		}
	}

	@Override
	public ArrayList<Valoracion> getValoracionesPropietario() {
		return this.valoraciones.stream().filter(v->this.esValidaCategoriaPropietario(v.getCategoria())).collect(Collectors.toCollection(ArrayList::new));
	}
	
	private boolean esValidaCategoriaInquilino(String categoria){
		return this.sitio.esValidaCategoriaInquilino(categoria);
	}

	@Override
	public double promedioValoracionInquilino() {
	    OptionalDouble promedioProp = this.valoraciones.stream().filter(v->this.esValidaCategoriaInquilino(v.getCategoria())).mapToInt(v->v.getVal()).average();
		if (promedioProp.isPresent()) {
			return promedioProp.getAsDouble();
		}else {
			return 0.0;
		}
	}
	

	@Override
	public ArrayList<Valoracion> getValoracionesInquilino() {
		return this.valoraciones.stream().filter(v->this.esValidaCategoriaInquilino(v.getCategoria())).collect(Collectors.toCollection(ArrayList::new));
	}
	public void agregarValoracion(Valoracion val) {
		this.valoraciones.add(val);
	}
	
	public void darDeAltaInmueble(Inmueble inmu) {
		if (sitio.esValidoInmueble(inmu)) {this.inmuebles.add(inmu);}
	}
	
	public ArrayList <Reserva> getReservas() {
		return this.reservas;
	}
	
	public ArrayList <Reserva> reservasFuturas(Date hoy){
		//se asume que la fecha en el parametro es la fecha actual otorgada por la interfaz web
		return this.reservas.stream().filter(reserva -> reserva.fechaCheckIn().after(hoy)).collect(Collectors.toCollection(ArrayList::new));
	}
	public int getAntiguedadUsuario(Date hoy) {
		//asumiendo que la fecha en el parametro es la fecha actual otorgada por la interdaz web
		int acum = 0;
		while (hoy.after(fechaIngreso)) {
			acum +=1;
		}
		return acum;
	}

	@Override
	public ArrayList<Inmueble> inmueblesAlquilados() {
		return inmuebles.stream().filter(inm->inm.vecesAlquilado()>0).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public int vecesAlquilado() {
		return this.inmuebles.stream().mapToInt(inm->inm.vecesAlquilado()).sum();
	}

	@Override
	public ArrayList<Reserva> reservasEnCiudad(String ciudad) {
		return this.reservas.stream().filter(res->res.getCiudad() == ciudad).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public HashSet<String> ciudadesReservadas() {
		return this.reservas.stream().map(res->res.getCiudad()).collect(Collectors.toCollection(HashSet::new));
	}

	@Override
	public ArrayList<Reserva> reservasDeInmuebles() {
		return this.inmuebles.stream().flatMap(inm->inm.getReservas().stream()).collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public void añadirReserva(Reserva reserva) {
		this.reservas.add(reserva);		
	}

	@Override
	public ArrayList<Inmueble> getInmuebles() {
		return this.inmuebles;
	}

	@Override
	public void abonarMonto(Double monto) {
	}
	
	public double promedioValoracionCategoria(String categoria) {
	    OptionalDouble promedio = this.valoraciones.stream().filter(v->v.getCategoria() == categoria).mapToInt(v->v.getVal()).average();
		if (promedio.isPresent()) {
			return promedio.getAsDouble();
		}else {
			return 0.0;
		}
	}
	
}
