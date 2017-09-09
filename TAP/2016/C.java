import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class C {
	static int contadores[];
	static int n, m;
	static HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<Integer, ArrayList<Integer>> ();
	static boolean[] activos;
	
	public static void main (String[] args) {
		FastReader in = new FastReader ();
		n = in.nextInt ();
		m = in.nextInt ();
		activos = new boolean[n];
		contadores = new int [n];
		int contador=0;	
		//ArrayList<Integer> starts = new ArrayList<Integer>(n), actual;
		ArrayList<Integer> actual;
		
		//for  (int i=0; i<n; i++) 
		//	starts.add (i);
		for (int j=0,a,b; j<m; j++) {
			a = in.nextInt ()-1;
			b = in.nextInt ()-1;
			actual = lisAdy.get (a);
			if (actual==null) {
				actual = new ArrayList<Integer>();
				actual.add (b);
				lisAdy.put (a, actual);
			}
			else
				actual.add (b);
			//starts.remove (new Integer(b));
			contadores[b]++;
		}
		for (int i=0; i<n; i++) {
			int v=in.nextInt ()-1;
			activos[v] = true;
			contador += dfs (v);
			System.out.println (contador);
		}
	}

	public static int dfs (int v) {
		if (activos[v] && contadores[v]==0) {
			if (lisAdy.get (v)==null || lisAdy.get (v).isEmpty ())
				return 1;
			int suma=1;
			for  (int vecino  : lisAdy.get (v)) {
				contadores[vecino]--;
				suma += dfs (vecino);
			}
			return suma;
		}
		return 0;
	}
	
	static class FastReader
   {
       BufferedReader br;
       StringTokenizer st;

       public FastReader()
       {
           br = new BufferedReader(new
                    InputStreamReader(System.in));
       }

       String next()
       {
           while (st == null || !st.hasMoreElements())
           {
               try
               {
                   st = new StringTokenizer(br.readLine());
               }
               catch (IOException  e)
               {
                   e.printStackTrace();
               }
           }
           return st.nextToken();
       }

       int nextInt()
       {
           return Integer.parseInt(next());
       }

       long nextLong()
       {
           return Long.parseLong(next());
       }

       double nextDouble()
       {
           return Double.parseDouble(next());
       }

       String nextLine()
       {
           String str = "";
           try
           {
               str = br.readLine();
           }
           catch (IOException e)
           {
               e.printStackTrace();
           }
           return str;
       }
   }
 }
