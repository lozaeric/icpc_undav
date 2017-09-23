class Mcbm {
	HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<> ();
	boolean visitados[];
	int match[],n;
	
	// Max cardinality bipartite matching
	// O (V*E)
	// matching en grafos bipartitos, max independent set, min vertex cover
	
	
	public Mcbm (int n) {
		for (int i=0; i<n; i++) 
			lisAdy.put(i, new ArrayList<Integer> ());
		this.n = n;
	}
	
	void addEdge (int i, int j) {
		lisAdy.get(i).add(j);
	}
	
	int mcbm () { //max matching = min vertext conver  //max independent set = n-mcbm
		int cont = 0;
		
		match = new int[n];
		Arrays.fill (match, -1);
		for (int i=0; i<n; i++) {
			visitados = new boolean[n];
			cont += aug(i);
		}
		return cont;
	}
	
	int aug (int i) {
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