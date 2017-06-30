package pack_redeselectricas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
	
public class Redelectrica {

	private int cantnodo;
	private int cantarista;
	private String vectobj[];
	private String vectresord[];
	private double matady [][];
	private int ultimo[];
	private double D[];
	private boolean F[];
	private int n;
	private int origen;
	private int destino;
	private int posfin;
	
	public Redelectrica() 
		{
		cantnodo = 0;
		cantarista = 0;
		vectobj= new String[0];
		matady = new double [0][0];	
		vectresord = new String[0];
		ultimo=new int[0];
		D=new double[0];
		F=new boolean[0];
		n=0;
		origen=0;
		destino=0;
		posfin=0;
		}

	public Redelectrica(String arg)
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
			String [] origenydest;
			if ((linea=br.readLine())!=null)
				{
				String [] datos;
				datos = linea.split(" ");
				this.cantnodo = Integer.parseInt(datos[0]);
				this.cantarista = Integer.parseInt(datos[1]);
				this.vectobj= new String[cantnodo];
				this.matady = new double [cantnodo][cantnodo];
				this.ultimo=new int[cantnodo];
				this.D=new double[cantnodo];
				this.F=new boolean[cantnodo];
				this.n=0;
				this.posfin=0;
				this.vectresord = new String[cantnodo];
				for(int p=0; p<cantnodo;p++)
					for(int q=0; q<cantnodo;q++)
						this.matady[p][q]=0xFFFF;
				linea=br.readLine();
				origenydest= linea.split(" ");
				for (int j=0; j<cantarista; j++)
					{
					linea=br.readLine();
					datos = linea.split(" ");
					this.guardarciudad(datos[0]);
					this.guardarciudad(datos[1]);
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
					this.matady[pos1][pos2]=17*Double.parseDouble(datos[2])/Double.parseDouble(datos[3]);
					//this.matady[pos2][pos1]=Double.parseDouble(datos[2]);
					for(int k=0;k<cantnodo; k++)
						{
						if(origenydest[0].equals(vectobj[k]))
							pos1=k;
						}
					for(int l=0;l<cantnodo; l++)
						{
						if(origenydest[1].equals(vectobj[l]))
							pos2=l;
						}
					this.origen=pos1;
					this.destino=pos2;
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
		
	public int getOrigen() {
		return origen;
	}

	public void setOrigen(int origen) {
		this.origen = origen;
	}

	public int getDestino() {
		return destino;
	}

	public void setDestino(int destino) {
		this.destino = destino;
	}

	private void guardarciudad(String cadena) 
		{
		int i=0,band=1;
		for(int k=0;k<cantnodo; k++)
			{
			if(cadena.equals(vectobj[k]))
				{
				band=0;
				}
			}
		if(band!=0)
			{
			while(vectobj[i]!=null)
				{
				i++;
				}
			vectobj[i]=cadena;
			}
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
		Redelectrica Red1=new Redelectrica("C:/Users/Mutu/Documents/Programacion 3/Grafos/Red1.txt");
		System.out.println(Red1.toString());
		Red1.dijkstra(Red1.getOrigen());//posicion del nodo empieza por el 0
		Red1.recuperarcamino(Red1.getDestino()); //posicion del nodo empieza por el 0
		Red1.costocamino(Red1.getDestino());
		Red1.caminofinal();
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
		System.out.println(" El costo es " + D[destino]);
		}
	
	private void caminofinal()
	{
	System.out.println(" El camino es " + Arrays.toString(this.vectresord));
	}
}
