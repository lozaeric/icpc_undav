import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class shattered {
	
	public static void main(String[] args) {
		FastReader in = new FastReader();
		long w = in.nextInt(), n = in.nextInt();
		long area = 0;
		
		for (int i=0; i<n; i++) 
			area += in.nextInt()*in.nextInt();
		System.out.println(area/w);
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
	}
}