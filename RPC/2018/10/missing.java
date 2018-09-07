import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

class missing {
	static int INF = 100000000;
	static HashMap<Integer, ArrayList<Par>> lisAdy;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat(".00");
		
		while(in.hasNext()) {
			lisAdy = new HashMap<>();
			int v = in.nextInt(), e = in.nextInt();
			int s = in.nextInt(), y = in.nextInt();
			
			for (int i=0; i<v; i++)
				lisAdy.put(i, new ArrayList<Par>());
			for (int i=0; i<e; i++) {
				int a = in.nextInt()-1, b = in.nextInt()-1, w = in.nextInt();
				lisAdy.get(a).add(new Par(b,w));
				lisAdy.get(b).add(new Par(a,w));
			}
			int d = dijkstra(in.nextInt()-1, in.nextInt()-1);
			double res = d/(s+y+0.0);
			System.out.println(df.format(res));
		}
	}
	
	static int dijkstra (int inicio, int destino) {
		int n = lisAdy.keySet().size();
		PriorityQueue<Par> pq = new PriorityQueue<>();
		int distancia[] = new int[n]; 
	  
		Arrays.fill (distancia, INF);
		pq.add (new Par(inicio,0));
		distancia[inicio] = 0;
		while (!pq.isEmpty ()) {
			Par actual = pq.remove ();
			if (actual.w>distancia[actual.v] || actual.v == destino)
				continue;
			for (Par vec : lisAdy.get(actual.v)) {
				if (distancia[actual.v]+vec.w<distancia[vec.v]) {
					distancia[vec.v] = distancia[actual.v]+vec.w;
					pq.add (new Par(vec.v,distancia[vec.v]));
				}
			}
		}
		return distancia[destino];
	}
	
	static class Par implements Comparable<Par> {
		int w, v;
		
		public Par (int v, int w) {
			this.v = v; 
			this.w = w;
		}

		public int compareTo (Par o) {
	      return w-o.w;
      }
	}
}
