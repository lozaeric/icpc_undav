import java.util.Arrays;

public class Main {
	public static void main (String[] args) {
		Hole holes[] = {new Hole (new double[] {10,20,20}, 10),new Hole (new double[] {40,50,60}, 40)};
		Cheese c = new Cheese (5, holes);
		
		c.calculatePesos ();
		System.out.println (c);
	}
}
