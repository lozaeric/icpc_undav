import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class BellmanFord {
	static final int INF = 100000000;
	
	// Single Source Shortest Path
	// O (V E)
	// camino mas corto, detectar ciclos negativos
	
	static void bellman_ford (HashMap<Integer, ArrayList<Par>> lisAdy, int inicio, int n) {
		int distancias[] = new int[n];
		
		Arrays.fill (distancias, INF);
		distancias[inicio] = 0;
	   for (int i = 0; i<n-1; i++)  
	   	for (int u = 0; u<n; u++)  
			   for (Par vec : lisAdy.get (u)) 
			   	distancias[vec.v] = Math.min (distancias[u]+vec.w, distancias[vec.v]);
	}
	
	static boolean hasNegCycle (HashMap<Integer, ArrayList<Par>> lisAdy, int distancias[], int n) {  // posterior a bellman_ford
	   for (int u = 0; u<n; u++)  
		   for (Par vec : lisAdy.get (u)) 
		   	if (distancias[vec.v]>distancias[u]+vec.w)
		   		return true;
	   return false;
	}
	
	static class Par {
		public int v,w;
		
		public Par (int v, int w) {
			this.v = v; this.w = w;
		}
	}
}
