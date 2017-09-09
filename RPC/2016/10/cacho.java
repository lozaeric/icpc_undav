import java.util.Scanner;


public class cacho {
	static int num[] = new int[5];
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt ();
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<num.length; j++) 
				num[j] = in.nextInt ();
			System.out.println (calcular());
		}
	}

	public static String calcular () {
		int actual = num[0];
		for (int i=1; i<num.length; i++) {
			if (num[i]-actual!=1)
				return "N";
			actual = num[i];
		}
		return "Y";
	}
}
