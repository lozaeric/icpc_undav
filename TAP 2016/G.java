import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class G  {
	static HashMap<Integer, ArrayList<Par>> lisAdy = new HashMap<Integer, ArrayList<Par>> ();
	static int n, val[];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt ();
		val = new int[n];
		
		for (int i=0; i<n; i++)
			lisAdy.put (i, new ArrayList<Par> ());
		for (int i=0,a,b,p; i<n-1; i++) {
			a = in.nextInt ()-1; b = in.nextInt ()-1;
			p = in.nextInt ();
			lisAdy.get (a).add (new Par (b,p));
			lisAdy.get (b).add (new Par (a,p));
		}
		dfs ();
		Trie trie = new Trie (val);
		for (int i=0; i<n; i++)
			System.out.println (trie.find (val[i]));
	}
	
	static void dfs () {
		ArrayDeque <Integer> pila = new ArrayDeque <Integer> ();
		boolean visitados[] = new boolean[n];
		
		pila.add (0);
		visitados[0] = true;
		val[0] = 0;
		do  {
			int actual = pila.pop ();
			for (Par vec : lisAdy.get (actual)) {
				if (!visitados[vec.v]) {
					pila.push (vec.v);
					val[vec.v] = val[actual]^vec.p;
					visitados[vec.v] = true;
				}
			}
		} while (!pila.isEmpty ());
	}
	
	static class Trie {
		HashMap<Integer, int[]>  hijos = new HashMap<Integer, int[]> ();
		ArrayList<Boolean> terminables;
		int size = 0; //incluye nodo vacio inicial
		
		// Trie
		// agregar O(n) , buscar O(m)
		// genera un diccionario y busca eficientemente si existe una palabra o si es prefijo
		
		public Trie (int val[]) {
			terminables = new ArrayList<Boolean> ();
			crearNodo ();
			for (int v : val) 
				add (v);
		}
		
		void add (int num) {
			int res[] = last (num), nuevo=0;
			if (res[1]==-1) {
				terminables.set (res[0], true);
				return;
			}
			for (int i=res[1],id=res[0]; i>=0; i--) {
				nuevo = crearNodo ();
				hijos.get (id)[(num>>i)&1] = nuevo;
				id = nuevo;
			}
			terminables.set (nuevo, true);
		}
		
		private int crearNodo () {
			hijos.put (size, new int[2]);
			terminables.add (false);
			return size++;
		}
		
		int[] last (int num) {
			int id=0,index=30;
			
			for (int i=30,aux=0; i>=0; i--) {
				aux = hijos.get (id)[(num>>i)&1];
				if (aux!=0) {
					index = i-1;
					id = aux;					
				}
				else
					break;
			}
			return new int[] {id,index};			
		}
		
		int find (int v) {
			int id=0,val=v;
			
			for (int i=30,aux=0,b; i>=0; i--) {
				b = 0;
				if (((v>>i) & 1) == 0) {
					if (hijos.get (id)[1]!=0) {
						aux = hijos.get (id)[1];
						b = 1;
					}
					else 
						aux = hijos.get (id)[0];
				}
				else {
					if (hijos.get (id)[0]!=0)
						aux = hijos.get (id)[0];
					else {
						aux = hijos.get (id)[1];
						b = 1;
					}
				}
				val ^= b<<i;
				id = aux;
			}
			return val;	
		}
	}
	
	static class Par {
		int v,p;
		
		public Par (int v, int p) {
			this.v = v; this.p = p;
		}
	}
}
