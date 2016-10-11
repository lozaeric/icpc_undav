import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class MaxFlow {
	public static void main (String[] args) {
		Grafo g = new Grafo(5,0,1);
		
		g.setEdge(0, 2, 100);
		g.setEdge(0, 3, 50);
		//g.setEdge(2, 3, 50);
		g.setEdge(2, 4, 5);
		g.setEdge(3, 4, 100);
		g.setEdge(4, 1, 125);
		g.setEdge(2, 1, 5);
		
		System.out.println (g.maxFlow ());
	}

	static class Grafo {
		int mf,f,s,p[],res[][],t;
		HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<Integer, ArrayList<Integer>> ();
		
		public Grafo (int n, int s, int t) {
			res = new int[n][n];
			p = new int[n];
			this.s = s;
			this.t = t;
			for (int i=0; i<n; i++)
				lisAdy.put(i, new ArrayList<Integer> ());
		}
		
		public void setEdge (int i, int j, int c) {
			res[i][j] += c;
			//res[j][i] += c;
			if (!lisAdy.get(i).contains(j))
				lisAdy.get(i).add(j);
			//if (!lisAdy.get(j).contains(i))
				//lisAdy.get(j).add(i);
		}
		
		public int maxFlow () {
			PriorityQueue<Integer> q = new PriorityQueue<Integer> ();
			boolean visitados[];
			int u;
			
			do {
				f = 0;
				visitados = new boolean [res.length];
				Arrays.fill (p, -1);
				q.add (s);
				visitados[s] = true;
				while (!q.isEmpty ()) {
					u = q.remove ();
					if (u==t)
						break;
					for (int vec : lisAdy.get(u)) {
						if(res[u][vec]>0 && !visitados[vec]) {
							visitados[vec] = true;
							q.add (vec);
							p[vec] = u;
						}
					}
				}
				augment(t,1000000);
				if (f==0)
					break;
				mf += f;
				q.clear();
			} while (true);
			return mf;
		}
		
		public void augment (int v, int minEdge) {
			if (v==s) {
				f = minEdge;
				return;
			}
			else if (p[v]!=-1) {
				augment(p[v],Math.min (minEdge, res[p[v]][v]));
				res[p[v]][v] -= f;
				res[v][p[v]] += f;
				if (!lisAdy.get(v).contains(p[v]))
					lisAdy.get(v).add(p[v]);
				//if (!lisAdy.get(p[v]).contains(v))
					//lisAdy.get(p[v]).add(v);
			}
		}
	}
}