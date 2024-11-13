package tpFinal_POO2.Filtros;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import tpFinal_POO2.Inmueble.Inmueble;

public class FiltroCompuesto {
	
	private LocalDate fechaIni;
	private LocalDate fechaFin;
	private List<Filtro> filtros;
	
	public FiltroCompuesto(LocalDate checkIn, LocalDate checkOut, String ciudad) {
		super();
		this.fechaIni = checkIn;
		this.fechaFin = checkOut;
		this.filtros = new ArrayList<Filtro>();
		this.filtros.add(new FiltroFecha(checkIn, checkOut));
		this.filtros.add(new FiltroCiudad(ciudad));
	};
	
	public void agregarFiltro(Filtro filtro) {
		this.filtros.add(filtro);
	};
	
	public List<Inmueble> filtrar(List<Inmueble> inmuebles){
		
		List<Inmueble> inmueblesValidos = new ArrayList<Inmueble>();
		
		for (Inmueble i : inmuebles) {
			if (this.filtros.stream().allMatch(f -> f.cumplePara(i, this))) {
				inmueblesValidos.add(i);
			}
		}
		
		return inmueblesValidos;
	};
	
	public List<Filtro> getFiltros(){
		return this.filtros;
	}
	
	public LocalDate getFechaInicio() {
		return this.fechaIni;
	};
	
	public LocalDate getFechaFin() {
		return this.fechaFin;
	}

}
