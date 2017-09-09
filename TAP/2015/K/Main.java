import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;


public class Main {
	private static HashMap<Integer, TreeSet <Integer>> especies = new HashMap<Integer, TreeSet <Integer>> (); 
	private static int n[];
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		n =new int[in.nextInt()];
		int reg=in.nextInt (), anteriores[] = new int[n.length];
		TreeSet <Integer> actual;
		String s;
		SegmentTree st;
		
		
		for (int i=0; i<n.length; i++) {
			n[i] = in.nextInt ();
			actual = especies.get (n[i]);
			if (actual==null) {
				actual = new  TreeSet <Integer> ();
				actual.add (i);
				anteriores[i] = -1;
				especies.put (n[i], actual);
			}
			else {
				anteriores[i] = actual.last ();
				actual.add (i);
			}
		}
		st = new SegmentTree (anteriores);
		for (int i=0; i<reg; i++) {
			s = in.next ();
			
			if (s.equals ("E")) {
				int fin = in.nextInt (), eleccion = st.rmq (0, fin-1);
				if (anteriores[eleccion]==-1)
					System.out.println (fin);
				else
					System.out.println (fin-anteriores[eleccion]-1);
				
			}
			else if (s.equals ("C")) {
				int pos = in.nextInt ()-1, cambio = in.nextInt (),anterior, siguiente,ambos[];
				boolean nueva;
				
				if (n[pos]!=cambio) {
					nueva = false;
					actual = especies.get (cambio);
					if (actual==null) {
						actual = new  TreeSet <Integer> ();
						actual.add (pos);
						especies.put (cambio, actual);
						nueva = true;
					}
					anterior = anteriores[pos];
					siguiente = next (pos);
					if (siguiente!=-1) {
							anteriores[siguiente]=anterior;
							st.update (siguiente,anterior);
					}
					especies.get (n[pos]).remove (pos);
					n[pos] = cambio;
					if (!nueva) {
						ambos = ant_next (pos);
						anterior = ambos[0];
						siguiente = ambos[1];
						anteriores[pos] = anterior;
						st.update (pos,anterior);
						if (siguiente!=-1) {
							anteriores[siguiente]=pos;
							st.update (siguiente,pos);
						}
						actual.add (pos);
					}
					else {
						anteriores[pos]=-1;
						st.update (pos,-1);
					}
				}
			}
		}
	}
	
	public static int[] ant_next (int i) {
		TreeSet<Integer> tree = especies.get (n[i]);
		Integer l = tree.lower (i), r = tree.higher (i);
		return new int[] {l==null? -1:l, r==null? -1:r};
	}
	
	public static int ant (int i) {
		TreeSet<Integer> tree = especies.get (n[i]);
		Integer l = tree.lower (i);
		if (l==null)
			return -1;
		return l;
	}
	public static int next (int i) {
		TreeSet<Integer> tree = especies.get (n[i]);
		Integer r = tree.higher (i);
		if (r==null)
			return -1;
		return r;
	}
	
	public static class SegmentTree {
		private int n, st[],a[];
		
		public SegmentTree (int[] vector) {
			a = vector;
			n = vector.length;
			st = new int [4*n];
			build (1,0,n-1);
		}
		
		public void update (int i, int value) {
			update_point (1,0,n-1,i,value);
		}
		
		public int rmq (int p, int L, int R, int i, int j) {
			if (i>R || j<L)
				return -1;
			if (L>=i && R<=j)
				return st[p];
			int p1 = rmq(left(p), L,(L+R)/2,i,j), p2 = rmq(right (p), (L+R)/2+1, R,i,j);
			if (p1==-1)
				return p2;
			if (p2==-1)
				return p1;
			return a[p1]>=a[p2]? p1:p2;
		}
		
		public int rmq (int i,int j) {
			return rmq(1,0,n-1,i,j);
		}
		
		public void build (int p, int L, int R) {
			if (L==R)
				st[p]=L;
			else {
				build (left(p), L, (L+R)/2);
				build (right(p), (L+R)/2+1, R);
				int p1 = st[left(p)], p2 = st[right(p)];
				st[p]=a[p1]>=a[p2]? p1:p2;
			}
		}
		public int left (int p) {
			return p<<1;
		}
		public int right (int p) {
			return (p<<1)+1;
		}
		
		  public int update_point(int p, int L, int R, int idx, int new_value) {
			    int i = idx, j = idx;

			    if (i > R || j < L)
			      return st[p];

			    if (L == i && R == j) {
			      a[i] = new_value; 
			      return st[p] = L; 
			    }

			    int p1, p2;
			    p1 = update_point(left(p) , L              , (L + R) / 2, idx, new_value);
			    p2 = update_point(right(p), (L + R) / 2 + 1, R          , idx, new_value);

			    return st[p] = (a[p1] >= a[p2]) ? p1 : p2;
			  }
	}
}