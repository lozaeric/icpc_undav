import java.util.ArrayList;
import java.util.HashMap;

public class ArticulationpointBridge {
	static HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<Integer, ArrayList<Integer>> () ;
	static int n, dfsRoot, bridges, dfsCount, rootChildren;
	static int dfs_low[], dfs_num[], dfs_parent[];
	static boolean isArticulation[];
	
	public static void main (String[] args) {
		// Grafo No Dirigido
		// n -> cantidad de vertices
		// lisAdy -> lista de adyacencia
		ejecutar ();
	}
	
	static void ejecutar () {
		dfs_low = new int[n];
		dfs_num = new int[n];
		dfs_parent = new int[n];
		isArticulation = new boolean[n];
		dfsCount = bridges = 0;
		for (int i=0; i<n; i++) {
			if (dfs_num[i]==0) {
				dfsRoot = i;
				rootChildren = 0;
				searchAPyB (i);
				isArticulation[i] = rootChildren>1;
			}
		}
		for (int i=0; i<n; i++) {
			if (isArticulation[i])
				System.out.println (i+" is an articulation point");
		}
		System.out.println ("There is "+bridges+" bridges");
	}
	
	static void searchAPyB (int u) {
		dfs_low[u] = dfs_num[u] = ++dfsCount;
		for (int vec : lisAdy.get(u)) {
			if (dfs_num[vec]==0) {
				dfs_parent[vec] = u;
				if (u==dfsRoot)
					rootChildren++;
				searchAPyB (vec);
				if (dfs_low[vec]>=dfs_num[u])
					isArticulation[u] = true;
				if (dfs_low[vec]>dfs_num[u])
					bridges++;
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[vec]);
			}
			else if (vec!=dfs_parent[u])
				dfs_low[u] = Math.min(dfs_low[u], dfs_num[vec]);
		}
	}
}