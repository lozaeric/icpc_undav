import java.util.Arrays;
import java.util.Scanner;


public class River {
	static int minutos[],m;
	static int guardados[] = new int[2501];
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int n;
		n = in.nextInt ();
		m = in.nextInt ();
		minutos = new int[n+1];
		minutos[0] = m;
		for (int i=1; i<minutos.length; i++) 
			minutos[i] = minutos[i-1]+in.nextInt ();
		
		System.out.println (calcular (n));
	}

	
	public static int calcular (int n) {
		if (guardados[n]!=0)
			return guardados[n];
		int min = Integer.MAX_VALUE;
		for (int i=1; i<=n && i<minutos.length; i++) {
			if (i<n)
				min = Math.min (min, m+minutos[i]+calcular(n-i));
			else 
				min = Math.min (min, minutos[i]);
		}
		return guardados[n] = min;
	}
}