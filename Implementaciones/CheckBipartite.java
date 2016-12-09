import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public class CheckBipartite {
	// BFS
	// O(V+E)
	// verificar que los vertices puedan pintarse de dos colores sin que haya un eje entre dos colores iguales
	
	static boolean esBipartito (HashMap<Integer, ArrayList<Integer>> lisAdy, int n) {
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