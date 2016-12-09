import java.util.ArrayList;
import java.util.HashMap;

public class ArticulationpointBridge {
	static HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<> () ;
	static int dfsRoot, bridges, dfsCount, rootChildren;
	static int dfs_low[], dfs_num[], dfs_parent[];
	static boolean isArticulation[];
	
	// Encontrar ejes que sean puentes y vertices que sean puntos de articulacion 
	// O (V+E)
	
	static void searchAPyB (int n) {
		dfs_low = new int[n];
		dfs_num = new int[n];
		dfs_parent = new int[n];
		isArticulation = new boolean[n];
		dfsCount = bridges = 0;
		for (int i=0; i<n; i++) {
			if (dfs_num[i]==0) {
				dfsRoot = i;
				rootChildren = 0;
				dfs (i);
				isArticulation[i] = rootChildren>1;
			}
		}
	}
	
	static void dfs (int u) {
		dfs_low[u] = dfs_num[u] = ++dfsCount;
		for (int vec : lisAdy.get(u)) {
			if (dfs_num[vec]==0) {
				dfs_parent[vec] = u;
				if (u==dfsRoot)
					rootChildren++;
				dfs (vec);
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