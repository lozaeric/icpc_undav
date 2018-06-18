import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class BloodGroups {
    public static void main(String[] args) {
    	FastReader in = new FastReader();
    	StringBuilder out = new StringBuilder ();
    	HashMap<Integer, ArrayList<Integer>> padres = new HashMap<Integer, ArrayList<Integer>> ();
    	Integer n=0,q;
    	boolean used [], correcto;
    	
    	while (n!=null) {
    		n = in.nextInt();
    		if  (n==null)
    			break;
    		q = in.nextInt();
    		
			for (int i=0; i<n; i++) 
				padres.put(i, new ArrayList<Integer> ());
    		for (int i=0, e; i<n; i++) {
    			e = in.nextInt();
    			for (int j=0; j<e; j++) 
        			padres.get(i).add(in.nextInt());
    			if (e<n) 
    				padres.get(i).add(0);
    		}
    		for (int i=0,bg; i<q; i++) {
        		used = new boolean[n];
        		Grafo g = new Grafo (2*n);
    			bg = in.nextInt();
    			for (int j=0, a; j<bg; j++) {
    				a = in.nextInt();
	    			for (int t=0; t<n; t++) {
	    				if (padres.get(t).contains(a)) {
	    					g.setEdge(t, n+a-1);
	    					used[t] = true;
	    				}
	    			}
    			}
    			correcto = true;
    			for (int t=0; t<n && correcto; t++) {
    				if (padres.get(t).contains(0) && bg<n) 
    					used[t] = true;
    				if (!used[t])
    					correcto = false;
    			}    			
    			if (!correcto)
    				out.append("N\n");
    			else 
    				out.append(g.mcbm()==bg? "Y\n":"N\n");
    		}
    		padres.clear();
    	}
    	System.out.print (out.toString ());
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

    
	static class Grafo {
		HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<Integer, ArrayList<Integer>> ();
		boolean visitados[];
		int match[], n;
		ArrayList<Integer> genesHijo  = new ArrayList<Integer> ();
		
		public Grafo (int n) {
			this.n = n;
			for (int i=0; i<n; i++) 
				lisAdy.put(i, new ArrayList<Integer> ());
			match = new int[n];
			for (int i=0; i<n; i++) 
				match[i] = -1;
		}
		
		void setEdge (int i, int j) {
			lisAdy.get(i).add(j);
		}
		
		int mcbm () {
			int cont = 0;
			
			for (int i=0; i<(n>>1); i++) {
				visitados = new boolean[n];
				cont += aug(i);
			}
			return cont;
		}
		
		private int aug (int i) {
			if (visitados[i])
				return 0;
			visitados[i] = true;
			for (int vec : lisAdy.get(i)) {
				if (match[vec]==-1 || aug(match[vec])>0) {
					match[vec] = i;
					return 1;
				}
			}
			return 0;
		}
	}
}