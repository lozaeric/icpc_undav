import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class Main {
	public static void main (String[] args) {
		//long t = System.nanoTime ();
		System.out.println (dividir (5,3));
		System.out.println (dividir (5,2));
		System.out.println (dividir (1000000,1));
		System.out.println (dividir (1000000,30000));
		//System.out.println ("Tardo: "+(System.nanoTime ()-t)/1000000);
	}


	public static long dividir (int l, int n) {
		long e = calcularCuadrado (l)/n, i = 1;
		if (n==1)
			return e;
		else if (n==l)
			return 1;
		return busquedaBinaria (i,e+1,l,n);
	}

	public static long busquedaBinaria (long i, long f, int l, int n) {
		long medio = (long) ((f-i)/2d);

		if (esValorMinimo (l,n,f-medio)) {
			if (f-i==1 || f==i)
				return f;
			if (f-i<=8) {
				if (esValorMinimo (l,n,f))
					return f;
				return busquedaBinaria (f-medio,f-1,l,n);
			}
			return busquedaBinaria (f-medio,f,l,n);
		}
		else if (esValorMinimo (l,n,i)) {
			if (f-i==1)
				return i;
			return busquedaBinaria (i,f-medio-1,l,n);
		}
		return 0;
	}


	public static boolean esValorMinimo (int l, int n, long min) {
		int inicio=0,fin;
		for (int i=1; i<=n; i++) {
			fin = buscarValor (inicio,inicio+1,l,min);
			if (fin==-1 || (l-fin)<(n-i))
				return false;
			inicio = fin;
		}
		return true;
	}

	public static int buscarValor (int i, int f, int f2, long v) {
		int medio = (int) ((f2-f)/2d);
		long inicio = calcularCuadrado (i),actual,izquierda,derecha = calcularCuadrado (f2)-inicio;

		if (derecha<v)
			return -1;
		if (f2==f)
			return f;
		if (f2-f==1) {
			izquierda  = calcularCuadrado (f)-inicio;
			if (izquierda>=v)
				return f;
			return f2;
		}
		actual = calcularCuadrado (f+medio)-inicio;
		if (actual==v)
			return f+medio;
		else if (actual<v)
			return buscarValor (i,f+medio+1,f2,v);
		else
			return buscarValor (i,f,f+medio,v);
	}

	public static long calcularCuadrado (int i) {
		return (long) Math.pow (i, 2);
	}
}
