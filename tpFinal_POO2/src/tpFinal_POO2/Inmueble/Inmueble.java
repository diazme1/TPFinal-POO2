package tpFinal_POO2.Inmueble;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	private List<Periodo> periodosExtraordinarios;
	private List<Valoracion> valoraciones;
	
	
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
		this.periodosExtraordinarios = new ArrayList<Periodo>();
	}
	
	//Getters
	public Propietario getDueño() {
		return this.dueño;
	}
	
	public List<Servicio> getServicios(){
		return this.servicios;
	}
	
	public TipoInmueble getTipoInmueble() {
		return this.tipoInmueble; 
	}
	
	public void agregarValoracion(Valoracion val) {
		this.valoraciones.add(val);
	}
	
	public double getMontoTotalPara(LocalDate fechaIni, LocalDate fechaFin) {
		//PENDIENTE
		return 0.00;
	}

}
