import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;


public class laser {
	static int[] eulerPhi;
	static boolean[] isnotPrime;
	
	public static void main (String[] args) {
		FastReader in = new FastReader ();
		int t,n;
		sieveOptimized (100000);
		eulerSieve (100000);
		t = in.nextInt ();
		for (int i=0; i<t; i++) {
			n = in.nextInt ();
			System.out.println (eulerPhi[n]);
		}
	}

	/*
	public static int calcular (int n) {
		int index=0,j,cont=0;
		boolean recorridos[] = new boolean[n];
		ArrayList<Integer> lista = new ArrayList<Integer> ();
		
		for (int i=1; i<n; i++) {
			Arrays.fill (recorridos, false);
			index = 0;
			for (j=0; j<n; j++) {
				if (recorridos[index])
					break;
				recorridos[index] = true;
				if (index+i<n)
					index += i;
				else
					index = index+i-n; 
				//System.out.print (index+" ");
			}
			//System.out.println (Arrays.toString (recorridos));
			//System.out.println ();
			if (j==n) {
				lista.add (i);
				cont++;
			}
		}
		//System.out.println (lista);
		return cont;
	}*/
	


	public static void eulerSieve (int N) {
	    eulerPhi = new int[N + 1];

	    for (int i = 1; i <= N; i++)
	        eulerPhi[i] = i;
	    for (int i = 1; i <= N; i++) {
	        if (!isnotPrime[i])
	            for (int j = i; j <= N; j += i)
	                eulerPhi[j] -= eulerPhi[j] / i;
	    }
	}
	
	public static void sieveOptimized(int N) {
		isnotPrime = new boolean[N + 1];

	    //for (int i = 2; i <= N; i++)
	     //   isPrime[i] = true;
	    //Arrays.fill (isPrime, 2, N+1, true);
		isnotPrime[0]=isnotPrime[1]=true;
	    for (int i = 2; i * i <= N; i++) {
	        if (!isnotPrime[i]) {
	            for (int j = i * i; j <= N; j += i) 
	            	isnotPrime[j] = true;
	        }
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

	
	/*
	 * SUM DIV
	 * 
	while (T-- > 0) {
        int n = sc.nextInt();
        int divisorSum[] = new int [n + 1];

        // For every number i, You know that 2*i,3*i,4*i   upto k*i such that k*i&lt;=n, will have i 
        // as one of it's divisors, so add that to divisorSum[j]

        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                divisorSum[j] += i;
            }
        }

        // Complexity of this code is O(n * logn)
        // Proof: Expression for the complexity can be written as n / 1 + n / 2 + ... + n / n
        // take n common
        // n * (1 + 1 / 2 + ..... + 1/n)
        // (1 + 1 / 2 + 1 / 3 + ... 1 / n) is harmonic sum and it's bounded by logn.
        // A simple Proof: Let us integrate 1 / x from 1 to n. 
        // (Note that we are doing integration, which means sum of area under the curve 1/x
        // which is greater than (1 + 1 / 2 + ... + 1 / n)
        // value of integration can be found easily
        // as integration of 1/x dx is ln(x)

        for (int i = 1; i <= n; i++) 
            System.out.printf("%d ", divisorSum[i]);

        System.out.printf("\n");
    }
*/
 }
