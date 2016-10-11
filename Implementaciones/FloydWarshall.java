
import java.util.Arrays;
import java.util.Scanner;

public class FloydWarshall {
	static int matAdy[][];
	
	public static void main (String[] args) {
		Scanner in = new Scanner (System.in);
		int n = in.nextInt();
		
		String ciudades[] = new String[n];
		matAdy = new int[n][n];
		for (int i=0; i<n; i++) 
			ciudades[i] = in.next();		
		for (int i=0; i<n; i++)  {
			char actual[] = ciudades[i].toCharArray();
			for (int j=i+1; j<n; j++) {
				matAdy[i][j] = matAdy[j][i] = 1+lcs (actual, ciudades[j].toCharArray());
			}
		}
		System.out.println (fw ());
	}
	
	static int fw () {
		int max = -1;
		
		//floyd warshall - minimax
		for (int k = 0; k < matAdy.length; k++)
			for (int i = 0; i < matAdy.length; i++)
				for (int j = 0; j < matAdy.length; j++)
					matAdy[i][j] = Math.min(matAdy[i][j], Math.max(matAdy[i][k],matAdy[k][j]));
		for (int i = 0; i < matAdy.length; i++) {
			for (int j = i+1; j < matAdy.length; j++) {
				if (max<matAdy[i][j])
					max = matAdy[i][j];
			}
		}
		return max;
	}
	
	static int fw2 () {
		int max = -1;
		
		//floyd warshall - maximin
		for (int k = 0; k < matAdy.length; k++)
			for (int i = 0; i < matAdy.length; i++)
				for (int j = 0; j < matAdy.length; j++)
					matAdy[i][j] = i==j? 0:Math.max(matAdy[i][j], Math.max(matAdy[i][k],matAdy[k][j]));
		for (int i = 0; i < matAdy.length; i++) {
			for (int j = i+1; j < matAdy.length; j++) {
				if (max<matAdy[i][j])
					max = matAdy[i][j];
			}
		}
		return max;
	}
	
	
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