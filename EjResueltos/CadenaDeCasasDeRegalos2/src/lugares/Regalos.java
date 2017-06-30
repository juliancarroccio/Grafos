package lugares;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Regalos {
	
	private int adyacenciaLugares[][];
	private int colorDelNodo[];
	private int cantidadLugares;
	private int cantColores;
	private int vectorColores[];
	private int lugaresAdecuados[];
	
	public Regalos(String pathGrafo) {
		FileReader fr = null;
		try {
			File archivo = new File(pathGrafo);
			fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			
			int i,j, lugar, lugar_cercano;
			String linea;
			String datos[];
			cantidadLugares = Integer.parseInt(br.readLine());
			adyacenciaLugares = new int[cantidadLugares+1][cantidadLugares+1];
			for(i=0; i<cantidadLugares; i++) {
				linea = br.readLine();
				datos = linea.split(" ");
				j=1;
				lugar = Integer.parseInt(datos[0]);
				while(Integer.parseInt(datos[j]) != -1) {
					lugar_cercano = Integer.parseInt(datos[j]);
					adyacenciaLugares[lugar][lugar_cercano] = 1;
					adyacenciaLugares[lugar_cercano][lugar] = 1;
					j++;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		finally {
			try {
				if(fr != null)
					fr.close();
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void resolver() {
		colorearLugares();
		contarColores();
		buscarSucursalesMaxima();
	}
	
	private void colorearLugares() {
		int i, color;
		colorDelNodo = new int[cantidadLugares+1];
		cantColores = 0;
		for(i=1; i<=cantidadLugares; i++) {
			color = 1;
			while(!sePuedeColorear(i, color))
				color++;
			colorDelNodo[i] = color;
			if(color > cantColores)
				cantColores = color;
		}
	}
	
	private boolean esAdyacente(int nodo1, int nodo2) {
		return adyacenciaLugares[nodo1][nodo2] == 1 || adyacenciaLugares[nodo2][nodo1] == 1;
	}
	
	private boolean sePuedeColorear(int nodo, int color) {
		int i=1;
		while(i <= cantidadLugares) {
			if(colorDelNodo[i] == color) {
				if(esAdyacente(i,nodo))
					return false;
			}
			i++;
		}
		return true;
	}
	
	private void contarColores() {
		vectorColores = new int[cantColores+1];
		
		int i, j, color = 1, cant;
		for(i=1; i<=cantColores; i++) {
			cant = 0;
			for(j=1; j<=cantidadLugares; j++)
				if(colorDelNodo[j] == color)
					cant++;
			vectorColores[i] = cant;
			color++;
		}
	}
	
	private void buscarSucursalesMaxima() {
		int i, j = 0, max = vectorColores[1], colormax = 1;
		for(i=2; i<=cantColores; i++)
			if(vectorColores[i] > max) {
				max = vectorColores[i];
				colormax = i;
			}
		
		lugaresAdecuados = new int[max];
	
		for(i=1; i<=cantidadLugares; i++) {
			if(colorDelNodo[i] == colormax) {
				lugaresAdecuados[j] = i;
				j++;
			}
		}
	}
	
	public void escribirArchivo(String path) {
		FileWriter archivo = null;
		PrintWriter pw = null;
		
		try {			
			archivo = new FileWriter(path);
			pw = new PrintWriter(archivo);
			
			pw.println(lugaresAdecuados.length);
			String escribir = "";
			for (int i=0; i<lugaresAdecuados.length; i++)
				escribir += lugaresAdecuados[i] + " ";
			pw.println(escribir);
		}
		catch (Exception e) {
            e.printStackTrace();
		}
		finally {
			try {
				if(archivo != null)
					archivo.close();
			}
			catch(Exception e2){
				e2.printStackTrace();
			}
		}
	}
}
