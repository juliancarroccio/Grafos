package grafo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class Grafo {

	private int matrizAdy[][];
	private Nodo vecNodo[];
	private int dimension;
	private boolean EsPosibleResolver = false;
	private ArrayList<Integer> iniciales;
	private ArrayList<Integer> finales;
	
	
	
	public Grafo(String path){
		File arch = null;
		FileReader fr = null;
		BufferedReader br = null;
		int MAX = Integer.MAX_VALUE; 
		
		arch = new File(path);
		try {
			fr = new FileReader(arch);
			br = new BufferedReader(fr);
			
			int escenaF;
			int ini1,ini2,fin1,fin2;
			String linea,campos[];
			linea = br.readLine();
			campos = linea.split(" ");
			dimension = Integer.parseInt(campos[0]);
			escenaF = Integer.parseInt(campos[1]);
			vecNodo = new Nodo[dimension];
			matrizAdy = new int[dimension][dimension];
			iniciales = new ArrayList<Integer>();
			finales = new ArrayList<Integer>();
			
			for(int i=0; i<dimension;i++){
						
				for(int j=0; j<dimension;j++)
					matrizAdy[i][j] = MAX;
			}
				 
			
			for(int i=0; i<dimension;i++){
				linea = br.readLine();
				campos = linea.split(" ");
				ini1 = Integer.parseInt(campos[1]);
				fin1 = Integer.parseInt(campos[2]);
				vecNodo[i] = new Nodo(ini1,fin1);
								
				if(ini1 == 1)
					iniciales.add(i+1);
				if(fin1 == escenaF)
					finales.add(i+1);
			}
			
			for(int i=0; i<dimension;i++){
				EsPosibleResolver = false;
				
				for(int j=0; j<dimension;j++){
					
					if(i!=j && (vecNodo[i].getEscenaFin()+1 >= vecNodo[j].getEscenaIni())
							&& vecNodo[i].getEscenaFin() < vecNodo[j].getEscenaFin() 
							&& vecNodo[i].getEscenaIni()<= vecNodo[j].getEscenaIni()){
						
						int costo;
						costo = (vecNodo[i].getEscenaFin()- vecNodo[j].getEscenaIni() ) +1;
						matrizAdy[i][j] = costo;
						EsPosibleResolver = true;
					}
					
				
				}
				
				if(i == dimension-1 && EsPosibleResolver != true && vecNodo[i].getEscenaFin() == escenaF)
					EsPosibleResolver = true;
				if(!EsPosibleResolver && vecNodo[i].getEscenaFin() != escenaF)
					break;
				
				
					
			}
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(br != null)
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
		
	}
	
	
	
	public Camino dysktraPelicula(Integer inicial){
			Camino caminos[] = new Camino[dimension+1];
			
			caminos[0] = new Camino(Integer.MAX_VALUE,null);
						
			for(int i=1;i<dimension+1;i++){
				caminos[i] = new Camino(matrizAdy[inicial-1][i-1],null);
				
				if(caminos[i].getCosto() != Integer.MAX_VALUE)
					caminos[i].setRecorrido(Integer.toString(i));
				else
					caminos[i].setRecorrido(null);
			}
			
			
			ArrayList <Integer> U = new ArrayList<Integer>();
			for(int i=0;i<dimension;i++)
				U.add(i+1);			
			
			U.remove(inicial);											//Saco el inicial
			
			for(int i=0;i<dimension-1;i++){
				Integer W = menor(U,caminos);
				if(W == -1)
					break;
				
				U.remove(W);											//Saco el W elegido con menor costo
				
				int cmp;
				
				for (Integer variable : U) {
					if(matrizAdy[W-1][variable-1] != Integer.MAX_VALUE){
						cmp = comparar(caminos[variable].getCosto(), (caminos[W].getCosto() + matrizAdy[W-1][variable-1]));
						if(cmp > 0){
							caminos[variable].setCosto((caminos[W].getCosto() + matrizAdy[W-1][variable-1]));							
							String aux = new String();
							caminos[variable].setRecorrido(aux.concat(caminos[W].getRecorrido()+" "+ variable));
						}
							
						
					}
					
					
				}
			}
			
			int menor = Integer.MAX_VALUE;							//Busco el nodo final que tenga costo menor
			int pos=0;
			for (Integer variable : finales) {
				if(caminos[variable].getCosto() < menor ){
					pos = variable;
					menor = caminos[variable].getCosto();
				}
					
			}
			
			caminos[pos].setRecorrido(inicial.toString()+" " + caminos[pos].getRecorrido());
					
			
			return caminos[pos];
	}
	
	
	public void Resolver(){
		ArrayList<Camino>resultado = new ArrayList<Camino>();
		
		if(!EsPosibleResolver)
			toFile(null);
		else{
			
			for (Integer variable : iniciales) {
				resultado.add(dysktraPelicula(variable));
			}
			
			int menor = Integer.MAX_VALUE;							//Busco entre todos los inicios el que tenga costo menor
			int pos=0;
			int i=0;
			for (Camino variable : resultado) {
				if(variable.getCosto() < menor ){
					pos = i;
					menor = variable.getCosto();
				}
				i++;	
			}
			
			toFile(resultado.get(pos).getRecorrido());
		}
	}
	
	
	public int menor(ArrayList<Integer> U,Camino caminos[]){
		int menor = Integer.MAX_VALUE,pos = -1;
		int i = 0;
		
		Iterator<Integer>it = U.iterator();
		
		while(it.hasNext()){
			
			if(caminos[U.get(i)].getCosto() < menor){
				menor = caminos[U.get(i)].getCosto();
				pos = U.get(i);
			}
			
			i++;
			it.next();
				
		}
		
		return pos;
	}
	
	
	public void toFile(String linea){
		FileWriter fw = null;
		PrintWriter pw = null;
		
		try {
			fw = new FileWriter("prueba varios caminos varios finales.out");
			pw = new PrintWriter(fw);
			if(!EsPosibleResolver)
				pw.println("NO ES POSIBLE...");
			else
				pw.println(linea);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(pw != null)
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		}
		
	}
	public int comparar(int a,int b){
		return a - b;
	}
	public int[][] getMatrizAdy() {
		return matrizAdy;
	}


	public void setMatrizAdy(int[][] matrizAdy) {
		this.matrizAdy = matrizAdy;
	}


	public Nodo[] getVecNodo() {
		return vecNodo;
	}


	public void setVecNodo(Nodo[] vecNodo) {
		this.vecNodo = vecNodo;
	}


	public static void main(String[] args) {
//		Grafo g1 = new Grafo("prueba.in");
//		
//		
//		
//		for(int i=0; i<9;i++){			
//			for(int j=0; j<9;j++)
//				System.out.println(i+1 +" "+ (j+1) + " "+ g1.getMatrizAdy()[i][j]);
//		}
//		
//		
//		g1.Resolver();

//		Grafo g2 = new Grafo("prueba varios caminos.in");
//		for(int i=0; i<4;i++){			
//			for(int j=0; j<4;j++)
//				System.out.println(i+1 +" "+ (j+1) + " "+ g2.getMatrizAdy()[i][j]);
//		}
//		g2.Resolver();
		
		Grafo g3 = new Grafo("prueba varios caminos varios finales.in");
		for(int i=0; i<4;i++){			
			for(int j=0; j<4;j++)
				System.out.println(i+1 +" "+ (j+1) + " "+ g3.getMatrizAdy()[i][j]);
		}
		g3.Resolver();
		
	}

}
