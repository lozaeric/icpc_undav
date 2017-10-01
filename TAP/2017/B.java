import java.util.Arrays;
import java.util.Scanner;

public class B {
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		int n = in.nextInt();
		int[] c = new int[n], t = new int[n];
		long comb = 1;
		
		for (int i=0; i<n; i++) 
			c[i] = in.nextInt();
		for (int i=0; i<n; i++) 
			t[i] = in.nextInt();
		Arrays.sort(c);
		Arrays.sort(t);
		for (int i=n-1, j; i>=0; i--) {
			j = Arrays.binarySearch(t, c[i]-1);
			j = j<0? -(j+1):j+1;
			if (j==n) {
				comb = 0;
				break;
			}
			else {
				comb *= n-j-(n-1-i);
				comb %= 1000000007;
			}
		}
		System.out.println(comb);
	}

}
