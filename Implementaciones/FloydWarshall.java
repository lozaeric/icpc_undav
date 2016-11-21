
import java.util.Arrays;
import java.util.Scanner;

public class FloydWarshall {
	static int matAdy[][];
	
	public static void main (String[] args) {
		int n = 5;
		matAdy = new int[n][n];
		
		for (int i=0; i<n; i++)  
			for (int j=0; j<n; j++) 
				matAdy[i][j] = 10000000; // minimax y classic  
		   // matAdy[i][j] = 0;     // maximin
		for (int i=0; i<n; i++)
			matAdy[i][i] = 0;
		matAdy[0][1] = 2; matAdy[0][2] = 1; matAdy[0][4] = 3;
		matAdy[1][3] = 4;
		matAdy[2][1] = 1; matAdy[2][4] = 1; matAdy[3][0] = 1;
		matAdy[3][2] = 3; matAdy[3][4] = 5;
		
		for (int i=0; i<n; i++)
			System.out.println (Arrays.toString (matAdy[i]));
		System.out.println ();
		fw_classic ();
		for (int i=0; i<n; i++)
			System.out.println (Arrays.toString (matAdy[i]));		
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