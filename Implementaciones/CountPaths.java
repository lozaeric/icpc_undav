class CountPaths {
	HashMap<Integer, ArrayList<Integer>> lisAdy;
	ArrayDeque<Integer> cola;
	boolean visitados[];
	
	// Topological Sort
	// O(V+E)
	// ordenamiento tal que si V1  es anterior a V2 entonces hay un eje V1->V2
	// contar caminos distintos para llegar a un vertice en un DAG
	
	// Floodfill 
	// contar vertices de componente conexo en matriz
	
	void countPaths (int inicio) {
		int n = lisAdy.keySet().size();
		int paths[] = new int[n];
		visitados = new boolean[n];
		cola = new ArrayDeque<> ();
		
		toposort(inicio);
		paths[inicio] = 1;
		for (int i : cola) {
			for (int v : lisAdy.get(i)) 
				paths[v] += paths[i];
		}
	}


	void toposort (int u) {
		visitados[u] = true;
		for (int vec : lisAdy.get(u)) {
			if (!visitados[vec])
				toposort(vec);
		}
		cola.addFirst (u);
	}
	
	
	 char matriz[][];
	 int mx[] = {0,0,-1,1}, my[] = {-1,1,0,0};
	 int floodfill (int i, int j) {
		if (matriz[i][j]=='1') //caracter invalido
			return 0;
		int r = 1;
		matriz[i][j] = '1';
		for (int p=0, ii, jj; p<mx.length; p++) {
			ii = i+mx[p];
			jj = j+my[p];
			if (ii<0 || ii>=matriz.length || jj<0 || jj>=matriz[0].length) 
				continue;
			else
				r += floodfill(ii,jj);
		}
		return r;
	}
}