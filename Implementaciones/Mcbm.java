import java.util.ArrayList;
import java.util.HashMap;


public class Mcbm {
	
	public static void main (String[] args) {
		int n = 5;
		Grafo g = new Grafo (n);
		g.setEdge(0, 2);
		g.setEdge(0, 3);
		g.setEdge(0, 4);
		g.setEdge(1, 2);
		g.setEdge(1, 3);
		g.setEdge(1, 4);
		int mbcm = g.mcbm(), mis = n-mbcm;
		System.out.println ("MCBM y MVC = "+mbcm);
		System.out.println ("MIS = "+mis);
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
}