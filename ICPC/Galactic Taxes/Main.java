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
	static HashMap<Integer, ArrayList<Edge>> lisAdy = new HashMap<Integer, ArrayList<Edge>> ();
	static Integer n;
	
	public static void main (String[] args) {
		FastReader in = new FastReader ();
		DecimalFormat df = new DecimalFormat ("0.00000");
		StringBuilder out = new StringBuilder ();
		
		
		while (true) {
			n = in.nextInt();
			if (n==null)
				break;
			int m = in.nextInt();
			
			for (int i=0; i<n; i++) 
				lisAdy.put(i, new ArrayList<Edge> ());
			for (int i=0,p,q,a,b; i<m; i++) {
				p = in.nextInt()-1; q = in.nextInt()-1;
				a = in.nextInt(); b = in.nextInt();
				lisAdy.get(p).add (new Edge (q,a,b));
				lisAdy.get(q).add (new Edge (p,a,b));
			}
			out.append (df.format(ternarySearch())).append("\n");
			lisAdy.clear();
		}
		System.out.print(out);
	}
	
	static double ternarySearch () {
		double l=0,r=24*60,mid,midmid,md,mmd,max=0;
		
        for (int it = 0; it < 100; it++) {
            mid = (l+r)/2;
            midmid = (mid+r)/2;
            md = dijsktra(mid);
            mmd = dijsktra(midmid);
            max = Math.max(max, md);
            if (Math.abs(md - mmd) < 1e-6)
                break;
            if (md < mmd)
                l = mid;
            else
                r = midmid;
        }		
		return max;
	}
	
	static double dijsktra (double t) {
		 PriorityQueue<Vertex> pq = new PriorityQueue<Vertex> ();
		 Vertex actual;
		 double distancia[] = new double [n];
		 
		 pq.add(new Vertex (0,0));
		 Arrays.fill(distancia, 2000000000);
		 distancia[0] = 0;
		 while (!pq.isEmpty()) {
			 actual = pq.remove();
			 if (actual.d>distancia[actual.v])
				 continue;
			 for (Edge e : lisAdy.get(actual.v)) {
				 e.recalcular(t);
				 if (distancia[actual.v]+e.d<distancia[e.v]) {
					 distancia[e.v] = distancia[actual.v]+e.d;
					 pq.add(new Vertex (e.v,distancia[e.v]));
				 }
			 }
		 }
		 return distancia[n-1];
	 }
	 
	static class Vertex implements Comparable<Vertex> {
		int v;
		double d;
		
		public Vertex (int v, double d){
			this.d = d;
			this.v = v;
		}

		@Override
		public int compareTo(Vertex v) {
			if (d==v.d)
				return 0;
			return d>v.d? 1:-1;
		}
	}
	
	static class Edge {
		int v,a,b;
		double d;
		
		public Edge (int v, int a, int b) {
			this.v = v;
			this.a = a;
			this.b = b;
		}
		
		void recalcular (double t) {
			d = a*t+b;
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