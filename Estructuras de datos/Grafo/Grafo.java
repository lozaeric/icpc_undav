import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;


public class Grafo{
	private static HashMap<Integer, ArrayList<Integer>> listaAdy = new HashMap<Integer, ArrayList<Integer>> () ;
	private static BitSet visitados = new BitSet(5);
	private static int distancias[] = new int [5];
	
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
		
		dfs(1);
		System.out.println ();
		visitados.clear ();
		bfs(1);
	}
	
	public static void dfs (int v) {
		Stack <Integer> pila = new Stack <Integer> ();
		Integer actual;
		
		pila.add (v);
		visitados.set (v);
		do  {
			actual = pila.pop ();
			System.out.println (actual);
			for (int n : listaAdy.get (actual)) {
				if (!visitados.get (n)) {
					pila.push (n);
					visitados.set (n);
				}
			}
			
		} while (!pila.isEmpty ());
		
	}
	
	public static void bfs (int v) {
		ArrayDeque <Integer> cola = new ArrayDeque <Integer> ();
		Integer actual;
		
		cola.add (v);
		visitados.set (v);
		distancias[v] = 0;
		do  {
			actual = cola.remove ();
			System.out.println (actual);
			for (int n : listaAdy.get (actual)) {
				if (!visitados.get (n)) {
					cola.add (n);
					distancias[n] = distancias[actual]+1;
					visitados.set (n);
				}
			}
			
		} while (!cola.isEmpty ());
		System.out.println (Arrays.toString (distancias));
	}
}
