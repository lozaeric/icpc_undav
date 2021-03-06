import java.util.Scanner;

public class Outof {
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(), m = in.nextInt(), a = in.nextInt(), c = in.nextInt(), x0 = in.nextInt();
		
		long sequence[] = new long[n];
		for (int i = 0; i < n; i++) {
			long prev = i == 0? x0:sequence[i-1];
			sequence[i] = (a*prev+c) % m;
		}
		
		int found = 0;
		for (int i = 0; i < n; i++) {
			if (binarySearch(sequence, sequence[i])) {
				found++;
			}
		}
		System.out.println(found);
	}
	
   static boolean binarySearch(long arr[], long x) {
       int l = 0, r = arr.length - 1;
       while (l <= r) {
           int m = (l+r)/2;

           if (arr[m] == x)
               return true;

           if (arr[m] < x)
               l = m+1;
           else
               r = m-1;
       }
       return false;
   }
}
