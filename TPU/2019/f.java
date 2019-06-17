import java.util.HashMap;
import java.util.Scanner;

public class Main {	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);		
		HashMap<Character, Integer> num = new HashMap<Character, Integer> ();
		num.put ('I', 1);
		num.put ('V', 5);
		num.put ('X', 10);
		num.put ('L', 50);
		num.put ('C', 100);
		num.put ('D', 500);
		num.put ('M', 1000);
		
		int n = in.nextInt ();
		for (int i=0; i<n; i++) {
			char r[] = in.next ().toCharArray ();
			int dec = 0, ant = Integer.MAX_VALUE;
			
			for (char c : r) {
				int d = num.get (c);
				if (d <= ant)
					dec += d;
				else
					dec += d-2*ant;
				ant = d;
			}			
			System.out.println ((dec%3 == 0? "YES ":"NO ")+dec);
		}
	}
}