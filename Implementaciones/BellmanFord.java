import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class BellmanFord {
	static HashMap<Integer, ArrayList<Par>> lisAdy = new HashMap<Integer, ArrayList<Par>> ();
	static int n;
	
	public static void main (String[] args) {
		int inicio = 0;
		// Grafo No Dirigido o Dirigido
		// n -> cantidad de vertices
		// lisAdy -> lista de adyacencia
		// inicio -> vertice
		bellman_ford (inicio);
	}
	
	static void bellman_ford (int inicio) {
		int distancias[] = new int[n];
		boolean modified = true;
		
		Arrays.fill (distancias, 1000000);
		distancias[inicio] = 0;
	   for (int i = 0; i<n-1 && modified; i++)  {
	   	modified = false;
		   for (int u = 0; u<n; u++)  {
			   for (Par vec : lisAdy.get (u)) {
			   	if (distancias[u]+vec.d<distancias[vec.v]) {
			   		distancias[vec.v] = distancias[u]+vec.d;
			   		modified = true;
			   	}
			   }
		   }
	   }
	   boolean negCycle = false;
	   for (int u = 0; u<n && !negCycle; u++)  
		   for (Par vec : lisAdy.get (u)) 
		   	if (distancias[vec.v]>distancias[u]+vec.d)
		   		negCycle = true;
	   
	   if (negCycle)
	   	System.out.println ("Hay ciclo negativo");
	   else
	   	System.out.println (Arrays.toString (distancias));
	}
	
	static class Par {
		public int d, v;
		
		public Par (int v, int d) {
			this.d = d;
			this.v = v;
		}
	}
}
