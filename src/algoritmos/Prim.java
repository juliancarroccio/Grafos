package algoritmos;

/**
 * Clase que encuentra un subconjunto de aristas que forman un �rbol con todos
 * los vertices, donde el peso total de todas las aristas en el �rbol es el
 * m�nimo posible.
 * <p>
 * El grafo debe ser no dirigido.
 * 
 * ACLARACION: EL ARDEN DE LOS VERTICES LO DA LA FILA DE LA MATRIZ DE ADYACENCIA
 */
public class Prim {
	/**
	 * Matriz de adyacencia. 
	 */
	private int[][] matrizAdyacencia;
	/**
	 * Tama�o de la matriz.
	 */
	private int tama�o;
	/**
	 * Nodo origen. <br>
	 */
	private int[] padre;
	/**
	 * Peso m�nimo para llegar a un nodo.
	 */
	private int[] pesoMinimo;
	/**
	 * Recorrido realizado por el algoritmo.
	 */
	private boolean[] recorridoRealizado;

	/**
	 * Busca el subconjunto de aristas que forman un �rbol con todos los
	 * vertices, donde el peso total de todas las aristas en el �rbol es el
	 * m�nimo posible.
	 * 
	 * @param matrizAdy
	 *            Matriz de adyacencia del grafo.
	 */
	public Prim(final int[][] matrizAdy) {
		this.tama�o = matrizAdy.length;
		this.matrizAdyacencia = matrizAdy;
		this.padre = new int[this.tama�o];
		this.pesoMinimo = new int[this.tama�o];
		this.recorridoRealizado = new boolean[this.tama�o];
		for (int i = 0; i < this.tama�o; i++) {
			this.pesoMinimo[i] = Integer.MAX_VALUE;
		}
		this.pesoMinimo[0] = 0;
		this.padre[0] = -1;
		for (int count = 0; count < this.tama�o - 1; count++) {
			int i = menorPeso();
			this.recorridoRealizado[i] = true;
			for (int j = 0; j < this.tama�o; j++) {
				if (this.matrizAdyacencia[i][j] != 0 && this.recorridoRealizado[j] == false
						&& this.matrizAdyacencia[i][j] < this.pesoMinimo[j]) {
					this.padre[j] = i;
					this.pesoMinimo[j] = this.matrizAdyacencia[i][j];
				}
			}
		}
	}

	/**
	 * Muestra el subconjunto de aristas que forman el �rbol el menor peso
	 * posible. <br>
	 */
	public void mostarResultado() {
		int peso = 0;
		System.out.println("Resultado:");
		for (int i = 1; i < this.tama�o; i++) {
			if (this.matrizAdyacencia[i][this.padre[i]] != Integer.MAX_VALUE) {
				System.out.println("De nodo " + (this.padre[i] + 1) + " a nodo " + (i + 1) + " Con peso " + this.matrizAdyacencia[i][this.padre[i]]);
				peso += this.matrizAdyacencia[i][this.padre[i]];
			}
		}
		System.out.print("\n");
		System.out.println("Peso total: " + peso);
		System.out.print("\n");
	}

	/**
	 * Devuelve la posici�n de la arista con menor peso.
	 * 
	 * @return �ndice.
	 */
	private int menorPeso() {
		int min = Integer.MAX_VALUE, indice = 0;
		for (int i = 0; i < this.tama�o; i++) {
			if (this.recorridoRealizado[i] == false && this.pesoMinimo[i] < min) {
				min = this.pesoMinimo[i];
				indice = i;
			}
		}
		return indice;
	}
}