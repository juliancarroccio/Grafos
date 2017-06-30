package reconstruyendo_la_pelicula;

public class Empalme {
	public Segmento s1;
	public Segmento s2;
	public int costo;
	
	public Empalme(Segmento s1,Segmento s2,int costo)
	{
		this.s1=s1;
		this.s2=s2;
		this.costo=costo;
	}

	public Empalme(Empalme e)
	{
		this.s1=e.s1;
		this.s2=e.s2;
		this.costo=e.costo;
	}
	
	public Segmento getS1() {
		return s1;
	}
	
	public void setS1(Segmento s1) {
		this.s1 = s1;
	}
	
	
	public Segmento getS2() {
		return s2;
	}
	
	public void setS2(Segmento s2) {
		this.s2 = s2;
	}
	
	public int getCosto() {
		return costo;
	}
	
	public void setCosto(int costo) {
		this.costo = costo;
	}


	public String toString() {
		return "Empalme entre " + s1 + " y " + s2 + ", costo: " + costo;
	}
	
	
	
}
