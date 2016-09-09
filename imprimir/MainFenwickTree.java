import java.util.ArrayList;


public class MainFenwickTree {
	public static void main (String[] args) {
		FenwickTree ft = new FenwickTree (10);     // ft = {-,0,0,0,0,0,0,0, 0,0,0}
		ft.adjust(2, 1);        // ft = {-,0,1,0,1,0,0,0, 1,0,0}, idx 2,4,8 => +1
		ft.adjust(4, 1);        // ft = {-,0,1,0,2,0,0,0, 2,0,0}, idx 4,8 => +1
		ft.adjust(5, 2);        // ft = {-,0,1,0,2,2,2,0, 4,0,0}, idx 5,6,8 => +2
		ft.adjust(6, 3);        // ft = {-,0,1,0,2,2,5,0, 7,0,0}, idx 6,8 => +3
		ft.adjust(7, 2);        // ft = {-,0,1,0,2,2,5,2, 9,0,0}, idx 7,8 => +2
		ft.adjust(8, 1);        // ft = {-,0,1,0,2,2,5,2,10,0,0}, idx 8 => +1
		ft.adjust(9, 1);        // ft = {-,0,1,0,2,2,5,2,10,1,1}, idx 9,10 => +1
		ArrayList<Long> sumas = new ArrayList<Long> ();
		sumas.add (ft.rsq(1, 1));  // 0 => ft[1] = 0
		sumas.add (ft.rsq(1, 2));  // 1 => ft[2] = 1
		sumas.add (ft.rsq(1, 6));  // 7 => ft[6] + ft[4] = 5 + 2 = 7
		sumas.add (ft.rsq(1, 10)); // 11 => ft[10] + ft[8] = 1 + 10 = 11
		sumas.add (ft.rsq(3, 6));  // 6 => rsq(1, 6) - rsq(1, 2) = 7 - 1
		for (long s : sumas)
			System.out.println (s);
	}
	
	
	public static class FenwickTree {
		private long ft[];
		
		public FenwickTree (int n) {
			ft = new long [n+1];
		}
		public int LSOne (int s)  {
			return s & (-s);
		}
		public long rsq (int b) {
			long sum=0;
			int _b=b;
			
			for (; _b>0; _b-=LSOne(_b))
				sum += ft[_b];
			return sum;
		}
		public long rsq (int a,int b) {
			return rsq(b)-(a==1? 0:rsq(a-1));
		}		
		public void adjust(int k, long v) {
			int _k=k;
			for (; _k < ft.length; _k += LSOne(_k)) 
				ft[_k] += v; 
	   }
	}
}