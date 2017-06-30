

import java.util.ArrayList;

public class BusquedaEnProfundidad { /// DFS

	private int[][] adyacencia;
	private int contador = 0;
	private int ultimo;

	public BusquedaEnProfundidad(int[][] matAdy, int primero) {
		adyacencia = matAdy;
		ultimo = matAdy.length-1;
		dfs(matAdy, primero);
	}

	private void dfs(int[][] matAdy, int primero) {

		for (int w : nodosAdyacentesA(primero)) {
			dfs(matAdy, w);	
		}
		if(primero == this.ultimo)
			contador++;
	}

	public ArrayList<Integer> nodosAdyacentesA(int nodo) {
		ArrayList<Integer> nodosAdy = new ArrayList<Integer>();
		for (int i = 0; i < adyacencia.length; i++) {
			if (esAdyacente(nodo, i) && nodo != i) {
				nodosAdy.add(i);
			}
		}
		return nodosAdy;
	}

	public boolean esAdyacente(int nodoA, int nodoB) {
		return adyacencia[nodoA][nodoB] != 0;
	}

	public int getContador() {
		return contador;
	}
	

}
