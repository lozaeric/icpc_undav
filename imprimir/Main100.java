import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


class Main100 {
	static int sieveSize = 10000001;
	static BitSet bs;
	static ArrayList<Integer> primos = new ArrayList<Integer> ();
	
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		sieve (1121);
		int a=2184,b=2200,cont=0;
		HashSet<Integer> map = new HashSet<Integer>();
		Integer actual;
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
	
	public static int next(int n, int fin) {
		for (int i=fin; i>n; i--) {
			if (gcd(n,i)!=1)
				return i;
		}
		return 0;
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
		if (n<sieveSize)
			return bs.get (n);
		return factoresPrimos(n).size ()==1;
	}
	
	public static void sieve (int upperbound) {
		sieveSize = Math.min (upperbound+1, sieveSize);
		int fin = (int) Math.sqrt (sieveSize-1);
		bs = new BitSet(sieveSize);
		bs.set (0, bs.size ()+1, true);
		bs.set (0, false);
		bs.set (1, false);
		for (int i=2; i<sieveSize; i++) {
			if (bs.get (i)) {
				primos.add (i);
				if (i>fin)
					continue;
				for (long j=i*i; j<sieveSize; j+=i) 
					bs.set ((int)j, false);
			}
		}
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