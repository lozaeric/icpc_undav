import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Supply {
	static HashMap<Integer, ArrayList<Integer>> parents = new HashMap<Integer, ArrayList<Integer>> () ;
	static Integer n,e;
	static HashMap<Integer, Integer> dp = new HashMap<Integer, Integer> () ;
	
	public static void main (String[] args) {
		FastReader in = new FastReader();
		
		while (true) {
			n = in.nextInt ();
			if (n==null)
				break;
			e = in.nextInt ();
			
			for (int i=0; i<n; i++)
				parents.put (i, new ArrayList<Integer> ());
			for (int i=0,a,b; i<e; i++) {
				a = in.nextInt ()-1; b = in.nextInt ()-1;
				parents.get (b).add (a);
			}
			int contador = 0;
			for (int i=0; i<n; i++) {
				for (int j=i+1; j<n; j++) 
					contador += sonDisjoint(i,j);
			}
			System.out.println (contador);
			
			parents.clear ();
			dp.clear ();
		}
	}
	
	static int sonDisjoint (int i, int j) {
		if (i==0 || j==0)
			return 1;
		if (i==j) 
			return 0;
		int index = i*10000+j;
		if (dp.containsKey (index)) 
			return dp.get (index);
		
		int disjoint = 0;
		for (int p : parents.get (j)) {
			disjoint = sonDisjoint (Math.min (p, i),Math.max (p, i));
			if (disjoint==1) 
				break;
		}
		dp.put (index, disjoint);
		return disjoint;
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
