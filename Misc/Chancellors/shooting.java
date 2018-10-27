import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class shooting {
	static ArrayList<Integer> primos = new ArrayList<> ();
	static boolean np[];
	
	static void sieve (int max) {  
		np = new boolean[max+1];
		np[0] = np[1] =  true;

		for (int i=2; i<=max; i++) {
			if (!np[i]) {
				primos.add(i);
				for (long j=i; i*j<=max; j++) 
					np[(int) (i*j)] = true;
			}
		}
	}
	
	static boolean esPrimo(int n) {
		if (n>np.length-1)
			return false;
		return !np[n];
	}
	
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		sieve(1000000);
		int t = in.nextInt();
		for (int i=0; i<t; i++) {
			int a = in.nextInt(), b = in.nextInt(), c = 0;
			
			if (a%2==0 && b%2==0) {
				out.println("unknown");
				continue;
			}
			String res[] = new String[10];
			for (int k=0; k<=1000 && c<10; k++) {
				int m = a*k+b;
				if (esPrimo(m))
					res[c++] = String.valueOf(m);
			}
			out.println(c==10? String.join(" ", res):"unknown");
		}
		out.flush();
	}
}
