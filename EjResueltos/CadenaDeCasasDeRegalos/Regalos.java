package pk_regalos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class Regalos {

	private int cantnodo;
	private int col_fin;
	private int vectobj[];
	private double matriz [][];
	private int[]vec_gra;
	private int color[];
	
	public Regalos() {
		cantnodo = 0;
		//cantarista = 0;
		vectobj= new int[0];
		matriz = new double [0][0];	
		}

	public Regalos(String arg) 
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
				cantnodo = Integer.parseInt(linea);
				matriz = new double [cantnodo][cantnodo];
				vectobj= new int[cantnodo];
				vec_gra= new int[cantnodo];
				
				for (int i=0;i<cantnodo;i++)
				{
					linea=br.readLine();
					datos=linea.split(" ");
					
					vectobj[i]=Integer.parseInt(datos[0]);
					
					int j=1;
					
					while(Integer.parseInt(datos[j])!=-1)
					{
						matriz[Integer.parseInt(datos[0])-1][Integer.parseInt(datos[j])-1]=1;
						matriz[Integer.parseInt(datos[j])-1][Integer.parseInt(datos[0])-1]=1;
						//cantarista++;
						j++;
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

	
	
	@Override
	public String toString() {
		for (int i=0;i<cantnodo;i++)
			System.out.println(Arrays.toString(matriz[i]));
		
		return "Regalos [cantnodo=" + cantnodo + ", vectobj="
				+ Arrays.toString(vectobj) + ", vec_gra="
				+ Arrays.toString(vec_gra) + "]";
	}

	public int colorearuc()
	{
	int coloractual=0;
	int i=0;
	color=new int[this.cantnodo];
	int max=0;
	int aux=0;
			
	for(int m=0;m<this.cantnodo;m++)
		color[m]=0;
	while(i<this.cantnodo)
		{
		if(color[i]==0)
			{
			aux++;
			coloractual++;
			color[i]=coloractual;
			}
		else 
			{
			i++;
			continue;
			}
		boolean pintar;
		for (int j=i+1;j<this.cantnodo;j++)
			if(color[j]==0 && !this.esadyacente(this.vectobj[i]-1,this.vectobj[j]-1))
				{
				pintar=true;
				for(int k=0;k<=j;k++)
					if(color[k]==coloractual && this.esadyacente(this.vectobj[k]-1,this.vectobj[j]-1))
						{
						pintar=false;
						break;
						}
				if (pintar)
					{color[j]=coloractual;
					aux++;
					}
					
					
				}
		if(aux>max)
		{
			max=aux;
			col_fin=coloractual;
		}
		aux=0;
		i++;
		}
	//System.out.println("Coloreo de a un color por vez");
	//System.out.println("\tnodo\tcolor no.");
	//for ( i=0;i<this.cantnodo;i++)
	//	{
		//System.out.println("\t" + this.vectobj[i] + "\t " + color[i]);
		//}
	
	
	return max;
	}
	
	private boolean esadyacente(int i, int j)
	{
	double esady;
	esady=this.matriz[i][j];
	if (esady==1)
		return true;
	else return false;
	}
	
	private void quicksort(int low, int high) {
		int i = low, j = high;
		// Get the pivot element from the middle of the list
		int pivot = vec_gra[low + (high-low)/2];

		while (i <= j) 
		{
			while (vec_gra[i] > pivot) 
				i++;
						
			while (vec_gra[j] < pivot) 
				j--;
			
			if (i <= j) 
			{
				exchange(i, j);
				i++;
				j--;
			}
		}
		
		// Recursion
		if (low < j)
			quicksort(low, j);
		if (i < high)
			quicksort(i, high);
	}
	
	private void exchange(int i, int j) {
		int temp = vectobj[i];
		int temp2= vec_gra[i];
		
		vectobj[i] = vectobj[j];
		vec_gra[i] = vec_gra[j];
		
		vectobj[j] = temp;
		vec_gra[j] = temp2;
	}
	
	private int grado (int fila)
	{
		int aux=0;
		
		for (int i=0;i<cantnodo;i++)
		{
			if(matriz[fila][i]==1)
				aux++;
		}
		return aux;
	}
	
	public void Powell()
	{
		vec_gra=new int[cantnodo];
		
		for (int i=0;i<cantnodo;i++)
		{
			vec_gra[i]=grado(i);
		}
		//System.out.println("Vector con los nodos sin ordenar" + Arrays.toString(vectobj));
		
		//System.out.println("Vector con los grados original" + Arrays.toString(vec_gra));
		
		quicksort(0 ,cantnodo-1);
		
		//System.out.println("Vector con los nodos ordenados por grado" + Arrays.toString(vectobj));
		
		//System.out.println("Vector con los grados ordenados" + Arrays.toString(vec_gra));
	}
	
	public void resolver()
	{
		Powell();
		int max=colorearuc();
		
		int vec_fin[]=new int [max];
		int j=0;
		
		for (int i=0;i<cantnodo;i++)
		{
			if (color[i]==col_fin)
				{vec_fin[j]=vectobj[i];
				j++;}
		}
		//System.out.println("Una posible solucion seria\n"+Arrays.toString(vec_fin));
		
		escribir_archivo(vec_fin,max);
	}
	
	private void escribir_archivo(int vec_fin[],int max)
	{
		{
			FileWriter fw=null;
			PrintWriter pw=null;
			
			try
			{
				fw=new FileWriter ("regalos.out");
				pw=new PrintWriter (fw);
				
				pw.println(max);
				
				for (int i=0;i<vec_fin.length;i++)
				{
					System.out.println(vec_fin[i]);
					pw.print(vec_fin[i]+" ");
				}
				
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(fw!=null)
						fw.close();
				}
				catch(Exception e2)
				{
					e2.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Regalos r1=new Regalos("regalos.in");
		
		r1.resolver();
		
		
	}

}
