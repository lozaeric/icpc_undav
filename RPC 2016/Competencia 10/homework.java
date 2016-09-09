
import java.util.Scanner;


public class homework {
	public static void main (String[] args) {
		Scanner in = new Scanner (System.in); 
		int n = in.nextInt ();
		long w, res;
		
		for (int i=0; i<n; i++) {
			w = in.nextLong ();
			res = inversa(w);
			if (res==-1)
				System.out.println ("No solution");
			else
				System.out.println (res);
		}
	}
	/*
	public static void calcular (int n) {
		int cont=0,c;
		
		for (int a=0; a<=n; a++) {
			for (int b=0; b<=n-a; b++) {
				System.out.println (a+" "+b+" "+(n-a-b));
				cont ++;
			}	
		}
		System.out.print (cont);
	}
	*/
	public static long function (long valor) {
		return (valor+1)*(valor+2)/2;
	}
	
	
	public static long inversa (long w) {
		double valor = (-3+Math.sqrt (9+(8*w-8)))/2;
		if (Math.abs (valor-(long) valor)<0.000001)
			return (long) valor;
		return -1;
	}
}
