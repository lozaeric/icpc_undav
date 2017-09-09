import java.util.Scanner;


public class medal {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int n=in.nextInt (), medallas[]=new int[6],
				sumaUsa=0, sumaUrss=0;
		
		for (int i=0; i<n; i++) {
			sumaUsa=0; sumaUrss=0;
			for (int j=0; j<6; j++) {
				medallas[j] = in.nextInt ();
				if (j<3)
					sumaUsa+=medallas[j];
				else
					sumaUrss+=medallas[j];
			}
			for (int j=0; j<5; j++) 
				System.out.print(medallas[j]+" ");
			System.out.println (medallas[5]);	
			if (sumaUsa>sumaUrss && ganoColor (medallas))
				System.out.println ("both");
			else if (sumaUsa>sumaUrss)
				System.out.println ("count");
			else if (ganoColor (medallas))
				System.out.println ("color");
			else
				System.out.println ("none");
		}
	}

	private static boolean ganoColor (int[] medallas) {
	   if (medallas[0]>medallas[3])
	   	return true;
	   if (medallas[0]==medallas[3] && medallas[1]>medallas[4])
	   	return true;
	   if (medallas[0]==medallas[3] && medallas[1]==medallas[4] && medallas[2]>medallas[5])
	   	return true;
	   return false;
   }
	
}