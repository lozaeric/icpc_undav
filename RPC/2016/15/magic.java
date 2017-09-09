import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class magic {
	static int num[];
	static String com[];
	
	public static void main (String args[]) {
		FastReader in  = new FastReader ();
		int op = in.nextInt ();
		num = new int[op];
		com = new String[op];
		
		for (int i=0; i<op; i++) {
			com[i] = in.next ();
			num[i] = in.nextInt ();
		}
		int validos = 0;
		for (int i=1; i<=100; i++) {
			validos += esValido(i)? 0:1;
		}
		System.out.println (validos);
	}
	
	static boolean esValido (int n) {
		double v = n;
		
		for (int i=0; i<num.length; i++) {
			if (com[i].equals ("SUBTRACT")) {
				v -= num[i];
			}
			else if (com[i].equals ("ADD")) {
				v += num[i];
			}
			else if (com[i].equals ("DIVIDE")) {
				v /= num[i];
			}
			else if (com[i].equals ("MULTIPLY")) {
				v *= num[i];
			}
			if (v<0)
				return false;
			if (v-(int) v > 0)
				return false;
		}
		return true;
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