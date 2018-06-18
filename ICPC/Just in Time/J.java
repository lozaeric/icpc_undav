import java.util.ArrayList;
import java.util.Scanner;

public class J  {
	
	static ArrayList<Integer> primos = new ArrayList<Integer> ();
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
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt ();
		sieve (Math.min (32000, n));
		
		for (int i=n; i>=2; i--) {
			if (esPrimo(i)) {
				System.out.println (i);
				break;
			}
		}
	}
	
	static boolean esPrimo(int n) {
		if (n<np.length)
			return !np[n];
		return testPrimalidad(n);
	}
	
	static boolean testPrimalidad (int n) { // TODOS los factores primos, n<=(ultimo primo)^2
		for (long pf : primos) {
			if (pf*pf>n)
				break;
			if(n%pf==0)
				return false;
		}
		return true;
	}
}
