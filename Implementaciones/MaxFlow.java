
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


class MaxFlow {
	static final int  INF = 1<<29;
	
	public static void main (String[] args) {
		// Grafo Dirigido o No Dirigido
		int n=2,source=0,sink=1;
		Grafo g = new Grafo (n,source,sink); 
		
	}
	
	
	static class Grafo {
		int mf,f,s,p[],res[][],t;
		static HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<Integer, ArrayList<Integer>> ();
		
		public Grafo (int n, int s, int t) { 
			res = new int[n][n];
			p = new int[n];
			for (int i=0; i<n; i++)
				lisAdy.put(i, new ArrayList<Integer> ());
			this.s = s;
			this.t = t;
		}
		
		
		void setEdge (int i, int j, int c) {
			res[i][j] = c;
			lisAdy.get(i).add(j);
			//res[j][i] = c;   lisAdy.get(j).add(i);   // no dirigido 
		}
		
		int maxFlow () {
			ArrayDeque<Integer> q  = new ArrayDeque<Integer> ();
			boolean visitados[];
			int u;
			
			mf = 0;
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
				if (!lisAdy.get(v).contains (p[v]))
					lisAdy.get(v).add(p[v]);
			}
		}
	}
}