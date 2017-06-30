package lugares;

public class TestRegalos {

	public static void main(String[] args) {
		
		Regalos lugares = new Regalos("REGALOS.IN");
		lugares.resolver();
		lugares.escribirArchivo("REGALOS.OUT");
	}
}
