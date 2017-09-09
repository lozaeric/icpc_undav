import java.util.Scanner;


public class twins {
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt (), numeros[];
		boolean hay17=false, hay18=false;
		
		for (int i=0; i<n; i++) {
			numeros = new int [10];
			hay17=false; hay18=false;
			for (int j=0; j<numeros.length; j++) {
				numeros[j] = in.nextInt ();
				if (numeros[j]==17)
					hay17 = true;
				if (numeros[j]==18)
					hay18 = true;
			}
			for (int j=0; j<numeros.length-1; j++) {
				System.out.print (numeros[j]+" ");
			}
			System.out.println (numeros[9]);
			if  (hay17 && hay18) 
				System.out.println ("both");
			else if (hay17)
				System.out.println ("zack");
			else if (hay18)
				System.out.println ("mack");
			else
				System.out.println ("none");
		}
 	}
}
