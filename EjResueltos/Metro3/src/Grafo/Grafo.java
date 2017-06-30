package Grafo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class Grafo {

	private Integer CantidadDeNodos;
	private Integer Adyacencia[][];
//	private Vector <Arco> MisArcos;
	private Integer Vistos[];
	private Integer NodoActual;
	private Integer UltAdyVisto;
	
	
	public Grafo(String Ruta){
		this.UltAdyVisto = -1;
		this.NodoActual = -1;
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String cadena; 
		String[] info;
		
		try {
			archivo = new File(Ruta);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
		    cadena = br.readLine();
		    info = cadena.split(" ");
			Integer arcos;
			Integer Tuneles, Puentes;
			
			
		 	this.CantidadDeNodos = Integer.parseInt(info[0]);
		 	Tuneles = Integer.parseInt(info[1]);
		 	Puentes = Integer.parseInt(info[2]);
		 	arcos= Tuneles + Puentes;
		 	
			this.InicializarMatriz();
			this.InicializarVistos();
		
			for(int i=0; i<Tuneles;i++){
				cadena = br.readLine();
				info = cadena.split(" ");
				this.Adyacencia[Integer.parseInt(info[0])-1][Integer.parseInt(info[1])-1]= 0;
				this.Adyacencia[Integer.parseInt(info[1])-1][Integer.parseInt(info[0])-1]= 0;
			}
			
			for(int j=0; j<Puentes;j++){
				cadena = br.readLine();
				info = cadena.split(" ");
				this.Adyacencia[Integer.parseInt(info[0])-1][Integer.parseInt(info[1])-1]= 1;
				this.Adyacencia[Integer.parseInt(info[1])-1][Integer.parseInt(info[0])-1]= 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null)
					fr.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}	
	
	
	public void InicializarMatriz(){

		this.Adyacencia =  new Integer[this.CantidadDeNodos][this.CantidadDeNodos];
		
		for(int i=0;i<this.CantidadDeNodos;i++)
			for(int j=0; j < this.CantidadDeNodos;j++)
		    	this.Adyacencia[i][j]=-1;
	}
	
	
	public void InicializarVistos(){
		
		this.Vistos = new Integer[this.CantidadDeNodos];
		
		for(int i=0;i<this.CantidadDeNodos;i++)
			this.Vistos[i] =-1; 
	}
	
	public int ObtenerSiguienteVisto(int NodoActual){
		
		int Posicion = NodoActual + 1;
		
		while(Posicion < this.Vistos.length && this.Vistos[Posicion] == -1)
			Posicion++;
		
		if (Posicion == this.Vistos.length)
		 return -1;
		else
		 return Posicion;	 
	}
	
	public int ObtenerSiguienteTunel(){
	 	  
		boolean encontrado = false;
	    this.UltAdyVisto++;
	    
	    while(!encontrado && (this.UltAdyVisto <= this.CantidadDeNodos-1)){
	    	if((this.UltAdyVisto != this.NodoActual) && this.Adyacencia[this.NodoActual][this.UltAdyVisto]== 0) 
	    		encontrado = true;
	    	else
	    		this.UltAdyVisto++; 
	    } 
	    
	    if(encontrado)
	    	return this.UltAdyVisto;
	    else
	        return -1;	
	}
	
	public int ObtenerSiguientePuente(){
	 	  
		boolean encontrado = false;
	    this.UltAdyVisto++;
	    
	    while(!encontrado && (this.UltAdyVisto <= this.CantidadDeNodos-1)){
	    	if((this.UltAdyVisto != this.NodoActual) && (this.Adyacencia[this.NodoActual][this.UltAdyVisto])==1)
	    		encontrado = true;
	    	else
	    		this.UltAdyVisto++; 
	    } 
	    
	    if(encontrado)
	    	return this.UltAdyVisto;
	    else
	        return -1;	
	}
	
	
    public int ObtenerCantDePuentesMin(){
	   int PuentesMin=0;
	   int CantNodosVistos=1;
	   int conexion=0;
	   boolean HayPuenteOTunel = false;
	   this.Vistos[0]= 1;
	   
	   while(CantNodosVistos != this.CantidadDeNodos){
		  this.NodoActual  = -1;
		  
		  while((!HayPuenteOTunel) && (this.NodoActual=this.ObtenerSiguienteVisto(this.NodoActual))!=-1){
			  this.UltAdyVisto = -1;
			  if(conexion==0){
				  while((!HayPuenteOTunel)&&(this.ObtenerSiguienteTunel()!=-1)){
					  if(this.Vistos[this.UltAdyVisto]==-1){
						  this.Vistos[this.UltAdyVisto]=1;
						  CantNodosVistos++;
						  HayPuenteOTunel=true;
					  }
				  }
			  }
			  else
				  while((!HayPuenteOTunel)&&this.ObtenerSiguientePuente()!=-1)
					  if(this.Vistos[this.UltAdyVisto]==-1){
						  this.Vistos[this.UltAdyVisto]=1;
						  CantNodosVistos++;
						  PuentesMin++;
						  HayPuenteOTunel=true;
					  }	  
		  }
		  if((!HayPuenteOTunel)){
			  conexion=1;
		  }
		  else{
			  HayPuenteOTunel=false;
			  conexion=0;
		  }
			
	   }
	  
	   return PuentesMin;
    }	
	
	

}
