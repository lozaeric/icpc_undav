import java.util.ArrayList;
import java.util.Scanner;


public class Main2 {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
      int N=in.nextInt(), M=in.nextInt(), R=in.nextInt(), E=in.nextInt();
      Nlogonia n = new Nlogonia (N,R,E);
      
      for (int i=0; i<M; i++) 
      	n.conectar (in.nextInt (), in.nextInt ());
      System.out.println (n.obtenerMinimoCosto());
	}
	
	private static class Nlogonia {
		private int matrizAdyacencia [][], costoEstadio, costoRuta;
		private boolean analizadas[];
		
		public Nlogonia (int n, int r, int e) {
			this.matrizAdyacencia = new int [n][n];
			this.analizadas = new boolean[n];
			this.costoEstadio = e;
			this.costoRuta = r;
		}
		
		public int obtenerMinimoCosto () {
			int costoTotal=0, rutasFaltantes;
			ArrayList<Integer> grupo = new ArrayList<Integer>();
			
			for (int i=0; i<matrizAdyacencia.length; i++) {
				if (analizadas[i])
					continue;
				rutasFaltantes=0;
				grupo = obtenerGrupo(i);
				if (grupo.isEmpty ())
					continue;
				for (int g : grupo) {
					for (int j : grupo) {
						if (matrizAdyacencia[g][j]!=1 && g!=j)
							rutasFaltantes++;
					}
				}
				if (rutasFaltantes/2*costoRuta<=costoEstadio*grupo.size ())
					costoTotal+=rutasFaltantes/2*costoRuta;
				else
					costoTotal+=costoEstadio*grupo.size ();
			}
			return costoTotal;
		}
		
		public void conectar (int i, int j) {
			matrizAdyacencia[i-1][j-1] = matrizAdyacencia[j-1][i-1] = 1;
		}
		
		public ArrayList<Integer> obtenerGrupo (int i) {
			ArrayList<Integer> grupo = new ArrayList<Integer> ();
			if (analizadas[i])
				return grupo;
			grupo.add (i);
			analizadas[i] = true;
			for (int j=0; j<matrizAdyacencia.length; j++) {
				if (matrizAdyacencia[i][j]==1) 
					grupo.addAll (obtenerGrupo(j));
			}
			return grupo;
		}
	}

}