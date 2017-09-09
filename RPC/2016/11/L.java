
import java.util.Scanner;


public class L {
	static int matriz[][], objetivo[][];
	static int dr[] = {1,0,-1,0};
	static int dc[] = {0,1,0,-1};
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int r,c;
		
		while (in.hasNext ()) {
			r = in.nextInt ();
			c = in.nextInt ();
			matriz = new int[r][c];
			for (int i=0; i<r; i++) {
				for (int h=0; h<c; h++) 
					matriz[i][h] = in.nextInt ();
			}
			int ff,min=Integer.MAX_VALUE;
			for (int j=0; j<r; j++) {
				for (int t=0; t<c; t++) {
					ff = floodfill(j,t,matriz[j][t]);
					if (ff>0) 
						min = Math.min (ff, min);
				}
			}
			System.out.println (min);
		}
		
	}
	
	public static int floodfill (int r, int c, int c1) {
		if (r<0||r>=matriz.length||c<0||c>=matriz[0].length) //c o r invalidos
			return 0;
		if (c1==-1)
			return 0;
		if (matriz[r][c]!=c1) //caracter inutil
			return 0;
		int ans=1;
		matriz[r][c]=-1;
		for (int d=0; d<4; d++) 
			ans += floodfill(r+dr[d],c+dc[d],c1);
		return ans;
	}
}
