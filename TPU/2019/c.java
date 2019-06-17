import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int l = in.nextInt(), k = in.nextInt();
		System.out.println(solve(l,k));
	}
	
	public static long solve(long l, long k) {
		if (l == 1)
			return k == 1? 1:0;
		if (k == 1)
			return l*l;
		long left = 0, right = (l*l)/k+1;
		while (right-left > 1) {
			long med = (left+right)/2;
			boolean valid = isValid(l,k,med);
			if (valid)
				left = med;
			else 
				right = med;
		}
		return left;
	}
	
	public static boolean isValid(long l, long k, long o) {
		int j = 0;
		for (long i=k; i>0; i--) {
			int r = (int) Math.ceil(calcR(j,o));
			j += r;
			if (l-j < i-1)
				return false;
		}
		return true;
	}
	
	public static double calcR(long j, long o) {
		return Math.sqrt(o+j*j)-j;
	}
}