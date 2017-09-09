import java.util.Scanner;


public class copious {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int cont,l;
		
		String dictionary[] = new String[in.nextInt ()];
		for (int i=0; i<dictionary.length; i++) {
			dictionary[i] = in.next ();
		}
		int w = in.nextInt ();
		char []word;
		for (int i=0; i<w; i++) {
			word = in.next ().toCharArray ();
			cont = 0;
			System.out.println ("Word #"+(i+1)+": "+new String(word));
			for (String s : dictionary) {
				if (s.length ()==word.length) {
					for (l=0; l<s.length () && (word[l]==s.charAt (l) || !Character.isLetter (word[l])); l++);
					if  (l==s.length ()) {
						System.out.println (s);
						cont++;
					}
				}
			}
			System.out.println ("Total number of candidate words = "+cont);
		}
		
	}
}
