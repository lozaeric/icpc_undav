public class FenwickTree {
	int ft[];
	
	// O (log n) para suma en un rango y modificacion de un elemento
	
	public FenwickTree (int n) { 
		ft = new int [n+1];
	}
	int rsq (int index) { 
		int sum=0;
		for (int i=index; i>0; i-=(-i&i))
			sum += ft[i];
		return sum;
	}
	int rsq (int i,int j) { 
		return rsq(j)-rsq(i-1);
	}		
	void adjust(int index, int diff) {
		for (int i=index; i<ft.length; i+=(-i&i)) 
			ft[i] += diff; 
   }
}