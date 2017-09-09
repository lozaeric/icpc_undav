import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Mainf {
	public static void main(String[] args) {
		FastReader in = new FastReader();
		SegmentTree st;
		TreeSet<Integer> todosX = new TreeSet<Integer> (), todosY = new TreeSet<Integer> ();;
		HashMap<Integer, ArrayList<Planta>> plantas = new HashMap<Integer, ArrayList<Planta>> ();
		HashMap<Integer, TreeSet<Integer>> vertices = new HashMap<Integer, TreeSet<Integer>> ();
		HashMap<Integer, Integer> cy = new HashMap<Integer, Integer> ();
		Integer p,v;
		int yy;
		
		while (true) {
			p = in.nextInt();
			if (p==null)
				return;
			v = in.nextInt();
			yy=1;
			for (int i=0,x,y; i<p; i++) {
				x = in.nextInt(); y = in.nextInt();
				if (!plantas.containsKey(x)) {
					ArrayList<Planta> list = new ArrayList<Planta> ();
					list.add(new Planta(y,i+1));
					plantas.put(x, list);
				}
				else
					plantas.get(x).add(new Planta(y,i+1));
				todosX.add(x);
				todosY.add(y);
			}
			for (int i=0,x,y; i<v; i++) {
				x = in.nextInt(); y = in.nextInt();
				if (!vertices.containsKey(x)) {
					TreeSet<Integer> tree = new TreeSet<Integer> ();
					tree.add(y);
					vertices.put(x, tree);
				}
				else
					vertices.get(x).add(y);
				todosX.add(x);
				todosY.add(y);
			}
			for (int y : todosY) {
				cy.put(y, yy);	
				yy+=2;
			}
			st = new SegmentTree (new int [yy]);
			long costoTotal = 0;
			for (int x : todosX){
				ArrayList<Planta> plantasX = plantas.get(x);
				TreeSet<Integer> verticesX = vertices.get(x);
				
				if (plantasX!=null) {
					for (Planta planta : plantasX) 
						costoTotal += st.pq(cy.get(planta.y)+1)%2==0? planta.v:0; 
				}
				if (verticesX!=null) {
					int ant = Integer.MIN_VALUE;
					for (int y : verticesX) {
						if (ant>Integer.MIN_VALUE) {
							st.updateRange(cy.get(ant)+1, cy.get(y)-1, 1);
							ant = Integer.MIN_VALUE;
						}
						else
							ant = y;	
					}
				}
			}
			System.out.println (costoTotal);	
			vertices.clear(); plantas.clear();
			cy.clear(); todosX.clear(); todosY.clear();
		}
	}
	
	static class Planta {
		int y,v;
		
		public Planta (int y, int v) {
			this.y = y;
			this.v = v;
		}
		@Override
		public String toString() {
			return "Planta [y=" + y + ", v=" + v + "]";
		}
	}
	
	static class SegmentTree { 
		int st[], lazy[], n;
		
		public SegmentTree (int values[]) {
			n = values.length;
			st = new int[4*n];
			lazy = new int[4*n];
		}
		
		private void updateRange (int p,int l, int r, int i, int j, int diff) {
			if (lazy[p]!=0) {
				st[p] += lazy[p];
				if (l!=r) {
					lazy[right(p)] += lazy[p];
					lazy[left(p)] += lazy[p];
				}
				lazy[p] = 0;
			}
			
			if (j<l || i>r)
				return;
			if (i<=l && r<=j) {
				st[p] += diff;
				if (l!=r) {
					lazy[right(p)] += diff;
					lazy[left(p)] += diff;
				}
				return;
			}
			updateRange (left(p), l, getMid(l,r), i, j, diff);
			updateRange (right(p), getMid(l,r)+1, r, i, j, diff);
			st[p] = Math.max(st[left(p)], st[right(p)]);
		}
		
		void updateRange (int i, int j, int diff) {
			updateRange (1, 0, n-1, i, j, diff);
		}
		
		int pq (int i) {
			return pq(1,0,n-1,i);
		}
		
		private int pq(int p, int l, int r, int i) {
			if (i > r || i < l) 
				return -1; 
			if (lazy[p]!=0) {
				st[p] += lazy[p];
				if (l!=r) {
					lazy[right(p)] += lazy[p];
					lazy[left(p)] += lazy[p];
				}
				lazy[p] = 0;
			}
			if (l==i && r==i)
				return st[p];
			
			int p1 = pq(left(p),l,getMid(l,r), i),
			    p2 = pq(right(p), getMid(l,r)+ 1, r, i);
			return Math.max(p1, p2);
		}
		
		static int left (int p) {
			return p<<1;
		}
		static int right  (int p) {
			return (p<<1)+1;
		}
		static int getMid (int a, int b) {
			return (a+b)>>1;
		}
	}
	
	static class FastReader {
	    BufferedReader br;
	    StringTokenizer st;

	    public FastReader() {
	        br = new BufferedReader(new InputStreamReader(System.in));
	    }

	    String next() {
	 	   String  line;
	        while (st == null || !st.hasMoreElements()) {
	           line = nextLine ();
	           if (line == null)
	              return null;
	           st = new StringTokenizer(line);
	        }
	        return st.nextToken();
	    }

	    Integer nextInt() {
	 	   String element = next ();
	        return element==null? null:Integer.parseInt(element);
	    }

	    Long nextLong() {
	 	   String element = next ();
	        return element==null? null:Long.parseLong(element);
	    }

	    Double nextDouble() {
	 	   String element = next ();
	        return element==null? null:Double.parseDouble(element);
	    }

	    String nextLine() {
	        String str = null;
	        try {
	            str = br.readLine();
	        }
	        catch (IOException e)
	        {}
	        return str;
	    }
	}
}
