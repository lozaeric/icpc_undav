import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Nlogonia {
	private int matrizAdyacencia [][], costoEstadio, costoRuta;
	
	public Nlogonia (int n, int r, int e) {
		this.matrizAdyacencia = new int [n][n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (i!=j)
					matrizAdyacencia[i][j] = 2500;
			}
		}
		this.costoEstadio = e;
		this.costoRuta = r;
	}
	
	public int obtenerMinimoCosto () {
		int costoTotal=0, matrizVisitables[][] = floyd_warshall (matrizAdyacencia);
		boolean estadios[] = new boolean [matrizAdyacencia.length], esInfeliz=false,necesitaEstadio=false;
		ArrayList<Integer> ciudadesAmigas = new ArrayList<Integer>(),ciudadesSinRuta = new ArrayList<Integer>();
		
		for (int i=0; i<matrizVisitables.length; i++) {
			esInfeliz=false;
			necesitaEstadio=false;
			ciudadesAmigas.clear ();
			ciudadesSinRuta.clear ();
			for (int j=0; j<matrizVisitables.length; j++) {
				if (matrizVisitables[i][j]<2500 && matrizVisitables[i][j]>0) { 
					if (!hayRutaEntre (i,j)) {
						ciudadesSinRuta.add (j);
						esInfeliz = true;
					}
					if (estadios[j]) {
						necesitaEstadio = true;
						esInfeliz = true;
					}
					ciudadesAmigas.add (j);
				}
			}
			if (esInfeliz) {
				if (!necesitaEstadio && ciudadesSinRuta.size ()*costoRuta<=costoEstadio) {
					costoTotal += ciudadesSinRuta.size ()*costoRuta;
					for (Integer j : ciudadesSinRuta) 
						matrizAdyacencia[i][j] = matrizAdyacencia[j][i] = 1;
				}
				else {
					costoTotal += costoEstadio;
					estadios[i] = true;
				}
			}
		}
		return costoTotal;
	}
	 
	public boolean hayRutaEntre (int i, int j) {
		return matrizAdyacencia[i][j]==1;
	}
	
	public void conectar (int i, int j) {
		matrizAdyacencia[i-1][j-1] = matrizAdyacencia[j-1][i-1] = 1;
	}
	
	public static int[][] floyd_warshall (int w[][]) {
		int[][] aux,previa = w;
		
		for (int t=0; t<w.length; t++) {
			aux = new int[w.length][w.length];
			for (int i=0; i<w.length; i++) {
				for (int j=0; j<w.length; j++) 
					aux[i][j] = Math.min (previa[i][j], previa[i][t]+previa[t][j]);
			}
			previa = aux;
		}
		return previa;
	}

	public int[][] getMatrizVisitables () {
	   return floyd_warshall(matrizAdyacencia);
   }
}
