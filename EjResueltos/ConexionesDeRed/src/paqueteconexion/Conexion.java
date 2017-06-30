package paqueteconexion;

import java.io.*;
import java.util.Arrays;

public class Conexion 
{
	private char matrizady[][];
	private int tamMat;
	
	public Conexion ()
	{
		matrizady =null;
		tamMat = 0;
	}	
	
	public Conexion(String path)//Constructor para cargar la matriz de adyacencia
	{
		File archivo=null;
		FileReader fr =null;
		BufferedReader br=null;
		FileWriter fw=null;
		PrintWriter pw = null;
		
		archivo = new File(path);
		try 
		{
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			fw = new FileWriter("Solucion.out");
			pw = new PrintWriter(fw);
			String linea;
			while((linea=br.readLine())!=null)
			{
				String [] campos = linea.split(" ");
				if(!campos[0].equals("F"))
				{
				int aux = Integer.parseInt(campos[1]);
				if(tamMat<aux)
					tamMat = aux;
				aux = Integer.parseInt(campos[2]);
				if(tamMat<aux)
					tamMat = aux;
				}
				System.out.println(tamMat);
			}
			matrizady = new char [tamMat][tamMat];
			for(int i=0;i<tamMat;i++)
				for(int j=0;j<tamMat;j++)
					matrizady[i][j] = 'N';
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			while(!(linea=br.readLine()).equals("F"))
			{
				String [] campos = linea.split(" ");
				System.out.println(Arrays.toString(campos));
				if(campos[0].equals("C"))
				{
					this.ponerAdy(Integer.parseInt(campos[1])-1,Integer.parseInt(campos[2])-1);
				}
				if(campos[0].equals("P"))
				{
					if(matrizady[Integer.parseInt(campos[1])-1][Integer.parseInt(campos[2])-1]=='S')
					{
						pw.println("S");
					}
					else
						pw.println("N");
				}
			}
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(fr !=null && fw != null)
			{
				try {
					fr.close();
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void ponerAdy(int i, int j)
	{
		this.matrizady[i][j] = 'S';
		this.matrizady[j][i] = 'S';
		
		for(int k=0;k<tamMat;k++)
		{
			if(this.matrizady[i][k]=='S')
			{
				this.matrizady[j][k] = 'S';
				this.matrizady[k][j] = 'S';
			}
			if(this.matrizady[j][k] == 'S')
			{
				this.matrizady[i][k] = 'S';
				this.matrizady[k][i] = 'S';
			}
		}
	}
	
	public static void main(String[] args) 
	{
		String path = "C:/Users/Chris/Desktop/Faculteishion/Progra 3/Programas que hago/ConexionesDeRedes/caso 00.in";
		Conexion c1 = new Conexion(path);
		for(int i=0;i<c1.matrizady.length;i++)
		{
			for(int j=0;j<c1.matrizady.length;j++)
				System.out.print(c1.matrizady[i][j]+" ");
			System.out.println();
		}
	}

}
