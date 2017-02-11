
import java.util.Scanner;
import java.util.TreeSet;


public class D {
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int a,b,c,d;
		
		while (in.hasNext ()) {
			a = in.nextInt ();
			b = in.nextInt ();
			c = in.nextInt ();
			d = in.nextInt ();
			System.out.println (getDivisors (a,b,c,d));
		}
	}
	
	// Function to print the divisors
	static int getDivisors(int a, int b, int n, int d)
	{
	    int min = Integer.MAX_VALUE;
	    
	    for (int i=1; i<=Math.sqrt(n)+1; i++)
	    {
	        if (n%i==0)
	        {
	            // If divisors are equal, print only one
	            if (n/i == i && i%a==0 && i%b!=0 && d%i!=0)
	                min = Math.min (min, i);
	            else {
	            	if (i%a==0 && i%b!=0 && d%i!=0)
	            		min = Math.min (min, i);
	            	if ((n/i)%a==0 && (n/i)%b!=0 && d%(n/i)!=0)
	            		min = Math.min (min, n/i);
	            }
	        }
	    }
	    return min==Integer.MAX_VALUE? -1: min;
	}

}
