import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class coolest {		
	static HashMap<Integer, ArrayList<Par>> lisAdy = new HashMap<Integer, ArrayList<Par>>();
	static Integer mem[];
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);		
		int n = in.nextInt(), m = in.nextInt();
		mem = new Integer[n];
		HashSet<Integer> noIncoming = new HashSet<Integer>();

		for (int i=0; i<n; i++) {
			noIncoming.add(i);
			lisAdy.put(i, new ArrayList<Par>());
		}
		for (int i=0; i<m; i++) {
			int a = in.nextInt()-1, b = in.nextInt()-1, v = in.nextInt();

			noIncoming.remove(b);
			lisAdy.get(a).add(new Par(b,v));
		}
		int res = 0;
		for (int ni : noIncoming)
			res = Math.max(res, solve(ni));
		System.out.println(res);
	}
		
	static int solve(int v) {
		if (mem[v] != null)
			return mem[v];

		int res = 0;
		for (Par p: lisAdy.get(v))
			res = Math.max(res, p.w+solve(p.v));
		return mem[v] = res;
	}
	
	static class Par implements Comparable<Par> {
		int w, v;
		
		public Par (int v, int w) {
			this.v = v; 
			this.w = w;
		}

		public int compareTo (Par o) {
	      return o.w-w;
      }
	}
}