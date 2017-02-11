import java.util.ArrayDeque;
import java.util.Scanner;

public class grid {
	static int mat[][];
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		int n = in.nextInt(), m = in.nextInt();
		mat = new int[n][m];
		for (int i=0; i<n; i++) {
			char num[] = in.next().toCharArray();
			for (int j=0; j<m; j++)
				mat[i][j] = num[j]-'0';
		}
		int r  = bfs();
		System.out.println (r==-1? "IMPOSSIBLE":r);
		
	}
	
	static int bfs () {
		ArrayDeque <Vertex> cola = new ArrayDeque <> ();
		int distancias[][] = new int [mat.length][mat[0].length];
		boolean visitados[][] = new boolean[mat.length][mat[0].length];
		int[] mc = {0,0,1,-1}, mr = {1,-1,0,0}; 
		
		cola.add (new Vertex(0,0));
		visitados[0][0] = true;
		distancias[0][0] = 0;
		do  {
			Vertex actual = cola.remove();
			for (int t=0,r,c; t<mc.length; t++) {
				r = actual.i+(mr[t]*mat[actual.i][actual.j]); c = actual.j+(mc[t]*mat[actual.i][actual.j]);
				if (r<mat.length && r>=0 && c>=0 && c<mat[0].length) 
					if (!visitados[r][c]) {
						cola.add (new Vertex(r,c));
						visitados[r][c] = true;
						distancias[r][c] = distancias[actual.i][actual.j]+1;
					}
					
			}
			
		} while (!cola.isEmpty ());
		return visitados[mat.length-1][mat[0].length-1]? distancias[mat.length-1][mat[0].length-1]:-1;
			
	}

	static class Vertex {
		int i,j;
		public Vertex (int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
