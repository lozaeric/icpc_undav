import java.util.Arrays;
import java.util.Scanner;


public class Main10 {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt (), s  = in.nextInt (), j = in.nextInt (), d = in.nextInt ();
		
	   System.out.println (calcularResultado (n,s,j,d,in.next ()));
	}

	private static String calcularResultado (int n, int s, int j, int d, String juegos) {
		int resultado[] = new int [2], aux[] = new int [2];
		char _juegos[] = juegos.toCharArray ();
		
		for (char c : _juegos) {
			if (c=='A')
				aux[0]++;
			else
				aux[1]++;
			
			if (aux[0]>=j && aux[0]-aux[1]>=d) {
				resultado[0]++;
				aux[0] = aux[1] = 0;
			}
			else if (aux[1]>=j && aux[1]-aux[0]>=d) {
				resultado[1]++;
				aux[0] = aux[1] = 0;
			}
		}
		return resultado[0]+" "+resultado[1];
   }
}
