import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class Main3 {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int n=in.nextInt(), friends, aux=0,a1,a2;
		String amigo1, amigo2;
		HashMap<String, Integer> todos = new HashMap<String, Integer> ();
		DisjointSet set;
		ArrayList<String> amigos = new ArrayList<String> ();
		
		
		for (int i=0; i<n; i++) {
			friends = in.nextInt ();
			for (int j=0; j<friends; j++) {
				amigo1 = in.next ();
				amigo2 = in.next ();
				amigos.add (amigo1);
				amigos.add (amigo2);
				if (!todos.containsKey (amigo1))
					todos.put (amigo1, aux++);
				if (!todos.containsKey (amigo2))
					todos.put (amigo2, aux++);
			}
			set = new DisjointSet (aux);
			for (int j=1; j<amigos.size (); j+=2) {
				a1 = todos.get (amigos.get (j-1));
				a2 = todos.get (amigos.get (j));
				set.union (a1,a2);
				System.out.println (set.getSize (a1));
			}
			todos.clear ();
			amigos.clear ();
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