package agrupandoPeces;

import java.io.*;

public class Acuario {

	
	public int matAdy[][];
	private String peces[];
	public int cantPeces;
	private int color;
	private int coloreado[][];
	private int nodos[];
	
	public Acuario(String archivo)
	{
		File arch =null;
		FileReader fr = null;
		BufferedReader br = null;
		
		String linea;
		try
		{
			arch = new File(archivo);
			fr = new FileReader(arch);
			br = new BufferedReader(fr);
		
			linea = br.readLine();
			cantPeces = Integer.parseInt(linea);
			matAdy = new int [cantPeces][cantPeces];
			for(int f=0;f<cantPeces ; f++)
				for(int c =0 ; c < cantPeces ; c++)
					matAdy[f][c]=1;
			peces = new String [cantPeces];
			for(int i =0; i< cantPeces; i++){
				linea = br.readLine();
				peces[i] = linea;
			}
			nodos = new int[cantPeces];
			for(int p=0;p<cantPeces;p++)
				nodos[p]=p+1;
			
			coloreado = new int [cantPeces][2];
			String arco[] = new String[2];
			
			while((linea = br.readLine()) != null)
			{
				arco = linea.split(" ");
				
				adyasencia(arco[0],arco[1]);
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
				if(fr != null)
					fr.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}
	
	private void adyasencia(String a, String b)
	{
		int i=0,j=0;
		while(i < peces.length && !peces[i].equals(a) )
		{
			i++;
		}
		
		while(j < peces.length && !peces[j].equals(b))
		{
			j++;
		}
		
		if (j >= peces.length)
			if(b.equals("todos"))
				for(int c=0; c < cantPeces;c++)
					{
						matAdy[i][c] =0;
						matAdy[c][i] =0;
					}
			if(b.equals("ninguno"))
				for(int c=0; c < cantPeces;c++)
					{
						matAdy[i][c] =1;
						matAdy[c][i] =1;
					}
		else
			if(i<cantPeces && j < cantPeces)
				{
					matAdy[i][j] = 0;
					matAdy[j][i] = 0;
				}
		//elimino diagonal p abajo
		int tope;
		for(int f=0;f<cantPeces ; f++)
		{
			tope=f+1;
			for(int c =0 ; c < tope; c++)
				matAdy[f][c] = 1;
		}
	}
	
	
	//coloreo
	
	
	
	public void coloreo()
	{
		
		int a=0;
		boolean existe, ady;
		color=1;
		for(int i =0; i< cantPeces;i++)//recorro los nodos para colorear
		{
			for ( int j = i; j< cantPeces;j++)
			{
				existe = repetido(j);
				ady = adyasente(j,color);//tiene q devolver falso si no encuentra el color o aun no existe ningn color
			
				if (existe == false && ady == false)
				{
					coloreado[a][0] = nodos[j];
					coloreado [a][1] = color;
					a++;
					
				}
			}
			color++;
		}
		for (int i = 0; i < coloreado.length; i++) {
			
				System.out.println(coloreado[i][0] +" "+coloreado[i][1]);
		}
		escribir();
	}
				                  
			

	private boolean repetido(int posicion)
	{
		for (int i = 0; i < coloreado.length; i++) {
			if(nodos[posicion]==coloreado[i][0])
				return true;
		}
		return false;
	}
	
	private boolean adyasente(int posicion, int color)
	{
		for (int i = 0; i < coloreado.length; i++) 
		{
			if(coloreado[i][1] == color)
				if(matAdy[coloreado[i][0]-1][nodos[posicion]-1] == 1)
					return true;
		}
		return false;
	}
			
		
	public void escribir()
	{
		FileWriter arch = null;
		PrintWriter pw = null;
			
		try
		{
			arch = new FileWriter("Acuario.out");
			pw = new PrintWriter (arch);
				
			String linea;
			linea = ""+coloreado[coloreado.length-1][1];
			pw.println(linea);
			int col =1;
			while(col <= color)
			{	
				linea = "";
				for (int i = 0; i < coloreado.length; i++) 
				{
					if(coloreado[i][1]==col)
						linea += peces[coloreado[i][0]-1]+ " ";
				}
				pw.println(linea);
				col++;	
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
				if(arch != null)
					arch.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}
	
}
