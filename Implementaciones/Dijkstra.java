import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Dijkstra {
	static 	HashMap<Integer, ArrayList<Par>> lisAdy = new HashMap<Integer, ArrayList<Par>> ();
	static int n;
	
	public static void main (String[] args) {
		int inicio = 0;
		// Grafo Dirigido o No Dirigido
		// n -> cantidad de vertices
		// lisAdy -> lista de adyacencia
		// inicio -> vertice  
		dijkstra (inicio);
	}
	
	static void dijkstra (int inicio) {
	      PriorityQueue<Par> pq = new PriorityQueue<Par>();
	      Par actual;
	      int distancia[] = new int[n];
	      
	      Arrays.fill (distancia, 1000000);
	      pq.add (new Par(inicio,0));
	      distancia[inicio] = 0;
	      while (!pq.isEmpty ()) {
	      	actual = pq.remove ();
	      	if (actual.d>distancia[actual.v])
	      		continue;
	      	for (Par vec : lisAdy.get(actual.v)) {
      			if (distancia[actual.v]+vec.d<distancia[vec.v]) {
      				distancia[vec.v] = distancia[actual.v]+vec.d;
      				pq.add (new Par(vec.v,distancia[vec.v]));
      			}
	      	}
	      }
	      System.out.println (Arrays.toString (distancia));
	}
	
	static class Par implements Comparable<Par> {
		public int d, v;
		
		public Par (int v, int d) {
			this.d = d;
			this.v = v;
		}

		public int compareTo (Par o) {
	      return d-o.d;
      }
	}
}