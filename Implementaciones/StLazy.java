
import java.util.Scanner;

public class StLazy {

	public static void main(String[] args) {
        int arr[] = {1,5,6,1,3,5,8,9,1,2};
        SegmentTree tree = new SegmentTree(arr);
        // actualiza rango en O(log n)
	}
	
	static class SegmentTree {   //suma,max,min en O(log n)
		int st[], lazy[], n;
		
		public SegmentTree (int values[]) {
			n = values.length;
			st = new int[4*n];
			lazy = new int[4*n];
			build (1,0,n-1, values);
		}
		
		void build (int p, int l, int r, int values[]) {
			if (l==r)
				st[p] = values[l];
			else {
				build(left(p), l, getMid(l,r), values);
				build(right(p), getMid(l,r)+1, r, values);
				st[p] = Math.max(st[left(p)], st[right(p)]); // importante
			}
		}
		
		private void updateRange (int p,int l, int r, int i, int j, int diff) {
			if (lazy[p]!=0) {
				st[p] += lazy[p];
				if (l!=r) {
					lazy[right(p)] += lazy[p];
					lazy[left(p)] += lazy[p];
				}
				lazy[p] = 0;
			}
			
			if (j<l || i>r)
				return;
			if (i<=l && r<=j) {
				st[p] += diff; // importante
				if (l!=r) {
					lazy[right(p)] += diff;
					lazy[left(p)] += diff;
				}
				return;
			}
			updateRange (left(p), l, getMid(l,r), i, j, diff);
			updateRange (right(p), getMid(l,r)+1, r, i, j, diff);
			st[p] = Math.max(st[left(p)], st[right(p)]); // importante
		}
		
		void updateRange (int i, int j, int diff) {
			updateRange (1, 0, n-1, i, j, diff);
		}
		
		int rmq (int i, int j) {
			return rmq(1,0,n-1,i,j);
		}
		
		private int rmq(int p, int l, int r, int i, int j) {
			if (i > r || j < l) 
				return -1;   // importante
			if (lazy[p]!=0) {
				st[p] += lazy[p];
				if (l!=r) {
					lazy[right(p)] += lazy[p];
					lazy[left(p)] += lazy[p];
				}
				lazy[p] = 0;
			}
			if (l>=i && r<=j)
				return st[p];
			
			int p1 = rmq(left(p),l,getMid(l,r), i, j),
			    p2 = rmq(right(p), getMid(l,r)+ 1, r, i, j);
			return Math.max(p1, p2);  // importante
		}
		
		static int left (int p) {
			return p<<1;
		}
		static int right  (int p) {
			return (p<<1)+1;
		}
		static int getMid (int a, int b) {
			return (a+b)>>1;
		}
	}
}