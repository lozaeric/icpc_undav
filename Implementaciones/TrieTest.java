import java.util.ArrayList;
import java.util.HashMap;


public class TrieTest {
	public static void main (String[] args) {
		String p[] = new String[] {"hello","hallo","abc","good","goodie"};
		Trie trie = new Trie (p);
		System.out.println (trie.find ("hell".toCharArray ()));
		System.out.println (trie.startWith ("hell".toCharArray ()));
		System.out.println (trie.find ("abc".toCharArray ()));
		System.out.println (trie.startWith ("abc".toCharArray ()));	
		System.out.println (trie.startWith ("ab".toCharArray ()));		
	}
	
	
	static class Trie {
		HashMap<Integer, ArrayList<Edge>>  hijos = new HashMap<Integer, ArrayList<Edge>> ();
		ArrayList<Boolean> terminables;
		int global_id = -1;
		
		public Trie (String palabras[]) {
			terminables = new ArrayList<Boolean> ();
			crearNodo ();
			for (String s : palabras) 
				add (s.toCharArray ());
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
}
