import java.io.*;
import java.util.Vector;

public class balompie {
	private int cant_equipos;
	private int cant_partidos;
	private int [] disponibilidad;
	private String [] partidos;
	private int max_partidos;
	private Vector <String> part_salida;
	
	public balompie(String ruta)
	{
		File entrada=null;
		FileReader fr=null;
		BufferedReader br=null;
		
		try{
			entrada = new File (ruta);
			fr = new FileReader (entrada);
			br = new BufferedReader (fr);
			
			String linea = br.readLine();
			String[] datos = linea.split(" ");
			this.cant_equipos = Integer.parseInt(datos[0]);
			this.cant_partidos = Integer.parseInt(datos[1]);
			
			disponibilidad = new int[this.cant_equipos];
			partidos = new String [this.cant_partidos];
			
			System.out.println("partidos:"+this.cant_partidos);
			for (int i=0;i<this.cant_equipos;i++)
			{
				disponibilidad[i]=Integer.parseInt(br.readLine());
				System.out.println(disponibilidad[i]);
			}
			
			for (int i=0;i<this.cant_partidos;i++)
			{
				partidos[i]=br.readLine();
				System.out.println(partidos[i]);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean disponibles(int[] vector)
	{
		int cantidad=0;
		for (int i=0;i<this.cant_equipos;i++)
			if (vector[i]>0)
				cantidad++;
		
		if (cantidad>=2)
			return true;
		else
			return false;
	}
	public void cantidad_maxima ()
	{
		int [] disp_aux = new int [this.cant_equipos];
		int cant_aux=0;
		int i,j;
		
		part_salida = new Vector <String>();
		
		
		for (int k=0;k<this.cant_equipos;k++)
			disp_aux[k]=disponibilidad[k];
		System.out.println("disp_aux");
		for (int k=0;k<this.cant_equipos;k++)
			System.out.println(disp_aux[k]);
		while(disponibles(disp_aux)==true)
		{i=0;
		while (i<this.cant_partidos)
		{
				j=0;
				String datos[]=partidos[i].split(" ");
				
				if(disp_aux[Integer.parseInt(datos[0])-1]>0 && disp_aux[Integer.parseInt(datos[1])-1]>0)
				{
					cant_aux++;
					part_salida.add(partidos[i]);
					disp_aux[Integer.parseInt(datos[0])-1]--;
					disp_aux[Integer.parseInt(datos[1])-1]--;
				}
				
				
				while (j<this.cant_partidos && disponibles(disp_aux)==true )
				{
					String datos2[]=partidos[j].split(" ");
					if(disp_aux[Integer.parseInt(datos2[0])-1]>0 && disp_aux[Integer.parseInt(datos2[1])-1]>0 && i!=j)
					{
						cant_aux++;
						part_salida.add(partidos[j]);
						disp_aux[Integer.parseInt(datos2[0])-1]--;
						disp_aux[Integer.parseInt(datos2[1])-1]--;
					}
					j++;
					System.out.println("j= "+j);
				}
				
				for (int k=0;k<this.cant_equipos;k++)
					System.out.println(disp_aux[k]);
				i++;
				System.out.println("i = "+i);
				if(this.max_partidos<cant_aux)
					this.max_partidos=cant_aux;	
				
		}
		System.out.println("cantidad: "+cant_aux);
		cant_aux=0;
		}
		System.out.println(this.max_partidos);
		for(int h=0;h<this.part_salida.size();h++)
			System.out.println(this.part_salida.get(h));
	}
	
	public void salida()
	{
		FileWriter salida = null;
		PrintWriter pw = null;
		
		try{
			salida = new FileWriter ("Caso02_el_ultimo_equipo_juega_con_todos.out");
			pw = new PrintWriter (salida);
			
			pw.println(this.max_partidos);
			
			for(int h=0;h<this.part_salida.size();h++)
				pw.println(this.part_salida.get(h));
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			try{
			if(salida!=null)
				salida.close();
			}catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		balompie b = new balompie ("Caso02_el_ultimo_equipo_juega_con_todos.in");
		b.cantidad_maxima();
		b.salida();
	}

}
