package minotauro;

import grafo.Grafo;

public class MinotauroPrim {
	
	Grafo grafo;
	int costoTotal;
	
	public int getCostoTotal() {
		return costoTotal;
	}

	public MinotauroPrim(String rutaEntrada,String rutaSalida) throws Exception {
		//Utilizando Prim
		grafo = new Grafo(rutaEntrada);
		costoTotal = grafo.Prim();
		grafo.MostrarArbolReducido();
		grafo.grabarResultado(rutaSalida);
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("Resultado de Batiendo al Minotauro:\n-----------------------------------\n");
		
		MinotauroPrim m1 = new MinotauroPrim("minotauro.in","minotauro.out");
		
		System.out.println("Cantidad de Nodos: "+m1.grafo.getCantidadDeNodos());
		System.out.println("Cantidad de Arcos : "+m1.grafo.getCantidadArcosHabilitados());
		System.out.println("Costo Total: "+m1.getCostoTotal());
	}
}
