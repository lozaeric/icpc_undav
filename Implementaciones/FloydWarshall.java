public class FloydWarshall {
	static int matAdy[][];
	
	public static void main (String[] args) {
		// Grafo Dirigido o No Dirigido
		// matAdy -> matriz de adyacencia  // Importante: minimax y classic, los ejes no existentes tienen peso INFINITO
													  //				  maximin, los ejes no existentes tienen peso CERO
	}
	
	static void fw_classic () {
		for (int k = 0; k < matAdy.length; k++)
			for (int i = 0; i < matAdy.length; i++)
				for (int j = 0; j < matAdy.length; j++)
					matAdy[i][j] = Math.min(matAdy[i][j], matAdy[i][k]+matAdy[k][j]);
	}
	
	static void fw_minimax () {
		for (int k = 0; k < matAdy.length; k++)
			for (int i = 0; i < matAdy.length; i++)
				for (int j = 0; j < matAdy.length; j++)
					matAdy[i][j] = Math.min(matAdy[i][j], Math.max(matAdy[i][k],matAdy[k][j]));
	}
	
	static void fw_maximin () {
		for (int k = 0; k < matAdy.length; k++)
			for (int i = 0; i < matAdy.length; i++)
				for (int j = 0; j < matAdy.length; j++)
					matAdy[i][j] = Math.max(matAdy[i][j], Math.min(matAdy[i][k],matAdy[k][j]));
	}
	
	//longest common substring
	static int lcs (char a[], char b[]) {
		int cr[] = new int [b.length+1], lr[] = new int [b.length+1];
		
		for (int i=1; i<=a.length; i++) {
			cr = new int [b.length+1];
			
			for (int j=1; j<=b.length; j++) {
				cr[j] = lr[j-1] + (a[i-1]==b[j-1]? 1:-1000000); //match
				cr[j] = Math.max(cr[j], lr[j]); //delete
				cr[j] = Math.max(cr[j], cr[j-1]);  //insert
			}
			lr = cr;
		}
		return cr[b.length];
	}
}