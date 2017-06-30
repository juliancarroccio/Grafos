package pack_rpelicula;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class Pelicula {
	
	private int cantnodo;
	private int ultimaescena;
	private String vectobj[];
	private double matady [][];
	private int ultimo[];
	private int march[][];
	private double D[];
	private boolean F[];
	private int n;
	
public Pelicula() 
	{
	cantnodo = 0;
	ultimaescena=0;
	vectobj= new String[0];
	matady = new double [0][0];	
	ultimo=new int[0];
	march=new int [0][0];
	D=new double[0];
	F=new boolean[0];
	n=0;
	}

public Pelicula(String arg)
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
			this.ultimaescena = Integer.parseInt(datos[1]);
			this.vectobj= new String[cantnodo];
			this.matady = new double [cantnodo][cantnodo];
			this.ultimo=new int[cantnodo];
			this.march=new int [cantnodo][3];
			this.D=new double[cantnodo];
			this.F=new boolean[cantnodo];
			this.n=0;
			for(int p=0; p<cantnodo;p++)
				for(int q=0; q<cantnodo;q++)
					this.matady[p][q]=0xFFFF;
			linea=br.readLine();
			for(int i=0;i<this.cantnodo;i++)
				{
				datos = linea.split(" ");
				this.vectobj[i]=(datos[0]+"-"+datos[2]);
				march[i][0]=Integer.parseInt(datos[0]);
				march[i][1]=Integer.parseInt(datos[1]);
				march[i][2]=Integer.parseInt(datos[2]);
				linea=br.readLine();
				}
			for(int j=0;j<this.cantnodo;j++)
				{
				for (int q=j+1;q<this.cantnodo;q++)
					{
					System.out.println(march[j][2]+">="+march[q][1]+ "&&" + march[q][2]+">"+march[j][2] + "\n");
					if(j<this.cantnodo && march[j][2]>=march[q][1] && march[q][2]>march[j][2])
						{
						System.out.println("Se cumple que "+ march[j][2]+">="+march[q][1]+ "&&" + march[j+1][2]+">"+march[j][2] + "\n");
						double valor=march[j][2]-march[q][1]+1;
						System.out.println("Hay coneccion entre "+ march[j][0]+ " y " +  march[q][0]+ " y tiene valor de " + valor + "\n");
						this.matady[march[j][0]-1][march[q][0]-1]=valor;
						}
					else System.out.println("No se cumple que "+march[j][2]+">="+march[q][1]+ "&&" + march[q][2]+">"+march[j][2] + "\n");
					}
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
	return "Grafos [cantnodo=" + cantnodo 
			+ ", vectobj=" + Arrays.toString(vectobj) 
			+ "]";
	}

public static void main(String[] args) 
	{
	Pelicula Grafo1=new Pelicula("C:/Users/Mutu/Documents/Programacion 3/Grafos/Grafopelicula.txt");
	System.out.println(Grafo1.toString());
	Grafo1.dijkstra(0);//posicion del nodo empieza por el 0
	int s=0,smin=0;
	double aux=0, min=0xFFFF;
	while(s<Grafo1.cantnodo && s<Grafo1.cantnodo)
		{
		if(Grafo1.march[s][2]==100)
			{
			System.out.println("El nodo " + Grafo1.vectobj[s] + " es un nodo de fin");
			aux=Grafo1.costocamino(s); //posicion del nodo empieza por el 0
			if(aux<min)
				{
				min=aux;
				smin=s;
				}
			}
		s++;
		}
	System.out.println("El nodo " + Grafo1.vectobj[smin] + " es el mejor nodo de fin ya que tiene costo " + min);
	Grafo1.recuperarcamino(smin); //posicion del nodo empieza por el 0
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
}
