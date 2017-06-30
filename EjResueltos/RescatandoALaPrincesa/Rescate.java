package pk_rescate;

import java.io.*;
import java.util.Arrays;

public class Rescate {
	
	private int clar,sen,cantdr,dragon[],pra,pre;
	private int mat[][];
	
	public Rescate() {
	 clar=0;
	 sen=0;
	 cantdr=0;
	 dragon=new int [0];
	 pra=0;
	 pre=0;
	 mat=new int[0][0];
	}

	public Rescate(String arg)
	{
		File f=null;
		FileReader fr=null;
		BufferedReader br=null;
		
		try
		{
			f=new File ("rescate.in");
			fr=new FileReader(f);
			br=new BufferedReader (fr);
			
			String linea;
			String[]datos=null;
			
			if((linea=br.readLine())!=null)
				datos=linea.split(" ");
			
			clar=Integer.parseInt(datos[0]);
			sen=Integer.parseInt(datos[1]);
			cantdr=Integer.parseInt(datos[2]);
			
			mat=new int [sen][3];
			dragon=new int[cantdr];
			
			linea=br.readLine();
			datos=linea.split(" ");
			
			pra=Integer.parseInt(datos[0]);
			pre=Integer.parseInt(datos[1]);

			linea=br.readLine();
			datos=linea.split(" ");
			
			for (int i=0;i<cantdr;i++)
			{
				dragon[i]=Integer.parseInt(datos[i]);
			}
			
			for (int i=0;i<sen;i++)
			{
				linea=br.readLine();
				datos=linea.split(" ");
				for (int j=0;j<3;j++)
				{
					mat[i][j]=Integer.parseInt(datos[j]);
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{}
	}
	

	public String toString() {
		
		for (int i=0;i<sen;i++)
		{
			System.out.println(Arrays.toString(mat[i])); 
		}
		
		
		return "Rescate [clar=" + clar + ", sen=" + sen + ", cantdr=" + cantdr
				+ ", dragon=" + Arrays.toString(dragon) + ", pra=" + pra
				+ ", pre=" + pre + "]";
	}

public boolean hay_dragon(int c1,int c2,int drag[])
{
	boolean aux=true;
	
	for(int i=0;i<drag.length;i++)
	{
		if(drag[i]==c1||drag[i]==c2)
			aux=false;
	}
	
	return aux;
}
	
	
	public int[] resolver()
	{
	 @SuppressWarnings("unused")
	int aux=0,max=0;
	 int i=0,j=0;
	 int[] resul1=new int [clar];
	 int[] resul2=new int [clar];
	 
	 resul1[0]=resul2[0]=pre;
	
	 while (mat[i][0]!=pre && !hay_dragon(mat[i][0],mat[i][1],dragon))
		 i++;
	 
	 
		while(i!=sen)
		{
			
			
			while(j!=sen)
			{
				if(i!=j && !hay_dragon(mat[j][0],mat[j][1],dragon))
				{
					
					
				}
				j++;
			}
		}
		
		return resul1;
	}
	
	public static void main(String[] args) {
			Rescate res=new Rescate ("rescate.in");
			int[] resul;
			
			System.out.println(res);
			
			resul=res.resolver();
			
			for (int i=0;i<resul.length;i++)
			{
				System.out.println(Arrays.toString(resul)); 
			}
			
	}

}
