import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class D101873 {
	static HashMap<Integer, ArrayList<Edge>> lisAdy = new HashMap<>();
	static HashMap<String, Integer> ids = new HashMap<>();
	static int countID = 0;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int s = in.nextInt(), q = in.nextInt();
		for (int i=0; i<2*s; i++)
			lisAdy.put(i, new ArrayList<>());
		in.nextLine();
		for (int i=0; i<s; i++) {
			int v[] = parse(in.nextLine());
			lisAdy.get(v[0]).add(new Edge(v[1], 1));
			lisAdy.get(v[1]).add(new Edge(v[0], -1));
		}
		for (int i=0; i<q; i++) {
			int v[] = parse(in.nextLine());

			if (v[0]>=lisAdy.keySet().size() || v[1]>=lisAdy.keySet().size()) {
				System.out.println("Pants on Fire");
				continue;
			}
			boolean found = dfs(v[0], v[1], false), foundReverse = dfs(v[0], v[1], true);
			if (found) {
				System.out.println("Fact");
			} else if (foundReverse) {
				System.out.println("Alternative Fact");
			} else {
				System.out.println("Pants on Fire");
			}
		}
	}
	
	public static boolean dfs(int a, int b, boolean reverse) {
		int n = lisAdy.keySet().size();
		ArrayDeque <Integer> s = new ArrayDeque <> ();
		boolean visitados[] = new boolean[n];
		
		s.add (a);
		visitados[a] = true;
		do  {
			int curr = s.pop ();
			if (curr == b)
				return true;
			for (Edge e: lisAdy.get (curr)) {
				if (!visitados[e.v] && e.w > 0 == !reverse) {
					s.push (e.v);
					visitados[e.v] = true;
				}
			}
		} while (!s.isEmpty ());
		return false;
	}
	
	public static int[] parse(String s) {
		String v[] = s.replace(" are worse than ", " ").split(" ");
		int result[] = new int[] {getID(v[0]), getID(v[1])};
		return result;
	}
	
	public static int getID(String s) {
		if (!ids.containsKey(s))
			ids.put(s, countID++);
		return ids.get(s);	
	}
	
	static class Edge {
		int v, w;
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
}