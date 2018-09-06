import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class howmany {

	public static void main(String[] args) {
		FastReader in = new FastReader();
		PrintWriter out = new PrintWriter(System.out);
		int t = in.nextInt();
		double log2 = Math.log10(2), log5 = Math.log10(5), epsilon = 0.000001;
		
		while (t-- > 0) {
			int a = in.nextInt(), b = in.nextInt();
			int res = (int) Math.ceil(a*log2+b*log5+epsilon);
			out.println(res);
		}
		out.flush();
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

	    String nextLine() {
	       String str = null;
	       try {
	           str = br.readLine();
	       }
	       catch (IOException e)
	       {}
	       return str;
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
	}
}
