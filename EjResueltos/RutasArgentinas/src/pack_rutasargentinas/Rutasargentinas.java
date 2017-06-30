package pack_rutasargentinas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class Rutasargentinas {
	
	private int cantnodo;
	private int cantarista;
	private String vectciud[];
	private double matady [][];
	private int ultimo[];
	private double D[];
	private boolean F[];
	private int n;
	private int origen;
	private int destino;
	private int vectpeajes[];

	
	public Rutasargentinas() 
		{
		cantnodo = 0;
		cantarista = 0;
		origen=0;
		destino=0;
		vectciud= new String[0];
		vectpeajes= new int[0];
		matady = new double [0][0];	
		ultimo=new int[0];
		D=new double[0];
		F=new boolean[0];
		n=0;
		}

	public Rutasargentinas(String arg)
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
				this.cantnodo = Integer.parseInt(linea);
				this.vectciud= new String[cantnodo];
				this.matady = new double [cantnodo][cantnodo];
				this.vectpeajes= new int[cantnodo];
				this.ultimo=new int[cantnodo];
				this.D=new double[cantnodo];
				this.F=new boolean[cantnodo];
				this.n=0;
				for(int p=0; p<cantnodo;p++)
					for(int q=0; q<cantnodo;q++)
						this.matady[p][q]=0xFFFF;	
				for(int i=0; i<cantnodo; i++)
					{
					linea=br.readLine();
					datos = linea.split(" ");
					this.vectciud[i]=datos[0];
					this.vectpeajes[i]=Integer.parseInt(datos[1]);
					}
				linea=br.readLine();
				this.cantarista=Integer.parseInt(linea);
				for (int j=0; j<cantarista; j++)
					{
					linea=br.readLine();
					datos = linea.split(" ");
					int pos1=-1;
					int pos2=-1;
					for(int k=0;k<cantnodo; k++)
						{
						if(datos[0].equals(vectciud[k]))
							pos1=k;
						}
					for(int l=0;l<cantnodo; l++)
						{
						if(datos[1].equals(vectciud[l]))
							pos2=l;
						}
					this.matady[pos1][pos2]=Double.parseDouble(datos[2])*0.23+this.vectpeajes[pos2];
					this.matady[pos2][pos1]=Double.parseDouble(datos[2])*0.23+this.vectpeajes[pos1];
					}
				linea=br.readLine();
				datos = linea.split(" ");
				int posorig=0,posdest=0;
				for(int k=0;k<cantnodo; k++)
					{
					if(datos[0].equals(vectciud[k]))
					posorig=k;
					}
				for(int l=0;l<cantnodo; l++)
					{
					if(datos[1].equals(vectciud[l]))
					posdest=l;
					}
				this.origen=posorig;
				this.destino=posdest;
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
		return "Ruta [cantnodo=" + cantnodo + ", cantidad de rutas=" + cantarista
				+ ", Ciudades=" + Arrays.toString(vectciud) 
				+ "]";
		}

	public int getOrigen() {
		return origen;
	}

	public int getDestino() {
		return destino;
	}

	public static void main(String[] args) 
		{
		Rutasargentinas Rutas1=new Rutasargentinas("C:/Users/Mutu/Documents/Programacion 3/Grafos/Rutas2.txt");
		System.out.println(Rutas1.toString());
		Rutas1.dijkstra(Rutas1.getOrigen());//posicion del nodo empieza por el 0
		Rutas1.recuperarcamino(Rutas1.getDestino()); //posicion del nodo empieza por el 0
		Rutas1.costocamino(Rutas1.getDestino());
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
				System.out.println(" -> " + this.vectciud[destino-1]);
				}
			else
				{
				n++;
				System.out.print(this.vectciud[n-1] + "\n");
				}
			}
		}
private void costocamino(int destino)
	{
	System.out.println(" El costo es " + D[destino]);

	}
}
