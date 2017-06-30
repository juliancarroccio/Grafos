package algoritmos;

public class Dijkstra {
	private int[][] matrizAdy;
	private boolean[] visitados;
	private int[] costos; /// NODO RESULTADO
	private int nodoActual;
	private int cantidadDeNodos;
	private int[] ruta; // NODO RUTA
	static final Integer INF = 0;

	public Dijkstra(int[][] matAdy, int nodoInicio) {
		this.matrizAdy = matAdy;
		this.nodoActual = nodoInicio;
		this.cantidadDeNodos = matAdy.length;
		this.visitados = new boolean[this.cantidadDeNodos];
		this.costos = new int[this.cantidadDeNodos];
		this.ruta = new int[this.cantidadDeNodos];
	}

	public void resuelveDijkstra() {


	     for (int i = 0; i < this.cantidadDeNodos; i++) {
	        costos[i] = Integer.MAX_VALUE;
	        visitados[i] = false;
	     }
	     // La distancia del vertice origen hacia el mismo es siempre 0
	     costos[nodoActual] = 0;
	 
	     //Encuentra el camino mas corto para todos los vertices
	     for (int count = 0; count < cantidadDeNodos-1; count++)
	     {

	       //Toma el vertice con la distancia minima del cojunto de vertices aun no procesados
	       //En la primera iteracion siempre se devuelve nodoactual
	       int u = distanciaMinima(costos, visitados);
	 
	       // Se marca como ya procesado
	       visitados[u] = true;
	 
	    
	       for (int v = 0; v < cantidadDeNodos; v++)

	         //Se actualiza la costs[v] solo si no esta en visitados, hay un
	         //arco desde u a v y el peso total del camino desde nodoinicial hasta v a traves de u es 
	         // mas pequeno que el valor actual de costos[v]
	         if (!visitados[v] && matrizAdy[u][v] > 0 && costos[u] != Integer.MAX_VALUE 
	                                       && costos[u]+matrizAdy[u][v] < costos[v]){
	        	 costos[v] = costos[u] + matrizAdy[u][v];
	        	 ruta[v] = u;
	         }
	            
	     }
	 
	}

	// Funcion utilitaria para encontrar el vertice con la costosancia minima,
	// a partir del conjunto de los vertices todavia no incluidos en el
	// camino mas corto
	private int distanciaMinima(int[] costos, boolean[] visitados) {
		int min = Integer.MAX_VALUE;
		int v = 1;

		for (int i = 0; i < cantidadDeNodos; i++)
			if (!visitados[i] && costos[i] <= min) {
				min = costos[i];
				v = i;
			}

		return v;
	}
	public void muestraCostos(){
		for (int i = 0; i < costos.length; i++) {
			System.out.print(costos[i] + " ");
		}
	}
	
	public void MostrarRuta(int destino) {
		System.out.println(destino +1);
		while(ruta[destino] != 0) {
			System.out.println(ruta[destino] + 1);
			destino = ruta[destino];
		}
		System.out.println(ruta[destino] + 1);
	}
}
