import java.util.ArrayList;
import java.util.HashMap;


public class TrieTest {
	public static void main (String[] args) {
		String p[] = new String[] {"hello","hallo","abc","good","goodie"};
		Trie trie = new Trie (p);
		System.out.println (trie.find ("hell".toCharArray ()));
		System.out.println (trie.startWith ("hell".toCharArray ()));
		System.out.println (trie.find ("hella".toCharArray ()));
		System.out.println (trie.find ("abc".toCharArray ()));	
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
			int res[] = last (palabra);
			if (res[1]==palabra.length) {
				terminables.set (res[0], true);
				return;
			}
			int nuevo=0;
			for (int i=res[1],id=res[0]; i<palabra.length; i++) {
				nuevo = crearNodo ();
				hijos.get (id).add (new Edge (nuevo, palabra[i]));
				id = nuevo;
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
			int res[] = last(palabra);
			return res[1]==palabra.length && terminables.get (res[0]);
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
	}
}
