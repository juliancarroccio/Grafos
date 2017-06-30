package grafo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Grafo {

	private Integer CantidadNodos;
	private Integer CantidadArcos;
	private Integer Raices[][];
	private Vector<Arco> MisArcos;

	private Integer CantidadArcosHabilitados;

	public Integer getCantidadArcosHabilitados() {
		return CantidadArcosHabilitados;
	}

	public Integer getCantidadDeNodos() {
		return CantidadNodos;
	}

	public Integer getCantidadArcos() {
		return CantidadArcos;
	}

	public void InicializarRaices() {

		this.Raices = new Integer[2][this.CantidadNodos];

		for (int i = 0; i < this.CantidadNodos; i++) {
			this.Raices[0][i] = i;
			this.Raices[1][i] = 0;
		}
	}

	public Grafo(String Ruta) {

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String cadena;
		String[] info;

		try {
			archivo = new File(Ruta);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			cadena = br.readLine();

			this.CantidadNodos = Integer.parseInt(cadena);

			// Todos los nodos llegan a los demas nodos...
			this.CantidadArcosHabilitados = this.CantidadArcos = (this.CantidadNodos)
					* (this.CantidadNodos - 1);

			this.InicializarRaices();
			MisArcos = new Vector<Arco>(this.CantidadArcos);

			for (int i = 0; i < this.CantidadNodos; i++) {
				cadena = br.readLine();
				info = cadena.split(" ");
				//info.length = CantidadNodos
				for (int j = 0; j < info.length; j++) {
					if (i != j) {
						Arco Arc = new Arco(i, j, Integer.parseInt(info[j]),
								false);
						MisArcos.insertar(Arc);
					}
				}
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

	public static int CompPeso(Arco a1, Arco a2) {
		return a1.getCosto() - a2.getCosto();
	}

	public void UnionConRango(int x, int y) {

		int xaux = this.ObtenerRaiz(x);
		int yaux = this.ObtenerRaiz(y);
		if (this.Raices[1][xaux] > this.Raices[1][yaux])
			this.Raices[0][yaux] = xaux;
		else {
			this.Raices[0][xaux] = yaux;
			if (this.Raices[1][xaux] == this.Raices[1][yaux])
				this.Raices[1][yaux] += 1;
		}
	}

	public int ObtenerRaiz(int vertice) {

		if (this.Raices[0][vertice] == vertice)
			return vertice;
		else
			return this.ObtenerRaiz(this.Raices[0][vertice]);
	}

	public void OrdenarCostos() throws Exception {
		Arco TemArc;
		Arco Arcj;
		int j = 0;

		for (int i = 1; i < MisArcos.getTamaño(); i++) {
			TemArc = MisArcos.getElemento(i);
			j = i - 1;
			Arcj = MisArcos.getElemento(j);

			while ((CompPeso(Arcj, TemArc) > 0) && (j >= 0)) {
				MisArcos.insertar(Arcj, j + 1);
				j--;
				if (j >= 0)
					Arcj = MisArcos.getElemento(j);
			}
			MisArcos.insertar(TemArc, j + 1);
		}
	}

	public int Kruskal() {
		Arco Arc;
		Integer NodoOrigen;
		Integer NodoDestino;
		Integer Costo;
		int CostoTotal = 0;

		CantidadArcosHabilitados = 0;

		for (int i = 0; i < this.CantidadArcos; i++) {
			Arc = this.MisArcos.getElemento(i);
			NodoOrigen = Arc.getNodo1();
			NodoDestino = Arc.getNodo2();
			Costo = Arc.getCosto();
			if (this.ObtenerRaiz(NodoOrigen) != this.ObtenerRaiz(NodoDestino)) {
				CostoTotal += Costo;
				Arc.setEstado(true);
				CantidadArcosHabilitados++;
				this.UnionConRango(NodoOrigen, NodoDestino);
			}
		}
		return CostoTotal;
	}

	public void MostrarArbolReducido() {
		Arco Arc;
		System.out.println(this.CantidadArcosHabilitados);
		for (int i = 0; i < this.CantidadArcos; i++) {
			Arc = this.MisArcos.getElemento(i);
			if (Arc.isEstado()) {
				System.out.println((Arc.getNodo1() + 1) + " "
						+ (Arc.getNodo2() + 1) + " " + Arc.getCosto());
			}
		}
	}

	public void grabarResultado(String rutaSalida) {

		FileWriter fichero = null;
		PrintWriter pw = null;
		Arco Arc;

		System.out.println();

		try {
			fichero = new FileWriter(rutaSalida);
			pw = new PrintWriter(fichero);
			
			pw.println(this.CantidadArcosHabilitados);
			
			for (int i = 0; i < this.CantidadArcos; i++) {
				
				Arc = this.MisArcos.getElemento(i);

				if (Arc.isEstado()) {
					pw.println((Arc.getNodo1() + 1) + " "
							+ (Arc.getNodo2() + 1) + " " + Arc.getCosto());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
