import java.util.Arrays;

public class MinSpanningTree {	
	// Minimum (o Maximum) Spanning Tree 
	// O(E Log V)
	// costo total, maximin, minimax, spanning forest
	
	static long costoMST (Edge ejes[], int n) { 
		long costo = 0;
		UnionFind uf = new UnionFind (n);
		
		Arrays.sort (ejes);
		for (Edge eje : ejes) {
			if (!uf.isSameSet (eje.i, eje.j)) {
				costo += eje.w;
				uf.union (eje.i, eje.j);
				if (uf.count==1)
					break;
			}
		}
		return costo;
	}
	
	static class UnionFind {
	    int parent[],count;
	    
	    UnionFind (int n) {
	        parent = new int [n];
	        count = n;
	        for(int i = 0; i < n; i++)
	            parent[i] = i;
	    }
	    int find (int i) {
	        return parent[i]==i? parent[i]:(parent[i] = find(parent[i]));
	    }
	    boolean isSameSet (int i, int j) {
	   	 return find(i)==find(j);
	    }
	    void union(int i, int j) {
	        if (find(i) != find(j)) { 
	            parent[find(j)] = find(i);
	            count--;
	        }
	    }
	}
	
	static class Edge implements Comparable<Edge> {
		int i,j,w;

		public Edge (int i, int j, int w) {
			this.i = i; this.w = w; this.j = j;
		}
		public int compareTo (Edge o) {
	     return w-o.w;
     }
	}
}
