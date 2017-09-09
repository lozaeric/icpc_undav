import java.util.Arrays;
import java.util.Scanner;


public class Main100 {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int jugadores[]=new int [in.nextInt ()], suma=0, suma2=0, diferencias[]=new int[jugadores.length/2];
		
		for (int i=0; i<jugadores.length; i++) 
			jugadores[i] = in.nextInt ();
		Arrays.sort (jugadores);
		for (int i=jugadores.length-1; i>=0; i-=2) {
			suma += jugadores[i];
			suma2 += jugadores[i-1];
			diferencias[i/2] = jugadores[i]-jugadores[i-1];
		}
		if (suma<=suma2) 
			System.out.println (-1);
		else {
			int diff=suma-suma2, i=0;
			if (diff%2==0)
				diff = diff/2-1;
			else
				diff = diff/2;
			Arrays.sort (diferencias);
			for (i=0; i<diferencias.length && diff>=0; i++) 
				diff -= diferencias[i];
			System.out.println (i-1);
		}
		
	}
}