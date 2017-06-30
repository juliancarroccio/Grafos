package pack_rescatandolaprincesa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class Rescatandoprincesa {
	
		private int cantnodo;
		private int cantarista;
		private int cantdragones;
		private int posprincesa;
		private int posprincipe;
		private int posdragones[];
		private String vectobj[];
		private double matady [][];
		private int ultimo[];
		private double D[];
		private boolean F[];
		private int n;

		
		public Rescatandoprincesa() 
			{
			cantdragones=0;
			posprincesa=0;
			posprincipe=0;
			posdragones=new int [0];
			cantnodo = 0;
			cantarista = 0;
			vectobj= new String[0];
			matady = new double [0][0];	
			ultimo=new int[0];
			D=new double[0];
			F=new boolean[0];
			n=0;
			}

		public Rescatandoprincesa(String arg)
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
					String [] datos;
					datos = linea.split(" ");
					this.cantnodo = Integer.parseInt(datos[0]);
					this.cantarista = Integer.parseInt(datos[1]);
					this.cantdragones = Integer.parseInt(datos[2]);
					this.vectobj= new String[cantnodo];
					this.matady = new double [cantnodo][cantnodo];
					this.ultimo=new int[cantnodo];
					this.D=new double[cantnodo];
					this.F=new boolean[cantnodo];
					this.n=0;
					for(int p=0; p<cantnodo;p++)
						for(int q=0; q<cantnodo;q++)
							this.matady[p][q]=0xFFFF;	
					linea=br.readLine();
					datos = linea.split(" ");
					this.posprincesa = Integer.parseInt(datos[0]);
					this.posprincipe = Integer.parseInt(datos[1]);
					this.posdragones=new int[cantdragones];
					linea=br.readLine();
					datos = linea.split(" ");
					for(int g=0;g<this.cantdragones;g++)
						{
						this.posdragones[g]=Integer.parseInt(datos[g]);
						}
					for(int i=0; i<cantnodo; i++)
						{
						this.vectobj[i]=String.valueOf(i+1);
						}
					for (int j=0; j<cantarista; j++)
						{
						linea=br.readLine();
						datos = linea.split(" ");
						int pos1=-1;
						int pos2=-1;
						for(int k=0;k<cantnodo; k++)
							{
							if(datos[0].equals(vectobj[k]))
								pos1=k;
							}
						for(int l=0;l<cantnodo; l++)
							{
							if(datos[1].equals(vectobj[l]))
								pos2=l;
							}
						this.matady[pos1][pos2]=Double.parseDouble(datos[2]);
						this.matady[pos2][pos1]=Double.parseDouble(datos[2]);
						}
					}
				}
			catch(Exception e)
				{
				e.printStackTrace();			
				}
			finally
				{}
			}

		
		public String toString()
			{
			for (int i=0; i<this.cantnodo; i++)
				System.out.println(Arrays.toString(matady[i]));
			return "Grafos [cantnodo=" + cantnodo + ", cantarista=" + cantarista
					+ ", vectobj=" + Arrays.toString(vectobj) 
					+ "]";
			}

		public static void main(String[] args) 
			{
			Rescatandoprincesa Rescate1=new Rescatandoprincesa("C:/Users/Mutu/Documents/Programacion 3/Grafos/Rescate1.txt");
			System.out.println(Rescate1.toString());
			Rescate1.resolver();
			}
		
		//determina la minima distancia para viajar de ese nodo a todos los nodos del grafo
		
		public void dijkstra(int nodoorigen)
			{
			n=nodoorigen;
			for(int i=0;i<this.cantnodo;i++)
				{
				F[i]=false;
				D[i]=matady[nodoorigen][i];
				ultimo[i]=nodoorigen;			
				}
			F[nodoorigen]=true;
			D[nodoorigen]=0;
			for(int j=1;j<this.cantnodo;j++)
				{
				int v=minimo();
				F[v]=true;
				for(int w=0; w<this.cantnodo;w++)
					if(!F[w])
						if((D[v]+this.matady[v][w])<D[w])
							{
							D[w]=D[v]+this.matady[v][w];
							ultimo[w]=v;
							}
				}
			System.out.println(Arrays.toString(D));
			}

		private int minimo()
			{
			double mx=0xFFFF;
			int v=1;
			for (int j=1; j<this.cantnodo; j++)
				if(!F[j] && (D[j]<=mx))
					{
					mx=D[j];
					v=j;
					}
			return v;
			}
		
		//muestra el camino del nodo elegido en dijkstra como origen hacia el elegido como destino
		
		public void recuperarcamino(int destino)
			{
			if (D[destino]==0xFFFF)
				{
				System.out.println("No existe un camino");
				}
			else
				{
				int anterior=ultimo[destino];
				if(destino!=n)
					{
					recuperarcamino(anterior);
					destino++;
					System.out.println(" -> " + this.vectobj[destino-1]);
					}
				else
					{
					n++;
					System.out.print(this.vectobj[n-1] + "\n");
					}
				}
			}
		
		private double costocamino(int destino)
			{
			System.out.println(" El costo es " + D[destino]);
			return D[destino];
			}
		
		private void resolver()
			{
			double costoprincipe;
			double aux, min=0xFFFF;
			this.dijkstra(this.posprincipe-1);//posicion del nodo empieza por el 0
			costoprincipe=this.costocamino(this.posprincesa-1);
			if(costoprincipe!=0xFFFF)
				{
				for(int f=0;f<this.cantdragones;f++)
					{
					this.dijkstra(this.posdragones[f]-1);//posicion del nodo empieza por el 0
					aux=this.costocamino(this.posprincesa-1);
					if(aux<min)
						min=aux;
					}
				}
			if(costoprincipe<min)
				{
				this.dijkstra(this.posprincipe-1);
				this.recuperarcamino(this.posprincesa-1);
				System.out.println("La distancia a la princesa es " + costoprincipe);
				}
			else 
				if (costoprincipe==0xFFFF)
					System.out.println("No hay camino");
				else System.out.println("Interceptado");
			}
	}

