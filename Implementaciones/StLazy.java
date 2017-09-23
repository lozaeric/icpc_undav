class StLazy {
	int st[], lazy[], n;
	
	// Segment Tree
	// max,min o suma en un rango en O(log n)
	// modificacion diferencial en un rango en O(log n)
	
	public StLazy (int values[]) {
		n = values.length;
		st = new int[4*n];
		lazy = new int[4*n];
		build (1,0,n-1, values);
	}
	
	void build (int p, int l, int r, int values[]) {
		if (l==r)
			st[p] = values[l];
		else {
			int left = p<<1, right = left+1, mid = (l+r)>>1;
			build(left, l, mid, values);
			build(right, mid+1, r, values);
			st[p] = Math.max(st[left], st[right]); // importante
		}
	}
	
	void updateRange (int p,int l, int r, int i, int j, int diff) {
		int left = p<<1, right = left+1, mid = (l+r)>>1;
		if (lazy[p]!=0) {
			st[p] += lazy[p];
			if (l!=r) {
				lazy[right] += lazy[p];
				lazy[left] += lazy[p];
			}
			lazy[p] = 0;
		}
		
		if (j<l || i>r)
			return;
		if (i<=l && r<=j) {
			st[p] += diff; // importante
			if (l!=r) {
				lazy[right] += diff;
				lazy[left] += diff;
			}
			return;
		}
		updateRange (left, l, mid, i, j, diff);
		updateRange (right, mid+1, r, i, j, diff);
		st[p] = Math.max(st[left], st[right]); // importante
	}
	
	void updateRange (int i, int j, int diff) {
		updateRange (1, 0, n-1, i, j, diff);
	}
	
	int rmq (int i, int j) {
		return rmq(1,0,n-1,i,j);
	}
	
	int rmq(int p, int l, int r, int i, int j) {
		if (i > r || j < l) 
			return -1;   // importante
		int left = p<<1, right = left+1, mid = (l+r)>>1;
		if (lazy[p]!=0) {
			st[p] += lazy[p];
			if (l!=r) {
				lazy[right] += lazy[p];
				lazy[left] += lazy[p];
			}
			lazy[p] = 0;
		}
		if (l>=i && r<=j)
			return st[p];
		return Math.max(rmq(left,l,mid, i, j), rmq(right, mid+1, r, i, j));  // importante
	}
}