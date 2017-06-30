package pk_muros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Muros 
{
	private int cant_paises;
	private int []paises;
	private int [] muros;
	private int[][] mat_ady;
	private String vectobj[];
	private int ultimo[];
	private double D[];
	private boolean F[];
	private int n;
	
	public Muros() 
	{
		cant_paises=0;
		paises= new int [0];
		muros= new int[0];
		mat_ady=new int[0][0];
		vectobj=new String [0];
		ultimo=new int[0];
		D=new double[0];
		F=new boolean[0];
		n=0;
		
	}

	public Muros(String arg) 
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
				cant_paises = Integer.parseInt(linea);
			
			paises=new int [cant_paises];
			muros= new int [cant_paises-1];
			
			vectobj= new String[cant_paises];
			mat_ady = new int [cant_paises][cant_paises];
			ultimo=new int[cant_paises];
			D=new double[cant_paises];
			F=new boolean[cant_paises];
			n=0;
			
			
			for (int i=0;i<cant_paises;i++)
			{
				linea=br.readLine();
				paises[i]=Integer.parseInt(linea);
				vectobj[i]=String.valueOf(i+1);
			}
			
			for (int i=0;i<cant_paises-1;i++)
			{
				linea=br.readLine();
				muros[i]=Integer.parseInt(linea);
			}
				
				for(int p=0; p<cant_paises;p++)
					for(int q=0; q<cant_paises;q++)
						this.mat_ady[p][q]=0xFFFF;
				
				for(int p=0; p<cant_paises;p++)
				{
					for(int q=0; q<cant_paises;q++)
					{
						
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
	
	public static void main(String[] args) 
	{
		Muros muro1=new Muros("muros.in");
		
		System.out.println(muro1);
	}

}
