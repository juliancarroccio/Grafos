package grafo;

public class Vector<T> {

	private int tama�o;
	private T vector[];
	private int ultimo;

	@SuppressWarnings("unchecked")
	public Vector(int tam) {
		tama�o = tam;
		vector = (T[]) new Object[tama�o];
		for (int i = 0; i < tam; i++)
			vector[i] = null;
	}

	public int getTama�o() {
		return tama�o;
	}

	public T getElemento(int pos) {
		return vector[pos];
	}

	public void setElemento(int pos, T elemento) {
		vector[pos] = elemento;
	}

	public void insertar(T o, int pos) throws Exception {
		if (pos > tama�o)
			throw new Exception("Posicion fuera de rango");
		vector[pos] = o;
	}

	public void insertar(T o) throws Exception {
		int i = 0;
		while (vector[i] != null && i < tama�o)
			i++;
		if (i == tama�o)
			throw new Exception("No hay mas lugar en el vector");
		else {
			vector[i] = o;
			ultimo = i;
		}
	}

	public int getUltimo() {
		return ultimo;
	}

	public void setUltimo(int ultimo) {
		this.ultimo = ultimo;
	}

	public T obtener(int pos) {

		return vector[pos - 1];
	}

	public T obtener() {
		return vector[ultimo];
	}

}
