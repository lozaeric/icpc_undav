import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Grafo{
	static HashMap<Integer, ArrayList<Integer>> listaAdy = new HashMap<Integer, ArrayList<Integer>> () ;
	static int n;
	
	public static void main (String[] args) {
		int inicio = 0;
		// Grafo No Dirigido o Dirigido
		// n -> cantidad de vertices
		// lisAdy -> lista de adyacencia
		// inicio -> vertice
		dfs(inicio);
		bfs(inicio);
	}
	
	public static void dfs (int v) {
		ArrayDeque <Integer> pila = new ArrayDeque <Integer> ();
		int actual;
		boolean visitados[] = new boolean[n];
		
		pila.add (v);
		visitados[v] = true;
		do  {
			actual = pila.pop ();
			System.out.println (actual);
			for (int vec : listaAdy.get (actual)) {
				if (!visitados[vec]) {
					pila.push (vec);
					visitados[vec] = true;
				}
			}
			
		} while (!pila.isEmpty ());
		
	}
	
	public static void bfs (int v) {
		ArrayDeque <Integer> cola = new ArrayDeque <Integer> ();
		int actual, distancias[] = new int [n];
		boolean visitados[] = new boolean[n];
		
		cola.add (v);
		visitados[v] = true;
		distancias[v] = 0;
		do  {
			actual = cola.remove();
			System.out.println (actual);
			for (int vec : listaAdy.get (actual)) {
				if (!visitados[vec]) {
					cola.add (vec);
					visitados[vec] = true;
					distancias[vec] = distancias[actual]+1;
				}
			}
			
		} while (!cola.isEmpty ());
		System.out.println (Arrays.toString (distancias));
	}
}
