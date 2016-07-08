import java.util.ArrayList;


public class Genetica {
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
	
	private static String mutar (String adn, int i) {
		char[] original = adn.substring (i).toCharArray (), conjugado = new char[original.length];
		
		for (int j=0; j<conjugado.length; j++) 
			conjugado[j] = conjugar(original[original.length-j-1]);
		return adn.substring (0, i).concat (new String(conjugado));
	}
	
	private static int calcularMutaciones (String actual, String objetivo, int i, int contador) {
		boolean sonIguales = actual.equals (objetivo);
		int mutaciones=-1, mutaciones2=-1, finPrefijo;
		
		if (actual.length ()<=i || sonIguales) 
			return sonIguales? contador:-1; 
		
		for (finPrefijo=i; finPrefijo<actual.length() && actual.charAt (finPrefijo)==objetivo.charAt (finPrefijo); finPrefijo++);
		if (finPrefijo>i) 
			mutaciones = calcularMutaciones (actual, objetivo, finPrefijo, contador);
		if (objetivo.charAt (i)==conjugar (actual.charAt (actual.length ()-1))) 
			mutaciones2 = calcularMutaciones (mutar(actual, i), objetivo, i+1, contador+1);
		
		if (mutaciones==-1 && mutaciones2!=-1)
			return mutaciones2;
		else if (mutaciones!=-1 && mutaciones2==-1)
			return mutaciones;		
		return Math.min (mutaciones, mutaciones2);
	}
	
	public static int procesar (String actual, String objetivo) {
		return calcularMutaciones(actual,objetivo,0,0);
	}
}

