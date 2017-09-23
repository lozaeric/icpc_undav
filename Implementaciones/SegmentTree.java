class SegmentTree {
	int st[], n;
	
	// Segment Tree
	// max,min o suma en un rango en O(log n)
	// modificacion de un elemento en O(log n)
	
	public SegmentTree (int values[]) {
		n = values.length;
		st = new int[4*n];
		build (1,0,n-1, values);
	}
	
	void build (int p, int l, int r, int values[]) {
		if (l==r)
			st[p] = values[l];
		else {
			int left = p<<1, right = left+1, mid = (l+r)>>1;
			build(left, l, mid, values);
			build(right, mid+1, r, values);
			st[p] = Math.max(st[left], st[right]);  // importante
		}
	}
	
	void update_point(int p, int L, int R, int idx, int new_value) {
		   if (idx > R || idx < L)
		      return;
		   if (L == idx && R == idx) {
		      st[p] = new_value; 
		      return;
		   }
		   int left = p<<1, right = left+1, mid = (L+R)>>1;
		   update_point(left , L, mid, idx, new_value);
		   update_point(right, mid+1, R , idx, new_value);
		   st[p] = Math.max(st[left], st[right]);  // importante
	}
	  
	void update_point(int idx, int new_value) {
		update_point(1, 0, n - 1, idx, new_value); 
	}
	
	int rmq (int i, int j) {
		return rmq(1,0,n-1,i,j);
	}
	
	int rmq(int p, int l, int r, int i, int j) {
		if (i > r || j < l) 
			return -1;    // importante
		if (l>=i && r<=j)
			return st[p];
		int left = p<<1, right = left+1, mid = (l+r)>>1;
		return Math.max(rmq(left,l,mid, i, j), rmq(right, mid+1, r, i, j));  // importante
	}
}