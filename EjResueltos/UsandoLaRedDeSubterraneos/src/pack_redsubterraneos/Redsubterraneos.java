package pack_redsubterraneos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class Redsubterraneos {
	private int cantlineas;
	private int cantestaciones;
	private int matrizlineas [][];
	private int origen;
	private int destino;
	private String vectobj[];
	private double matady [][];
	private int ultimo[];
	private double D[];
	private boolean F[];
	private int n;
	private String vectresord[];
	private int posfin;
	
	
	public Redsubterraneos() 
		{
		cantestaciones = 0;
		cantlineas = 0;
		origen=0;
		destino=0;
		matrizlineas= new int[0][0];
		vectresord = new String[0];
		posfin=0;
		vectobj= new String[0];
		matady = new double [0][0];	
		ultimo=new int[0];
		D=new double[0];
		F=new boolean[0];
		n=0;
		}
	
	public Redsubterraneos(String arg)
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
			this.cantlineas = Integer.parseInt(datos[0]);
			this.cantestaciones = Integer.parseInt(datos[1]);
			this.vectobj= new String[cantlineas];
			this.matady = new double [cantlineas][cantlineas];
			this.ultimo=new int[cantlineas];
			this.D=new double[cantlineas];
			this.F=new boolean[cantlineas];
			this.n=0;
			this.vectresord = new String[cantlineas];
			this.posfin=0;
			this.matrizlineas= new int[cantlineas][cantestaciones];
			for(int p=0; p<cantlineas;p++)
				for(int q=0; q<cantlineas;q++)
					this.matady[p][q]=0xFFFF;	
			for(int i=0; i<cantlineas; i++)
				{
				this.vectobj[i]=String.valueOf(i+1);
				linea=br.readLine();
				datos = linea.split(" ");
				for(int y=1; y<=Integer.parseInt(datos[0]);y++)
					this.matrizlineas[i][Integer.parseInt(datos[y])-1]=1;	
				}
			for (int j=0; j<cantestaciones; j++)
				{
				for(int k=0;k<cantlineas; k++)
					{
					for(int l=k+1;l<cantlineas;l++)
						{
						if(k<cantestaciones-1 && l<cantestaciones-1)
							{
							if(matrizlineas[k][j]==matrizlineas[l][j] && matrizlineas[k][j]==1)
								{
								this.matady[k][l]=1;
								this.matady[l][k]=1;
								}
							}
						}
					}
				}
			linea=br.readLine();
			datos = linea.split(" ");
			
			if(!this.buscarlinea(Integer.parseInt(datos[0]),Integer.parseInt(datos[1])))
				{
				origen=buscarpos(Integer.parseInt(datos[0]));
				System.out.println("origen "+ origen);
				destino=buscarpos(Integer.parseInt(datos[1]));
				System.out.println("destino "+ destino);
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
	
	private int buscarpos( int num)
	{
		for (int i=0;i<cantlineas;i++)
		{
			if (matrizlineas[i][num-1]==1)
				return i;
		}
		return 0xFFFF;
	}
	
	private boolean buscarlinea(int origen,int destino)
	{
		for (int i=0;i<cantlineas;i++)
		{
			if (matrizlineas[i][origen-1]==1 && matrizlineas[i][destino-1]==1)
				{
				origen=i;
				destino=i;
				return true;
				}
		}
		return false;
	}
	
	public String toString()
		{
		for (int i=0; i<this.cantlineas; i++)
			{
			System.out.println(Arrays.toString(matady[i]));
			}
		for (int i=0; i<this.cantlineas; i++)
			{
			System.out.println(Arrays.toString(matrizlineas[i]));
			}
		return "Grafos [cantlineas=" + cantlineas + ", cantestaciones=" + cantestaciones
			+ ", vectobj=" + Arrays.toString(vectobj) 
			+ "]";
		}
	
	public void dijkstra(int nodoorigen)
	{
	n=nodoorigen;
	for(int i=0;i<this.cantlineas;i++)
		{
		F[i]=false;
		D[i]=matady[nodoorigen][i];
		ultimo[i]=nodoorigen;			
		}
	F[nodoorigen]=true;
	D[nodoorigen]=0;
	for(int j=1;j<this.cantlineas;j++)
		{
		int v=minimo();
		F[v]=true;
		for(int w=0; w<this.cantlineas;w++)
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
	for (int j=1; j<this.cantlineas; j++)
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
		this.vectresord[posfin]=this.vectobj[destino-1];
		System.out.println(" -> " + this.vectobj[destino-1]);
		posfin++;
		}
	else
		{
		n++;
		this.vectresord[posfin]=this.vectobj[n-1];
		System.out.print(this.vectobj[n-1] + "\n");
		posfin++;
		}
	}
}

private void costocamino(int destino)
	{
	double num= D[destino]+1;
	System.out.println(" La cantidad de estaciones es " + num);
	}

private void caminofinal()
{
System.out.println(" El camino es " + Arrays.toString(this.vectresord));
}
	
	


	
	public static void main(String[] args) {
		Redsubterraneos Redsubte1=new Redsubterraneos("C:/Users/Mutu/Documents/Programacion 3/Grafos/Redsubte1.txt");
		System.out.println(Redsubte1.toString());
		Redsubte1.dijkstra(Redsubte1.origen);//posicion del nodo empieza por el 0
		Redsubte1.recuperarcamino(Redsubte1.destino);//posicion del nodo empieza por el 0
		Redsubte1.costocamino(Redsubte1.destino);
		Redsubte1.caminofinal();
		}

	}


