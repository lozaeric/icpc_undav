import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class classy {
	public static void main (String args[]) {
		FastReader in  = new FastReader ();
		int n = in.nextInt ();
		Persona pers[] = new Persona[n];
		
		for (int i=0; i<n; i++) {
			String palabras[] = in.nextLine ().split (" ");
			int clases[] = new int[palabras.length-2];
			for (int j=1; j<=clases.length; j++) {
				if (palabras[j].equals ("middle"))
					clases[clases.length-j] = 0;
				else if (palabras[j].equals ("upper"))
					clases[clases.length-j] = 1;
				else
					clases[clases.length-j] = -1;
			}
			pers[i] = new Persona(palabras[0].substring (0, palabras[0].length ()-1), clases);
		}
		Arrays.sort (pers);
		for (int i=n-1; i>=0; i--)
			System.out.println (pers[i].nombre);
	}
	
	static class Persona implements Comparable<Persona> {
		String nombre;
		int clases[];
		
		public Persona (String nombre, int clases[]) {
			this.nombre = nombre; this.clases = clases;
		}

		public int compareTo (Persona o) {
			int c[],c2[];
			
			if (clases.length<o.clases.length) {
				c = Arrays.copyOf (clases, o.clases.length);
				c2 = o.clases;
			}
			else if (clases.length>o.clases.length) {
				c2 = Arrays.copyOf (o.clases, clases.length);
				c = clases;				
			}
			else {
				c = clases;
				c2 = o.clases;
			}
			
			for (int i=0; i<c.length; i++) {
				if (c[i]>c2[i])
					return 1;
				else if (c[i]<c2[i])
					return -1;
			}
			return -nombre.compareTo (o.nombre);
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