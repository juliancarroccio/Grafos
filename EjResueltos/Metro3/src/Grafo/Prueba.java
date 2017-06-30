package Grafo;

public class Prueba {
	public static void main(String[] args) {
      Grafo g1 = new Grafo("E:/Users/ariel/workspace/Parcial/puentes.txt");
      int puentesmin= g1.ObtenerCantDePuentesMin();
      System.out.println(puentesmin);

	}

}
