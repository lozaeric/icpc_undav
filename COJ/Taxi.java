import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;


public class Taxi {
	static int array[];
	static HashMap<Integer, Recorrido> calculados = new HashMap<Integer, Recorrido> ();
	
	public static void main (String[] args) {
		Scanner in = new Scanner (System.in);
		array = new int [11];
		for (int i=1; i<array.length; i++)
			array[i] = in.nextInt ();
		Recorrido resultado = calcular (in.nextInt ());
		//Collections.sort (resultado.camino);
		for (int c : resultado.camino) 
			System.out.println (c+" "+array[c]);
		System.out.println (resultado.total);
	}

	private static Recorrido calcular (int objetivo) {
		Recorrido  r = null;
		ArrayList<Integer> nuevo = new ArrayList<Integer> ();
		int min,aux;
		
		nuevo.add (1);
		calculados.put (1, new Recorrido (array[1],nuevo));
		nuevo.clear ();
		for (int i=2; i<=objetivo; i++) {
			if (i<11) {
				min = array[i];
				nuevo.add (i);
				r = new Recorrido (array[i], nuevo);
				nuevo.clear ();
			}
			else
				min = Integer.MAX_VALUE;
			for (int j=i-1; j>=i>>1; j--) {
				aux = calculados.get (j).total+calculados.get (i-j).total;
				nuevo.addAll (calculados.get (i-j).camino);
				nuevo.addAll (calculados.get (j).camino);
				if (min>aux) {
					r = new Recorrido (aux, nuevo);
					min = aux;
				}
				else if (min==aux) {
					if (r.camino.size ()>nuevo.size ())
						r = new Recorrido (aux, nuevo);
				}
				nuevo.clear ();
			}
			calculados.put (i, r);
		}
		return calculados.get (objetivo);
   }
	
	public static class Recorrido {
		int total;
		ArrayList<Integer> camino;
		
		public Recorrido (int total, ArrayList<Integer> camino) {
			this.total = total;
			this.camino = new ArrayList<Integer> (camino);
		}
		
		public String toString () {
			return "total="+total+", camino="+camino;
		}
	}
}

