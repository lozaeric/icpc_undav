import java.util.ArrayList;


class Primes {
	static ArrayList<Integer> primos = new ArrayList<> ();
	static boolean np[];
	
	// Calcular primos, obtener cantidad de divisores y coprimos menores a un numero
	// obtener factores primos de un numero
	
	static void sieve (int max) {  // es prerequisito para todas las demás funciones
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
	
	static ArrayList<Integer> factoresPrimos (int n) { // TODOS los factores primos, n<=(ultimo primo)^2
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
	
	static int cantDivisors(int n) { // cantidad de divisores, n<=(ultimo primo)^2
		int ans=1, num=n;
		
		for (long pf : primos) {
			if (pf*pf>num)
				break;
			int power = 0;
			while(num%pf==0) {
				num /= pf;
				power++;
			}
			ans *= (power+1);
		}
		return num!=1? 2*ans:ans;
	}	
	
	static int eulerPhi(int n) { // cantidad de coprimos de n que son menores, n<=(ultimo primo)^2
		int ans=n, num=n;
		
		for (long pf : primos) {
			if (pf*pf>num)
				break;
			if(num%pf==0)
				ans -= ans/pf;
			while(num%pf==0) 
				num /= pf;
		}
		return num!=1? ans-ans/num:ans;
	}	
}