package metro;

import grafo.Grafo;

public class MetroKruskal {

	Grafo grafo;

	public MetroKruskal(String rutaEntrada, String rutaSalida) throws Exception {
		// Utilizando Kruskal
		grafo = new Grafo(rutaEntrada);
		grafo.OrdenarCostos();
		grafo.grabarResultado(rutaSalida);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Resultado de Metro:\n-------------------\n");

		MetroKruskal m1 = new MetroKruskal("metro.in", "metro.out");

		System.out.println("Cantidad de Nodos: "
				+ m1.grafo.getCantidadDeNodos());
		System.out.println("Cantidad de Arcos Resultantes : "
				+ m1.grafo.getCantidadArcosHabilitados());
	}
}
