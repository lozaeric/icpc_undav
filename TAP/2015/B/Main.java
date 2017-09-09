import java.util.Scanner;


public class Main {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
      System.out.println (dividir (in.nextInt(),in.nextInt()));
	}
	

	public static long dividir (long l, long n) {
		double l2 = l*l;
		long e = (long) (l2/n);
		if (n==1)
			return e;
		else if (n==l)
			return 1;
		return busquedaBinaria (2,e+1,l,n);
	}
	
	public static long busquedaBinaria (long i, long f, long l, long n) {
		long medio = (long) ((f-i)/2d);
		
		if (esValorMinimo (l,n,f-medio)) {
			if (f-i==1 || f==i)
				return f;
			if (f-i==2) {
				if (esValorMinimo (l,n,f))
					return f;
				return f-medio;
			}
			return busquedaBinaria (f-medio,f,l,n);		
		}
		if (f-i==1)
			return i;
		return busquedaBinaria (i,f-medio-1,l,n);
	}
	
	public static boolean esValorMinimo (long l, long n, long min) {
		long inicio=0,fin=0;
		for (int i=1; i<=n-1; i++) {
			fin = buscarValor (inicio*inicio,inicio+1,l-1,min);
			if (fin==-1 || (l-fin)<(n-i)) 
				return false;
			inicio = fin;
		}
		return l*l-fin*fin>=min;
	}
	
	public static long buscarValor (long offset, long f, long f2, long v) {
		long medio = (long) ((f2-f)/2d),actual,izquierda = f*f-offset,derecha = f2*f2-offset;
		
		if (derecha<v)
			return -1;
		else if (izquierda>=v)
			return f;
		if (f2==f)
			return f;
		else if (f2-f==1) 
			return f2;
		actual = (f+medio)*(f+medio)-offset;
		if (actual==v)
			return f+medio;
		else if (actual<v)
			return buscarValor (offset,f+medio+1,f2,v);
		else 
			return buscarValor (offset,f+1,f+medio,v);
	}
}