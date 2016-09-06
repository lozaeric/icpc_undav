import java.util.Scanner;


public class generateso {
	static int a,c,m,x,q,n;
	static int seq[],guardados[];
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int query;
		a = in.nextInt ();
		c = in.nextInt ();
		m = in.nextInt ();
		x = in.nextInt ();
		q = in.nextInt ();
		n = in.nextInt ();
		seq = new int[m];
		seq[0] = x;
		guardados = new int[m];
		precalc ();
		for (int i=0; i<q; i++) {
			query = in.nextInt ();
			System.out.println (ipos(query));
		}
		//System.out.println (Arrays.toString (guardados));
	}
	
	
	public static void precalc () {
		for (int i=1; i<m; i++) 
			seq[i] = (a*seq[i-1]+c)%m;
		
		int veces = n/m, resto = n%m;
		
		for (int i=0; i<m; i++) 
			guardados[i] += veces;
		for (int i=0; i<resto; i++) 
			guardados[seq[i]] ++;
		
	}
	
	public static int ipos (int i) {
		int offset=0;
		
		for (int j=0; j<guardados.length; j++) {
			if (offset+guardados[j]>=i)
				return j;
			offset += guardados[j];
		}
		return -1;
	}
}
