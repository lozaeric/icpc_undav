import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


class Mcbm {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final int  INF = 1<<29;
	
	public static void main (String[] args) {
		Grafo g = new Grafo (100,0,1);
		g.setEdge(0, 2, 70);
		g.setEdge(0, 3, 30);
		g.setEdge(1, 2, 25);
		g.setEdge(1, 3, 70);
		g.setEdge(2, 0, 70);
		g.setEdge(2, 3, 5);
		g.setEdge(2, 1, 25);
		g.setEdge(3, 0, 30);
		g.setEdge(3, 2, 5);
		g.setEdge(3, 1, 70);
		System.out.println (g.maxFlow());
		
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
		
		
		public void setEdge (int i, int j, int c) {
			res[i][j] = c;
			if (!lisAdy.get(i).contains (j))
				lisAdy.get(i).add(j);
		}
		
		public int maxFlow () {
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
		
		public void augment (int v, int minEdge) {
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