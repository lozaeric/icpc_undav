import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Scanner;


public class sixdegrees {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int n=in.nextInt(), friends, aux,id1,id2;
		String amigo1, amigo2;
		HashMap<String, Integer> todos = new HashMap<String, Integer> ();
		Grafo grafo;
		
		for (int i=0; i<n; i++) {
			friends = in.nextInt ();
			grafo = new Grafo (3001);
			aux = 0;
			for (int j=0; j<friends; j++) {
				amigo1 = in.next ();
				amigo2 = in.next ();
				if (!todos.containsKey (amigo1)) {
					id1 = aux;
					todos.put (amigo1, aux++);
				}
				else
					id1 = todos.get (amigo1);
				if (!todos.containsKey (amigo2)) {
					id2 = aux;
					todos.put (amigo2, aux++);
				}
				else
					id2 = todos.get (amigo2);
				grafo.setEdge (id1,id2);

			}
			grafo.limitar (aux);
			if (grafo.puede ())
				System.out.println ("YES");
			else
				System.out.println ("NO");
			todos.clear ();
		}
	}
	

	public static class Grafo {
		private int matrizAdy[][],matrizCaminoMin[][];
		private int size, distancias[];
		private BitSet visitados;
		
		public Grafo (int n) {
			matrizAdy = new int[n][n];
		}
		
		public void limitar (int aux) {
			size = aux;
			distancias = new int [size];
			visitados = new BitSet(size);
      }

		public void mostrar () {
			for (int i=0; i<size; i++) {
				for (int j=0; j<size; j++) {
					System.out.print (matrizCaminoMin[i][j]+" ");
				}
				System.out.println ();
			}
		}
		
		public int[] bfs (int v) {
			ArrayDeque <Integer> cola = new ArrayDeque <Integer> ();
			int actual,max[]= {-1,-1,0};
			
			Arrays.fill (distancias, 0);
			visitados.clear ();
			cola.add (v);
			visitados.set (v);
			distancias[v] = 0;
			do  {
				actual = cola.remove ();
				for (int n=0; n<size; n++) {
					if (!visitados.get (n) && matrizAdy[actual][n]==1) {
						cola.add (n);
						distancias[n] = distancias[actual]+1;
						visitados.set (n);
						if (distancias[n]>max[1]) {
							max[1] = distancias[n];
							max[0] = n;
						}
						if(distancias[n]>6)
							max[2]++;
					}
				}
				
			} while (!cola.isEmpty ());
			return max;
		}
		
		public boolean puede () {
			int masLejano = bfs(0)[0];
			
			return bfs(masLejano)[2]<size/20d;
		}
		
		private void setEdge (int i, int j) {
			matrizAdy[i][j] = matrizAdy[j][i] = 1;
		}
	}
}