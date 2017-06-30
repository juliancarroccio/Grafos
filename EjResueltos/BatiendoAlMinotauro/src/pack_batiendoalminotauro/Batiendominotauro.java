package pack_batiendoalminotauro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class Batiendominotauro {

		private int []vec_nod;
		private int [][]mat_ady;
		private int []vec_aux;
		private int cant_nodos,cant_aris;
		
		public Batiendominotauro() 
		{
			vec_nod=new int[0];
			mat_ady=new int[0][0];
			vec_aux=new int[0];
			cant_nodos=0;
			cant_aris=0;
		}

		public Batiendominotauro(String arg) 
		{
			File file = null;
			FileReader fr = null;
			BufferedReader br =null;
			try
				{
				file = new File(arg);
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String linea;
				if ((linea=br.readLine())!=null)
					{
					String datos[];
					cant_nodos = Integer.parseInt(linea);
					vec_nod= new int[cant_nodos];
					vec_aux= new int[cant_nodos];
					mat_ady = new int [cant_nodos][cant_nodos];
					for(int i=0; i<cant_nodos; i++)
						{
						vec_nod[i]=i+1;
						}
					for (int j=0; j<cant_nodos; j++)
						{
						linea=br.readLine();
						datos = linea.split(" ");
						for (int s=0; s<cant_nodos; s++)
						mat_ady[j][s]=Integer.parseInt(datos[s]);
						}
					}
				}
			catch(Exception e)
				{
				e.printStackTrace();			
				}
			finally{}
		}
		
		
		
			
		public String toString() {
				for (int i=0;i<cant_nodos;i++)
				{
					System.out.println(Arrays.toString(mat_ady[i]));
				}
				
			return "Prim [vec_nod=" + Arrays.toString(vec_nod) + ", vec_aux="
					+ Arrays.toString(vec_aux) + ", cant_nodos=" + cant_nodos
					+ ", cant_aris=" + cant_aris + "]";
		}

		public void resolver ()
		{
			vec_aux[0]=vec_nod[0];
			vec_nod[0]=0;
			int i;
			int min=0xFFFF;
			int aux;
			int pos=0;
			int posini=0;
			int posfin=0;
			int cost=0;
			
			while (!(termina()))
			{
				i=0;
				while (vec_aux[i]!=0)
				{
					for(int j=0;j<cant_nodos;j++)
					{
						if(vec_nod[j]!=0)
						{
						aux=costo(vec_aux[i]-1,vec_nod[j]-1);
						if (aux<min)
							{
							min=aux;
							pos=j;
							posini=vec_aux[i];
							}
						}
					}
					i++;
				}
				cost+=min;
				posfin=pos+1;
				//System.out.println("El nodo "+ posini +" se conecta al nodo " + posfin + " con costo " + min);
				System.out.println( posini +" " + posfin + " " + min);
				min=0xFFFF;
				vec_aux[i]=vec_nod[pos];			
				vec_nod[pos]=0;
			}
			System.out.println(Arrays.toString(vec_aux));
			System.out.println("Costo: "+cost);
		}
		
		private int costo (int pos1,int pos2)
		{
			return mat_ady[pos1][pos2];
		}
		
		public boolean termina ()
		{
			int aux=0;
			for (int i=0;i<cant_nodos;i++)
				aux+=vec_nod[i];
			
			return aux==0;
		}
		
			public static void main(String[] args) 
			{
				Batiendominotauro grafo=new Batiendominotauro ("C:/Users/Mutu/Documents/Programacion 3/Grafos/Batiendo2.txt");
				
				//System.out.println(grafo);
				
				grafo.resolver();
			}

	}

