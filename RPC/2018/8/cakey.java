import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class cakey {
	
	public static void main(String[] args) {
		FastReader in = new FastReader();
		int n = in.nextInt(), m = in.nextInt();
		int[] entry = new int[n], exit = new int[m];
		int maxO = 0, minD = 1000000011;
		TreeMap<Integer,Integer> diff = new TreeMap<Integer,Integer>();
		
		for(int i=0; i<n; i++) 
			entry[i] = in.nextInt();
		for(int i=0; i<m; i++) 
			exit[i] = in.nextInt();
		
		
		for (int i=0; i<n; i++) {
			int index = Arrays.binarySearch(exit, entry[i]);
			if (index < 0)
				index = -(index+1);
			for (int j=index; j<m; j++) {
				int v = exit[j]-entry[i];
				Integer count = diff.get(v);
				count = count==null? 1:count+1;
				
				diff.put(v, count);
				if (maxO<count || (maxO==count && minD>v)) {
					maxO = count;
					minD = v;
				}
			}
		}
		
		System.out.println(minD==1000000011? 0:minD);
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