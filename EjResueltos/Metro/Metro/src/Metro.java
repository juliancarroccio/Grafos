import java.io.*;

public class Metro {
	
		private Integer mat[][];
		private boolean IslasVisitadas[];
		
		public Metro(){
			mat=null;
		}
		

		public Metro(String Ruta){
			File archivo = null;
			FileReader fr = null;
			BufferedReader br = null;

			try {
				archivo = new File(Ruta);
				fr = new FileReader(archivo);
				br = new BufferedReader(fr);
				
				
				String linea = br.readLine();
				String cad[] = linea.split(" ");
				
				int N = Integer.parseInt(cad[0]);
				int K = Integer.parseInt(cad[1]);
				int M = Integer.parseInt(cad[2]);
				
				mat = new Integer[N][N];
				IslasVisitadas = new boolean[N];
				
				//Inicializo Matriz y Vector de Islas Visitadas
				for(int i=0; i<N ; i++){
					IslasVisitadas[i]=false;
					for(int j=0; j<N ; j++)
						mat[i][j]=-1;
				}
				int k=0;
				while(k<K){
					linea = br.readLine();
					String cad2[] = linea.split(" ");
					int i = Integer.parseInt(cad2[0]);
					int j = Integer.parseInt(cad2[1]);
					mat[i-1][j-1]=0;
					mat[j-1][i-1]=0;
					k++;			
				}
				
				int m=0;
				while(m<M){
					linea = br.readLine();
					String cad2[] = linea.split(" ");
					int i = Integer.parseInt(cad2[0]);
					int j = Integer.parseInt(cad2[1]);
					if(mat[i-1][j-1]==-1) //Si no están conectadas por túneles, las conecto por puente si es necesario
						mat[i-1][j-1]=1;
					if(mat[i-1][j-1]==-1)
						mat[j-1][i-1]=1;
					m++;			
				}			
				} catch(Exception e) {
				e.printStackTrace();
			    }
			finally {
				try {
					if( fr!= null)
					fr.close();
				     } catch(Exception e2) {
			         e2.printStackTrace();
				}
			}
			
	}
		
		
		public int calcularPuentes(String ruta){
			int n = mat.length;
			int puentes = 0;
			IslasVisitadas[0] = true;
			for(int i = 0 ; i < n ; i++){ //Para cada isla 
				if(IslasVisitadas[i]){   //Parto a buscar desde las islas visitadas

									int j=0;
									while( j < n ){     //Busco túneles
														if(mat[i][j]==0){    //Si encontré un túnel 
																		if(IslasVisitadas[j]==false){ //Si el túnel va a una isla
																			IslasVisitadas[j] = true; //que aun no fue visitada
																			i=0;                     //visito la isla
																							  }
																		
																		else
																			if(recorriTodasLasIslas()) //Verifico que aun quedan
																				return puentes;        //islas por visitar
																						
																	    }//fin if Encontro Túnel														
														j++;
												}//fin while Buscar túneles
									j=0;
									}//fin if
				
				else{  //si no puedo llegar mediante un túnel, debo buscar un puente
						IslasVisitadas[i] = true;
						i=0;
						puentes++; //Construyo puente
				}
				
			}//fin for
			
			imprimirSalida(puentes);
			grabarSalida(puentes,ruta);
			return puentes;
		}
		
		private boolean recorriTodasLasIslas(){
			//Si la isla ya había sido visitada
			int visitadas=0;
			for(int h=0; h<mat.length; h++)
					if(IslasVisitadas[h]==false) //Verifico que queden islas por visitar
						visitadas++;

			if(visitadas == mat.length) //Si no hay más islas por visitar, fin.
					return true;
			return false;
		}
		
		
		private void imprimirSalida(int puentes){
			for(int i = 0 ; i < mat.length ; i++)
				System.out.println("Isla Número: " + (i+1) + " fue visitada? " +  IslasVisitadas[i]);
			System.out.println("Cantidad de puentes a construir: " + puentes);
		}
		
		
		private void grabarSalida(int puentes, String ruta){
			FileWriter fw = null;
			PrintWriter pw = null;

			try {
				fw = new FileWriter(ruta);
				pw = new PrintWriter(fw);
				
				pw.println(puentes);

			    } catch(Exception e) {
				e.printStackTrace();
			    }
			finally {
				try {
					if( fw!= null)
					fw.close();
				     } catch(Exception e2) {
			         e2.printStackTrace();
				}
			}
		}
		
		
		public void mostrarMatrizAdyacencias(){
			for(int i=0; i<mat.length; i++){
				for(int j=0; j<mat.length; j++){
					System.out.print(mat[i][j] + "    ");
				}
				System.out.println();
			}
		}
		
		
		public void mostrarIslasVisitadas(){
				for(int i=0; i<IslasVisitadas.length; i++)
					System.out.println(IslasVisitadas[i]);
		}
		
		
		public static void main(String arg[]){
			String rutas[] = { "00Enunciado", "01SoloTuneles", "02Prioridad", "03UnaIsla",
								"04SoloPuentes" , "05Ciclo de Tuneles", "06Aleatorio",
								"07FatigaTuneles", "08FatigaPuentes", "09Doble Camino"
							 };
			String directorio = "D:/Eclipse/workspace/Metro/Lote de Pruebas Metro/";
			for(String archivo : rutas){
				Metro m = new Metro(directorio + archivo + ".txt");
			/*	m.mostrarMatrizAdyacencias();
				m.mostrarIslasVisitadas(); */
				m.calcularPuentes(directorio + archivo + "Out" + ".txt");
			}
		}
		
}
