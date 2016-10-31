
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CountPaths {
	static HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<Integer, ArrayList<Integer>> () ;
	static int n,e, paths[];
	static ArrayDeque<Integer> cola = new ArrayDeque<Integer> ();
	static boolean visitados[];
	
	public static void main (String[] args) {
		int  inicio;
		
		n = 5;
		e = 6;
		inicio = 0;
		paths = new int[n];
		visitados = new boolean[n];
		for (int i=0; i<n; i++) 
			lisAdy.put(i, new ArrayList<Integer> ());
		lisAdy.get(0).add(1);
		lisAdy.get(1).add(2);
		lisAdy.get(2).add(3);
		lisAdy.get(1).add(4);
		lisAdy.get(2).add(4);
		lisAdy.get(3).add(4);
		toposort(inicio);
		paths[inicio] = 1;
		for (int i : cola) {
			for (int v : lisAdy.get(i)) 
				paths[v] += paths[i];
		}
		System.out.println ("inicio: "+inicio);
		System.out.println (Arrays.toString(paths));
	}
	


	static void toposort (int u) {
		visitados[u] = true;
		for (int vec : lisAdy.get(u)) {
			if (!visitados[vec])
				toposort(vec);
		}
		cola.addFirst (u);
	}
}