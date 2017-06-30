import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reserva {
	
	private int cantidadNodos;
	private int cantidadAristas;
	private int[][] matAdy;
	BusquedaEnProfundidad b;

	public Reserva(String path) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(path));
			cantidadNodos = sc.nextInt();
			cantidadAristas = sc.nextInt();
			matAdy = new int [cantidadNodos][cantidadNodos];
			
			for (int i = 0; i < cantidadAristas; i++) {
				matAdy[sc.nextInt()-1][sc.nextInt()-1] = 1;
			}
	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			sc.close();
		}
	}
	public void resuelve(){
		b = new BusquedaEnProfundidad(matAdy, 0);
	}
	public void muestraResultado(){
		System.out.println(b.getContador());
	}
	public static void main(String[] args) {
		Reserva r = new Reserva("lote2.in");
		r.resuelve();
		r.muestraResultado();
	}
}
