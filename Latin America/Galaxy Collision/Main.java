import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static HashMap<Integer, ArrayList<Integer>> listaAdy = new HashMap<Integer, ArrayList<Integer>> () ;
	static HashMap<Integer, TreeSet<Par>> puntos = new HashMap<Integer, TreeSet<Par>> () ;
	static Integer n;
	static boolean visitados[];
	
	
	public static void main (String[] args) {
		FastReader in = new FastReader ();
		TreeSet<Par> pp;
		
		while (true) {
			n = in.nextInt();
			if (n==null)
				return;
			visitados  = new boolean [n];
			for (int i=0; i<n; i++) 
				listaAdy.put(i, new ArrayList<Integer> ());
			for (int i=0,x,y; i<n; i++) {
				x = in.nextInt();
				y = in.nextInt();
				pp = puntos.get(x);
				if (pp==null) {
					pp = new TreeSet<Par>();
					pp.add(new Par (y,i));
					puntos.put(x, pp);
				}
				else
					pp.add(new Par (y,i));
			}
			for  (int x : puntos.keySet()) {
				for (Par s : puntos.get(x)) {
					for  (int xx=x; xx<=x+5; xx++) {
						if (!puntos.containsKey(xx))
							continue;
						for (Par p : puntos.get(xx).subSet(new Par(s.y-5,-1),true, new Par(s.y+5,-1),true)) {
							if (s.y==p.y && x==xx)
								continue;
							if (diffSubset (x,s.y,xx,p.y)) {
								listaAdy.get(s.v).add(p.v);
								listaAdy.get(p.v).add(s.v);
							}
						}
					}
				}
			}
			int suma = 0;
			for (int i=0; i<n; i++) {
				if (!visitados[i])
					suma += bfs(i);
			}
			System.out.println (suma);
			listaAdy.clear();
			puntos.clear();
		}
	}
	
	static boolean diffSubset (int x, int y, int x2, int y2) {
		return Math.hypot(x-x2, y-y2)<=5;
	}
	
	
	static int bfs (int v) {
		ArrayDeque <Integer> cola = new ArrayDeque <Integer> ();
		int actual, distancias[] = new int[n], set1=0,set2=0;
		
		cola.add (v);
		visitados[v] = true;
		distancias[v] = 0;
		set1++;
		do  {
			actual = cola.remove ();
			for (int vec : listaAdy.get (actual)) {
				if (!visitados[vec]) {
					cola.add (vec);
					distancias[vec] = distancias[actual]+1;
					if (distancias[vec]%2==0)
						set1++;
					else
						set2++;
					visitados[vec] = true;
				}
			}
			
		} while (!cola.isEmpty ());
		return Math.min(set1, set2);
	}
	
	static class Par implements Comparable<Par> {
		int y,v;
		public Par (int y, int v) {
			this.y = y; this.v = v;
		}
		@Override
		public int compareTo(Par p) {
			return y-p.y;
		}
	}
	
	static class FastReader {
	    BufferedReader br;
	    StringTokenizer st;

	    public FastReader() {
	        br = new BufferedReader(new InputStreamReader(System.in));
	    }

	    String next() {
	 	   String  line;
	        while (st == null || !st.hasMoreElements()) {
	           line = nextLine ();
	           if (line == null)
	              return null;
	           st = new StringTokenizer(line);
	        }
	        return st.nextToken();
	    }

	    Integer nextInt() {
	 	   String element = next ();
	        return element==null? null:Integer.parseInt(element);
	    }

	    Long nextLong() {
	 	   String element = next ();
	        return element==null? null:Long.parseLong(element);
	    }

	    Double nextDouble() {
	 	   String element = next ();
	        return element==null? null:Double.parseDouble(element);
	    }

	    String nextLine() {
	        String str = null;
	        try {
	            str = br.readLine();
	        }
	        catch (IOException e)
	        {}
	        return str;
	    }
	}
}