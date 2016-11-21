
import java.util.Scanner;

public class Kmp {
	static int b[];
	static char s[],p[];
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		s  = "abaaaaba".toCharArray(); // string
		p  = "aba".toCharArray(); // pattern
		preKmp ();
		kmp (); // encuentro ocurrencias en O(len(s) + len(p))
	}

	
	static void preKmp () {
		int i=0, j=-1;
		b = new int[s.length+1];
		b[0] = -1;
		while  (i<p.length) {
			while (j>=0 && p[i]!=p[j])
				j = b[j];
			i++; j++;
			b[i] = j;
		}
	}
	
	static void kmp () {
		int i=0, j=0;
		while  (i<s.length) {
			while (j>=0 && s[i]!=p[j])
				j = b[j];
			i++; j++;
			if (j==p.length) {
				System.out.println ("found, index="+(i-j));
				j = b[j];
			}
		}
	}
}
