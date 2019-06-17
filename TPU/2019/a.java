import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int i = 0, n = in.nextInt();
		
		while (n > 0) {
			n = next(n);
			i++;
		}
		System.out.println(i);
	}
	
	public static int next(int n) {
		char nStr[] = String.valueOf(n).toCharArray();
		int m = 0;
		
		Arrays.sort(nStr);		
		for (int i = 0; i < nStr.length; i++)
			m += (nStr[i]-'0')*Math.pow(10, nStr.length-1-i);

		return n-m;
	}
}