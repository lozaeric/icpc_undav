import java.util.Scanner;

public class c987 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] fonts = new int[n];
		int[] costs = new int[n];
		
		for (int i=0; i<n; i++)
			fonts[i] = in.nextInt();
		for (int i=0; i<n; i++)
			costs[i] = in.nextInt();
		
		long res = 5000000000l;
		for (int i=0; i<n; i++) {
			long cur = costs[i];
			
			int v=1000000011;
			for (int j=0; j<i; j++) 
				if (fonts[j]<fonts[i])
					v = Math.min(v, costs[j]);
			if (v==1000000011) 
				continue;
			cur += v;
			
			v=1000000011;
			for (int j=i+1; j<n; j++) 
				if (fonts[j]>fonts[i])
					v = Math.min(v, costs[j]);
			if (v==1000000011) 
				continue;
			cur += v;
			
			res = Math.min(res, cur);
		}
		System.out.println(res==5000000000l? -1:res);
	}
}
