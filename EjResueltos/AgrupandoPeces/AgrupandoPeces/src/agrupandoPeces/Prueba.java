package agrupandoPeces;

public class Prueba {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Acuario a= new Acuario("Acuario.in");
		
		for(int f=0;f<a.cantPeces ; f++)
			{
			for(int c =0 ; c < a.cantPeces ; c++)
				System.out.print(a.matAdy[f][c]+" ");
			System.out.println(" ");
			}
		a.coloreo();
	}

}
