import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class c1199 {
	public static void main(String[] args) {
		FastReader in = new FastReader();
		int n = in.nextInt();
		long o = 8*in.nextInt();
		TreeMap<Integer, Integer> distinct = new TreeMap<>();

		for (int i=0; i<n; i++) {
			int num = in.nextInt();
			Integer v = distinct.get(num);
			if (v == null)
				distinct.put(num, 1);
			else
				distinct.put(num, v+1);
		}
		long perValue = Math.floorDiv(o, n);
		long limitDistinct = (long) Math.pow(2, perValue);

		if (distinct.size() <= limitDistinct) {
			System.out.println(0);
		} else {
			long sum = 0;
			long res = Long.MAX_VALUE;
			int i = 0, values[] = new int[distinct.size()], j = 0;
			for (Integer v: distinct.values()) {
				sum += v;
				if (i>=limitDistinct)
					sum -= values[j++];
				values[i++] = v;
				res = Math.min(res, n-sum);
			}
			System.out.println(res);
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