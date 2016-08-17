import java.util.Scanner;


class Main100 {
	static int n[];
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		n=new int [in.nextInt ()+1];
		
		n[0] = n.length-1;
		for (int i=1; i<n.length; i++) 
			n[i]  = in.nextInt ();
		for (int i=1; i<n.length; i++)  {
			n[0]  = i;
			if (esValido(0))
				System.out.println (i);
		}	
		
	}
	
	public static boolean esValido(int i) {
		if(i>n.length-1)
			return false;
		if (n.length-1<n[i]+i)
			return false;
		if (n.length-1==n[i]+i)
			return true;
		return esValido (i+n[i]+1);
	}
}