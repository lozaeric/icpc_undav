
public class FT {

	public static void main (String[] args) {
		int n[]={1,7,2,100};
		FenwickTree ft = new FenwickTree (n.length);
		//Importante: el rango de los indices en el FenwickTree es [1,n]
		
		for (int i=0; i<n.length; i++) 
			ft.adjust(i+1, n[i]);
		System.out.println (ft.rsq(n.length));
		ft.adjust(1, 0-ft.rsq(1,1));
		System.out.println (ft.rsq(n.length));
	}

	public static class FenwickTree {
		int ft[];
		
		public FenwickTree (int n) { 
			ft = new int [n+1];
		}
		static int LSOne (int s)  {
			return s & (-s);
		}
		int rsq (int b) { 
			int sum=0;
			for (int _b=b; _b>0; _b-=LSOne(_b))
				sum += ft[_b];
			return sum;
		}
		int rsq (int a,int b) { 
			return rsq(b)-rsq(a-1);
		}		
		void adjust(int index, int diff) {
			for (int _k=index; _k < ft.length; _k += LSOne(_k)) 
				ft[_k] += diff; 
	   }
	}
}