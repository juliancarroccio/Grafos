import java.util.Vector;


public class Grafo {
	private int matAdyacencia[][];
	private int cant_nodos;
	private Vector<Vector<Integer>> nodos;
	private int matCosto[][];

	public Grafo (Vector<Vector<Integer>> nodos, int cant_nodos)
	{
		this.cant_nodos=cant_nodos;
		this.nodos=nodos;
		
		matAdy(nodos);

		
		for(int i=0;i<this.cant_nodos;i++)
		{
			for(int j=0;j<this.cant_nodos;j++)
				System.out.print(this.matAdyacencia[i][j]);
			System.out.println();
		}
		
		this.matCosto=new int [this.cant_nodos][this.cant_nodos];
		matCosto();
	}
	
	private void matCosto()
	{
		System.out.println("matCosto");
		for (int i=0;i<this.cant_nodos;i++)
		{
			for(int j=0;j<this.cant_nodos;j++)
			{
				if(this.matAdyacencia[i][j]==1)
					this.matCosto[i][j]=this.matAdyacencia[i][j];
				else

						this.matCosto[i][j]=99999;
			
			System.out.print(this.matCosto[i][j]+" ");
			}
			System.out.println();
		}
		
		
	}
	private void matAdy(Vector<Vector<Integer>> nodos)
	{
		boolean encontrado;
		this.matAdyacencia = new int [this.cant_nodos][this.cant_nodos];
		System.out.println("Mat ady");
		for (int i=0;i<this.cant_nodos;i++)
			for(int j=0;j<this.cant_nodos;j++)
			{
				encontrado=false;
				int k=0;
				while (k<nodos.get(i).size()&& !encontrado)
				{
					int l=0;
					while(l<nodos.get(j).size() && !encontrado)
					{
						//System.out.println(i+" "+k+" "+nodos.get(i).get(k)+" "+j+" "+l+" "+nodos.get(j).get(l));
						if(nodos.get(i).get(k)==nodos.get(j).get(l) && i!=j)
						{
							//System.out.println(nodos.get(j).size());
							
							this.matAdyacencia[i][j]=1;
							encontrado=true;
						}
						l++;
					}
					//encontrado=false;
					k++;
				}
			}
		
	}
	
	public Vector<Integer> buscoInicio(int estacion)
	{
		Vector<Integer> resultado;
		resultado = new Vector<Integer>();
		for (int i=0;i<this.cant_nodos;i++)
			for(int j=0;j<this.nodos.get(i).size();j++)
				if(this.nodos.get(i).get(j)==estacion)
				{
					resultado.add(i);
				}
		return resultado;
	}
	
	public Vector<Integer> buscoDestino(int estacion)
	{
		Vector<Integer> resultado;
		resultado = new Vector<Integer>();
		for (int i=0;i<this.cant_nodos;i++)
			for(int j=0;j<this.nodos.get(i).size();j++)
				if(this.nodos.get(i).get(j)==estacion)
				{
					resultado.add(i);
				}
		return resultado;
	}
	
	public Vector<Integer> buscaCamino (int origen, int fin)
	{
		int [][] caminos;
		Vector<Integer> resultado=new Vector<Integer>();
		Vector<Integer> inicio=new Vector<Integer>();
		inicio=buscoInicio(origen);
		Vector<Integer> destino=new Vector<Integer>();
		destino=buscoDestino(fin);
		

		System.out.println("Inicio");
		for(int i=0;i<inicio.size();i++)
			System.out.println(inicio.get(i));
		
		System.out.println("Destino");
		for(int i=0;i<destino.size();i++)
			System.out.println(destino.get(i));
		int anterior=-1;
		int nodo_i=0;
		int nodo_f=0;
		caminos=this.floyd();
		
		System.out.println("matCosto");
		for(int i=0;i<this.cant_nodos;i++)
		{
			for(int j=0;j<this.cant_nodos;j++)
				System.out.print(matCosto[i][j]+" ");
			System.out.println();
		}
		
		int menorCosto=999999;
		for(int i=0;i<inicio.size();i++)
		{
			for(int j=0;j<destino.size();j++)
			{
				if (menorCosto>this.matCosto[inicio.get(i)][destino.get(j)])
				{
					menorCosto=matCosto[inicio.get(i)][destino.get(j)];
					nodo_i=inicio.get(i);
					nodo_f=destino.get(j);
					//anterior = caminos[i][j];
				}
			}
		}
		System.out.println("ESTE");
		
		System.out.println("desde "+nodo_i+" Hasta "+nodo_f );
		System.out.println(nodo_i);
		System.out.println(nodo_f);
		anterior=caminos[nodo_i][nodo_f];
		System.out.println(anterior);
		while (anterior!=-1)
		{
			System.out.println(anterior);
			resultado.add(anterior+1);
			anterior=caminos[nodo_i][anterior-1];
		}
		resultado.add(nodo_i+1);
		
		System.out.println("camino");
		int i=resultado.size()-1;
		System.out.println(resultado.size());
		while(i>=0 && resultado.get(i)!=-1)
		{
			System.out.println(resultado.get(i));
			i--;
		}
		
		return resultado;
		
	}
	
	
	public int[][] floyd()
	{
		int[][]matCamino=matrizCamino();
		int aux;
		System.out.println("Mat Camino");
		for(int k=0;k<this.cant_nodos;k++)
			for(int i=0;i<this.cant_nodos;i++)
				for(int j=0;j<this.cant_nodos;j++)
				{
					if((aux=matCosto[i][k]+matCosto[k][j])<matCosto[i][j] && i!=j)
					{System.out.println(i+" "+j+" "+aux+"<"+matCosto[i][j]);
						matCosto[i][j]=aux;
						matCamino[i][j]=k+1;
					}
					if(this.matAdyacencia[i][j]==1)
						matCamino[i][j]=i+1;

					//System.out.print(matCamino[i][j]+" ");
				}
		
		for(int k=0;k<this.cant_nodos;k++)
		{
			for(int i=0;i<this.cant_nodos;i++)
				System.out.print(matCamino[k][i]+" ");
			System.out.println();
		}
		return matCamino;
	}
	

	private int [][] matrizCamino()
	{
		int[][] matCamino = new int [this.cant_nodos][this.cant_nodos];
		
		for (int i=0;i<this.cant_nodos;i++)
		{
			for (int j=0;j<this.cant_nodos;j++)
			{
				if(i==j)
					matCamino[i][j]=-1;
				else
					matCamino[i][j]=0;
				
			System.out.print(matCamino[i][j]+" ");
			}
			System.out.println();
		}
		return matCamino;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
