package Reconstruir;

public class TestRecontruyePelicula {
	public static void main(String[] args) {
		Pelicula peli1 = new Pelicula();
		peli1.leerArchivo("pelicula.in");			
		peli1.reconstruyePelicula();
		peli1.escribirArchivo("pelicula.out");		
	}
}
