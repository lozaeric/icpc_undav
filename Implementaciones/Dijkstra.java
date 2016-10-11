import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Dijkstra {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
      
		int distancia[] = new int [5], inicio = 2, 
		    matrizAdy[][] = {
			 {0,0,0,0,1},
			 {0,0,0,3,6},
			 {6,2,0,7,0},
			 {0,0,0,0,5},
			 {0,0,0,0,0}
		};
      PriorityQueue<Par> pq = new PriorityQueue<Par>();
      Par actual;
      
      Arrays.fill (distancia, 1000000);
      pq.offer (new Par(0,inicio));
      distancia[inicio] = 0;
      while (!pq.isEmpty ()) {
      	actual = pq.remove ();
      	if (actual.d>distancia[actual.v])
      		continue;
      	for (int j=0; j<matrizAdy[actual.v].length; j++) {
      		if (matrizAdy[actual.v][j]!=0) {
      			if (distancia[actual.v]+matrizAdy[actual.v][j]<distancia[j]) {
      				distancia[j] = distancia[actual.v]+matrizAdy[actual.v][j];
      				pq.offer (new Par(distancia[j], j));
      			}
      		}
      	}
      }
      System.out.println (Arrays.toString (distancia));
      
	}
	
	public static class Par implements Comparable<Par> {
		public int d, v;
		
		public Par (int d, int v) {
			this.d = d;
			this.v = v;
		}
		
		public String toString () {
			return "v=>"+d;
		}

		public int compareTo (Par o) {
			if (d==o.d)
				return 0;
	      return d<o.d? -1:1;
      }
	}
}