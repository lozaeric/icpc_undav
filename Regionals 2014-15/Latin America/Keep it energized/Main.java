import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static Integer n;
	static long sumaLevels[];
	static HashMap<Integer, ArrayList<Shop>> shops = new HashMap<Integer, ArrayList<Shop>> ();
	
	public static void main (String[] args) {
		FastReader in = new FastReader ();
		PrintWriter out = new PrintWriter (System.out);
		
		
		while (true) {
			n = in.nextInt();
			if (n==null)
				break;
			sumaLevels = new long[n];
			int m = in.nextInt();
			
			
			for (int i=0; i<n; i++) {
				sumaLevels[i] = in.nextInt()+(i>0? sumaLevels[i-1]:0);
				shops.put(i, new ArrayList<Shop> ());
			}
			for (int i=0,l,s,c; i<m; i++) {
				l = in.nextInt()-1; s = in.nextInt(); c = in.nextInt();
				shops.get(l).add(new Shop(s,c));
			}
			long res = calcular ();
			out.println (res>=2000000001? -1:res);
			shops.clear();
		}
		out.flush();
	}
	
	static long calcular () {
		long min, res=0, dp[]=new long[n+1];
		int fin;
		SegmentTree st;
		
		st = new SegmentTree (dp);
		for (int i=n-1; i>=0; i--) {
			min = 2000000001;
			for (Shop s : shops.get(i)) {
				fin = Arrays.binarySearch(sumaLevels, s.s+(i>0? sumaLevels[i-1]:0));
				if (fin<0)
					fin = -fin-1;
				else
					fin++;
				if (i+1<=fin)
					min = Math.min(min, s.c+st.rmq(i+1, fin));
			}
			st.update_point(i, min);
			if (i==0)
				res = min;
		}
		return res;
	}
	
	static  class Shop {
		int c,s;
		public Shop (int s, int c) {
			this.c = c;
			this.s = s;
		}
	}
	
	static class SegmentTree { 
		long st[];
		int n;
		
		public SegmentTree (long values[]) {
			n = values.length;
			st = new long[4*n];
			build (1,0,n-1, values);
		}
		
		void build (int p, int l, int r, long values[]) {
			if (l==r)
				st[p] = values[l];
			else {
				build(left(p), l, getMid(l,r), values);
				build(right(p), getMid(l,r)+1, r, values);
				st[p] = Math.min(st[left(p)], st[right(p)]);
			}
		}
		
		private void update_point(int p, int L, int R, int idx, long new_value) {
			   if (idx > R || idx < L)
			      return;
			   if (L == idx && R == idx) {
			      st[p] = new_value; 
			      return;
			   }

			   update_point(left(p) , L, getMid(L,R), idx, new_value);
			   update_point(right(p), getMid(L,R) + 1, R , idx, new_value);
			   st[p] = Math.min(st[left(p)], st[right(p)]);
		}
		  
		void update_point(int idx, long new_value) {
			update_point(1, 0, n - 1, idx, new_value); 
		}
		
		long rmq (int i, int j) {
			return rmq(1,0,n-1,i,j);
		}
		
		private long rmq(int p, int l, int r, int i, int j) {
			if (i > r || j < l) 
				return 2000000001; 
			if (l>=i && r<=j)
				return st[p];
			
			long p1 = rmq(left(p),l,getMid(l,r), i, j),
			    p2 = rmq(right(p), getMid(l,r)+ 1, r, i, j);
			return Math.min(p1, p2);
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