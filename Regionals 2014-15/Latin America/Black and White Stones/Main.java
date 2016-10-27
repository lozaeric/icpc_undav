import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static Long total,ady;
	static int num[],f;
	
	public static void main (String[] args) {
		FastReader in = new FastReader ();
		char s[];

		while (true) {
			total = in.nextLong();
			if (total==null)
				break;
			ady = total-in.nextInt();
			s = in.next().toCharArray();
			f = 0;
			num = new int[s.length];
			for (int i=0; i<s.length; i++) {
				num[i] = s[i]=='B'? 1:0;
				f += num[i];
			}
			System.out.println (calcular());
		}
	}

	static long calcular () {
		int i,j,ii=0,jj=num.length-1;
		long costo=0;

		while (true) {
			for (i=ii; i<f && num[i]==1; i++);
			for (j=jj; j>=f && num[j]==0; j--);
			if (i>=j)
				return costo;
			costo += Math.min((j-i)*ady, total);
			ii=i+1; jj=j-1;
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