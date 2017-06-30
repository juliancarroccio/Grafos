package reconstruir_pelicula;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
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
	private String camino;
	//private String cam[];

	
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
	camino=null;
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
					//System.out.println(march[j][2]+">="+march[q][1]+ "&&" + march[q][2]+">"+march[j][2] + "\n");
					if(j<this.cantnodo && march[j][2]>=march[q][1] && march[q][2]>march[j][2])
						{
						//System.out.println("Se cumple que "+ march[j][2]+">="+march[q][1]+ "&&" + march[j+1][2]+">"+march[j][2] + "\n");
						double valor=march[j][2]-march[q][1]+1;
						//System.out.println("Hay coneccion entre "+ march[j][0]+ " y " +  march[q][0]+ " y tiene valor de " + valor + "\n");
						this.matady[march[j][0]-1][march[q][0]-1]=valor;
						}
					//else System.out.println("No se cumple que "+march[j][2]+">="+march[q][1]+ "&&" + march[q][2]+">"+march[j][2] + "\n");
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

private int[] busca_inicio ()
{
	int cont_ini=0;
	for (int i=0;i<cantnodo;i++)
	{
		if (march[i][1]==1)
			cont_ini++;
	}
	
	int[] vec_ini=new int [cont_ini];
	int j=0;
	for (int i=0;i<cantnodo;i++)
	{
		if (march[i][1]==1)
			{vec_ini[j]=i;
			j++;
			}
	}
	
	return vec_ini;
}

private int[] busca_fin ()
{
	int cont_fin=0;
	for (int i=0;i<cantnodo;i++)
	{
		if (march[i][2]==ultimaescena)
			cont_fin++;
	}
	
	int[] vec_fin=new int [cont_fin];
	int j=0;
	for (int i=0;i<cantnodo;i++)
	{
		if (march[i][2]==ultimaescena)
			{vec_fin[j]=i;
			j++;
			}
	}
	
	return vec_fin;
}

private boolean esAdyacente (int pos1,int pos2)
{
	
	return matady[pos1-1][pos2-1]<200;
}


public void resolver()
{
	int []vec_ini=busca_inicio();
	int []vec_fin=busca_fin();
	
	double aux=0, min=0xFFFF;
	int pos=0;
	
	for (int i=0;i<vec_ini.length;i++)
	{
		dijkstra(vec_ini[i]);
		
		for (int j=0;j<vec_fin.length;j++)
		{
			aux=D[vec_fin[j]];
			if(aux<min)
				{min=aux;
				pos=vec_fin[j];
				}
		}
	}
				
	recuperarcamino(pos);
	String []datos=null;
	
	datos=camino.split("-");
	
	camino=datos[0];
	
	for (int i=2;i<datos.length;i+=2)
		camino+=" "+datos[i];
	
	System.out.println("Camino: "+camino);
	
	//System.out.println("Camino: "+Arrays.toString(cam));
	escribir_archivo(camino);
}

private void escribir_archivo (String camino)
{
	FileWriter fw=null;
	PrintWriter pw=null;
	
	try
	{
		fw=new FileWriter("peliculas.out");
		pw=new PrintWriter(fw);
		
		pw.println(camino);
		
	}
	
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally{
		  try 
	       {
	    	   if (null != fw)
	    		   fw.close();
	       } 
	       catch (Exception e2) 
	       {
	          e2.printStackTrace();
	       }
	}
}

public static void main(String[] args) 
	{
	Pelicula Grafo1=new Pelicula("dijkstra.in");
	//System.out.println(Grafo1);
	Grafo1.resolver();
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

private void recuperarcamino(int destino)
	{
	if (D[destino]==0xFFFF)
		{
		System.out.println("No existe un camino");
		camino="No existe un camino";
		}
	else
		{
		int anterior=ultimo[destino];
		if(destino!=n)
			{
			recuperarcamino(anterior);
			//destino++;
			//System.out.println(" -> " + this.vectobj[destino-1]);
			camino+=(this.vectobj[destino]+"-");
			
			}
		else
			{
			//n++;
			//System.out.print(this.vectobj[n-1] + "\n");
			camino=(this.vectobj[n]+"-"); 
			
			}
		}
	//return camino;
	}

}
