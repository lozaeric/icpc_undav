
import java.util.Scanner;

public class StLazy {

	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
        int arr[] = new int [1000001], n = in.nextInt(), i, j;
        SegmentTree tree = new SegmentTree(arr);
 
        while (n-- > 0) {
        	i = in.nextInt()-1;
        	j = in.nextInt()-1;
        	tree.updateRange(i,j, 1);
        }
        System.out.println( tree.rmq(0,arr.length-1) );
	}
	
	static class SegmentTree { //minimum
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
				st[p] = Math.max(st[left(p)], st[right(p)]);
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
				st[p] += diff;
				if (l!=r) {
					lazy[right(p)] += diff;
					lazy[left(p)] += diff;
				}
				return;
			}
			updateRange (left(p), l, getMid(l,r), i, j, diff);
			updateRange (right(p), getMid(l,r)+1, r, i, j, diff);
			st[p] = Math.max(st[left(p)], st[right(p)]);
		}
		
		void updateRange (int i, int j, int diff) {
			updateRange (1, 0, n-1, i, j, diff);
		}
		
		private int update_point(int p, int L, int R, int idx, int new_value) {
			   if (idx > R || idx < L)
			      return st[p];
			   if (L == idx && R == idx) 
			      return st[p] = new_value; 

			   int p1, p2;
			   p1 = update_point(left(p) , L, getMid(L,R), idx, new_value);
			   p2 = update_point(right(p), getMid(L,R) + 1, R , idx, new_value);

			   return st[p] = Math.max(p1, p2);
		}
		  
		int update_point(int idx, int new_value) {
			return update_point(1, 0, n - 1, idx, new_value); 
		}
		
		int rmq (int i, int j) {
			return rmq(1,0,n-1,i,j);
		}
		
		private int rmq(int p, int l, int r, int i, int j) {
			if (i > r || j < l) 
				return -1; 
			
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
			int p1 = rmq(left(p),l,getMid(l,r), i, j),p2 = rmq(right(p), getMid(l,r)+ 1, r, i, j);
			if (p1 == -1) 
				return p2;
			if (p2 == -1) 
				return p1;
			return Math.max(p1, p2);
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