package metro;

import grafo.Grafo;

public class MetroPrim {

	Grafo grafo;
	int costoTotal;

	public int getCostoTotal() {
		return costoTotal;
	}

	public MetroPrim(String rutaEntrada, String rutaSalida) throws Exception {
		// Utilizando Prim
		grafo = new Grafo(rutaEntrada);

		grafo.grabarResultado(rutaSalida);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Resultado del Metro:\n--------------------\n");

		MetroPrim m1 = new MetroPrim("metro.in", "metro.out");
		System.out.println("\nArcos Resultantes: "+m1.grafo.arcosHabilitados());
	}
}
