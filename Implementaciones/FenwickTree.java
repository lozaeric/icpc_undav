// O (log n) para suma en un rango y modificacion de un elemento
static class Fenwick{
	long t[];
	Fenwick(int sz){
		 t = new long[sz+1];
	}
	void adjust(int p, long v){ //valid with p in [1, sz+1), O(lgn)
		for(int i=p; i<t.length; i+=(i&-i))
			t[i]+=v;
	}
	long sum(int p){ //cumulative sum in [1, p], O(lgn)
		long s=0;
		for(int i=p; i>0; i-=(i&-i))
			s+=t[i];
		return s;
	}
	long sum(int a, int b){ //one-indexed
		return sum(b)-sum(a-1);
	}
}