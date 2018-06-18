import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Fi {
	static HashMap<Integer, Integer> dp = new HashMap<Integer, Integer> ();
	
	public static void main (String[] args) {
		FastReader in = new FastReader ();
		int cont[];
		Integer n;
		
		while (true) {
			n = in.nextInt ();
			if (n==null)
				return;
			cont = new int[24];
			for  (int i=0; i<n; i++) 
				cont[in.nextInt ()+11]++;
			int bitmask = 0;
			for  (int i=0; i<cont.length; i++) {
				cont[i] %= 2;
				if (cont[i]==0)
					bitmask |= (1<<i);
			}
			System.out.println (formarParejas(bitmask));
			dp.clear ();
		}
	}
	
	static int formarParejas (int bitmask) {
		if (bitmask==(1<<24)-1)
			return 0;
		if (dp.containsKey (bitmask))
			return dp.get (bitmask);
		
		int ans = 100000000,p1, p2;
		for (p1=0; p1<24; p1++) {
			if ((bitmask & (1<<p1))==0)
				break;
		}
		for (p2=p1+1; p2<24; p2++) {
			if ((bitmask & (1<<p2))==0)
				ans = Math.min (ans, getDiff (p1-11,p2-11)+formarParejas (bitmask | (1<<p1) | (1<<p2)));
		}		
		dp.put (bitmask, ans);
		return ans;
	}
	
	static int getDiff (int a, int b) {
		return Math.min (Math.abs (a-b), 24-Math.abs (a-b));
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
