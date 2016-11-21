import java.util.ArrayList;
import java.util.Collections;


class Primes {
	static int sieveSize = 100; // calcula los primos menores o iguales a sieveSize
	static ArrayList<Integer> primos = new ArrayList<Integer> ();
	
	public static void main (String[] args) {
		sieve ();
		System.out.println (primos);
		System.out.println ("67 es primo?\t"+esPrimo (67));
		System.out.println ("Factores primos de 128\t"+factoresPrimos (128));
	}
	
	static int gcd(int a, int b) 
	{ return b == 0 ? a : gcd(b, a % b); }
	
	static int eulerPhi(int n) { // cantidad de coprimos de n que son menores
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
	
	static boolean esPrimo(int n) {
		if (n<=sieveSize)
			return Collections.binarySearch (primos, n)>=0;
		return factoresPrimos(n).size ()==1;
	}
	
	static void sieve () {
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
	
	public static ArrayList<Integer> factoresPrimos(int n){ // TODOS los factores primos
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