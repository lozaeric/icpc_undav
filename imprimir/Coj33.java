import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Coj33 {
	static int matriz[][];
	static int dr[] = {1,0,-1,0};
	static int dc[] = {0,1,0,-1};
	static long distancia[][];
	static PriorityQueue<Par> pq = new PriorityQueue<Par>();
	
	public static void main (String[] args) {
		FastReader in = new FastReader();
		int n = in.nextInt (), m = in.nextInt ();
		
		matriz = new int[n][m];
		distancia = new long[n][m];
 		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				matriz[i][j] = in.nextInt ();
				distancia[i][j] = 10000000000l;
			}
			distancia[i][0] = matriz[i][0];
			pq.offer (new Par(distancia[i][0], i, 0));
		}
 		System.out.println (dijkstra());
	}
	
	public static long dijkstra () {
      Par actual;
      long min = Integer.MAX_VALUE;
      int r,c;
      
      while (!pq.isEmpty ()) {
      	actual = pq.remove ();
      	if (actual.d>distancia[actual.r][actual.c] || actual.d<0)
      		continue;
         for (int j=0; j<4; j++) {
      		r = actual.r+dr[j];
      		c = actual.c+dc[j];
      		if ((actual.c==0 && c==0) || (actual.c==1 && c==0))
      			continue;
      		if (r<0||r>=matriz.length||c<0||c>=matriz[0].length)
      			continue;
      		if (distancia[actual.r][actual.c]+matriz[r][c]<distancia[r][c]) {
      			distancia[r][c] = distancia[actual.r][actual.c]+matriz[r][c];
      			if (c==matriz[0].length-1)  {
      				if (min>distancia[r][c])
      					min = distancia[r][c];
      			}
      			else
      				pq.offer (new Par(distancia[r][c], r, c));
      		}
      	}
      }
     // for (long dd[] : distancia)
      //	System.out.println (Arrays.toString (dd));
      return min;
	}
	
	public static class Par implements Comparable<Par> {
		public int r, c;
		public long d;
		
		public Par (long d, int r, int c) {
			this.d = d;
			this.r = r;
			this.c = c;
		}
		
		public String toString () {
			return r+","+c+" => "+d;
		}

		public int compareTo (Par o) {
			if (d==o.d)
				return 0;
	      return d<o.d? -1:1;
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
