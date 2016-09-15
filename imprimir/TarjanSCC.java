import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;


public class TarjanSCC {
	static int[] dfs_num, dfs_low, visited; // global variables
	static ArrayDeque<Integer> S = new ArrayDeque<Integer> ();
	static int dfsNumberCounter = 0, numSCC = 0;
	static HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<Integer, ArrayList<Integer>> ();
	static final int UNVISITED = 0, VISITED = 1;
	
	static void tarjanSCC(int u) {
		int v;
		
		dfs_low[u] = dfs_num[u] = dfsNumberCounter++; // dfs_low[u] <= dfs_num[u]
		S.add (u); // stores u in a vector based on order of visitation
		visited[u] = VISITED;
		for (Integer j : lisAdy.get (u)) {
			if (dfs_num[j] == UNVISITED)
				tarjanSCC(j);
			if (visited[j] == VISITED) // condition for update
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[j]); }
			if (dfs_low[u] == dfs_num[u]) { // if this is a root (start) of an SCC
				System.out.println("SCC: "+(++numSCC)); // this part is done after recursion
			v = -1;
			while (u != v) {
				v = S.removeLast (); 
				visited[v] = UNVISITED;
				System.out.println(v);
			}
			System.out.println();
		} 
	}
	  
	public static void main (String s[]) {
		for (int i=0; i<8; i++)
			lisAdy.put (i, new ArrayList<Integer> ());
		
		dfs_num = new int[lisAdy.keySet ().size ()];
		dfs_low = new int[dfs_num.length];
		visited = new int[dfs_low.length];
		lisAdy.get (0).add (1);
		lisAdy.get (1).add (3);
		lisAdy.get (3).add (2);
		lisAdy.get (2).add (1);
		lisAdy.get (3).add (4);
		lisAdy.get (4).add (5);
		lisAdy.get (5).add (7);
		lisAdy.get (7).add (6);
		lisAdy.get (6).add (4);
		tarjanSCC(0);
	}
}