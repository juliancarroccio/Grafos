package reconstruyendo_la_pelicula;

public class Segmento {
	
	public int idseg;
	public int ei;
	public int ef;
	
	public Segmento(int idseg,int ei,int ef)
	{
		this.idseg=idseg;
		this.ei=ei;
		this.ef=ef;
	}
	
	public Segmento(Segmento s)
	{
		this.idseg=s.idseg;
		this.ei=s.ei;
		this.ef=s.ef;
	}

	
	public int getIdseg() {
		return idseg;
	}

	
	public void setIdseg(int idseg) {
		this.idseg = idseg;
	}

	
	public int getEi() {
		return ei;
	}

	
	public void setEi(int ei) {
		this.ei = ei;
	}

	
	public int getEf() {
		return ef;
	}

	
	public void setEf(int ef) {
		this.ef = ef;
	}

	
	public String toString() {
		return ""+idseg;
	}

	
	
	
}
