import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class VirusPolimorfo {

	private int			cantInstrucciones;
	private int			trozosVirus;
	private Boolean		estado[];
	private Integer		orig[];
	public Integer		suma[];
	private int			cantSecuencias;
	private int			sumaCorrecta;

	ArrayList<Integer>	apariciones;

	public String posicionesVirus() {
		String cad = "";

		for (int i = 0; i < apariciones.size(); i++) {
			cad += apariciones.get(i) + " ";
		}

		return cad;
	}

	private void inicializarEstado() {
		for (int i = 0; i < estado.length; i++) {
			estado[i] = false;
		}
	}

	private int cantidadSecuencias() {

		int a = 0;

		while ((a + trozosVirus) <= cantInstrucciones) {
			a++;
		}
		return a;
	}

	private void rellenarVectorSuma() {

		// Primer Pasada
		suma[0] = 0;
		for (int i = 0; i < trozosVirus; i++) {
			suma[0] += orig[i];
		}

		// Con Programacion Dinámica
		for (int i = 1; i < cantSecuencias; i++) {
			suma[i] = suma[i - 1] + orig[i - 1 + trozosVirus] - orig[i - 1];
		}

	}

	public VirusPolimorfo(String ruta) {

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			archivo = new File(ruta);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea = br.readLine();
			String cad[] = linea.split(" ");

			cantInstrucciones = Integer.parseInt(cad[0]);
			trozosVirus = Integer.parseInt(cad[1]);

			int t2 = (trozosVirus - 1);
			sumaCorrecta = trozosVirus;

			while (t2 > 0) {
				sumaCorrecta += t2--;
			}

			cantSecuencias = cantidadSecuencias();
			
			orig = new Integer[cantInstrucciones];
			suma = new Integer[cantSecuencias];
			estado = new Boolean[trozosVirus];

			apariciones = new ArrayList<Integer>();

			for (int i = 0; i < cantInstrucciones; i++) {
				linea = br.readLine();
				orig[i] = Integer.parseInt(linea);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null)
					fr.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	private boolean todoTrueEstado() {

		for (int i = 0; i < estado.length; i++) {
			if (estado[i] == false) {
				return false;
			}
		}
		return true;
	}

	private int buscarAparicion() {

		for (int i = 0; i < cantSecuencias; i++) {
			if (suma[i] == sumaCorrecta) {
				inicializarEstado();

				for (int j = i; j < i + trozosVirus; j++) {

					if (orig[j] <= trozosVirus && orig[j] > 0) {
						estado[orig[j] - 1] = true;
					}
				}

				if (todoTrueEstado() == true) {
					apariciones.add(i + 1);
				}
			}

		}
		System.out.println(apariciones.size());
		System.out.println(posicionesVirus());
		return apariciones.size();
	}

	public static void main(String[] args) {
		VirusPolimorfo virus = new VirusPolimorfo("polimorfo.in");
		virus.rellenarVectorSuma();
		virus.buscarAparicion();
	}

}
