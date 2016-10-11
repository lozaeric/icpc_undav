import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class SpanningTree {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt ();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		DisjointSet set = new DisjointSet(n);;
		double minCosto = 0;
		int puntos[][]=new int[n][2];
		
		for (int i=0; i<n; i++) {
			puntos[i][0] = (int) (in.nextDouble ()*10);
			puntos[i][1] = (int) (in.nextDouble ()*10);
			
			for (int j=i-1; j>=0; j--) {
				if (puntos[i][0]!=puntos[j][0] || puntos[i][1]!=puntos[j][1])
					edges.add (new Edge (i,j, Math.hypot (puntos[i][0]-puntos[j][0],puntos[i][1]-puntos[j][1])));
				else
					set.union (i, j);
			}
		}
		
		Collections.sort (edges);
		for (Edge e : edges) {
			if (!set.isSameSet (e.i, e.j)) {
				minCosto += e.w;
				set.union (e.i, e.j);
			}
		}
		DecimalFormat d = new DecimalFormat("0.00");
      System.out.println (d.format (minCosto/10d));
	}
	
	public static class DisjointSet {
	    public int[] parent, sizes;
	    
	    public DisjointSet(int n)
	    {
	        parent = new int [n];
	        sizes = new int [n];
	        Arrays.fill (sizes, 1);
	        for(int i = 0; i < n; i++)
	            parent[i] = i;
	    }

	    public int find(int i)
	    {
	        if (parent[i] != i)
	            parent[i] = find(parent[i]);
	        return parent[i];
	    }
	    
	    public int getSize (int i) {
	   	 return sizes[find(i)];
	    }
	    
	    public boolean isSameSet (int x, int y) {
	   	 return find(x)==find(y);
	    }

	    public void union(int x, int y)
	    {
	        int x_root = find(x), y_root = find(y);
	        if (x_root != y_root) {
	            parent[y_root] = x_root;
	            sizes[x_root] += sizes[y_root];
	            sizes[y_root] = 0;
	        }
	    }
	}
	
	public  static class Edge implements Comparable<Edge> {
		public int i,j;
		public double w;

		public Edge (int i, int j, double w) {
			this.i = i;
			this.w = w;
			this.j = j;
		}
		
		public int compareTo (Edge o) {
	      if(w>o.w)
	      	return 1;
	      else if (w<o.w)
	      	return -1;
	      return 0;
      }
		
		public String toString () {
			return "["+i+","+j+"]="+w;
		}
	}
}