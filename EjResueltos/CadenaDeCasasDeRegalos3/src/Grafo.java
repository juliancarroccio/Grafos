import java.io.*;


public class Grafo {
	private int grafo[][];
	private int colores[];
	private int grados[];
	private int cantNodos;
	public Grafo(BufferedReader br){
		String linea;
		String aux[];
		try{
			linea=br.readLine();
			cantNodos=Integer.parseInt(linea);
			grafo=new int[cantNodos][cantNodos];
			grados=new int[cantNodos];
			for(int i=0;i<cantNodos;i++){
				linea=br.readLine();
				aux=linea.split(" ");
				for(int j=1;j<aux.length-1;j++){
					grafo[Integer.parseInt(aux[0])-1][Integer.parseInt(aux[j])-1]=1;
					grafo[Integer.parseInt(aux[j])-1][Integer.parseInt(aux[0])-1]=1;
					grados[Integer.parseInt(aux[0])-1]++;
					grados[Integer.parseInt(aux[j])-1]++;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(br!=null){
					br.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	public int[][] getGrafo() {
		return grafo;
	}

	public void setGrafo(int[][] grafo) {
		this.grafo = grafo;
	}

	public int[] getColores() {
		return colores;
	}

	public void setColores(int[] color) {
		this.colores = color;
	}

	public int[] getGrados() {
		return grados;
	}

	public void setGrados(int[] grados) {
		this.grados = grados;
	}

	public int getCantNodos() {
		return cantNodos;
	}

	public void setCantNodos(int cantNodos) {
		this.cantNodos = cantNodos;
	}

	public int[] Powell(){
		int [] powell=new int[cantNodos];
		int []aux=new int[cantNodos];
		for(int i=0;i<cantNodos;i++){
			aux[i]=grados[i];
			powell[i]=i;
		}
		
		int temp, temp2;
		for(int i=0;i<cantNodos;i++){
			int mayor=0;
			int indiceMay=i;
			for(int j=i;j<cantNodos;j++){
				if(aux[j]>mayor){
					mayor=aux[j];
					indiceMay=j;
				}
			}
			temp=aux[indiceMay];
			temp2=powell[indiceMay];
			aux[indiceMay]=aux[i];
			powell[indiceMay]=powell[i];
			aux[i]=temp;
			powell[i]=temp2;
		}
		for(int i=0;i<cantNodos-1;i++){
			for(int j=i;j<cantNodos&& aux[i]>=aux[i+1];j++){
				if(aux[i]==aux[i+1]&&Math.random()>0.5){
					temp=powell[i];
					powell[i]=powell[i+1];
					powell[i+1]=temp;
				}
			}
		}
		return powell;
	}
	public int colorear(int[] vec){
		colores=new int[cantNodos];
		int cantColores=0;
		int color=0;
		int bandera=1;
		//coloreamos el primero
		colores[vec[0]]=1;
		cantColores++;
		for(int i=1;i<cantNodos;i++){
			bandera=0;
			for(color=1;color<=cantColores&&bandera!=1;color++){
				bandera=1;
				for(int k=0;k<cantNodos&&bandera==1;k++){
					if(k!=vec[i]&&grafo[vec[i]][k]==1&&colores[vec[i]]==0&&colores[k]==color){
						bandera=0;
					}
				}	
			}
			if(bandera==1){
				colores[vec[i]]=color-1;
			}else{
				
				cantColores++;
				colores[vec[i]]=cantColores;
			}
			
		}
		
		return cantColores;
	}
	/*public int colorearGrafo(int[]vec){
		colores=new int[cantNodos];
		int cantcolores=0;
		int color=0;
		//coloreo
		cantcolores++;
		colores[vec[0]-1]=1;
		//recorro todos los nodos para ir coloreandolos
		for (int i=1;i<=cantNodos;i++){
			int bandera=0;
			//recorro cada nodo adyacente para ver si le puedo poner el color que queria
			for(color=1; color<=cantcolores && bandera!=1;color++){
				if(bandera==0){
					bandera=1;//indiraca si puedo seguir preguntando con el mismo color
					//pregunto si el algun nodo adyacente tiene el color que le quiero poner a este nodo
					for(int j=0;j<cantNodos && bandera ==1;j++){
						if(i!=j && grafo[vec[i]-1][j]==1 && colores[vec[i]-1]==0 && colores[j]==color){
							bandera=0;//indica que encontre un nodo que ya tiene este color y es adyacente al nodo en el que estoy parado
						}
					}
				}
			}
			if(bandera==1){
				colores[vec[i]-1]=(color-1);//pongo el menos uno porque cuando sale del for primero hiso color++ y desp recien sale del for -.-
			}else{
				cantcolores++;
				colores[vec[i]-1]=cantcolores;
			}	
		}
		return cantcolores;
	}*/
	

}