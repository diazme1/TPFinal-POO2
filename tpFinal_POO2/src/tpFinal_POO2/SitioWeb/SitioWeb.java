package tpFinal_POO2.SitioWeb;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.Reserva.Reserva;
import tpFinal_POO2.Usuario.Usuario;

public class SitioWeb {
	
	private Set<Categoria>categorias;
	private Set<Servicio>servicios;
	private Set<Usuario>usuarios;
	
	public void recibeSolicitudDeAltaDeInmueble(Inmueble inmuebleNuevo) {
		this.inmueblesSolicitados.add(inmuebleNuevo);
	}

	public List<Inmueble> filtrarPropiedadesPor(FiltroCompuesto filtro){
		return filtro.filtrar(misPropiedades);
	}

	public List<Reserva> getReservasUsuario(Usuario usuario) {
		return usuario.getReservas();
	}

	public List<Reserva>  getReservasEn(Usuario u, String ciudad) {
		return this.getReservasUsuario(u).stream().filter(r->r.getInmueble().getCiudad().equals(ciudad)).toList();
	}

	public List<String> getCiudadesConReservas(Usuario usuario) {
		return this.getReservasUsuario(usuario).stream().map(Reserva::getCiudad)
			    										.collect(Collectors.toList());
	}

	public List<Reserva> getReservasFuturas(Usuario usuario) {
		return ;
	}


	public int getPuntajePromedio(Usuario usuario) {
		
	}

	public void getPuntajeTotal(Usuario usuario) {
		
	}

	public void darAltaTipoInmueble(Categoria cat) {
		
	}

	public void darAltaCategoriaRankeo(Categoria cat) {
		
	}

	public void darAltaServicios(Servicio serv) {
		
	}

	public List<Usuario> getTopTenInquilinos(){
		return ;
	}

	public List<Inmueble> getInmueblesLibres(){
		return ;
	}

	public Double getTasaDeOcupacion() {
		return  ;
	}
	
	
}
