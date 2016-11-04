import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;


public class AtMostTwice {
	static Long num;
	static int valores[];
	static HashMap<String, Long> dp = new  HashMap<String, Long> ();
	static long potencia10[] = new long[19];
	
	public static void main (String[] args) {
		FastReader in = new FastReader();
		char v[];
		
		potencia10[0] = 1;
		for (int i=1;i<potencia10.length; i++)
			potencia10[i] = potencia10[i-1]*10;
		while (true) {
			num = in.nextLong ();
			if (num==null)
				break;
			v = Long.toString (num).toCharArray ();
			valores = new int[v.length];
			for (int i=0; i<valores.length; i++) 
				valores[i] = v[i]-'0';
			if (esValido(num))
				System.out.println (num);
			else
				System.out.println (calcular (0,false,0));
		}
	}
	
	static boolean esValido (long val) {
		int cont[] = new int[10];
		
		for (char c : Long.toString (val).toCharArray ()) {
			if (++cont[c-'0']>2)
				return false;
		}
		return true;
	}
	
	static long pot10 (int v, int p) {
		return v*potencia10[valores.length-1-p];
	}
	
	static long calcular (int pos, boolean menor, long val) {
		if (pos==valores.length )
			return val;
		StringBuilder index = new StringBuilder (pos+" "+(menor? 1:0)+" ");
		int cont[] = new int[10];
		char cc[] = Long.toString (val).toCharArray ();
		for (int i=0; i<Math.min (pos, cc.length); i++) {
			index.append (cc[i]);
			if (++cont[cc[i]-'0']>2)
				return 0;
		}
		if (dp.containsKey (index))
			return dp.get (index);
		long max = 0;
		if (!menor) {
			for (int i=valores[pos]; i>=0; i--)  {
				if (cont[i]<=1) 
					max = Math.max (calcular (pos+1, i<valores[pos], val+pot10(i,pos)), max);
			}
		}
		else {
			for (int i=9; i>=0; i--)  {
				if (cont[i]<=1) {
					max = calcular (pos+1, menor, val+pot10(i,pos));
					break;
				}
			}
		}
		dp.put (index.toString (), max);
		return max;
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
