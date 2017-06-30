package grafo;

public class Pila {
	private int tama�o;
	private int tope;
    private	Integer[] Vector;
	
public Pila(int tam){
	
	this.tama�o = tam;
	this.tope = -1;
	Vector = new Integer[tama�o];
}  

public int PilaVacia(){
	
	if(this.tope == -1)
	  return 1;
	
	return 0;
}

public int PilaLlena(){
	
	if(this.tope + 1 == this.tama�o)
	  return 1;
	
	return 0;
}

public void Apilar(int dat){
	this.tope++;
	this.Vector[this.tope]= dat;
}

public int Desapilar(){
	
	int dat;
	dat = this.Vector[this.tope];
	this.tope--;
	
	return dat;
}



}