import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CheckBipartite {
	static HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<Integer, ArrayList<Integer>> () ;
	static int n,e;
	
	public static void main (String[] args) {
		n = 5;
		for (int i=0; i<n; i++)
			lisAdy.put(i, new ArrayList<Integer> ());
		lisAdy.get(0).add (1);
		lisAdy.get(1).add (0);
		lisAdy.get(1).add (2);
		lisAdy.get(2).add (1);
		lisAdy.get(2).add (3);
		lisAdy.get(3).add (2);
		lisAdy.get(3).add (4);
		lisAdy.get(4).add (3);
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