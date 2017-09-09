import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class excellence {
	public static void main (String args[]) {
		FastReader in  = new FastReader ();
		int n = in.nextInt ();
		int ratings[] = new int[n];
		
		for (int i=0; i<ratings.length; i++)
			ratings[i] = in.nextInt ();
		Arrays.sort (ratings);
		
		int min = Integer.MAX_VALUE;
		for (int i=0; i<n/2; i++) {
			min = Math.min (min, ratings[i]+ratings[n-1-i]);
		}
		System.out.println (min);
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