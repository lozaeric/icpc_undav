import java.util.Scanner;


public class zanzibar {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int n=in.nextInt (), num, anterior=1000000, suma=0;
	
		for (int i=0; i<n; i++) {
			num = in.nextInt ();	
			suma = 0;
			while (num != 0) {
				if (num>anterior*2)
					suma += num-anterior*2;
				anterior  = num;
				num = in.nextInt ();	
			}
			System.out.println (suma);
		}
	}
	
}