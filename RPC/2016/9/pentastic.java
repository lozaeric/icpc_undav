import java.util.Scanner;


public class pentastic {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt (), array[]=new int[5];
		
		
		for (int t=0; t<n; t++) {
			for (int i=0; i<array.length; i++) 
				array[i] = in.nextInt ();
			calcular(array);
			System.out.println ("Pentagon #"+(t+1)+":");
			for (int j=0; j<array.length-1; j++) 
				System.out.print (array[j]+" ");
			System.out.println (array[4]);
		}
	}
	
	public static void calcular (int array[]) {
		int menor=Integer.MAX_VALUE, iMenor=-1;
		
		for (int i=0; i<array.length; i++) {
			if (array[i]<menor) {
				menor = array[i];
				iMenor = i;
			}
		}
		if (menor>=0)
			return;
		
		//System.out.println (Arrays.toString (array)+" "+menor);
		array[iMenor] *= -1;
		if (iMenor==0) {
			array[4] -= array[iMenor];
			array[1] -= array[iMenor];
		}
		else if (iMenor==4) {
			array[3] -= array[iMenor];
			array[0] -= array[iMenor];
		}
		else {
			array[iMenor-1] -= array[iMenor];
			array[iMenor+1] -= array[iMenor];
		}
		calcular (array);
	}
	
}
