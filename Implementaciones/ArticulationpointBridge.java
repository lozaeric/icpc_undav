import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class ArticulationpointBridge {
	static HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<Integer, ArrayList<Integer>> () ;
	static int n,e;
	static int dfsRoot, bridges, dfs_low[], dfs_num[], dfs_parent[], dfsCount, rootChildren;
	static boolean isArticulation[];
	
	public static void main (String[] args) {
		Scanner in = new Scanner (System.in);
		String numbers[];
		
		while (in.hasNext()) {
			n = in.nextInt();
			if (n==0)
				return;
			in.nextLine();
			dfs_low = new int[n];
			dfs_num = new int[n];
			dfs_parent = new int[n];
			isArticulation = new boolean[n];
			dfsCount = bridges = 0;
			for (int i=0; i<n; i++)
				lisAdy.put(i, new ArrayList<Integer> ());
			for (int i=0, o; i<=n; i++) {
				numbers = in.nextLine().split(" ");
				o = Integer.parseInt(numbers[0])-1;
				if (o==-1)
					break;
				for (int j=1,b; j<numbers.length; j++) {
					b = Integer.parseInt(numbers[j])-1;
					lisAdy.get(o).add (b);
					lisAdy.get(b).add (o);
				}
			}
			for (int i=0; i<n; i++) {
				if (dfs_num[i]==0) {
					dfsRoot = i;
					rootChildren = 0;
					searchAPyB (i);
					isArticulation[i] = rootChildren>1;
				}
			}
			int res = 0;
			for (boolean b : isArticulation) {
				if (b)
					res++;
			}
			System.out.println (res);
			lisAdy.clear();
		}
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