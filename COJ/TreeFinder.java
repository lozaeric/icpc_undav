import java.util.ArrayList;
import java.util.Scanner;


public class TreeFinder {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int v,e,cont=1;
		UnionFind set;
		
		while (true) {
			v = in.nextInt();
			e = in.nextInt();
			if (v==0 && e==0)
				return;
			set = new UnionFind (v);
			for (int i=0,a,b; i<e; i++) {
				a = in.nextInt()-1;
				b = in.nextInt()-1;
				set.union(a, b);
			}
			System.out.println ("Case "+(cont++)+": "+set);
		}
	}
	
	static class UnionFind {
		int parent[], edges[], sizes[], total = 0;
		ArrayList<Integer> roots;
		
		public UnionFind (int n) {
			parent = new int[n];
			edges = new int[n];
			sizes = new int[n];
			total = n;
			roots = new ArrayList<Integer> (n);
			for (int i=0; i<n; i++) {
				parent[i] = i;
				roots.add(i);
				sizes[i] = 1;
			}
		}
		
		int find (int i) {
			return (parent[i] == i) ? i : (parent[i] = find(parent[i]));
		}
		
		boolean isSameSet (int i, int j) {
			return find(i)==find(j);
		}
		
		void union (int i, int j) {
			int x1 = find(i), x2 = find(j);
			
			if (x1==x2) 
				edges[x1]++;
			else {
				parent[x2] = x1;
				edges[x1] += edges[x2]+1;
				sizes[x1] += sizes[x2];
				roots.remove(new Integer (x2));
				total--;
			}
		}
		
		public String toString () {
			int arboles=0;
			for (int r : roots) {
				if (edges[r]==sizes[r]-1)
					arboles++;
			}
			if (arboles==0) 
				return "No trees.";
			else if (arboles==1)
				return "There is one tree.";
			else 
				return "A forest of "+arboles+" trees.";
		}
	}
}