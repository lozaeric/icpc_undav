public class FloydWarshall {
	// All Source Shortest Path
	// O(V^3)
	// todos los caminos mas cortos, minimax, maximin
	
	static void fw (int matAdy[][]) {
		for (int k = 0; k < matAdy.length; k++)
			for (int i = 0; i < matAdy.length; i++)
				for (int j = 0; j < matAdy.length; j++)
					matAdy[i][j] = Math.min(matAdy[i][j], matAdy[i][k]+matAdy[k][j]);
					//minimax 		Math.min(matAdy[i][j], Math.max(matAdy[i][k],matAdy[k][j]));
					//maximin 		Math.max(matAdy[i][j], Math.min(matAdy[i][k],matAdy[k][j]));
	}
}