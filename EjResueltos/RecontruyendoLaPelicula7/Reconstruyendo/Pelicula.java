package Reconstruyendo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Vector;

public class Pelicula {		
	
		public class Segmento{
		      private int numeroSeg;
		      private int ei;
		      private int ef;  
		      
		      public Segmento(int num, int ini, int fin){
		    	  this.numeroSeg= num;
		    	  this.ei = ini;
		    	  this.ef= fin;		    	  
		      }
		}		
	
	int cantSeg; 			//cantidad Segmentos	
	int ady[][];			//matriz de adyacencia
	Vector<Integer> camino;	//resultado camino de segmentos a unir
	Segmento[] seg;			//vector de segmentos
	
	int path[][];			
	int cost;				
    int validar;						        
    int escenaInicial;		
    int escenaFinal;		
    static final int INF = 1000000;	//tomo  esta constante como si fuera el infinito
    
    
    public void leerArchivo(String archivoIN){
		File archivo = null;
	    FileReader fr = null;
	    BufferedReader br = null;
	    
	    try {
	    	archivo = new File (archivoIN);
	    	fr = new FileReader (archivo);
	    	br = new BufferedReader(fr);
	    	
	    	String [] datos;			
	    	String line = br.readLine();	    	
	    	datos = line.split(" ");
	    	this.cantSeg = Integer.parseInt(datos[0]);
			this.escenaFinal = Integer.parseInt(datos[1]);
	    	this.escenaInicial= 1;
	    	this.seg = new Segmento[ this.cantSeg ];
	    	int nro,ini,fin;    	
	    	for(int i=0; i<cantSeg; i++){
        		line = br.readLine();	    	
    	    	datos = line.split(" ");       		
        		nro = Integer.parseInt(datos[0]);
        		ini = Integer.parseInt(datos[1]);
        		fin = Integer.parseInt(datos[2]);
        		seg[i] = new Segmento(nro,ini,fin);
        	}
            this.construirMatriz();	    	
	 	}catch(Exception e){
	 		e.printStackTrace();
	 	}finally{
	 		try{
	 			if( null != fr ){
	               fr.close();
	            }
	 		}catch (Exception e2){
	            e2.printStackTrace();
	 		}
	 	}
	}
        
    public void construirMatriz(){
    	this.ady = new int[cantSeg][cantSeg];
    	for(int i=0; i<cantSeg; i++)
    		for(int j=0; j<cantSeg; j++)
    			this.ady[i][j] = INF;  
    	
    	for(int i=0; i< cantSeg; i++)
    		for(int j=0; j<cantSeg; j++){        		
    			this.ady[i][j] = this.ady[j][i]= costo( seg[i], seg[j] );
    		}
    }
    
    public int costo(Segmento a, Segmento b){
    	int aux;  
    	if( a.numeroSeg == b.numeroSeg )  // Si es el mismo segmento el costo es es INF por que no puede pegarse sobre si                        
    		return INF;
    	if(  ( b.ei>=a.ei ) &&  ( b.ef<=a.ef )){  //el segmento B esta contenido en A, ej A(20,80) B(50,60) costo 11                               
    		aux = ( b.ef - b.ei ) + 1;
    	    return aux;
        }
    	if(  ( a.ei <= b.ef+1 ) &&  ( a.ei>=b.ei+1) ){ // la parte baja de A esta contenido en B ej A(25,35) B (20,30) costo 6                                   
    		aux = ( b.ef - a.ei ) + 1;
    		return aux;
    	}
    	  if(  ( a.ef+1>=b.ei ) &&  ( a.ef+1<=b.ef ) ){ // la parte alta de A esta Contenida en B ej A(30,40) B(20,50) costo 21                                   
    	    aux = ( a.ef - b.ei ) + 1;
    	    return aux;
    	  }
    	  return INF; 		//si no se solapan de alguna forma entonces costo infinito, no se puede ir por ahi    	
    }
    
    public void reconstruir(){
    	Vector<Integer> SegIniciales =  new Vector();
    	Vector<Integer> SegFinales =  new Vector();  
    	
    	this.validar=0;
    	int valor, minA=0, maxB=0, min = INF; 
		for(int i=0; i<this.cantSeg; i++){
			if(this.seg[i].ei == 1)				//busco y guardo los segmentos que tengan la primer escena 
				SegIniciales.add( seg[i].numeroSeg-1 );
		    if( this.seg[i].ef == escenaFinal )	//busco y guardo los segmentos que tengan la ultima escena
		    	SegFinales.add( seg[i].numeroSeg-1 );
		}
		    
		//aplico Floyd entre todas los pares de segmentos entre SegIniciales y SegFinales obtenidos
		//para encontrar el par con menor costo de camino 		
		for(int i=0; i<SegIniciales.size(); i++)	  
			for(int j=0; j<SegFinales.size(); j++){
				valor = floyd( SegIniciales.elementAt(i), SegFinales.elementAt(j) );    
				if( valor < min ){
		          minA = SegIniciales.elementAt(i);
		          maxB = SegFinales.elementAt(j);          
		          this.cost = min = valor;
		          validar = 1;			//para saber si encontro algun camino          
				}
		    }    
		if(this.validar==1){
		  camino = new Vector();	
		  camino.add(minA+1);		
		  caminoDeFloyd( minA , maxB);
		  camino.add(maxB+1); 
		}
    }
    
    public int floyd(int a, int b){
       
        int aux[][] = new int[this.cantSeg][this.cantSeg];	//copia MatrizAdy para no modificarla        
        for(int i = 0; i < cantSeg; i++)
            for(int j = 0; j < cantSeg; j++)
              aux[i][j] = this.ady[i][j];     
        
        path = new int[this.cantSeg][this.cantSeg];    		//seteo la matriz de caminos    
        for(int i = 0; i < cantSeg; i++)
          for(int j = 0; j < cantSeg; j++)
            path[i][j] = 0;        
        
        for(int i = 0; i < cantSeg; i++)
            aux[i][i] = 0;        
        
        for(int k = 0; k < cantSeg; k++){
            for(int i = 0; i < cantSeg; i++)
                for(int j = 0; j < cantSeg; j++){
                    int dt = aux[i][k] + aux[k][j];
                    if(aux[i][j] > dt){
                        aux[i][j] = dt;
                        path[i][j] = k;
                    }    
                }
        }
        return aux[a][b];
    }
   
   public boolean caminoDeFloyd(int i, int j){
        int k= path[i][j];
        if(k==0)
          return false;
        caminoDeFloyd(i, k);
        camino.add(k+1);
        caminoDeFloyd(k, j);
        return true;
   }       

   public static String[] cargarArchivos(String path)
	{
		FileReader fr = null;
		BufferedReader br = null;
		String[] archivos = null;
		try{
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			int cant = Integer.parseInt(br.readLine());
			archivos= new String[cant];
			for(int i=0; i<archivos.length; i++)
				archivos[i]= br.readLine();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			try{
				if(fr!=null)
					fr.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return archivos;
	}
   
   private void escribirArchivo(String archivoOUT){
	   FileWriter fichero = null;
       PrintWriter pw = null;
       try
       {	
       	fichero = new FileWriter(archivoOUT);
        pw = new PrintWriter(fichero);
       	
        if( this.validar!=1 )
        	pw.println( "NO ES POSIBLE" );
        else        	
           for(int i=0;i<camino.size();i++)      	   
        	   pw.println( camino.elementAt(i) );          
       	
       }catch (Exception e) {
       	e.printStackTrace();
       }finally{
       	try {
       		if (null != fichero)
       			fichero.close();
       		}catch (Exception e2) {
       			e2.printStackTrace();
       		}
       }
	} 
	

	public static void main(String[] args) {
		
		Pelicula p = new Pelicula();
		String pathIN, pathOUT;
		String[] casos = cargarArchivos("Lote/ArchivosIN.txt");
		for(int i=0; i<casos.length; i++)			
		{
			pathIN = "Lote/IN/" + casos[i] + ".in";
			pathOUT = "Lote/OUT/" + casos[i] + ".out";
			
			p.leerArchivo(pathIN);			
			p.reconstruir();
			p.escribirArchivo(pathOUT);
		}
	}

}
