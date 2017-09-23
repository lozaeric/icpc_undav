class BellmanFord {
	HashMap<Integer, ArrayList<Par>> lisAdy;
	int INF = 100000000;
	
	// Single Source Shortest Path
	// O (V E)
	// camino mas corto, detectar ciclos negativos
	
	int[] bellman_ford (int inicio) {
		int n = lisAdy.keySet().size();
		int distancias[] = new int[n];
		
		Arrays.fill (distancias, INF);
		distancias[inicio] = 0;
		for (int i = 0; i<n-1; i++)  
			for (int u = 0; u<n; u++)  
			   for (Par vec : lisAdy.get (u)) 
			   	distancias[vec.v] = Math.min (distancias[u]+vec.w, distancias[vec.v]);
		return distancias;
	}
	
	boolean hasNegCycle (int distancias[]) {  // posterior a bellman_ford
		int n = lisAdy.keySet().size();
		for (int u = 0; u<n; u++)  
		   for (Par vec : lisAdy.get (u)) 
		   	if (distancias[vec.v]>distancias[u]+vec.w)
		   		return true;
	   return false;
	}
	
	class Par {
		int v,w;
		
		public Par (int v, int w) {
			this.v = v; 
			this.w = w;
		}
	}
}
