import java.util.Scanner;

class E {
	public static void main(String args[]) {
		Scanner in = new Scanner (System.in);
		int n = in.nextInt();
		
		while (n!=0) {
			int c = 0;
			for (int a=1; a<=n; a++) {
				for (int b=a; b<=n; b++) {
					boolean ok = Math.sqrt(a*a+b*b)==Math.floor(Math.sqrt(a*a+b*b));
					if (ok && a*a+b*b<=n*n) 
						c++;
					else if (a*a+b*b>n*n)
						break;
				}
			}
			System.out.println(c);
			n = in.nextInt();
		}
	}
}
