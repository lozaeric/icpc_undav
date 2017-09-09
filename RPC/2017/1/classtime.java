import java.util.Arrays;
import java.util.Scanner;

public class classtime {

	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		int n = in.nextInt();
		Nombre noms[] = new Nombre[n];
		for (int i=0; i<n; i++) 
			noms[i] = new Nombre (in.next(), in.next());
		Arrays.sort(noms);
		for (Nombre nn : noms) {
			System.out.println(nn.n+" "+nn.a);
		}
	}

	static class Nombre implements Comparable<Nombre> {
		String n,a;
		
		public Nombre (String n, String a) {
			this.n = n;
			this.a = a;
		}

		@Override
		public int compareTo(Nombre o) {
			if (a.compareTo(o.a)==0)
				return n.compareTo(o.n);
			return a.compareTo(o.a);
		}
		
	}
}
