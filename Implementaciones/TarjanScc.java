class TarjanScc {
	int dfsCount,scc,dfs_low[],dfs_num[];
	boolean visitados[];
	ArrayDeque<Integer> pila;
	HashMap<Integer, ArrayList<Integer>> lisAdy;
	
	// Tarjan 
	// O(V+E)
	// encontrar strongly connected components en grafo
	
	void tarjan () {
		int n = lisAdy.keySet().size();
		scc = dfsCount = 0;
		dfs_low = new int [n];
		dfs_num = new int [n];
		visitados = new boolean[n];
		pila = new ArrayDeque<>();
		
		for (int i=0; i<n; i++) {
			if (dfs_num[i]==0)
				tarjanSCC(i);
		}
	}
	
	void tarjanSCC (int u) {
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
			while (true) { // pila contiene elementos de un scc encontrado
				int v = pila.removeLast();
				visitados[v] = false;
				if (u==v) 
					break;
			}
		}
	}
}