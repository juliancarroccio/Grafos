
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Archivo {
	
	public String entrada;
	public String salida;

	public Archivo(String entrada, String salida) {
		this.entrada = entrada;
		this.salida = salida;
	}
	
	public Subtes leer(Subtes subte) {
		File file;
		FileReader fr = null;
		BufferedReader br = null;
		String[] datos;
		try {
			file = new File(entrada);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			datos = br.readLine().split(" ");
			int lineas = Integer.parseInt(datos[0]);
			int estaciones = Integer.parseInt(datos[1]);
			
			subte.cantLineas = lineas;
			subte.estaciones = estaciones;
			subte.subtes = new int[lineas+1][estaciones+1];
			
			for(int i = 1; i <=lineas;i++)
			{
				datos = br.readLine().split(" ");
				int estPorLinea = Integer.parseInt(datos[0]);
				for(int j = 1; j <= estPorLinea;j++){
					subte.subtes[i][Integer.parseInt(datos[j])]= 1;
				}
			}
			datos = br.readLine().split(" ");
			subte.salida = Integer.parseInt(datos[0]);
			subte.llegada = Integer.parseInt(datos[1]);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null)
					fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return subte;
	}
	
	public void guardar(Subtes subte){
		FileWriter fw = null;
		PrintWriter pw = null;
		try{
			fw = new FileWriter(this.salida);
			pw = new PrintWriter(fw);
			
			pw.println(subte.cantLineasRecorridas);
			for(int i=0; i<subte.cantLineasRecorridas; i++)
				pw.print(subte.lineasRecorridas[i] + " ");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (fw != null)
					fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

