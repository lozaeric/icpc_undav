import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class pells {
	
	public static void main(String[] args) {
		FastReader in = new FastReader();
		PrintWriter out = new PrintWriter(System.out);
		
		int values [] = new int[] {2,3,4,7,13};
		long series[][] = new long[values.length][50001];
		for (int j=0; j<values.length; j++) {
			series[j][0] = 0;
			series[j][1] = 1;
			for (int i=2; i<series[j].length; i++) {
				series[j][i] = 2*series[j][i-1]+series[j][i-2];
				series[j][i] %= values[j];
			}
		}

		while (true) {
			Integer n = in.nextInt();
			if (n==null)
				break;
			boolean div = false;
			for (int i=0; i<values.length; i++) {
				if (series[i][n]==0) {
					div = true;
					out.println("Pell's number "+n+" is divisible by "+values[i]);
				}
			}
			if (!div)
				out.println(-1);
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
