import java.util.Arrays;
import java.util.Scanner;


public class Floodfill {
	private static int dr[] = {1,1,0,-1,-1,-1,0,1};
	private static int dc[] = {0,1,1,1,0,-1,-1,-1};
	private static int n;
	private static char matriz[][];
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		char[] fila;
		int max,min,cont,ff;
		
		while (true) {
			n = in.nextInt ();
			if (n==0)
				return;
			max=-1;
			min=Integer.MAX_VALUE;
			cont=0;
			matriz = new char[n][n];
			for (int j=0; j<n; j++) {
				fila = in.next ().toCharArray ();
				for (int t=0; t<n; t++) 
					matriz[j][t] = fila[t];
			}
			for (int j=0; j<n; j++) {
				for (int t=0; t<n; t++) {
					ff = floodfill(j,t,'l','.');
					if (ff>0) {
						cont++;
						max = Math.max (ff, max);
						min = Math.min (ff, min);
					}
				}
			}
			if (cont==0)
				System.out.println ("0 0 0");
			else
				System.out.println (cont+" "+min+" "+max);
		}
 	}

	public static int floodfill (int r, int c, char c1, char c2) {
		if (r<0||r>=n||c<0||c>=n) //c o r invalidos
			return 0;
		if (matriz[r][c]!=c1) //caracter inutil
			return 0;
		
		int ans=1;
		matriz[r][c]=c2;
		for (int d=0; d<8; d++) {
			ans += floodfill(r+dr[d],c+dc[d],c1,c2);
		}
		return ans;
	}

}
