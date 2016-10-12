import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class TarjanScc {
	static HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<Integer, ArrayList<Integer>> () ;
	static HashMap<String, Integer> persons = new HashMap<String, Integer> () ;
	static int n,e;
	static int dfs_low[],dfs_num[],dfsCount,scc;
	static boolean visitados[];
	static ArrayDeque<Integer> pila = new ArrayDeque<Integer>();
	
	public static void main (String[] args) {
		Scanner in = new Scanner (System.in);
		
		while (in.hasNext()) {
			n = in.nextInt();
			e = in.nextInt();
			if (n==0 && e==0)
				return;
			scc = dfsCount = 0;
			dfs_low = new int [n];
			dfs_num = new int [n];
			visitados = new boolean[n];
			in.nextLine();
			for (int i=0; i<n; i++) {
				lisAdy.put(i, new ArrayList<Integer> ());
				persons.put(in.nextLine(), i);
			}
			for (int i=0,a=0,b=0; i<e; i++) {
				String aa = in.nextLine(), bb = in.nextLine();
				a = persons.get(aa);
				b = persons.get(bb);
				lisAdy.get(a).add(b);
			}
			for (int i=0; i<n; i++) {
				if (dfs_num[i]==0)
					tarjanSCC(i);
			}
			System.out.println (scc);
			pila.clear();
			lisAdy.clear();
			persons.clear();
		}
	}
	
	static void tarjanSCC (int u) {
		dfs_low[u] = dfs_num[u] = ++dfsCount;
		
		pila.add(u);
		visitados[u] = true;
		for (int vec : lisAdy.get(u)) {
			if (dfs_num[vec]==0)
				tarjanSCC(vec);
			if (visitados[vec])
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[vec]);
		}
		if (dfs_low[u]==dfs_num[u]) {
			scc++;
			while (true) {
				int v = pila.removeLast();
				visitados[v] = false;
				//System.out.print(v+" ");
				if (u==v) 
					break;
			}
			//System.out.println ();
		}
	}
}