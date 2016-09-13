
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class logotron {
   public static void main (String[] args) {
   	FastReader in = new FastReader ();
   	int n = in.nextInt (),r,s;
    	int variables,cont;
    	Relacion restricciones[];
    	boolean valida;
    	
    	
    	for (int i=0; i<n; i++) {
    		r = in.nextInt ();
    		s = in.nextInt ();
    		restricciones = new Relacion[s];
    		for (int j=0; j<s; j++) 
    			restricciones[j] = new Relacion (in.nextInt ()-1, in.nextInt ()-1, in.next ());
    		
    		cont = 0;
    		for (variables=0; variables<=(1<<r)-1; variables++) {
    			valida = true;
    			for (Relacion rel : restricciones) {
    				if (!rel.comprobar (variables)) {
    					valida = false;
    					break;
    				}
    			}
    			if (valida)
    				cont++;
    		}
    		System.out.println ("Case #"+(i+1)+": "+cont);
    	}
   }
   
   static class Relacion {
   	int a,b;
   	String tipo;
   	
   	public Relacion (int a, int b, String tipo) {
   		this.a = a;
   		this.b = b;
   		this.tipo = tipo;
   	}
   	
   	public boolean sonIguales (int variables, int a, int b) {
   		return ((variables>>b) & 1) == ((variables>>a) & 1);
   	}
   	
   	public boolean comprobar (int variables) {
   		//System.out.print (Integer.toBinaryString (variables)+" ");
   		if (tipo.equals ("T")) {
   			//System.out.println (a+" "+b+" "+tipo+"=>"+sonIguales(variables,a,b));
   			return sonIguales(variables,a,b);
   		}
   		//System.out.println (a+" "+b+" "+tipo+"=>"+((variables & (1<<a)) != (variables & (1<<b))));
   		return !sonIguales(variables,a,b);
   	}
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
     