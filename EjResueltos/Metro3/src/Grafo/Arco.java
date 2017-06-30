package Grafo;

public class Arco{
	
	private Integer nodo1;
	private Integer nodo2;
	private Integer costo;
	//public boolean isEstado() {
	//	return estado;
	//}

	//public void setEstado(boolean estado) {
	//	this.estado = estado;
//	}

	//private boolean estado;

	public Arco(){
		nodo1=-1;
		nodo2=-1;
		costo=-1;
		//estado = false;
	}
	
	@Override
	public String toString() {
		return "Arco [nodo1=" + nodo1 + ", nodo2=" + nodo2 + ", costo=" + costo
				+ "]";
	}

	public Arco(int n1, int n2, int c){
		nodo1= n1;
		nodo2= n2;
		costo= c;
	//	estado = est;
	}

	public Integer getNodo1() {
		return nodo1;
	}

	public void setNodo1(Integer nodo1) {
		this.nodo1 = nodo1;
	}

	public Integer getNodo2() {
		return nodo2;
	}

	public void setNodo2(Integer nodo2) {
		this.nodo2 = nodo2;
	}

	public Integer getCosto() {
		return costo;
	}

	public void setCosto(Integer costo) {
		this.costo = costo;
	}

	
	
}
