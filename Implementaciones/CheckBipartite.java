import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CheckBipartite {
	static HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<Integer, ArrayList<Integer>> () ;
	static int n;
	
	public static void main (String[] args) {
		// Grafo No Dirigido
		// n -> cantidad de vertices
		// lisAdy -> lista de adyacencia
		System.out.println (esBipartito ());
	}
	
	static String esBipartito () {
		ArrayDeque <Integer> cola = new ArrayDeque <Integer> ();
		int actual, colores[] = new int[n];
		
		cola.add (0);
		colores[0] = 1;
		do  {
			actual = cola.remove ();
			for (int vec : lisAdy.get (actual)) {
				if (colores[vec]==0) {
					cola.add (vec);
					colores[vec] = colores[actual]==1? 2:1;
				}
				else if (colores[vec]==colores[actual]) 
					return "NO";
			}
			
		} while (!cola.isEmpty ());
		System.out.println (Arrays.toString(colores));
		return "YES";
	}
}