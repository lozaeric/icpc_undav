import java.util.HashSet;
import java.util.Scanner;

public class a992 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		HashSet<Integer> set = new HashSet<Integer>();
		
		for (int i=0,v; i<n; i++) {
			v = in.nextInt();
			if (v!=0)
				set.add(v);
		}
		System.out.println(set.size());
	}
}
