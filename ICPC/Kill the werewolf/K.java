import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class K {
	public static void main(String args[]) {
		Scanner in = new Scanner (System.in);
		int n = in.nextInt();
		HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<Integer, ArrayList<Integer>>();
		
		for (int i=1; i<=n; i++) {
			lisAdy.put(i, new ArrayList<Integer>());
			lisAdy.get(i).add(in.nextInt());
			lisAdy.get(i).add(in.nextInt());
		}
		int ganan = 0;
		for (int lobo=1; lobo<=n; lobo++) {
			int votos = 0;
			boolean votaron[] = new boolean[n+1];
			for (int j=1; j<=n; j++) {
				for (int v : lisAdy.get(j)) {
					if (v==lobo) {
						votos++;
						votaron[j] = true;
						break;
					}
				}
			}
			votaron[lobo] = true;
			int restantes = n-votos-1, s = 0, t = n*2+1;
			MaxFlow mf = new MaxFlow(t+1, 0, t);
			for (int j=1; j<=n; j++) {
				if (!votaron[j]) {
					mf.addEdge(s, j, 1);
					for (int v : lisAdy.get(j)) 
						mf.addEdge(j, v+n, 1);
				}
				if (lisAdy.get(lobo).get(0)==j || lisAdy.get(lobo).get(1)==j) {
					if (votos>2)
						mf.addEdge(j+n, t, votos-2);
				}
				else if (votos>1)
					mf.addEdge(j+n, t, votos-1);
			}
			if (mf.maxFlow()<restantes)
				ganan++;
		}
		System.out.println(ganan);
	}
	
	static class MaxFlow {
		int INF = 1000000;
		int f,s,p[],res[][],t;
		HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<> ();
		
		public MaxFlow (int n, int s, int t) { 
			res = new int[n][n];
			p = new int[n];
			for (int i=0; i<n; i++)
				lisAdy.put(i, new ArrayList<Integer> ());
			this.s = s;
			this.t = t;
		}
		
		
		void addEdge (int i, int j, int c) {
			res[i][j] = c;
			lisAdy.get(i).add(j);
		}
		
		int maxFlow () {
			ArrayDeque<Integer> q  = new ArrayDeque<> ();
			boolean visitados[];
			int u, mf = 0;
			
			while (true) {
				f = 0;
				visitados = new boolean[res.length];
				Arrays.fill (p, -1);
				q.add (s);
				visitados[s] = true;
				while (!q.isEmpty ()) {
					u = q.remove ();
					if (u==t)
						break;
					for (int v : lisAdy.get(u)) {
						if(res[u][v]>0 && !visitados[v]) {
							visitados[v] = true;
							q.add (v);
							p[v] = u;
						}
					}
				}
				augment(t,INF);
				if (f==0)
					break;
				mf += f;
				q.clear();
			} 
			return mf;
		}
		
		private void augment (int v, int minEdge) {
			if (v==s) {
				f = minEdge;
				return;
			}
			else if (p[v]!=-1) { 
				augment(p[v],Math.min (minEdge, res[p[v]][v]));
				res[p[v]][v] -= f;
				res[v][p[v]] += f;
				lisAdy.get(v).add(p[v]);
			}
		}
	}
}
