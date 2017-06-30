package algoritmos;

public class Warshall {	///O(n cubo)
	public static void algWarshall(int[][] matrizAdy) {
		int cantPos = matrizAdy.length;
		
		for (int k = 0; k < cantPos; k++) {
			for (int i = 0; i < cantPos; i++) {
				for (int j = 0; j < cantPos; j++) {
					if(matrizAdy[i][j]!=1)
						matrizAdy[i][j] = matrizAdy[i][k] & matrizAdy[k][j];
				}
			}
		}
	}
}
