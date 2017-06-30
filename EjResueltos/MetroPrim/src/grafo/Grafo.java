package grafo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Grafo {

	private Integer CantidadDeNodos;
	private Integer Adyacencia[][];
	private Vector<Arco> MisArcos;
	private Integer CantArc;
	private Integer Vistos[];
	private Integer NodoActual;
	private Integer NodoAdy;

	private Integer CantidadArcosHabilitados;

	public void InicializarMatriz() {

		this.Adyacencia = new Integer[this.CantidadDeNodos][this.CantidadDeNodos];

		for (int i = 0; i < this.CantidadDeNodos; i++)
			for (int j = 0; j < this.CantidadDeNodos; j++)
				this.Adyacencia[i][j] = -1;
	}

	public void InicializarVistos() {

		this.Vistos = new Integer[this.CantidadDeNodos];

		for (int i = 0; i < this.CantidadDeNodos; i++)
			this.Vistos[i] = -1;
	}

	public Grafo(String Ruta) {
		this.NodoAdy = -1;
		this.NodoActual = -1;
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
			info = cadena.split(" ");
			int tuneles, puentes;

			this.CantidadDeNodos = Integer.parseInt(info[0]);
			tuneles = Integer.parseInt(info[1]);
			puentes = Integer.parseInt(info[2]);

			// Todos los nodos llegan a los demas nodos...
			this.CantArc = tuneles + puentes;

			this.InicializarMatriz();
			this.InicializarVistos();
			MisArcos = new Vector<Arco>(this.CantArc);

			for (int i = 0; i < tuneles; i++) {
				cadena = br.readLine();
				info = cadena.split(" ");
				this.Adyacencia[Integer.parseInt(info[0]) - 1][Integer
						.parseInt(info[1]) - 1] = 0;
				this.Adyacencia[Integer.parseInt(info[1]) - 1][Integer
						.parseInt(info[0]) - 1] = 0;
			}

			for (int i = 0; i < puentes; i++) {
				cadena = br.readLine();
				info = cadena.split(" ");
				this.Adyacencia[Integer.parseInt(info[0]) - 1][Integer
						.parseInt(info[1]) - 1] = 1;
				this.Adyacencia[Integer.parseInt(info[1]) - 1][Integer
						.parseInt(info[0]) - 1] = 1;
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

	public int CantidadVistos() {
		int cant = 0;

		for (int i = 0; i < this.Vistos.length; i++)
			if (this.Vistos[i] != -1)
				cant++;

		return cant;
	}

	public int ObtenerSiguienteAdy() {

		boolean encontrado = false;
		this.NodoAdy++;

		while (!encontrado && (this.NodoAdy <= this.CantidadDeNodos - 1)) {
			if ((this.NodoAdy != this.NodoActual)
					&& (this.Adyacencia[this.NodoActual][this.NodoAdy]) != -1)
				encontrado = true;
			else
				this.NodoAdy++;
		}

		if (encontrado)
			return this.NodoAdy;
		else {
			this.NodoAdy = -1;
			return -1;
		}
	}

	public int ObtenerSiguienteVisto(int actual) {

		int Posicion = actual + 1;

		while (Posicion < this.Vistos.length && this.Vistos[Posicion] == -1)
			Posicion++;

		if (Posicion == this.Vistos.length)
			return -1;
		else
			return Posicion;
	}

	public int Prim() throws Exception {

		this.Vistos[0] = 1;
		boolean flag = true;
		int CantDeVistos = 1;
		int costototal = 0;
		int costomin = -1;
		int PosColMenor = 0;
		int PosFilMenor = 0;

		while (CantDeVistos != this.CantidadDeNodos) {

			flag = true;
			this.NodoActual = -1;
			costomin = -1;

			while ((this.NodoActual = this
					.ObtenerSiguienteVisto(this.NodoActual)) != -1) {
				while (this.ObtenerSiguienteAdy() != -1) {

					if (this.Vistos[this.NodoAdy] == -1) {
						if (flag) {
							flag = false;
							costomin = this.Adyacencia[this.NodoActual][this.NodoAdy];
							PosColMenor = this.NodoAdy;
							PosFilMenor = this.NodoActual;
						} else if (this.Adyacencia[this.NodoActual][this.NodoAdy] < costomin) {
							costomin = this.Adyacencia[this.NodoActual][this.NodoAdy];
							PosColMenor = this.NodoAdy;
							PosFilMenor = this.NodoActual;
						}
					}
				}
			}
			this.Vistos[PosColMenor] = 1;
			CantDeVistos++;
			costototal += costomin;
			Arco Arc = new Arco(PosFilMenor, PosColMenor, costomin, false);
			MisArcos.insertar(Arc);
		}
		return costototal;
	}

	public void MostrarArbolReducido() {
		Arco Arc;
		int i = 0;
		System.out.println();
		Arc = this.MisArcos.getElemento(i);
		while (Arc != null) {
			System.out.println((Arc.getNodo1() + 1) + " "
					+ (Arc.getNodo2() + 1) + " " + Arc.getCosto());
			i++;
			Arc = this.MisArcos.getElemento(i);
		}
		CantidadArcosHabilitados = i;
	}
	
	public int arcosHabilitados(){
		Arco Arc;
		int i = 0;
		Arc = this.MisArcos.getElemento(i);
		while (Arc != null) {
			i++;
			Arc = this.MisArcos.getElemento(i);
		}
		return CantidadArcosHabilitados = i;
	}

	public void grabarResultado(String rutaSalida) {

		FileWriter fichero = null;
		PrintWriter pw = null;

		try {
			int costoTotal = this.Prim();
			fichero = new FileWriter(rutaSalida);
			pw = new PrintWriter(fichero);

			pw.println(costoTotal);
			System.out.println(costoTotal);

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

	public Integer getCantidadDeNodos() {
		return CantidadDeNodos;
	}

	public Integer getCantArc() {
		return CantArc;
	}

	public Integer getCantidadArcosHabilitados() {
		return CantidadArcosHabilitados;
	}

	public String toString() {
		return MisArcos.toString();
	}

}
