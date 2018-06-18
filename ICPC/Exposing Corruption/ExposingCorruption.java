import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;


public class ExposingCorruption {
	static HashMap<Integer, Integer> dp = new HashMap<Integer, Integer> ();
	static ArrayList<CC> cc = new ArrayList<CC> (), cc2 = new ArrayList<CC> (), componentes;  
	static int n,r,b;
	static int preciosD[],preciosP[];
	
	public static void main (String[] args) {
		FastReader in = new FastReader();
		StringBuilder out = new StringBuilder ();
		Integer aux = 0;
		DisjointSet set;
		
		while (aux!=null) {
			aux = in.nextInt();
			if (aux==null)
				break;
			preciosD = new int[aux];
			preciosP = new int[in.nextInt()];
			r = in.nextInt(); 
			b = in.nextInt();
			n = preciosP.length+preciosD.length;
			set = new DisjointSet (n);
			
			for (int i=0; i<preciosD.length; i++)
				preciosD[i] = in.nextInt();
			for (int i=0; i<preciosP.length; i++)
				preciosP[i] = in.nextInt();
			for (int i=0,dd,pp; i<r; i++) {
				dd = in.nextInt()-1; pp = in.nextInt()-1+preciosD.length;
				set.union(dd, pp);
			}
			int costo,c1,c2;
			for (int r : set.elements.keySet()) {
				costo = c1 = c2 = 0;
				for (int e : set.elements.get(r)) {
					costo += e>=preciosD.length? preciosP[e-preciosD.length]:preciosD[e];
					if(e>=preciosD.length) 
						c2++;
					else
						c1++;
				}
				if (costo>b || c1==c2)
					continue;
				else if (c1<c2)
					cc.add(new CC (c2-c1,costo));
				else if (c1>c2)
					cc2.add(new CC (c1-c2,costo));
			}
			componentes = cc;
			out.append (preciosD.length+calcular (0,b)).append(" ");
			dp.clear();
			componentes = cc2;
			out.append (preciosP.length+calcular (0,b)).append("\n");
			dp.clear();
			cc.clear();
			cc2.clear();
		}
		System.out.print(out);
	}
	
	static int calcular (int i, int b) {
		Integer index = i*100000+b;
		
		if (b<0)
			return -1000000;
		if (i==componentes.size() || b==0)
			return 0;
		if (dp.containsKey(index)) 
			return dp.get(index);		
		
		CC actual = componentes.get(i);
		int max = Math.max(actual.diff+calcular(i+1,b-actual.costo), calcular(i+1,b));
		dp.put(index, max);
		return max;
	}
	
	static int calcular2 (int i, int b) {
		Integer index = i*100000+b;
		
		if (b<0)
			return -1000000;
		if (i==componentes.size() || b==0)
			return 0;
		if (dp.containsKey(index)) 
			dp.get(index);		
		
		CC actual = componentes.get(i);
		int max = Math.max(actual.diff+calcular(i+1,b-actual.costo), calcular(i+1,b));
		dp.put(index, max);
		return max;
	}
	
	static class DisjointSet {
	    int[] parent;
	    HashMap<Integer, ArrayList<Integer>> elements = new HashMap<Integer, ArrayList<Integer>> ();
	
	    public DisjointSet(int n)
	    {
	        parent = new int [n];
	        for(int i = 0; i < n; i++) {
	        	ArrayList<Integer> nuevo = new ArrayList<Integer> ();
	            parent[i] = i;
	            nuevo.add(i);
	            elements.put(i, nuevo);
	        }
	    }

	    public int find(int i)
	    {
	        if (parent[i] != i)
	            parent[i] = find(parent[i]);
	        return parent[i];
	    }
	    
	    public boolean isSameSet (int x, int y) {
	   	 return find(x)==find(y);
	    }

	    public void union(int x, int y)
	    {
	        int x_root = find(x), y_root = find(y);
	        if (x_root != y_root) {
	            parent[y_root] = x_root;
	            elements.get(x_root).addAll(elements.remove(y_root));
	        }
	    }
	}
	
	static class CC {
		int costo, diff;
		
		public CC (int diff, int costo) {
			this.diff = diff;
			this.costo = costo;
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
