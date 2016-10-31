import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class TarjanScc {
	static HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<Integer, ArrayList<Integer>> () ;
	static int n,e;
	static int dfs_low[],dfs_num[],dfsCount,scc;
	static boolean visitados[];
	static ArrayDeque<Integer> pila = new ArrayDeque<Integer>();
	
	public static void main (String[] args) {
		n = 8;
		e = 9;
		scc = dfsCount = 0;
		dfs_low = new int [n];
		dfs_num = new int [n];
		visitados = new boolean[n];
		for (int i=0; i<n; i++) 
			lisAdy.put(i, new ArrayList<Integer> ());
		lisAdy.get(0).add(1);
		lisAdy.get(1).add(3);
		lisAdy.get(3).add(2);
		lisAdy.get(2).add(1);
		lisAdy.get(3).add(4);
		lisAdy.get(4).add(5);
		lisAdy.get(5).add(7);
		lisAdy.get(7).add(6);
		lisAdy.get(6).add(4);
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