import java.util.Scanner;

public class c1189 {	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);		
		int n = in.nextInt();
		int num[] = new int[n];
		
		for(int i=0; i<n; i++)
			num[i] = in.nextInt();
		SegmentTree t = new SegmentTree(num);
		
		int q = in.nextInt();
		for (int i=0; i<q; i++) {
			int a = in.nextInt()-1, b = in.nextInt()-1;
			Node r = t.rmq(a,b);
			System.out.println(r.v);
		}
	}
	
	static class SegmentTree {
		int n;
		Node st[];
		static Node zero = new Node(0,0);
		
		public SegmentTree (int values[]) {
			n = values.length;
			st = new Node[4*n];
			build (1,0,n-1, values);
		}
		
		void build (int p, int l, int r, int values[]) {
			if (l==r)
				st[p] = new Node(0, values[l]);
			else {
				int left = p<<1, right = left+1, mid = (l+r)>>1;
				build(left, l, mid, values);
				build(right, mid+1, r, values);
				int v = st[left].v+st[right].v+(st[left].m+st[right].m >= 10? 1:0);
				int m = (st[left].m+st[right].m)%10;
				st[p] = new Node(v,m);
			}
		}
		
		Node rmq (int i, int j) {
			return rmq(1,0,n-1,i,j);
		}
		
		Node rmq(int p, int l, int r, int i, int j) {
			if (i > r || j < l) 
				return zero;
			if (l>=i && r<=j)
				return st[p];
			int left = p<<1, right = left+1, mid = (l+r)>>1;
			Node leftNode = rmq(left,l,mid, i, j), rightNode = rmq(right, mid+1, r, i, j);
			int v = leftNode.v+rightNode.v+(leftNode.m+rightNode.m >= 10? 1:0);
			int m = (leftNode.m+rightNode.m)%10;
			return new Node(v,m);
		}
	}
	
	static class Node {
		int v,m;
		public Node(int v, int m) {
			this.v = v;
			this.m = m;
		}
	}
}