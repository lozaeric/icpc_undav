import java.util.ArrayList;
import java.util.HashMap;


public class TrieTest {
	public static void main (String[] args) {
		String p[] = new String[] {"hello","hallo","abc","good","goodie"};
		Trie trie = new Trie (p);
		System.out.println (trie.find ("hell".toCharArray ()));
		System.out.println (trie.startWith ("hell".toCharArray ()));
		System.out.println (trie.find ("hella".toCharArray ()));
		System.out.println (trie.find ("abcd".toCharArray ()));	
		System.out.println (trie.startWith ("ab".toCharArray ()));		
	}
	
	
	static class Trie {
		HashMap<Integer, int[]>  hijos = new HashMap<Integer, int[]> ();
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
				hijos.get (id)[palabra[i]-'a'] = nuevo;
				id = nuevo;
			}
			terminables.set (nuevo, true);
		}
		
		private int crearNodo () {
			++global_id;
			hijos.put (global_id, new int[30]);
			terminables.add (false);
			return global_id;
		}
		
		int[] last (char palabra[]) {
			int id=0,index=0;
			
			for (int i=0,aux=0; i<palabra.length; i++) {
				aux = hijos.get (id)[palabra[i]-'a'];
				if (aux!=0) {
					index = i+1;
					id = aux;					
				}
				else
					break;
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
}
