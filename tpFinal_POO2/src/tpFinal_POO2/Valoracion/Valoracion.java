package tpFinal_POO2.Valoracion;

public class Valoracion {
	
	private int puntaje; 
	private String comentario;
	//private Categoria 
	
	public Valoracion(int puntaje, String comentario) {
		this.puntaje = puntaje; // del 1 al 5
		this.comentario = comentario;
	}


	public int getPuntaje() {
		return puntaje;
	}

	public String getComentario() {
		return comentario;
	}
	
}
