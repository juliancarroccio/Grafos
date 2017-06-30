import java.io.*;
import java.util.Vector;
import java.util.Vector;

public class Red_Subterraneo {
	private int cant_lineas;
	private int cant_estaciones;
	private Vector<Vector<Integer>> lineas_estacion;
	private int destino;
	private int inicio;
	
	
	public Red_Subterraneo(String ruta)
	{
		File entrada = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		try{
			entrada = new File (ruta);
			fr = new FileReader(entrada);
			br = new BufferedReader (fr);
			String linea, datos[];
			
			linea = br.readLine();
			datos = linea.split(" ");
			
			this.cant_lineas = Integer.parseInt(datos[0]);
			this.cant_estaciones = Integer.parseInt(datos[1]);
			
			lineas_estacion=new Vector<Vector<Integer>>();
			for (int i=0;i<this.cant_lineas;i++)
			{
				lineas_estacion.add	(new Vector<Integer>());
				linea = br.readLine();
				datos = linea.split(" ");
				int largo = Integer.parseInt(datos[0]);
				for (int j=1;j<=largo;j++)
					lineas_estacion.get(i).add(Integer.parseInt(datos[j]));
			}
			
			linea = br.readLine();
			datos = linea.split(" ");
			this.inicio = Integer.parseInt(datos[0]);
			this.destino = Integer.parseInt(datos[1]);
		
			for (int i=0;i<this.cant_lineas;i++)
			{
				System.out.println(lineas_estacion.get(i).toString());
			}
			System.out.println(this.inicio);
			System.out.println(this.destino);
			
			Grafo g = new Grafo (lineas_estacion, cant_lineas);
			salida(g.buscaCamino(this.inicio, this.destino));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void salida ( Vector<Integer> resultado)
	{
		boolean valido;
		FileWriter salida= null;
		PrintWriter pw=null;
		
		try{
			 salida=new FileWriter ("enunciado.out");
			 pw = new PrintWriter(salida);
			
			 pw.println(resultado.size());
			 
			for(int i=resultado.size()-1;i>=0;i--)
				pw.print(resultado.get(i)+" ");
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try{
			if(salida!=null)
				salida.close();
			}catch(Exception e2)
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
		
		Red_Subterraneo subte = new Red_Subterraneo("C:\\Users\\German\\workspace\\Gonzalez_Pacheco\\IN\\Caso03_Pasa_por_todas_las_estaciones_de_todas_las_lineas.in");
		
		
	}

}
