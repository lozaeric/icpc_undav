import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Uva {
	public static void main (String[] args) {
		FastReader in = new FastReader ();
		String p[];
		Trie trie;
		DecimalFormat df = new DecimalFormat("0.00");
		
		while (true) {
			Integer n = in.nextInt ();
			if (n==null)
				break;
			p = new String[n];
			for (int i=0; i<n; i++) 
				p[i] = in.next ();
			trie = new Trie(p);
			System.out.println (df.format (trie.dfs ()/(n+0.0d)));
		}
	}
	
	static class Trie {
		HashMap<Integer, ArrayList<Edge>>  hijos = new HashMap<Integer, ArrayList<Edge>> ();
		ArrayList<Boolean> terminables;
		int global_id = -1;
		ArrayList<Integer> visitas;
		
		public Trie (String palabras[]) {
			terminables = new ArrayList<Boolean> ();
			visitas = new ArrayList<Integer> ();
			crearNodo ();
			for (String s : palabras) 
				add (s.toCharArray ());
		}
		
		int dfs () {
			ArrayDeque <Integer> pila = new ArrayDeque <Integer> ();
			int actual,changes=0,vis,vis2;
			boolean visitados[] = new boolean[visitas.size ()];
			
			pila.add (0);
			visitados[0] = true;
			do  {
				actual = pila.pop ();
				vis = visitas.get (actual);
				for (Edge vec : hijos.get (actual)) {
					if (!visitados[vec.id]) {
						vis2 = visitas.get (vec.id);
						if (vis!=vis2) 
							changes+=vis2;
						pila.push (vec.id);
						visitados[vec.id] = true;
					}
				}
				
			} while (!pila.isEmpty ());
			return changes;
		}
		
		void add (char[] palabra) {
			int v[] = last (palabra);
			if (v[1]==palabra.length) {
				terminables.set (v[0], true);
				return;
			}
			int nuevo=0;
			for (int i=v[1],n=v[0]; i<palabra.length; i++) {
				nuevo = crearNodo ();
				hijos.get (n).add (new Edge (nuevo, palabra[i]));
				n = nuevo;
			}
			terminables.set (nuevo, true);
		}
		
		private int crearNodo () {
			++global_id;
			hijos.put (global_id, new ArrayList<Edge> (30));
			terminables.add (false);
			if (global_id==0)
				visitas.add (0);
			else
				visitas.add (1);
			return global_id;
		}
		int[] last (char palabra[]) {
			int id=0,index=0;
			boolean sigue = true;
			
			for (int i=0; i<palabra.length && sigue; i++) {
				sigue = false;
				for (Edge e : hijos.get (id)) {
					if (e.c==palabra[i])  {
						sigue = true;
						index = i+1;
						id = e.id;
						break;
					}
				}
				if (sigue)
					visitas.set (id, visitas.get (id)+1);
			}
			return new int[] {id,index};			
		}
		
		boolean find (char palabra[]) {
			return terminables.get (last(palabra)[0]);
		}
		boolean startWith (char palabra[]) {
			return last(palabra)[1]==palabra.length;
		}		
		
	}
	
	static class Edge {
		int id;
		char c;
		
		public Edge (int id, char c) {
			this.id = id; this.c = c;
		}
		
		public String toString () {
			return id+"-"+c;
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
