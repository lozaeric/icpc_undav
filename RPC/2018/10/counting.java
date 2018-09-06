import java.util.Scanner;

public class counting {
	static int mem[][] = new int[10001][10001];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
	
		mem[1][1] = 1;
		for (int n=1; n<mem.length; n++) {
			for (int k=1; k<=n; k++) {
				if (n-1>0 && k-1>0)
					mem[n][k] += mem[n-1][k-1]%1000000007;
				if (n-k>0)
					mem[n][k] += mem[n-k][k]%1000000007; 
				mem[n][k] %= 1000000007; 
			}
		}
		
		while (in.hasNext()) {
			int n = in.nextInt();
			int res = 0;
			for (int i=1; i<=n; i++) {
				res += mem[n][i];
				res %= 1000000007;
			}
			System.out.println(res);
		}
	}
}
