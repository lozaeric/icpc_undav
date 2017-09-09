import java.util.Arrays;
import java.util.Scanner;

class I {
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		int t = in.nextInt();
		for (int tt=1; tt<=t; tt++) {
			int n = in.nextInt(), m = in.nextInt();
			if (n==0 || m==0) {
				System.out.println("Case #"+tt+": 0");
				continue;
			}
			Edge edges[] = new Edge[m];
			for (int i=0,u,v,w; i<m; i++) {
				u = in.nextInt(); v = in.nextInt(); w = in.nextInt();
				edges[i] = new Edge(u,v,-w);
			}
			System.out.println("Case #"+tt+": "+costoMST(edges, n+1));
		}
    }
	
	static int costoMST (Edge ejes[], int n) { 
		UnionFind uf = new UnionFind (n);
		int minimo = 100000000;
		
		Arrays.sort (ejes);
		for (Edge eje : ejes) {
			if (!uf.isSameSet (eje.i, eje.j)) {
				uf.union (eje.i, eje.j);
				minimo = Math.min(minimo, -eje.w);
				if (uf.count==1)
					break;
			}
		}
		return minimo;
	}
	
	static class UnionFind {
	    int parent[],count;
	    
	    public UnionFind (int n) {
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
