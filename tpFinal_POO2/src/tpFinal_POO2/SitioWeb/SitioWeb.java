package tpFinal_POO2.SitioWeb;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.Usuario.Usuario;

public class SitioWeb {
	
	private Set<TipoDeInmueble>tipoDeInmuebles;
	private Set<Servicio>servicios;
	private Set<Usuario>usuarios;
	
	public SitioWeb() {
		this.tipoDeInmuebles=new HashSet<TipoDeInmueble>();
		this.servicios=new HashSet<Servicio>();
		this.usuarios=new HashSet<Usuario>();
	}
	
	public void addUsuario(Usuario u) {
		usuarios.add(u);
	}

	public boolean esValidoInmueble(Inmueble i) {
		return sonValidosLosServicios(i.getServicios()) && esValidoTipoDeInmueble(i.getTipoDeInmueble());
	}
	
	public boolean esValidoTipoDeInmueble(TipoDeInmueble tipoDeInmueble) {
		return tipoDeInmuebles.stream().anyMatch(tipoDeInmueble::equals);
	}

	public boolean sonValidosLosServicios(List<Servicio> serviciosInm) {
		return serviciosInm.stream().allMatch(servicios::contains);
	}

	public List<Inmueble> filtrarPropiedadesPor(FiltroCompuesto filtro){
		return filtro.filtrar(this.getInmuebles());
	}

	public void darAltaTipoInmueble(TipoDeInmueble c) {
		tipoDeInmuebles.add(c);
	}

	public void darAltaCategoriaRankeo(Categoria c) {
		// DUDA
	}

	public void darAltaServicios(Servicio s) {
		servicios.add(s);
	}

	public Set<Usuario> getTopTenInquilinos(){
		Set<Usuario>topTen = new HashSet<Usuario>();
		Set<Usuario>sinLosMejores = this.usuarios;
		while( topTen.size()<10 && !sinLosMejores.isEmpty() ){
			topTen.add(this.getInquilinoConMasReservas(sinLosMejores));
			sinLosMejores.remove(this.getInquilinoConMasReservas(sinLosMejores));
		}
		return topTen;
	}

	public Usuario getInquilinoConMasReservas(Set<Usuario> usuarios) {
	    return usuarios.stream()
                		.max(Comparator.comparingInt(usuario -> usuario.getReservas().size()))
                		.orElse(null);
	}

	public List<Inmueble> getInmuebles(){
		return usuarios.stream().flatMap(usuario -> usuario.getInmuebles().stream())
                				.collect(Collectors.toList());
	}
	
	public float getTasaDeOcupacion() {
		return  this.cantidadDeInmueblesOcupados() / this.getInmuebles().size();
	}

	public int cantidadDeInmueblesOcupados() {
		return usuarios.stream().mapToInt(usuario->usuario.inmueblesAlquilados().size()).sum();  
	}

	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public Set<Servicio> getServicios() {
		return this.servicios;
	}

	public Set<TipoDeInmueble> getTipoDeInmuebles() {
		return this.tipoDeInmuebles;
	}
	
	
}
