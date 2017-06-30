
public class Main {

	public static void main(String[] args) {

		Archivo arch = new Archivo("LoteDePrueba/IN/caso_06_varias_opciones_llego_con_2_lineas.in", 
				"LoteDePrueba/OUT/caso_06_varias_opciones_llego_con_2_lineas.out");
		Subtes subte = new Subtes();
		subte = arch.leer(subte);
		subte.hallarRecorrido();
		arch.guardar(subte);
	}

}
