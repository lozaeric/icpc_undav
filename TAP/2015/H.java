import java.util.Scanner;


public class Main10 {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		long n = in.nextLong ();
		System.out.println (obtenerRestas (n));
	}

	private static int obtenerRestas (long n) {
		String caracteres = String.valueOf (n);
		int numeros[] = new int [caracteres.length ()], aux;
		char nuevo[] = new char [numeros.length];
		long resta;
		
		for (int j=0; j<numeros.length; j++) 
			numeros[j] = Integer.parseInt (caracteres.substring (j,j+1));
		
		for (int i=0; i<numeros.length-1; i++) {
			for (int j=i+1; j<numeros.length; j++) {
				if (numeros[j]<numeros[i]) {
					aux  = numeros[j];
					numeros[j] = numeros[i];
					numeros[i] = aux;
				}
			}
		}
		
		for (int j=0; j<numeros.length; j++) 
			nuevo[j] = String.valueOf (numeros[j]).charAt (0);
		resta = n-Long.parseLong (new String (nuevo));
		if (resta<=0)
			return 1;
		return 1+obtenerRestas(resta);
   }
}
