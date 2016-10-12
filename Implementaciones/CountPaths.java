
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CountPaths {
	static HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<Integer, ArrayList<Integer>> () ;
	static int n,e, paths[];
	static ArrayDeque<Integer> cola = new ArrayDeque<Integer> ();
	static boolean visitados[];
	
	public static void main (String[] args) {
		Scanner in = new Scanner (System.in);
		int  t = in.nextInt(), inicio, fin;
		
		while (t-- > 0) {
			n = in.nextInt();
			e = in.nextInt();
			inicio = in.nextInt()-1;
			fin = in.nextInt()-1;
			paths = new int[n];
			visitados = new boolean[n];
			for (int i=0; i<n; i++) 
				lisAdy.put(i, new ArrayList<Integer> ());
			for (int i=0,a,b; i<e; i++) {
				a = in.nextInt()-1;
				b = in.nextInt()-1;
				lisAdy.get(a).add(b);
			}
			toposort(inicio);
			paths[inicio] = 1;
			System.out.println (lisAdy);
			for (int i : cola) {
				for (int v : lisAdy.get(i)) 
					paths[v] += paths[i];
			}
			System.out.println (paths[fin]);
			lisAdy.clear();
			cola.clear();
		}
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