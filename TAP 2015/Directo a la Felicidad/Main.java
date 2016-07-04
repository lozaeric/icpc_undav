import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class Main {
	public static void main (String[] args) {
		Nlogonia n = new Nlogonia (9,11,12);
		n.conectar (1, 2);
		n.conectar (3, 2);
		n.conectar (4, 5);
		n.conectar (5, 6);
		n.conectar (6, 7);
		n.conectar (9, 7);
		System.out.println (n.obtenerMinimoCosto());
	}
}
