
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class CountPaths {
	static HashMap<Integer, ArrayList<Integer>> lisAdy;
	static ArrayDeque<Integer> cola;
	static boolean visitados[];
	
	// Topological Sort
	// O(V+E)
	// ordenamiento tal que si V1  es anterior a V2 entonces hay un eje V1->V2
	// contar caminos distintos para llegar a un vertice en un DAG
	
	static void countPaths (int inicio, int n) {
		int paths[] = new int[n];
		visitados = new boolean[n];
		cola = new ArrayDeque<> ();
		
		toposort(inicio);
		paths[inicio] = 1;
		for (int i : cola) {
			for (int v : lisAdy.get(i)) 
				paths[v] += paths[i];
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