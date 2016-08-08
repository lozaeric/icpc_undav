import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class MainSimplificado {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int n=in.nextInt(), friends, aux=0,id1,id2;
		String amigo1, amigo2;
		HashMap<String, Integer> todos = new HashMap<String, Integer> ();
		DisjointSet set;
		
		for (int i=0; i<n; i++) {
			friends = in.nextInt ();
			set = new DisjointSet (100000);
			for (int j=0; j<friends; j++) {
				amigo1 = in.next ();
				amigo2 = in.next ();
				if (!todos.containsKey (amigo1)) {
					id1 = aux;
					todos.put (amigo1, aux++);
				}
				else
					id1 = todos.get (amigo1);
				if (!todos.containsKey (amigo2)) {
					id2 = aux;
					todos.put (amigo2, aux++);
				}
				else
					id2 = todos.get (amigo2);
				set.union (id1,id2);
				System.out.println (set.getSize (id1));
			}
			todos.clear ();
		}
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
}