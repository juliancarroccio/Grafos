package Reconstruir;

import java.io.*;
import java.util.Vector;

public class Pelicula {	
int cantidadSegmentos; 				
int matrizAdyacencia[][];		
Vector<Integer> caminoResultante;	
Segmento[] segmentos;			

int path[][];			
int costo;				
boolean existeCamino;						        
int escenaInicioPeli;		
int escenaFinPeli;		
static final int INF = 9999999;	//tomo  esta constante como si fuera el infinito

///////////////////////////////////////////////////////////////////////////////////////////////////
public void leerArchivo(String archivoEntrada){
File archivo = null;
FileReader fr = null;
BufferedReader br = null;

try {
	archivo = new File (archivoEntrada);
	fr = new FileReader (archivo);
	br = new BufferedReader(fr);
	
	String [] datos;			
	String line = br.readLine();	    	
	datos = line.split(" ");
	cantidadSegmentos = Integer.parseInt(datos[0]);
	escenaFinPeli = Integer.parseInt(datos[1]);
	escenaInicioPeli= 1;
	segmentos = new Segmento[cantidadSegmentos];
	int nro,ini,fin;    	
	for(int i=0; i<cantidadSegmentos; i++){
		line = br.readLine();	    	
		datos = line.split(" ");       		
		nro = Integer.parseInt(datos[0]);
		ini = Integer.parseInt(datos[1]);
		fin = Integer.parseInt(datos[2]);
		segmentos[i] = new Segmento(nro,ini,fin);
	}
	
	
  this.construirMatrizAdy();	    	
  
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
/////////////////////////////////////////////////////////////////////////////////////////////////////
public void construirMatrizAdy(){
this.matrizAdyacencia = new int[cantidadSegmentos][cantidadSegmentos];
for(int i=0; i<cantidadSegmentos; i++)
	for(int j=0; j<cantidadSegmentos; j++)
		this.matrizAdyacencia[i][j] = INF;  

for(int i=0; i< cantidadSegmentos; i++)
	for(int j=0; j<cantidadSegmentos; j++){        		
		this.matrizAdyacencia[i][j] = this.matrizAdyacencia[j][i]= calcularCosto( segmentos[i], segmentos[j] );
	}
}
////////////////////////////////////////////////////////////////////////////////////////////////
public int calcularCosto(Segmento a, Segmento b){
int aux;  

	if( a.getId() == b.getId() )                          
		return INF;

	if((b.getEscenaInicial()>=a.getEscenaInicial()) && (b.getEscenaFinal()<=a.getEscenaFinal() )){                                 
		aux = ( b.getEscenaFinal() - b.getEscenaInicial() ) + 1;
		return aux;
	}

	if(( a.getEscenaInicial() <= b.getEscenaFinal()+ 1) &&  (a.getEscenaInicial()>=b.getEscenaInicial()+ 1)){                                   
		aux = ( b.getEscenaFinal() - a.getEscenaInicial() ) + 1;
		return aux;
	}

	if(( a.getEscenaFinal()>=b.getEscenaInicial()) && (a.getEscenaFinal()+1<=b.getEscenaFinal())){                                    
		aux = ( a.getEscenaFinal() - b.getEscenaInicial() ) + 1;
		return aux;
	}
	return INF; //no se solapan    	
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////
public void reconstruyePelicula(){
Vector<Integer> SegIniciales =  new Vector();
Vector<Integer> SegFinales =  new Vector();  

this.existeCamino=false;
int valor, minA=0, maxB=0, min = INF; 
	for(int i=0; i<this.cantidadSegmentos; i++){
		if(this.segmentos[i].getEscenaInicial() == 1)				
			SegIniciales.add( segmentos[i].getId()-1 );
		if( this.segmentos[i].getEscenaFinal() == escenaFinPeli )	
			SegFinales.add( segmentos[i].getId()-1 );
	}

for(int i=0; i<SegIniciales.size(); i++)	  
	for(int j=0; j<SegFinales.size(); j++){
		valor = floyd( SegIniciales.elementAt(i), SegFinales.elementAt(j) );    
		if( valor < min )
		{
        minA = SegIniciales.elementAt(i);
        maxB = SegFinales.elementAt(j);          
        this.costo = min = valor;
        existeCamino = true;			        
		}
  }    

	if(existeCamino){
		caminoResultante = new Vector();	
		caminoResultante.add(minA+1);		
		caminoDeFloyd( minA , maxB);
		caminoResultante.add(maxB+1); 
	}
	
	if(caminoResultante.elementAt(0)==caminoResultante.elementAt(1))
	caminoResultante.remove(1);
}
////////////////////////////////////////////////////////////////////////////////////////////////////
public int floyd(int a, int b){

int aux[][] = new int[this.cantidadSegmentos][this.cantidadSegmentos];	      
for(int i = 0; i < cantidadSegmentos; i++)
  for(int j = 0; j < cantidadSegmentos; j++)
    aux[i][j] = this.matrizAdyacencia[i][j];     

path = new int[this.cantidadSegmentos][this.cantidadSegmentos];    		    
for(int i = 0; i < cantidadSegmentos; i++)
	for(int j = 0; j < cantidadSegmentos; j++)
		path[i][j] = 0;        

for(int i = 0; i < cantidadSegmentos; i++)
  aux[i][i] = 0;        

for(int k = 0; k < cantidadSegmentos; k++){
  for(int i = 0; i < cantidadSegmentos; i++)
      for(int j = 0; j < cantidadSegmentos; j++){
          int dt = aux[i][k] + aux[k][j];
          if(aux[i][j] > dt){
              aux[i][j] = dt;
              path[i][j] = k;
          }    
      }
}
return aux[a][b];
}
////////////////////////////////////////////////////////////////////////////////////////////////////////
public boolean caminoDeFloyd(int i, int j){
int k= path[i][j];
	if(k==0)
		return false;
caminoDeFloyd(i, k);
caminoResultante.add(k+1);
caminoDeFloyd(k, j);
return true;
}       
///////////////////////////////////////////////////////////////////////////////////////////////////////
public void escribirArchivo(String archivoOUT){
FileWriter fichero = null;
PrintWriter pw = null;
	try
	{	
		fichero = new FileWriter(archivoOUT);
		pw = new PrintWriter(fichero);
		if( existeCamino!=true )
			pw.println( "NO ES POSIBLE" );
		else        	
			for(int i=0;i<caminoResultante.size();i++)      	   
				pw.print( caminoResultante.elementAt(i)+ " " );          
	}
	catch (Exception e) {
	e.printStackTrace();
	}	finally{
			try {
				if (null != fichero)
					fichero.close();
				}catch (Exception e2) {
					e2.printStackTrace();
				}
		}
	} 
}