package reconstruyendo_la_pelicula;

import java.util.ArrayList;
import java.util.ListIterator;

public class Camino {
	public int idcam;
	public int costo;
	public ArrayList<Segmento> cam;
	
	public Camino(int idcam)
	{
		this.idcam=idcam;
		costo=0;
		cam = new ArrayList<Segmento>();
	}
	
	public Segmento ultimo()
	{
		if(!cam.isEmpty())	
		{
			ListIterator<Segmento> it = cam.listIterator();
			while(it.hasNext())
				it.next();
			it.previous();
			return it.next();
		}
		else
			return null;
	}
}
