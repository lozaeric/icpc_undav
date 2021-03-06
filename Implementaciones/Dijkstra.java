class Dijkstra {
	int INF = 100000000;
	HashMap<Integer, ArrayList<Par>> lisAdy;
	
	// Single Source Shortest Path
	// O((V+E) log V)
	// camino mas corto (peso total y path)
	
	int dijkstra (int inicio, int destino) {
		int n = lisAdy.keySet().size();
      PriorityQueue<Par> pq = new PriorityQueue<>();
      int distancia[] = new int[n]; 
     // int parent[] = new int[n];
      
      Arrays.fill (distancia, INF);
      //Arrays.fill (parent, -1);
      pq.add (new Par(inicio,0));
      distancia[inicio] = 0;
      while (!pq.isEmpty ()) {
      	Par actual = pq.remove ();
      	if (actual.w>distancia[actual.v])
      		continue;
      	for (Par vec : lisAdy.get(actual.v)) {
   			if (distancia[actual.v]+vec.w<distancia[vec.v]) {
   				distancia[vec.v] = distancia[actual.v]+vec.w;
   			//	parent[vec.v] = actual.v;
   				pq.add (new Par(vec.v,distancia[vec.v]));
   			}
      	}
      }
      return distancia[destino];
      // (reverse) path generator
      // for(int i=destino; i!=-1; i=parent[i]) 
      // 		System.out.print (i+" ");
	}
	
	class Par implements Comparable<Par> {
		int w, v;
		
		public Par (int v, int w) {
			this.v = v; 
			this.w = w;
		}

		public int compareTo (Par o) {
	      return w-o.w;
      }
	}
}
