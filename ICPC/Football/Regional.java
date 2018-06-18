import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Regional {
	public static void main (String[] args) {
		FastReader in = new FastReader ();
		Integer n,g,res;
		PriorityQueue<Integer> partidos = new PriorityQueue<Integer>();
		
		while (true) {
			n = in.nextInt (); 
			if (n==null)
				break;
			g = in.nextInt ();
			res = 0;
			
			for (int i=0,a,b; i<n; i++) {
				a = in.nextInt (); b = in.nextInt ();
				if (b>=a) 
					partidos.add (b-a+1);
				else
					res+=3;
			}
			while (!partidos.isEmpty ()) {
				Integer d = partidos.remove ();
				if (g>=d) { //gano
					g -= d;
					res += d==0? 2:3;
				}
				else if (g==d-1) { //empato
					g -= d-1;
					res ++;
				}
				else
					break;
			}
			System.out.println (res);
			partidos.clear ();
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
