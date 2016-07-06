import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class Main {
	public static void main (String[] args) {
		/*
		int rivalidades[][] = {{3,2,1},{2,3,4},{1,4,3}};
		Favoritismo a = new Favoritismo(6,rivalidades);
		a.crearRuta (1, 2);
		a.crearRuta (1, 3);
		a.crearRuta (1, 4);
		a.crearRuta (3, 5);
		a.crearRuta (3, 6);
		a.setEquipo (1, 1);
		a.setEquipo (2, 1);
		a.setEquipo (3, 1);
		System.out.println (a.buscarMinimo ());
		System.out.println (a);
		*/
		int rivalidades[][] = {{2,1},{1,2}};
		Favoritismo a = new Favoritismo(3,rivalidades);
		a.crearRuta (1, 2);
		a.crearRuta (1, 3);
		System.out.println (a.buscarMinimo ());
		System.out.println (a);
	}
}
