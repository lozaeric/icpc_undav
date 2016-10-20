import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class STree {
	public static void main (String[] args) {
		int array[] = {1,5,7,9,11,53,0,5,1,78};
		SegmentTree st  = new SegmentTree (array);/*
		System.out.println (st.rmq(0, 1));
		System.out.println (st.rmq(1, 3));
		System.out.println (st.rmq(2, 4));
		System.out.println (st.rmq(1, 2));
		System.out.println (st.rmq(3, 6));*/
		st.updateRange(0, 2, 10);
		st.updateRange(3, 5, 2);
		st.updateRange(6, 6, 100);
		System.out.println (st.rmq(0, 1));
		System.out.println (st.rmq(1, 3));
		System.out.println (st.rmq(2, 4));
		System.out.println (st.rmq(1, 2));
		System.out.println (st.rmq(3, 6));
	}
	
	
	static class SegmentTree { 
		int st[], n;
		
		public SegmentTree (int values[]) {
			n = values.length;
			st = new int[4*n];
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
			if (j<l || i>r)
				return;
			if (l==r) {
				st[p] += diff;
				return;
			}
			updateRange (left(p), l, getMid(l,r), i, j, diff);
			updateRange (right(p), getMid(l,r)+1, r, i, j, diff);
			st[p] = Math.max(st[left(p)], st[right(p)]);
		}
		
		void updateRange (int i, int j, int diff) {
			updateRange (1, 0, n-1, i, j, diff);
		}
		
		private void update_point(int p, int L, int R, int idx, int new_value) {
			   if (idx > R || idx < L)
			      return;
			   if (L == idx && R == idx) {
			      st[p] = new_value; 
			      return;
			   }

			   update_point(left(p) , L, getMid(L,R), idx, new_value);
			   update_point(right(p), getMid(L,R) + 1, R , idx, new_value);
			   st[p] = Math.max(st[left(p)], st[right(p)]);
		}
		  
		void update_point(int idx, int new_value) {
			update_point(1, 0, n - 1, idx, new_value); 
		}
		
		int rmq (int i, int j) {
			return rmq(1,0,n-1,i,j);
		}
		
		private int rmq(int p, int l, int r, int i, int j) {
			if (i > r || j < l) 
				return -1; 
			if (l>=i && r<=j)
				return st[p];
			
			int p1 = rmq(left(p),l,getMid(l,r), i, j),
			    p2 = rmq(right(p), getMid(l,r)+ 1, r, i, j);
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