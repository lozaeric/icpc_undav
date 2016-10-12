import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CheckBipartite {
	static HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<Integer, ArrayList<Integer>> () ;
	static int n,e;
	
	public static void main (String[] args) {
		Scanner in = new Scanner (System.in);
		while (in.hasNext()) {
			n = in.nextInt();
			if (n==0)
				return;
			for (int i=0; i<n; i++)
				lisAdy.put(i, new ArrayList<Integer> ());
			while (in.hasNext()) {
				int a = in.nextInt()-1, b = in.nextInt()-1;
				if (a==-1 && b==-1)
					break;
				lisAdy.get(a).add (b);
				lisAdy.get(b).add (a);
			}
			System.out.println (esBipartito ());
			lisAdy.clear();
		}
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
		return "YES";
	}
}