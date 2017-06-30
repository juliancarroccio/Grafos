import java.io.*;


public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File f=null;
		FileReader fr=null;
		BufferedReader br=null;
		FileWriter fw=null;
		PrintWriter pw=null;
		try{
			f=new File("Regalos.in");
			fr=new FileReader(f);
			br=new BufferedReader(fr);
			Grafo g=new Grafo(br);
			int []vec=g.Powell();
			int totalcolores=g.colorear(vec);
			vec=g.getColores();
			//busco el que mas veses se repite
			// y lo voy gravando en el archivo
			fw=new FileWriter(new File("Regalos.out"));
			pw= new PrintWriter(fw);
			
			int []aux=new int [totalcolores+1];
			int max=0, cantmax=0;
			for(int i=0;i<totalcolores+1;i++){
				aux[vec[i]]++;		
				if(max<aux[vec[i]]){
					max=aux[vec[i]];
				}
			}
			for (int i=0;i<vec.length;i++){
				if(vec[i]==max)
					cantmax++;
			}
			// tengo el color que se repite mas veses en max
			pw.println(cantmax);
			for(int i=0;i<vec.length;i++){
				if(vec[i]==max){
					//System.out.println(i);
					pw.print(i+" ");
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pw!=null){
				try{
					pw.close();
				}catch(Exception e){
					e.printStackTrace();
				}
				}
		}
	}

}
