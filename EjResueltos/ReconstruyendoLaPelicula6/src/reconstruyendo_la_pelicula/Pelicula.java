package reconstruyendo_la_pelicula;

import java.util.ArrayList;
import java.io.*;

public class Pelicula {

	//--ATRIBUTOS--//
	public int matrizady[][];
	public int cantseg;
	public int cantemp;
	public int efinal;
	public int ncam=0;
	ArrayList<Segmento> segmentos;
	ArrayList<Empalme> empalmes;
	ArrayList<Camino> caminos;
	
	//--CONSTRUCTORES--//
	public Pelicula(String path) throws IOException
	{
		BufferedReader br = new BufferedReader (new FileReader(path));
		String [] linea = null;
		linea = br.readLine().split(" ");
		cantseg = Integer.parseInt(linea[0]);
		efinal = Integer.parseInt(linea[1]);
		segmentos = new ArrayList<Segmento> (cantseg);
		empalmes = new ArrayList<Empalme> ();
		caminos = new ArrayList<Camino> ();
		matrizady = new int[cantseg][cantseg];
		for(int i = 0; i < cantseg; i++)
		{
			linea = br.readLine().split(" ", 3);
			Segmento s1 = new Segmento(Integer.parseInt(linea[0]),Integer.parseInt(linea[1]),Integer.parseInt(linea[2]));
			if( segmentos.contains(s1) == false)
			{
				segmentos.add(s1);
			}
		}
		for (int i = 0; i < cantseg; i++)
		{
			for(int j=0;j<cantseg;j++)
			{
				matrizady[i][j]=-1;
			}
		}
		for (int i = 0; i < cantseg; i++)
		{
			for(int j=0;j<cantseg;j++)
			{

				if(i!=j && segmentos.get(i).getEf()>(segmentos.get(j).getEi()-2) && segmentos.get(i).getEi()<segmentos.get(j).getEi() && segmentos.get(i).getEf()!=efinal)
				{	
					matrizady[i][j]=segmentos.get(i).getEf()-segmentos.get(j).getEi()+1;
					Empalme e = new Empalme(segmentos.get(i),segmentos.get(j),matrizady[i][j]);
					if(empalmes.contains(e)==false)
						empalmes.add(e);
				}
			}
		}
		cantemp=empalmes.size();
	}
	
	//--METODOS--//
	public void mostrarMatAdy() {
		System.out.print("\t");
		for(int i=0;i<cantseg;i++)
			System.out.print("Seg "+segmentos.get(i).getIdseg()+"\t");
		System.out.print("\n");
		for(int i=0;i<cantseg;i++)
		{	System.out.print("Seg "+segmentos.get(i).getIdseg()+"\t");
			for(int j=0;j<cantseg;j++)
			{
				System.out.print(matrizady[i][j]+"\t");
			}
			System.out.print("\n");
		}
	}
	
	public void reconstruir(Segmento seg)
	{
			for(int j=0;j<cantseg;j++)
			{	
				if(matrizady[segmentos.indexOf(seg)][j]>-1)
				{
//					System.out.println("Ult("+ncam+") :"+caminos.get(ncam).ultimo()+" Seg: "+seg+" j: "+j);
					if(!caminos.get(ncam).cam.isEmpty())
					{
						if(!caminos.get(ncam).ultimo().equals(seg))
						{
							Camino c = new Camino(ncam++);
							caminos.add(c);
							int ind=0;
							while(ind<caminos.get(ncam-1).cam.size() && caminos.get(ncam-1).cam.get(ind)!=seg)
								{
								caminos.get(ncam).cam.add(caminos.get(ncam-1).cam.get(ind));
								ind++;
								}
							caminos.get(ncam).cam.add(seg);
						}
					}	
//					caminos.get(ncam).costo+=matrizady[segmentos.indexOf(seg)][j];
					caminos.get(ncam).cam.add(segmentos.get(j));
					reconstruir(segmentos.get(j));
				}
			}
	}
	
	public void costocaminos()
	{
		for(int i=0;i<caminos.size();i++)
		{
			for(int j=0;j<caminos.get(i).cam.size()-1;j++)
			{
				caminos.get(i).costo+=matrizady[segmentos.indexOf(caminos.get(i).cam.get(j))][segmentos.indexOf(caminos.get(i).cam.get(j+1))];
			}
		}
	}
	
	public void imprimircostomin()
	{
		if(caminos.isEmpty())
		{
			FileWriter fichero = null;
			
	        PrintWriter pw = null;
	        try
	        {
	            fichero = new FileWriter("peliculas.out");
	            pw = new PrintWriter(fichero);
	            if(caminos.isEmpty())
	            	pw.print("NO ES POSIBLE");
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           try {
	           // Nuevamente aprovechamos el finally para 
	           // asegurarnos que se cierra el fichero.
	           if (null != fichero)
	              fichero.close();
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
	        }
		}
		else
		{
			int min = caminos.get(0).costo; 
			int cammin=0;
			for(int i=1;i<caminos.size();i++)
			{
				if(caminos.get(i).costo<min)
				{
					min=caminos.get(i).costo;
					cammin=i;
				}
			}

//		System.out.println("Costo min ("+min+") en el camino :"+cammin);
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("peliculas.out");
            pw = new PrintWriter(fichero);
            for(int i=0;i<caminos.get(cammin).cam.size();i++)
            	pw.print(caminos.get(cammin).cam.get(i)+" ");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
        }
	}
	
	public void caminosposibles()
	{
		for(int i=0;i<caminos.size();i++)
		{
			if(caminos.get(i).ultimo().getEf()!=efinal)
				caminos.remove(i);
		}
	}
	
	
	
	
	//--MAIN--//
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Pelicula peli1 = new Pelicula ("peliculas.in");
//		peli1.mostrarMatAdy();
		for(int i=0;i<peli1.cantseg;i++)
		{
			if(peli1.segmentos.get(i).getEi()==1)
			{
				Camino k=new Camino(peli1.ncam);
				peli1.caminos.add(k);
				peli1.caminos.get(peli1.ncam).cam.add(peli1.segmentos.get(0));
				peli1.reconstruir(peli1.segmentos.get(0));
				peli1.ncam++;
			}
		}
//		Camino k=new Camino(peli1.ncam);
//		peli1.caminos.add(k);
//		peli1.caminos.get(peli1.ncam).cam.add(peli1.segmentos.get(0));
//		peli1.reconstruir(peli1.segmentos.get(0));

		peli1.caminosposibles();
		peli1.costocaminos();
		peli1.imprimircostomin();

//		System.out.println("Cantidad de caminos : "+peli1.caminos.size());
//		for(int b=0;b<peli1.caminos.size();b++)
//		{
//			{for(int c=0;c<peli1.caminos.get(b).cam.size();c++)
//				System.out.println("Paso "+c+": "+peli1.caminos.get(b).cam.get(c)+"\tCosto: "+peli1.caminos.get(b).costo);
//			}
//		}

	}

}
