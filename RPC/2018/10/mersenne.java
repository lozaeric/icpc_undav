import java.math.BigInteger;
import java.util.Scanner;


public class mersenne {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a,b;
		BigInteger r;
		while(sc.hasNext()){
			a = sc.nextInt();
			b = sc.nextInt();
			r = BigInteger.valueOf(2).pow(a+b).subtract(BigInteger.ONE);
			if (r.isProbablePrime(50)) 
				System.out.println("2^"+(a+b)+"-1 is prime");
			else
				System.out.println("2^"+(a+b)+"-1 not prime");
		}
	}

}
