class Grafo{
	HashMap<Integer, ArrayList<Integer>> lisAdy;
	
	// BFS, DFS
	// O (V+E)
	// recorrido grafo, camino mas corto en grafos no pesados
	// verificar que los vertices puedan pintarse de dos colores sin que haya un eje entre dos colores iguales
	
	void dfs (int v) {
		int n = lisAdy.keySet().size();
		ArrayDeque <Integer> pila = new ArrayDeque <> ();
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
	
	void bfs (int v) {
		int n = lisAdy.keySet().size();
		ArrayDeque <Integer> cola = new ArrayDeque <> ();
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
	
	boolean esBipartito () {
		int n = lisAdy.keySet().size();
		ArrayDeque <Integer> cola = new ArrayDeque <> ();
		int colores[] = new int[n];
		
		cola.add (0);
		colores[0] = 1;
		do  {
			int actual = cola.remove ();
			for (int vec : lisAdy.get (actual)) {
				if (colores[vec]==0) {
					cola.add (vec);
					colores[vec] = colores[actual]==1? 2:1;
				}
				else if (colores[vec]==colores[actual]) 
					return false;
			}
		} while (!cola.isEmpty ());
		return true;
	}
}
