
public class Subtes {
	
	static int infinito = Integer.MAX_VALUE; 
	public int subtes[][];
	public int salida;
	public int llegada;
	public int cantLineas;
	public int estaciones;
	public int conexiones[][];
	
	public int[] lineasRecorridas;
	public int cantLineasRecorridas;

	public Subtes(){
		
		subtes=null;
		salida=0;
		llegada=0;
		cantLineas=0;
		estaciones=0;
		conexiones=null;
		lineasRecorridas=null;
		cantLineasRecorridas=0;
	}
	
	
	public void hallarRecorrido(){
		
		conexiones = new int[cantLineas+1][cantLineas+1];

		for(int i=1; i<=cantLineas;i++)
			for(int j=1; j<=cantLineas;j++)
				conexiones[i][j]= infinito;
		
		//matriz de adyacencias entre lineas, pongo unos donde tengo lineas que se cruzan
		for (int i=1; i <=cantLineas; i++)
			for (int k=i+1; k <=cantLineas; k++) 
				for (int j=1; j<=estaciones && conexiones[i][k]!=1 ; j++) 
				{
					if (subtes[i][j]==1 && subtes[k][j]==1)
					{
						conexiones[i][k]=1;
						conexiones[k][i]=1;
					}
				}
		
		
		//vector con todas las posibles lineas de salida de las cuales puedo partir
		int[] lineaSalida= new int[cantLineas];
		int x=0;
		for (int i=1; i <=cantLineas; i++){
			if (subtes[i][salida]==1) {
				lineaSalida[x]= i;
				x++;
			}
		}	

		//vector con todas las posibles lineas de llegada
		int[] lineaLlegada=new int[cantLineas];
		x=0;
		for (int i=1; i <=cantLineas; i++){
			if (subtes[i][llegada]==1) {
				lineaLlegada[x]= i;
				x++;
			}
		}	
		
		//por cada posible linea de salida me fijo si hay algun recorrido que me permita usar menos lineas
		cantLineasRecorridas=cantLineas;
		int camino[];
		for(int i=0; lineaSalida[i]!=0; i++)
		{
			//dijkstra devuelve para una linea de salida especifica, la secuencia de lineas a usar (con menor cantidad de transbordos)
			camino = buscarCaminoDijkstra( lineaSalida[i], lineaLlegada);
			//para el camino devuelto por dijkstra, aca cuento cuántas lineas se usaron
			int cant;
			for(cant=0; camino[cant]!=0; cant++) {}
			// si la nueva linea de salida me permite hacer menos transbordos, me quedo con esa.	
			if( cant < cantLineasRecorridas )
			{
				lineasRecorridas = camino;
				cantLineasRecorridas = cant;
			}
		}
	}
	
	
	public int[] buscarCaminoDijkstra (int lineaSalida, int[]lineaLlegada)
	{
		int distancia[]= new int[cantLineas+1];
		int predecesores[]= new int[cantLineas+1];
		int w=0;
		boolean[]vistos = new boolean[cantLineas+1];
		vistos[lineaSalida]=true;
		int cantVistos=0;
		
		for(int i=1;i<cantLineas+1;i++)
		{
			distancia[i]= conexiones[lineaSalida][i];
			if(conexiones[lineaSalida][i]!=infinito)
				predecesores[i]=lineaSalida;
			else if(i!=lineaSalida)
				predecesores[i]=infinito;
		}
		distancia[lineaSalida]=0;
		int min=infinito;
		
		while ( cantVistos < cantLineas-1 )
		{
			for(int i=1; i<cantLineas+1;i++)
			{			
				if(min>=distancia[i] && vistos[i]!=true)
				{
					min=distancia[i];
					w=i;
				}
			}
			min=infinito;
			vistos[w]=true; cantVistos++;
			for(int i=1;i<cantLineas+1;i++)
			{
				if(conexiones[w][i]!=infinito)
				{
					if(distancia[i]>(distancia[w]+conexiones[w][i]))
					{
						distancia[i]=distancia[w]+conexiones[w][i];
						predecesores[i]=w;
					}
				}	
			}
		}
		//dentro de las posibles lineas de llegada, aca me fijo cual me dio menos cantidad de tranbordos
		int lineaElegida= lineaLlegada[0];  //MODIFICADO
		for(int i=1; lineaLlegada[i]!=0; i++)
		{
			if( distancia[lineaLlegada[i]] <  distancia[lineaElegida] )
				lineaElegida = lineaLlegada[i]; //MODIFICADO
		}	
		//busco el camino para la linea de salida pasada por parametro, y la linea de llegada que elegi antes
		return camino(predecesores,lineaSalida,lineaElegida); 
	}

	
	public int[] camino (int[]predecesores, int lineaSalida, int lineaLlegada)
	{
		int i=0;
		int[]caminoM= new int[cantLineas+1];
		caminoM[i]=lineaLlegada;
		while(lineaLlegada!=lineaSalida)
		{	
			i++;
			lineaLlegada=predecesores[lineaLlegada];
			caminoM[i]=lineaLlegada;
		}
		for(int j=0; j<i; j++)
		{
			int aux=caminoM[i];
			caminoM[i]=caminoM[j];
			caminoM[j]=aux;
			i--;
		}
		return caminoM;
	}

}