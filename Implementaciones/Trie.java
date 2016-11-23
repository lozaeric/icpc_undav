import java.util.ArrayList;
import java.util.HashMap;


public class Trie {
	HashMap<Integer, int[]>  hijos = new HashMap<Integer, int[]> ();
	ArrayList<Boolean> terminables;
	int size = 0; //incluye nodo vacio inicial
	
	// Trie
	// agregar O(n) , buscar O(m)
	// genera un diccionario y busca eficientemente si existe una palabra o si es prefijo
	
	public Trie (String palabras[]) {
		terminables = new ArrayList<Boolean> ();
		crearNodo ();
		for (String s : palabras) 
			add (s.toCharArray ());
	}
	
	void add (char[] palabra) {
		int res[] = last (palabra), nuevo=0;
		if (res[1]==palabra.length) {
			terminables.set (res[0], true);
			return;
		}
		for (int i=res[1],id=res[0]; i<palabra.length; i++) {
			nuevo = crearNodo ();
			hijos.get (id)[palabra[i]-'a'] = nuevo;
			id = nuevo;
		}
		terminables.set (nuevo, true);
	}
	
	private int crearNodo () {
		hijos.put (size, new int[30]);
		terminables.add (false);
		return size++;
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
