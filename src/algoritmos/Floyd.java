package algoritmos;

public class Floyd {	///O(n cubo)
	
	public void algoritmoFloyd(int[][] matrizAdy) {
		int cantPos = matrizAdy.length;
		
		for (int k = 0; k < cantPos; k++) {
			for (int i = 0; i < cantPos; i++) {
				for (int j = 0; j < cantPos; j++) {
					if(matrizAdy[i][k]!=Integer.MAX_VALUE && matrizAdy[k][j]!=Integer.MAX_VALUE &&
							Math.min(matrizAdy[i][j], matrizAdy[i][k] + matrizAdy[k][j])!=matrizAdy[i][j])
						matrizAdy[i][j] = matrizAdy[i][k] + matrizAdy[k][j];
				}
			}
		}
	}
	
}
