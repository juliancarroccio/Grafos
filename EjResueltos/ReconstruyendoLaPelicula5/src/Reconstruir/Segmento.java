package Reconstruir;

public class Segmento {

	private int idSegmento;
    private int escenaInicio;
    private int escenaFinal; 
    
////////////////////////////////////////////////
    public Segmento(int num, int ini, int fin){
    	  this.idSegmento= num;
    	  this.escenaInicio = ini;
    	  this.escenaFinal= fin;		 
    }    	  
/////////////////////////////////////////////////    
public int getId () {
	return idSegmento;
}
/////////////////////////////////////////////////
public int getEscenaInicial () {
	return escenaInicio;
}
//////////////////////////////////////////////////
public int getEscenaFinal () {
	return escenaFinal;
}
//////////////////////////////////////////////////
}
