import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;


public class Grafo{
	// BFS, DFS
	// O (V+E)
	// recorrido grafo, camino mas corto en grafos no pesados
	
	static void dfs (HashMap<Integer, ArrayList<Integer>> listaAdy, int v, int n) {
		ArrayDeque <Integer> pila = new ArrayDeque <Integer> ();
		boolean visitados[] = new boolean[n];
		
		pila.add (v);
		visitados[v] = true;
		do  {
			int actual = pila.pop ();
			for (int vec : listaAdy.get (actual)) {
				if (!visitados[vec]) {
					pila.push (vec);
					visitados[vec] = true;
				}
			}
		} while (!pila.isEmpty ());
	}
	
	static void bfs (HashMap<Integer, ArrayList<Integer>> listaAdy, int v, int n) {
		ArrayDeque <Integer> cola = new ArrayDeque <Integer> ();
		int distancias[] = new int [n];
		boolean visitados[] = new boolean[n];
		
		cola.add (v);
		visitados[v] = true;
		distancias[v] = 0;
		do  {
			int actual = cola.remove();
			for (int vec : listaAdy.get (actual)) {
				if (!visitados[vec]) {
					cola.add (vec);
					visitados[vec] = true;
					distancias[vec] = distancias[actual]+1;
				}
			}
			
		} while (!cola.isEmpty ());
	}
}
