import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;


public class Grafo{
	private static HashMap<Integer, ArrayList<Integer>> listaAdy = new HashMap<Integer, ArrayList<Integer>> () ;
	private static int n = 5;
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		
		ArrayList<Integer> ly = new ArrayList<Integer>();
		ly.add (1);
		listaAdy.put (0, ly);
		ly = new ArrayList<Integer>();
		ly.add (0);
		ly.add (2);
		ly.add (3);
		listaAdy.put (1, ly);
		ly = new ArrayList<Integer>();
		ly.add (1);
		ly.add (3);
		listaAdy.put (2, ly);
		ly = new ArrayList<Integer>();
		ly.add (4);
		listaAdy.put (3, ly);
		ly = new ArrayList<Integer>();
		listaAdy.put (4, ly);
		
		dfs(0);
		System.out.println ();
		bfs(0);
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
