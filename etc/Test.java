import java.util.Arrays;
import java.util.Scanner;


public class Test {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int numeros[] = new int [in.nextInt ()];
		
		for (int i=0; i<numeros.length; i++)
			numeros[i] = in.nextInt ();
		Arrays.sort (numeros);
		for (int i=numeros.length-1; i>=0; i--)
			System.out.print (numeros[i]+" ");
		
	}
}
