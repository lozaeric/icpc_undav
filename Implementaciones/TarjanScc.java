import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class TarjanScc {
	static HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<Integer, ArrayList<Integer>> () ;
	static int n,dfsCount,scc;
	static int dfs_low[],dfs_num[];
	static boolean visitados[];
	static ArrayDeque<Integer> pila = new ArrayDeque<Integer>();
	
	public static void main (String[] args) {
		// Grafo Dirigido 
		// n -> cantidad de vertices
		// lisAdy -> lista de adyacencia
		ejecutar ();
	}
	
	static void ejecutar () {
		scc = dfsCount = 0;
		dfs_low = new int [n];
		dfs_num = new int [n];
		visitados = new boolean[n];
		for (int i=0; i<n; i++) {
			if (dfs_num[i]==0)
				tarjanSCC(i);
		}
		System.out.println ("Total: "+scc);
	}
	
	static void tarjanSCC (int u) {
		dfs_low[u] = dfs_num[u] = ++dfsCount;
		
		pila.add(u);
		visitados[u] = true;
		for (int vec : lisAdy.get(u)) {
			if (dfs_num[vec]==0)
				tarjanSCC(vec);
			if (visitados[vec])
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[vec]);
		}
		if (dfs_low[u]==dfs_num[u]) {
			scc++;
			System.out.print("SCC: ");
			while (true) {
				int v = pila.removeLast();
				visitados[v] = false;
				System.out.print(v+" ");
				if (u==v) 
					break;
			}
			System.out.println ();
		}
	}
}