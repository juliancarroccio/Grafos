package algoritmos;

import java.util.ArrayList;

public class BusquedaEnProfundidad { /// DFS

	private boolean[] marcado;
	private int[][] adyacencia;
	private boolean raiz;
	private int cont = 0;

	public BusquedaEnProfundidad(int[][] matAdy, int primero) {
		adyacencia = matAdy;
		this.raiz = true;
		marcado = new boolean[matAdy.length];
		dfs(matAdy, primero);
	}

	private void dfs(int[][] matAdy, int primero) {
		if (this.raiz) {
			System.out.println("Raiz: " + (primero + 1));
			this.raiz = false;
		}
		marcado[primero] = true;
		for (int w : nodosAdyacentesA(primero)) {
			if (!marcado[w]) {
				System.out.println(w + 1);
				dfs(matAdy, w);
				System.out.println("salto a " + (w - cont));
				cont++;

			}

		}
	}

	public boolean marcado(int v) {
		return marcado[v];
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

}
