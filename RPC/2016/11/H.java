import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class H {
	
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		char[] s;
		ArrayList<Character> derecho = new ArrayList<Character> (),reves = new ArrayList<Character> ();
		
		while (in.hasNext ()) {
			s = in.next ().toCharArray ();
			for (int i=0; i<s.length; i++) {
				if (s[i]=='a'||s[i]=='e'||s[i]=='i'||s[i]=='o'||s[i]=='u')
					derecho.add (s[i]);
				if (s[s.length-1-i]=='a'||s[s.length-1-i]=='e'||s[s.length-1-i]=='i'||s[s.length-1-i]=='o'||s[s.length-1-i]=='u')
					reves.add (s[s.length-1-i]);
			}
			System.out.println (derecho.equals (reves)? "S":"N");
			derecho.clear ();
			reves.clear ();
		}
	}
	
	
}
