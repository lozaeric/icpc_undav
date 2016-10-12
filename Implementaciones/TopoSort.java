import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TopoSort {
	static HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<Integer, ArrayList<Integer>> () ;
	static int n,e;
	static boolean visitados[];
	static ArrayDeque<Integer> cola = new ArrayDeque<Integer> ();
	
	public static void main (String[] args) {
		Scanner in = new Scanner (System.in);
		ArrayList<Integer> ordenado = new ArrayList<Integer> ();
		ArrayList<Integer> esRoot = new ArrayList<Integer> ();
		
		while (true) {
			n = in.nextInt();
			e = in.nextInt();
			
			if (n==0 && e==0)
				return;
			visitados = new boolean[n];
			for (int i=0; i<n; i++) {
				lisAdy.put(i, new ArrayList<Integer> ());
				esRoot.add(i);
			}
			for (int i=0,a,b; i<e; i++) {
				a = in.nextInt()-1;
				b = in.nextInt()-1;
				lisAdy.get(a).add(b);
				esRoot.remove(new Integer(b));
			}
			for (int i : esRoot) {
				if (!visitados[i]) {
					toposort(i);
					ordenado.addAll(cola);
					cola.clear();
				}
			}
			System.out.print (ordenado.get(0)+1);
			for (int i=1; i<n; i++) 
				System.out.print (" "+(ordenado.get(i)+1));
			System.out.println ();
			lisAdy.clear();
			ordenado.clear();
			esRoot.clear ();
		}
	}
	

	
	static void toposort (int u) {
		visitados[u] =true;
		for (int vec : lisAdy.get(u)) {
			if (!visitados[vec])
				toposort(vec);
		}
		cola.addFirst (u);
	}
}