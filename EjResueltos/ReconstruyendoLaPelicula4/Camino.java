package Pelicula_marcos;

public class Camino {

	private String recorrido;
	private int costo;
	
	public Camino(int costo,String recorrido) {
		super();
		this.recorrido = recorrido;
		this.costo = costo;
	}
	
	

	public String getRecorrido() {
		return recorrido;
	}



	public void setRecorrido(String recorrido) {
		this.recorrido = recorrido;
	}



	public int getCosto() {
		return costo;
	}



	public void setCosto(int costo) {
		this.costo = costo;
	}



	public static void main(String[] args) {

	}

}
