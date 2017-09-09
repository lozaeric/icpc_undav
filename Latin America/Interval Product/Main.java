import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static int c,sign[],q;
	static Integer n;
	
	public static void main (String[] args) {
		FastReader in = new FastReader ();
		SegmentTree st;
		StringBuilder out;
		
		while (true) {
			n = in.nextInt ();
			if (n==null)
				break;
			q = in.nextInt ();
			sign = new int[n];
			out = new StringBuilder ();
			for (int i=0,v; i<n; i++) {
				v = in.nextInt ();
				if (v!=0)
					sign[i] = v>0? 1:-1;
			}
			st  = new SegmentTree (sign);
			for (int i=0,a,b,r; i<q; i++) {
				String s = in.next ();
				a = in.nextInt ()-1; b = in.nextInt ();
				if (s.equals ("C"))  {
					if (b!=0)
						b = b>0? 1:-1;
					st.update_point (a, b);
				}
				else {
					b--;
					r = st.riq (a, b);
					if (r==0)
						out.append ("0");
					else 
						out.append (r>0? "+":"-");
				}
			}
			System.out.println (out);
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
	
	static class SegmentTree { 
		int st[], n;
		
		public SegmentTree (int values[]) {
			n = values.length;
			st = new int[4*n];
			build (1,0,n-1, values);
		}
		
		void build (int p, int l, int r, int values[]) {
			if (l==r)
				st[p] = values[l];
			else {
				build(left(p), l, getMid(l,r), values);
				build(right(p), getMid(l,r)+1, r, values);
				st[p] = st[left(p)]*st[right(p)];
			}
		}
		
		private void update_point(int p, int L, int R, int idx, int new_value) {
			   if (idx > R || idx < L)
			      return;
			   if (L == idx && R == idx) {
			      st[p] = new_value; 
			      return;
			   }

			   update_point(left(p) , L, getMid(L,R), idx, new_value);
			   update_point(right(p), getMid(L,R) + 1, R , idx, new_value);
			   st[p] = st[left(p)]*st[right(p)];
		}
		  
		void update_point(int idx, int new_value) {
			update_point(1, 0, n - 1, idx, new_value); 
		}
		
		int riq (int i, int j) {
			return riq(1,0,n-1,i,j);
		}
		
		private int riq(int p, int l, int r, int i, int j) {
			if (i > r || j < l) 
				return 1; 
			if (l>=i && r<=j)
				return st[p];
			
			
			int p1 = riq(left(p),l,getMid(l,r), i, j),
			    p2 = riq(right(p), getMid(l,r)+ 1, r, i, j);
			return p1*p2;
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
}
