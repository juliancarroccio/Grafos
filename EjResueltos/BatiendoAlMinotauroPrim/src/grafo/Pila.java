package grafo;

public class Pila {
	private int tamaño;
	private int tope;
    private	Integer[] Vector;
	
public Pila(int tam){
	
	this.tamaño = tam;
	this.tope = -1;
	Vector = new Integer[tamaño];
}  

public int PilaVacia(){
	
	if(this.tope == -1)
	  return 1;
	
	return 0;
}

public int PilaLlena(){
	
	if(this.tope + 1 == this.tamaño)
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