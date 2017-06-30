package minotauro;

import grafo.Grafo;

public class MinotauroKruskal {
	
	Grafo grafo;
	int costoTotal;
	
	public int getCostoTotal() {
		return costoTotal;
	}

	public MinotauroKruskal(String rutaEntrada,String rutaSalida) throws Exception {
		//Utilizando Kruskal
		grafo = new Grafo(rutaEntrada);
		grafo.OrdenarCostos();
		costoTotal = grafo.Kruskal();
		grafo.MostrarArbolReducido();
		grafo.grabarResultado(rutaSalida);
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("Resultado de Batiendo al Minotauro:\n-----------------------------------\n");
		
		MinotauroKruskal m1 = new MinotauroKruskal("minotauro.in","minotauro.out");
		
		System.out.println("Cantidad de Nodos: "+m1.grafo.getCantidadDeNodos());
		System.out.println("Cantidad de Arcos : "+m1.grafo.getCantidadArcosHabilitados());
		System.out.println("Costo Total: "+m1.getCostoTotal());
	}
}
