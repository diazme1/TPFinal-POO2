package tpFinal_POO2.Filtros;
import tpFinal_POO2.Inmueble.Inmueble;

public class FiltroPrecio implements Filtro {

	double precioMinimo;
	double precioMaximo;
	
	public FiltroPrecio(double precioMin, double precioMax) {
		super();
		this.precioMinimo = precioMin;
		this.precioMaximo = precioMax;
	}
	
	public boolean cumplePara(Inmueble inmueble, FiltroCompuesto filtro){
		
		double montoTotalInmueble = inmueble.getMontoTotal(filtro.getFechaInicio(), filtro.getFechaFin());
		
		return (this.precioMinimo <= montoTotalInmueble && montoTotalInmueble <= this.precioMaximo);
	};
	
}
