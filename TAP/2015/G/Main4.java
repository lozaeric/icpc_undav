import java.util.HashMap;
import java.util.Scanner;


public class Main4 {
	private static HashMap<String, Integer> cache = new HashMap<String, Integer>();
	private static String adn2,adn;
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
      int N=in.nextInt();
     adn = in.next ();
     adn2 = in.next ();
      System.out.println (Genetica.procesar ());
   }
	
	private static class Genetica {
		
		private static char conjugar (char c) {
			switch (c) {
				case 'o':
					return 'o';
				case 'v':
					return 'v';
				case 'w':
					return 'w';
				case 'x':
					return 'x';
				case 'b':
					return 'd';
				case 'p':
					return 'q';
				case 'd':
					return 'b';
				case 'q':
					return 'p';
			}
			return 0;
		}
		
		public static String mutar (char[] adn) {
			char[] nuevoADN = new char [adn.length];
			
			for (int j=0; j<adn.length; j++) 
				nuevoADN[j] = conjugar(adn[adn.length-1-j]);
			return new String(nuevoADN);
		}
		
		private static int calcularMutaciones (String actual, String objetivo) {
			if (actual.length ()==0)
				return 0;
			int mutaciones=-1, mutaciones2=-1;
			String index = actual+objetivo;
			
			if(cache.containsKey (index))
				return cache.get (index);
			if (actual.equals (objetivo)) {
				cache.put (index, 0);
				return 0;
			}
			
			if (objetivo.charAt (0)==actual.charAt (0)) 
				mutaciones = calcularMutaciones (actual.substring (1), objetivo.substring (1));
			if (objetivo.charAt (0)==conjugar (actual.charAt (actual.length()-1))) 
				mutaciones2 = calcularMutaciones (mutar(actual.toCharArray ()).substring (1), objetivo.substring (1));
			
			if (mutaciones==-1 && mutaciones2==-1) {
				cache.put (index, -1);
				return -1;
			}
			else if (mutaciones==-1 && mutaciones2!=-1) {
				cache.put (index, mutaciones2+1);
				return mutaciones2+1;
			}
			else if (mutaciones!=-1 && mutaciones2==-1) {
				cache.put (index, mutaciones);
				return mutaciones;
			}	
			mutaciones = mutaciones2+1>=mutaciones? mutaciones:mutaciones2+1;
			cache.put (index, mutaciones);
			return mutaciones;
		}
		
		public static int procesar () {
			return calcularMutaciones(adn,adn2);
		}
	}

}