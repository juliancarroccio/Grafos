package pack_emergencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class Emergencias {

	
	private int cantnodo;
	private int cantarista;
	private String vectobj[];
	private double matady [][];
	private int VC[];
	private int ultimo[];
	private double D[];
	private boolean F[];
	private int n;
	private int nodoorigen;
	private int nododestino;

	
	public Emergencias() 
		{
		cantnodo = 0;
		cantarista = 0;
		nodoorigen=0;
		nododestino=0;
		vectobj= new String[0];
		matady = new double [0][0];	
		ultimo=new int[0];
		VC=new int[0];
		D=new double[0];
		F=new boolean[0];
		n=0;
		
		}

	public Emergencias(String arg)
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
				this.nodoorigen = Integer.parseInt(datos[2]);
				this.nododestino = Integer.parseInt(datos[3]);
				this.vectobj= new String[cantnodo];
				this.matady = new double [cantnodo][cantnodo];
				this.ultimo=new int[cantnodo];
				this.D=new double[cantnodo];
				this.F=new boolean[cantnodo];
				this.n=0;
				VC=new int[cantnodo];
				for(int p=0; p<cantnodo;p++)
					for(int q=0; q<cantnodo;q++)
						this.matady[p][q]=0xFFFF;	
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
		Emergencias Emergencia1=new Emergencias("C:/Users/Mutu/Documents/Programacion 3/Grafos/Emergencia1.txt");
		System.out.println(Emergencia1.toString());
		Emergencia1.dijkstra(Emergencia1.getNodoorigen()-1);//posicion del nodo empieza por el 0
		Emergencia1.costocamino(Emergencia1.getNododestino()-1);
		Emergencia1.cantcaminos(Emergencia1.getNododestino()-1);
		}
	
	//determina la minima distancia para viajar de ese nodo a todos los nodos del grafo
	
	public int getNodoorigen() {
		return nodoorigen;
	}

	public int getNododestino() {
		return nododestino;
	}

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
					if((D[v]+this.matady[v][w])<=D[w])
						{
						if ((D[v]+this.matady[v][w])==D[w])
							VC[w]++;
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
	
	private void costocamino(int destino)
		{
		System.out.println("El costo de los caminos potables es " + D[destino]);
		}
	
	private void cantcaminos(int destino)
		{
		int nodo=destino+1;
		int cant=this.VC[destino]+1;
		System.out.println("La cantidad de caminos potables para el nodo " + nodo + " es " + cant);
		}
}