package tpFinal_POO2.SitioWeb;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import tpFinal_POO2.Filtros.FiltroCompuesto;
import tpFinal_POO2.Inmueble.Inmueble;
import tpFinal_POO2.Usuario.Inquilino;
import tpFinal_POO2.Usuario.Propietario;
import tpFinal_POO2.Usuario.Usuario;

public class SitioWeb {
	
	private Set<String> tipoDeInmuebles;
	private Set<String> servicios;
	private Set<Usuario> usuarios;
	private Set<String> categoriasInmueble;
	private Set<String> categoriasInquilino;
	private Set<String> categoriasPropietario;
	
	public SitioWeb() {
		this.tipoDeInmuebles=new HashSet<String>();
		this.servicios=new HashSet<String>();
		this.usuarios=new HashSet<Usuario>();
		this.categoriasInmueble=new HashSet<String>();
		this.categoriasInquilino=new HashSet<String>();
		this.categoriasPropietario=new HashSet<String>();
	}
	
	public void addUsuario(Usuario u) {
		usuarios.add(u);
	}
	
	// CARGA DE CATEGORIAS EN EL SITIO DE DIFERENTES ENTIDADES
	public void darDeAltaCategoriaInmueble(String c) {
		this.categoriasInmueble.add(c);
	}
	
	public void darDeAltaCategoriaInquilino(String c) {
		this.categoriasInquilino.add(c);
	}
	
	public void darDeAltaCategoriaPropietario(String c) {
		this.categoriasPropietario.add(c);
	}
	
	public void darAltaTipoInmueble(String c) {
		this.tipoDeInmuebles.add(c);
	}

	public void darAltaServicios(String s) {
		this.servicios.add(s);
	}
	
	// CHEQUEO DE VALIDEZ DE POSIBLES RANKEOS DE ENTIDADES (PROVIENEN DE USUARIOS - INQ Y PROP)
	public boolean esValidaCategoriaInmueble(String cim) {
		return this.categoriasInmueble.contains(cim);
	}
	
	public boolean esValidaCategoriaInquilino(String ci) {
		return this.categoriasInquilino.contains(ci);
	}
	
	public boolean esValidaCategoriaPropietario(String cp) {
		return this.categoriasPropietario.contains(cp);
	}
	
	// CHEQUEOS DE VALIDEZ SOLICITADOS POR USUARIO PARA POSTERIOR CARGA DE INMUEBLES EN SITIO WEB
	public boolean esValidoInmueble(Inmueble i) {
		return sonValidosLosServicios(i.getServicios()) && esValidoTipoDeInmueble(i.getTipoInmueble());
	}
	
	public boolean esValidoTipoDeInmueble(String tipoDeInmueble) {
		return tipoDeInmuebles.stream()
							  .anyMatch(tipoDeInmueble::equals);
	}

	public boolean sonValidosLosServicios(List<String> serviciosInm) {
		return serviciosInm.stream()
						   .allMatch(servicios::contains);
	}
	
	// PEDIDOS DE FILTRACION DE INMUEBLES - ARRANCA DE ACA POR TEMAS DE INTERFAZ GRAFICA.
	public List<Inmueble> filtrarPropiedadesPor(FiltroCompuesto filtro){
		return filtro.filtrar(this.getInmuebles());
	}
	
	// FUNCIONES EVALUADAS EN FORMA DE ADMINISTRADOR
	public List<Inquilino> getTopTenInquilinos(){
		return this.getInquilinos()
	               .stream()
	               .sorted(Comparator.comparingInt(Inquilino::cantReservasFinalizadasInquilino).reversed())
	               .limit(10)
	               .toList();
	}

	public List<Inquilino> getInquilinos() {
		return usuarios.stream()
					   .filter(u->u.cantReservasFinalizadasInquilino()>0)
					   .collect(Collectors.toList());
	}
	
	public List<Propietario> getPropietarios() {
		return usuarios.stream()
				   .filter(u->u.inmueblesAlquilados().size()>0)
				   .collect(Collectors.toList());
	}
	
	public List<Inmueble> getInmuebles(){
		return usuarios.stream()
					   .flatMap(usuario -> usuario.getInmuebles().stream())
					   .collect(Collectors.toList());
	}
	
	public float getTasaDeOcupacion() {
		return (float) this.cantidadDeInmueblesOcupados() / this.getInmuebles().size();
	}

	public int cantidadDeInmueblesOcupados() {
		return this.getPropietarios()
				   .stream()
				   .mapToInt(p->p.inmueblesAlquilados().size())
				   .sum();  
	}
	
	
	// SETTERS
	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public Set<String> getServicios() {
		return this.servicios;
	}

	public Set<String> getTipoDeInmuebles() {
		return this.tipoDeInmuebles;
	}
	
	
}
