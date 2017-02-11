import java.util.Scanner;


public class pacman {
	private static int dr[] = {0,1,1};
	private static int dc[] = {1,1,0},matriz[][],r,c;
	private static int cache[][];
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt ();
		
		for (int i=0; i<n; i++) {
			r = in.nextInt ();
			c = in.nextInt ();
			matriz = new int [r][c];
			cache = new int[r][c];
			for (int j=0; j<r; j++) {
				for (int t=0; t<c; t++) {
					if (j==0 && t==0)
						in.next ();
					else if (j==r-1 && t==c-1)
						in.next ();
					else
						matriz[j][t] =  in.nextInt ();
				}
			}
			System.out.println ("Game Board #"+(i+1)+": "+maximum (0,0));
		}
	}

	private static int maximum (int r1, int c1) {
		if  (r1==r-1 && c1==c-1)
			return 0;
		if (cache[r1][c1]!=0)
			return cache[r1][c1];
		
		int ans=-1;
		for (int d=0; d<3; d++) {
			if (r1+dr[d]<matriz.length &&  c1+dc[d]<matriz[0].length)
				ans = Math.max (ans, matriz[r1+dr[d]][c1+dc[d]]+maximum(r1+dr[d],c1+dc[d]));
		}
		return cache[r1][c1] = ans;
   }
	
	
}
