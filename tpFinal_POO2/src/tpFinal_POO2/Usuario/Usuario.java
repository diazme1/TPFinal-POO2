package tpFinal_POO2.Usuario;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.Valoracion.Valoracion;
import tpFinal_POO2.SitioWeb.SitioWeb;
import tpFinal_POO2.Reserva.Reserva;

public class Usuario implements Inquilino,Propietario{
	private String nombre;
	private String email;
	private String telefono;
	private List<Inmueble> inmuebles = new ArrayList<Inmueble>();
	private List<Valoracion> valoraciones = new ArrayList<Valoracion>();
	private LocalDate fechaIngreso;
	private List<Reserva> reservas = new ArrayList<Reserva>();
	private SitioWeb sitio;
	
	public Usuario(String newNombre, String newEmail, String tlfn, LocalDate hoy, SitioWeb sitio) {
		//asumo que la fecha en el parametro es el dia de hoy entregado por la interfaz grafica
		this.nombre = newNombre;
		this.email = newEmail;
		this.telefono = tlfn;
		this.fechaIngreso = hoy;
		this.sitio = sitio;
	}
	
	// GETTERS
	@Override
	public String getNombre() {
		return nombre;
	}
	@Override
	public String getEmail() {
		return email;
	}
	@Override
	public String getTelefono() {
		return telefono;
	}
	
	// COMPORTAMIENTO DE PROPIETARIOS
	@Override
	public Double promedioValoracionPropietario() {
		OptionalDouble promedioProp = this.valoraciones.stream().filter(v->this.esValidaCategoriaPropietario(v.getCategoria())).mapToInt(v->v.getPuntaje()).average();
		if (promedioProp.isPresent()) {
			return promedioProp.getAsDouble();
		}else {
			return 0.0;
		}
	}

	@Override
	public List<Valoracion> getValoracionesPropietario() {
		return this.valoraciones.stream().filter(v->this.esValidaCategoriaPropietario(v.getCategoria())).toList();
	}
	
	@Override
    public void agregarValoracionPropietario(Valoracion val) {
        if(this.esValidaCategoriaPropietario(val.getCategoria())) {
            this.valoraciones.add(val);
        }
	}
	
	@Override
	public List<Inmueble> inmueblesAlquilados() {
		return this.inmuebles.stream().filter(inm->inm.vecesAlquilado()>0).toList();
	}

	@Override
	public int vecesAlquilado() {
		return this.inmuebles.stream().mapToInt(inm->inm.vecesAlquilado()).sum();
	}
	
	// CHEQUEA LA VALIDEZ DE LA CATEGORIA (DEPENDIENDO SI ES INQUILINO O PROPIETARIO)
	private boolean esValidaCategoriaInquilino(String categoria){
		return this.sitio.esValidaCategoriaInquilino(categoria);
	}
	
	private boolean esValidaCategoriaPropietario(String categoria){
		return this.sitio.esValidaCategoriaPropietario(categoria);
	}	

	@Override
	public double promedioValoracionInquilino() {
	    OptionalDouble promedioProp = this.valoraciones.stream().filter(v->this.esValidaCategoriaInquilino(v.getCategoria())).mapToInt(v->v.getPuntaje()).average();
		if (promedioProp.isPresent()) {
			return promedioProp.getAsDouble();
		}else {
			return 0.0;
		}
	}
	
	@Override
	public List<Valoracion> getValoracionesInquilino() {
		return this.valoraciones.stream().filter(v->this.esValidaCategoriaInquilino(v.getCategoria())).toList();
	}
	
	// AGREGA VALORACIONES DEPENDIENDO SU VALIDEZ
	@Override
    public void agregarValoracionInquilino(Valoracion val) {
        if(this.esValidaCategoriaInquilino(val.getCategoria())) {
            this.valoraciones.add(val);
        }
	}
	
	public void darDeAltaInmueble(Inmueble inmu) {
		if (sitio.esValidoInmueble(inmu)) {this.inmuebles.add(inmu);}
	}
	
	@Override
	public List<Reserva> getReservas() {
		// SOLO DE INQUILINOS, LOS PROPIETARIOS BUSCAN SUS RESERVAS APARTIR DE SUS INMUEBLES
		return this.reservas;
	}
	
	public List<Reserva> reservasFuturas(LocalDate hoy){
		//se asume que la fecha en el parametro es la fecha actual otorgada por la interfaz web
		return this.reservas.stream().filter(reserva -> reserva.getCheckIn().isAfter(hoy)).toList();
	}
	
	@Override
	public int getAntiguedadUsuario(LocalDate hoy) {
		//asumiendo que la fecha en el parametro es la fecha actual otorgada por la interdaz web
		return (int) ChronoUnit.DAYS.between(this.fechaIngreso, hoy);
	}

	@Override
	public List<Reserva> reservasEnCiudad(String ciudad) {
		return this.reservas.stream().filter(res->res.getCiudad() == ciudad).toList();
	}

	@Override
	public Set<String> ciudadesReservadas() {
		return this.reservas.stream().map(res->res.getCiudad()).collect(Collectors.toSet());
	}

	@Override
	public List<Reserva> reservasDeInmuebles() {
		return this.inmuebles.stream().flatMap(inm->inm.getReservas().stream()).toList();
	}

	@Override
	public void agregarReserva(Reserva reserva) {
		this.reservas.add(reserva);		
	}

	@Override
	public List<Inmueble> getInmuebles() {
		return this.inmuebles;
	}

	@Override
	public void abonarMonto(Double monto) {
	}
	
	@Override
	public double promedioValoracionCategoria(String categoria) {
	    OptionalDouble promedio = this.valoraciones.stream().filter(v->v.getCategoria() == categoria).mapToInt(v->v.getPuntaje()).average();
		if (promedio.isPresent()) {
			return promedio.getAsDouble();
		}else {
			return 0.0;
		}
	}

	@Override
	public boolean validarCategoriaInmueble(String categoria) {
		return this.sitio.esValidaCategoriaInmueble(categoria);	
	}
	
	@Override
	public int cantReservasFinalizadasInquilino() {
		return this.getReservas().stream().filter(r -> r.estaFinalizada()).toList().size();
	}
	
}
