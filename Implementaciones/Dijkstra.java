import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Dijkstra {
	static 	HashMap<Integer, ArrayList<Par>> lisAdy = new HashMap<Integer, ArrayList<Par>> ();
	static int n=5;
	
	public static void main (String[] args) {
		for (int i=0; i<n; i++) 
			lisAdy.put(i, new ArrayList<Par> ());
		lisAdy.get(0).add(new Par(2,6));
		lisAdy.get(2).add(new Par(0,6));
		lisAdy.get(0).add(new Par(4,1));
		lisAdy.get(4).add(new Par(0,1));
		lisAdy.get(2).add(new Par(3,7));
		lisAdy.get(3).add(new Par(2,7));
		lisAdy.get(1).add(new Par(2,2));
		lisAdy.get(2).add(new Par(1,2));
		lisAdy.get(1).add(new Par(3,3));
		lisAdy.get(3).add(new Par(1,3));
		lisAdy.get(1).add(new Par(4,6));
		lisAdy.get(4).add(new Par(1,6));
		lisAdy.get(3).add(new Par(4,5));
		lisAdy.get(4).add(new Par(3,5));    
		dijkstra (2);
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
	
	public static class Par implements Comparable<Par> {
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