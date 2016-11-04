import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Regional {
	static char mat [][];
	
	
	public static void main (String[] args) {
		FastReader in = new FastReader ();
		Integer n;
		Grafo g;
		int rcont,ccont,r[][],c[][];
		
		while (true) {
			n = in.nextInt (); 
			if (n==null)
				break;
			rcont = ccont = 0;
			mat = new char[n][n];
			r = new int[n][n];
			c = new int[n][n];
			for (int i=0; i<n; i++) 
				mat[i] = in.nextLine ().toCharArray ();
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					if (mat[i][j]=='.' && (j==0 || mat[i][j-1]=='X')) 
						rcont++;
					if (mat[i][j]=='.')
						r[i][j] = rcont;
				}
			}
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					if (mat[j][i]=='.' && (j==0 || mat[j-1][i]=='X')) 
						ccont++;
					if (mat[j][i]=='.')
						c[j][i] = ccont;
				}
			}
			//for (int cc[] : c)
			//	System.out.println (Arrays.toString (cc));
			//for (int rr[] : r)
			//	System.out.println (Arrays.toString (rr));
			
			g = new Grafo (rcont+ccont+2);
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					if (mat[i][j]=='.')
						g.setEdge (r[i][j], c[i][j]+rcont);
				}
			}
			System.out.println (g.mcbm ());
		}
	}
	
	static class Grafo {
		static HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<Integer, ArrayList<Integer>> ();
		static boolean visitados[];
		static int match[];
		
		public Grafo (int n) {
			visitados = new boolean[n];
			match = new int[n];
			for (int i=0; i<n; i++) {
				lisAdy.put(i, new ArrayList<Integer> ());
				match[i] = -1;
			}
		}
		
		void setEdge (int i, int j) {
			lisAdy.get(i).add(j);
		}
		
		int mcbm () {
			int cont = 0, v = visitados.length;
			
			for (int i=0; i<v; i++) {
				cont += aug(i);
				visitados = new boolean[v];
			}
			return cont;
		}
		
		private int aug (int i) {
			if (visitados[i])
				return 0;
			visitados[i] = true;
			for (int vec : lisAdy.get(i)) {
				if (match[vec]==-1 || aug(match[vec])>0) {
					match[vec] = i;
					return 1;
				}
			}
			return 0;
		}
	}
	
	static class FastReader {
	    BufferedReader br;
	    StringTokenizer st;

	    public FastReader() {
	        br = new BufferedReader(new InputStreamReader(System.in));
	    }

	    String next() {
	 	   String  line;
	        while (st == null || !st.hasMoreElements()) {
	           line = nextLine ();
	           if (line == null)
	              return null;
	           st = new StringTokenizer(line);
	        }
	        return st.nextToken();
	    }

	    Integer nextInt() {
	 	   String element = next ();
	        return element==null? null:Integer.parseInt(element);
	    }

	    Long nextLong() {
	 	   String element = next ();
	        return element==null? null:Long.parseLong(element);
	    }

	    Double nextDouble() {
	 	   String element = next ();
	        return element==null? null:Double.parseDouble(element);
	    }

	    String nextLine() {
	        String str = null;
	        try {
	            str = br.readLine();
	        }
	        catch (IOException e)
	        {}
	        return str;
	    }
	}
}
