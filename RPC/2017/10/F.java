import java.util.Arrays;
import java.util.Scanner;

class F {
	static char matriz[][];
	static int mx[] = {0,0,-1,1}, my[] = {-1,1,0,0}, n, m;
	public static void main(String args[]) {
		Scanner in = new Scanner (System.in);
		int t  = in.nextInt();
		while (t-- > 0) {
			n=in.nextInt();
			m=in.nextInt();
			int r=0;
			matriz = new char[n][m];
			in.nextLine();
			for (int i=0; i<n; i++) 
				matriz[i] = in.nextLine().toCharArray();
			for (int i=0; i<n; i+=n-1) {
				for (int j=0; j<n; j++) {
					if (matriz[i][j]=='0') 
						r += floodfill(i,j);
				}
			}
			if (r==0) {
				for (int i=0; i<n; i+=n-1) {
					for (int j=0; j<n; j++) {
						if (matriz[j][i]=='0') {
							r += floodfill(i,j);
						}
					}
				}
			}
			System.out.println(r);
		}
	}
	
	static int floodfill (int i, int j) {
		if (matriz[i][j]=='1')
			return 0;
		int r = 1;
		matriz[i][j] = '1';
		for (int p=0, ii, jj; p<mx.length; p++) {
			ii = i+mx[p];
			jj = j+my[p];
			if (ii<0 || ii>=n || jj<0 || jj>=m) 
				continue;
			else
				r += floodfill(ii,jj);
		}
		return r;
	}
}
