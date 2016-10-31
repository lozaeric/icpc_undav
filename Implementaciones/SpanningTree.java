
import java.util.ArrayList;
import java.util.Collections;


public class SpanningTree {
	public static void main (String[] args) {
		int n = 5, minCosto = 0;
		ArrayList<Edge> edges = new ArrayList<Edge>();
		DisjointSet set = new DisjointSet(n);;
		
		edges.add(new Edge (1,3,3));
		edges.add(new Edge (1,2,2));
		edges.add(new Edge (2,0,6));
		edges.add(new Edge (2,3,7));
		edges.add(new Edge (1,4,6));
		edges.add(new Edge (3,4,5));
		edges.add(new Edge (0,4,1));
		
		Collections.sort (edges);
		for (Edge e : edges) {
			if (!set.isSameSet (e.i, e.j)) {
				minCosto += e.w;
				set.union (e.i, e.j);
			}
			if (set.count==1)
				break;
		}
        System.out.println (minCosto);
	}
	
	public static class DisjointSet {
	    int[] parent;
	    int count;
	    
	    public DisjointSet(int n)
	    {
	        parent = new int [n];
	        count = n;
	        for(int i = 0; i < n; i++)
	            parent[i] = i;
	    }

	    public int find(int i)
	    {
	        if (parent[i] != i)
	            parent[i] = find(parent[i]);
	        return parent[i];
	    }
	    
	    
	    public boolean isSameSet (int x, int y) {
	   	 return find(x)==find(y);
	    }

	    public void union(int x, int y)
	    {
	        int x_root = find(x), y_root = find(y);
	        if (x_root != y_root) {
	            parent[y_root] = x_root;
	            count--;
	        }
	    }
	}
	
	public  static class Edge implements Comparable<Edge> {
		public int i,j,w;

		public Edge (int i, int j, int w) {
			this.i = i;
			this.w = w;
			this.j = j;
		}
		
		public int compareTo (Edge o) {
	     return w-o.w;
      }
	}
}