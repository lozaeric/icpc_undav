import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class C101873 {
	static HashMap<Integer, ArrayList<Integer>> lisAdy = new HashMap<>();
	static HashMap<Integer, Integer> mem = new HashMap<>();
	static int[][] ride;
	static int t;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		int n = in.nextInt(), m = in.nextInt();

		ride = new int[n][2];
		t = in.nextInt();
		for (int i=0; i<n; i++)
			lisAdy.put(i, new ArrayList<>());
		for (int i=0; i<m; i++) {
			int a = in.nextInt()-1, b = in.nextInt()-1;
			lisAdy.get(a).add(b);
			lisAdy.get(b).add(a);
		}
		for (int i=0; i<n; i++) {
			ride[i][0] = in.nextInt();
			ride[i][1] = in.nextInt();
		}
		int res = ride[0][1]+solve(0, x-ride[0][0]);
		System.out.println(res < 100000000? res:"It is a trap.");
	}
	
	static int solve(int i, int x) {
		if (x < 0) 
			return 100000000;
		else if (x == 0)
			return i == 0? 0:100000000;
		
		int key = i*10000+x;
		if (mem.containsKey(key))
			return mem.get(key);
		
		int r = ride[i][1]+solve(i, x-ride[i][0]);
		for (Integer j: lisAdy.get(i))
			r = Math.min(r, ride[j][1]+solve(j, x-t-ride[j][0]));
		mem.put(key, r);
		return r;
	}
}