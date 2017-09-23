class ArticulationpointBridge {
	HashMap<Integer, ArrayList<Integer>> lisAdy;
	int dfsRoot, bridges, dfsCount, rootChildren;
	int dfs_low[], dfs_num[], dfs_parent[];
	boolean isArticulation[];

	// Encontrar ejes que sean puentes y vertices que sean puntos de articulacion 
	// si los quito, el grafo ya no es un solo componente conexo
	// O (V+E)
	
	void searchAPyB () {
		int n = lisAdy.keySet().size();
		dfs_low = new int[n];
		dfs_num = new int[n];
		dfs_parent = new int[n];
		isArticulation = new boolean[n];
		dfsCount = bridges = 0;
		for (int i=0; i<n; i++) {
			if (dfs_num[i]==0) {
				dfsRoot = i;
				rootChildren = 0;
				dfs (i);
				isArticulation[i] = rootChildren>1;
			}
		}
	}
	
	void dfs (int u) {
		dfs_low[u] = dfs_num[u] = ++dfsCount;
		for (int vec : lisAdy.get(u)) {
			if (dfs_num[vec]==0) {
				dfs_parent[vec] = u;
				if (u==dfsRoot)
					rootChildren++;
				dfs (vec);
				if (dfs_low[vec]>=dfs_num[u])
					isArticulation[u] = true;
				if (dfs_low[vec]>dfs_num[u])
					bridges++;
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[vec]);
			}
			else if (vec!=dfs_parent[u])
				dfs_low[u] = Math.min(dfs_low[u], dfs_num[vec]);
		}
	}
}