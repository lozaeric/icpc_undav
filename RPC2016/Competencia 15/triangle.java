import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class triangle {
	public static void main (String args[]) {
		FastReader in  = new FastReader ();
		int a[] = new int[3], b[] = new int[3];
		
		for (int i=0; i<a.length; i++)
			a[i] = in.nextInt ();
		for (int i=0; i<b.length; i++)
			b[i] = in.nextInt ();		
		Arrays.sort (a);
		Arrays.sort (b);
		boolean valido = true;
		for (int i=0; i<a.length; i++) {
			if (a[i]!=b[i])
				valido = false;
		}
		if (valido) 
			valido = (a[0]*a[0]+a[1]*a[1]==a[2]);
		System.out.println (valido? "YES":"NO");
		
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