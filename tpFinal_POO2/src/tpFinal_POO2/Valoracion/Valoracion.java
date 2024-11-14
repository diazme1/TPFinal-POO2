package tpFinal_POO2.Valoracion;

public class Valoracion {

	private String categoria;
	private int puntaje;
	private String comentario;
	
	
	public Valoracion(String categoria, int puntaje, String comentario) {
		super();
		this.categoria = categoria;
		this.comentario = comentario;
		
		//Validacion puntaje limitado:
		if (puntaje < 1) {
			this.puntaje = 1;
		} else if (puntaje > 5) {
			this.puntaje = 5;
		} else {
			this.puntaje = puntaje;
		}
		
	}
	
	public String getCategoria() {
		return this.categoria;
	}
	
	public String getComentario() {
		return this.comentario;
	}
	
	public int getPuntaje() {
		return this.puntaje;
	}
}
