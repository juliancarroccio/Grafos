package prueba;
import algoritmos.*;
public class Principal {
	public static final int INF = 0;
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
	
		int[][] matriz = { {INF,1,1,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF},
						   {1,INF,1,INF,INF,INF,INF,INF,INF,INF,INF,1,INF,INF},
						   {1,1,INF,INF,1,INF,INF,INF,INF,INF,INF,INF,1,INF},
						   {INF,INF,INF,INF,INF,1,INF,INF,INF,INF,INF,INF,INF,INF},
						   {INF,INF,1,INF,INF,INF,1,1,INF,INF,INF,INF,INF,INF},
						   {INF,INF,INF,1,INF,INF,INF,INF,INF,INF,INF,1,INF,INF},
						   {INF,INF,INF,INF,1,INF,INF,1,INF,INF,INF,INF,INF,INF},
						   {INF,INF,INF,INF,1,INF,1,INF,INF,1,INF,INF,INF,INF},
						   {INF,INF,INF,INF,INF,INF,INF,INF,INF,1,1,1,1,INF},
						   {INF,INF,INF,INF,INF,INF,INF,1,1,INF,1,INF,INF,1},
						   {INF,INF,INF,INF,INF,INF,INF,INF,1,1,INF,INF,1,INF},
						   {INF,1,INF,INF,INF,1,INF,INF,1,INF,INF,INF,INF,INF},
						   {INF,INF,1,INF,INF,INF,INF,INF,1,INF,1,INF,INF,INF},
						   {INF,INF,INF,INF,INF,INF,INF,INF,INF,1,INF,INF,INF,INF}};
		
	   ///PRUEBA FLOYD
		
//		Floyd f = new Floyd();
//		f.algoritmoFloyd(matriz);
//		for (int i = 0; i < matriz.length; i++) {
//			for (int j = 0; j < matriz.length; j++) {
//				System.out.print(matriz[i][j] + " ");
//			}
//			System.out.print(System.lineSeparator());
//		}
		
		
		///PRUEBA DIJKSTRA
		
//		Dijkstra d = new Dijkstra(matriz,0);
//		d.resuelveDijkstra();
//		d.muestraCostos();
//		System.out.println();
//		d.MostrarRuta(3);
		
		
		
		///PRUEBA PRIM
		
//		Prim p = new Prim(matriz);
//		p.mostarResultado();
		
		///PRUEBA KRUSCAL

//		 Grafo g1= new Grafo("grafo2.txt");
//		  
//		 Integer c = g1.Kruscal();
//	     System.out.println(c);
//	     g1.MostrarArbolReducido();
		
		///PRUEBA BFS
		
//		Bfs b = new Bfs(matriz, 0);
//		b.resolver();
//		b.imprimirSalida();
//		b.imprimirCaminoHasta(13);
		
		///PRUEBA DFS
		
//		BusquedaEnProfundidad b = new BusquedaEnProfundidad(matriz,0 );
	
		
		
	}
}
