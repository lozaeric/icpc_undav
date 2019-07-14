import java.util.ArrayList;
import java.util.Scanner;

public class expired {		
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);		
		int n = in.nextInt();
		sieve(11000);
		for (int i=0; i<n; i++) {
			String a = in.next(), b = in.next();
			int va = Integer.parseInt(a.replace(".", "")), vb = Integer.parseInt(b.replace(".", ""));			
			int d = gcd(va, vb);
			
			if (va/d == 1 && vb/d == 1)
				System.out.println("2 2");
			else if (d>0 && esPrimo(va/d) && esPrimo(vb/d))
				System.out.println((va/d)+" "+(vb/d));
			else
				System.out.println("impossible");
		}
	}
	
	static int gcd (int a, int b) { 
		return b == 0 ? a : gcd(b, a % b); 
	}	
	
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
		if (n<np.length)
			return !np[n];
		return factoresPrimos(n).size ()==1;
	}
	
	static ArrayList<Integer> factoresPrimos (int n) {
		ArrayList<Integer> factores = new ArrayList<> ();
		int num=n;
		
		for (long pf : primos) {
			if (pf*pf>num)
				break;
			while(num%pf==0) {
				num /= pf;
				factores.add ((int) pf);
			}
		}
		if (num != 1)
			factores.add (num);
		return factores;
	}	
}