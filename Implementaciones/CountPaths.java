
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CountPaths {
	static HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<Integer, ArrayList<Integer>> () ;
	static int n, paths[];
	static ArrayDeque<Integer> cola = new ArrayDeque<Integer> ();
	static boolean visitados[];
	
	public static void main (String[] args) {
		int inicio = 0;
		// Grafo Dirigido Aciclico (DAG)
		// n -> cantidad de vertices
		// lisAdy -> lista de adyacencia
		// inicio -> vertice
		ejecutar (inicio);
	}
	
	static void ejecutar (int inicio) {
		paths = new int[n];
		visitados = new boolean[n];
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