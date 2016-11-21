


public class TernarySearch {
	static double epsilon = 0.000001d;
	
	public static void main (String[] args) {
		System.out.println ("Min: (x-5)^2");
		double xx = ts_min(-100000,5);
		System.out.println ("x = "+xx+"\ty = "+fm(xx));
	}
	
	static double fm (double i) {
		return (i-5)*(i-5);
	}
	
	static double  ts_min (double l, double r) {
		double lt, rt, left=l, right=r;
		
		while (true) {
			if (Math.abs (right-left)<epsilon)
				return (left+right)/2;
			lt = left+(right-left)/3;
			rt = right-(right-left)/3;
			if (fm(lt)>fm(rt)) // importante
				left = lt;
			else
				right = rt;
		}
	}
}