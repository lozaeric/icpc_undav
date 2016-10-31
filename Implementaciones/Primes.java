import java.util.ArrayList;
import java.util.Scanner;


class Primes {
	static int sieveSize = 100;
	static ArrayList<Integer> primos = new ArrayList<Integer> ();
	
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		sieve ();
		int a=2184,b=2200,cont=0;
		System.out.println (primos);
		/*
		for(int i=a; i<=b; i++) {
			System.out.println (factoresPrimos(i));
			for (int fp : factoresPrimos(i)) {
				map.add (fp);
			}
		}/*
		for(int i=a; i<=b; i++) {
			for (int fp : factoresPrimos(i)) {
				if (!map.contains (fp))
					System.out.println ("hola "+i);
			}
		}
		System.out.println ();*/
	}
	
	public static int gcd(int a, int b) 
	{ return b == 0 ? a : gcd(b, a % b); }
	
	public static int eulerPhi(int n) {
		int id=0, pf = primos.get (id), ans=n;
		while(pf*pf<=n) {
			if(n%pf==0)
				ans -= ans/pf;
			while(n%pf==0) 
				n /= pf;
			pf = primos.get (++id);
		}
		if (n != 1)
			ans -= ans/n;
		return ans;
	}
	
	public static boolean esPrimo(int n) {
		if (n<=sieveSize)
			return primos.contains(n);
		return factoresPrimos(n).size ()==1;
	}
	
	public static void sieve () {
		boolean np[] = new boolean[sieveSize+1];
		np[0] = np[1] =  true;
		long i,j;
		
		for (i=2; i*i<np.length; i++) {
			if (!np[(int) i]) {
				for (j=i; i*j<np.length; j++) 
					np[(int) (i*j)] = true;
			}
		}
		for (int t=0; t<np.length; t++) 
			if (!np[t])
				primos.add(t);
	}
	
	public static ArrayList<Integer> factoresPrimos(int n){
		ArrayList<Integer> factores = new ArrayList<Integer> ();
		int id=0, pf = primos.get (id);
		while(pf*pf<=n) {
			while(n%pf==0) {
				n /= pf;
				factores.add (pf);
			}
			pf = primos.get (++id);
		}
		if (n != 1)
			factores.add (n);
		return factores;
	}
	
}