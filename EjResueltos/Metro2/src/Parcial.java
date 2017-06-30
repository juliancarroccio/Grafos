import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

public class Parcial 
{
	private int[] _vecNodoA;
	private int[] _vecNodoB;
	private int[] _vecPeso;
	private int _cantidadNodos;
	
	public Parcial()
	{	
	}
	
	private ArrayList estaEnLaLista(int vec,ArrayList l)
	{
		Iterator ite = l.iterator();
		ArrayList aux;
		while(ite.hasNext())
		{
			aux = (ArrayList)ite.next();
			if(aux.contains(vec))
			{
				return aux;
			}	
		}
		return null;
	}
	
	public int Kruscal()
	{
		ArrayList listasListas = new ArrayList();
		ArrayList listaPrincipal = new ArrayList();
		listaPrincipal.add(_vecNodoA[0]);
		listaPrincipal.add(_vecNodoB[0]);
		int j=0;
		if(_vecPeso[0] == 2)
			j++;
		listasListas.add(listaPrincipal);
		int i=1;
		ArrayList auxA;
		ArrayList auxB;
		while(listaPrincipal.size() < _cantidadNodos)
		{
			if(_vecPeso[i] == 2)
				j++;
			if((auxA = estaEnLaLista(_vecNodoA[i],listasListas))!=null)
			{
				if((auxB = estaEnLaLista(_vecNodoB[i],listasListas))!=null)
				{
					if(!auxA.equals(auxB))
					{
						auxA.addAll(auxB);
						listasListas.remove(auxB);
					}
					else
					{
						if(_vecPeso[i] == 2)
							j--;
					}
				}
				else
				{
					auxA.add(_vecNodoB[i]);
				}
			}
			else
			{
				if((auxB = estaEnLaLista(_vecNodoB[i],listasListas))!=null)
				{
					auxB.add(_vecNodoA[i]);
				}
				else
				{
					ArrayList listaNueva = new ArrayList();
					listaNueva.add(_vecNodoA[i]);
					listaNueva.add(_vecNodoB[i]);
					System.out.println(listaPrincipal);
					System.out.println(listaNueva);
					listasListas.add(listaNueva);
				}
			}
			i++;
		}
		System.out.println(listaPrincipal);
		return j;
	}
	
	public void LeerArchivo(String ruta)
	{
		File archivo = null;
	     FileReader fr = null;
	     BufferedReader br = null;

	     try {
	        // Apertura del fichero y creacion de BufferedReader para poder
	        // hacer una lectura comoda (disponer del metodo readLine()).
	        archivo = new File (ruta);
	        fr = new FileReader (archivo);
	        br = new BufferedReader(fr);

	        // Lectura del fichero
	        String linea;
	        linea=br.readLine();
	        String [] auxTam = linea.split(" ");
	        String [] auxPuentes;
	        _cantidadNodos = Integer.parseInt(auxTam[0]);
	        int tamanio = Integer.parseInt(auxTam[1])+ Integer.parseInt(auxTam[2]);
	        _vecNodoA = new int[tamanio];
			_vecNodoB = new int[tamanio];
			_vecPeso = new int[tamanio];
	        //Parcial p = new Parcial(tamanio);
	        for(int i=0; i< Integer.parseInt(auxTam[1]);i++)
	        {
	        	linea = br.readLine();
	        	auxPuentes = linea.split(" ");
	        	_vecNodoA[i]=Integer.parseInt(auxPuentes[0]);
	        	_vecNodoB[i]=Integer.parseInt(auxPuentes[1]);
	        	_vecPeso[i]=1;
	        }
	        for(int i=Integer.parseInt(auxTam[1]); i< tamanio;i++)
	        {
	        	linea = br.readLine();
	        	auxPuentes = linea.split(" ");
	        	_vecNodoA[i]=Integer.parseInt(auxPuentes[0]);
	        	_vecNodoB[i]=Integer.parseInt(auxPuentes[1]);
	        	_vecPeso[i]=2;
	        }
	        
	     }
	     catch(Exception e){
	        e.printStackTrace();
	     }finally{
	        // En el finally cerramos el fichero, para asegurarnos
	        // que se cierra tanto si todo va bien como si salta 
	        // una excepcion.
	        try{                    
	           if( null != fr ){   
	              fr.close();     
	           }                  
	        }catch (Exception e2){ 
	           e2.printStackTrace();
	        }
	     }
		
	}
	public static void main(String[] args) 
	{
		 Parcial p = new Parcial();
		 p.LeerArchivo("./src/metro.in");
		 System.out.println(p.Kruscal());
	}

}
