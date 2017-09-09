import java.util.Scanner;


public class A {
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int a,b,c;
		
		while (in.hasNext ()) {
			a = in.nextInt ();
			b = in.nextInt ();
			c = in.nextInt ();
			if (a==b || a==c || b==c)
				System.out.println ("S");
			else if (a==b+c||b==a+c||c==a+b)
				System.out.println ("S");
			else
				System.out.println ("N");
		}
		
	}
	
	
	
}
