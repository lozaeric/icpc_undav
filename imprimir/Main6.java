import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;


class Main6 {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int nodos,conn,s,t,e1,e2,c,j=1;
		Grafo g;
		
		while (in.hasNext ()) {
			nodos=in.nextInt ();
			if(nodos==0)
				return;
			s = in.nextInt ()-1;
			t = in.nextInt ()-1;
			g = new Grafo (nodos,s,t);
			conn = in.nextInt ();
			for(int i=0; i<conn; i++) {
				e1 = in.nextInt ()-1; 
				e2 = in.nextInt ()-1; 
				c = in.nextInt ();
				g.setEdge (e1,e2,c);
			}
			System.out.println ("Network "+(j++));
			System.out.println ("The bandwidth is "+g.maxFlow ()+".");
			System.out.println ();
		}
	}
	static class Grafo {
		private int mf=0,f,s,p[],res[][],t;
		
		public Grafo (int n, int s, int t) {
			res = new int[n][n];
			p = new int[n];
			this.s = s;
			this.t = t;
		}
		
		public void setEdge (int i, int j, int c) {
			res[i][j] = res[j][i] = c;
		}
		
		public int maxFlow () {
			PriorityQueue<Integer> q = new PriorityQueue<Integer> ();
			long distancias[] = new long[res.length];
			Integer u;
			
			do {
				f = 0;
				Arrays.fill (distancias,1000000);
				Arrays.fill (p, -1);
				q.offer (s);
				distancias[s] = 0;
				while (!q.isEmpty ()) {
					u = q.poll ();
					if (u==t)
						break;
					for (int v=0; v<res.length; v++) {
						if(res[u][v]>0 && distancias[v]==1000000) {
							distancias[v] = distancias[u]+1;
							q.offer (v);
							p[v] = u;
						}
					}
				}
				augment(t,1000000);
				if (f==0)
					break;
				mf += f;
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
			}
		}
	}
}
