


public class TernarySearch {
	static double epsilon = 0.000001d;
	
	public static void main (String[] args) {
		System.out.println ("Max: -(x+10)^2");
		double x = ts_max(-1000,100);
		System.out.println ("x = "+x+"\ty = "+fM(x));
		System.out.println ("Min: (x-5)^2");
		double xx = ts_min(-100000,5);
		System.out.println ("x = "+xx+"\ty = "+fm(xx));
	}
	
	static double fm (double i) {
		return (i-5)*(i-5);
	}
	static double fM (double i) {
		return -(i+10)*(i+10);
	}
	
	static double  ts_min (double l, double r) {
		double lt, rt, left=l, right=r;
		
		while (true) {
			if (Math.abs (right-left)<epsilon)
				return (left+right)/2;
			lt = left+(right-left)/3;
			rt = right-(right-left)/3;
			if (fm(lt)>fm(rt)) // unica diferencia entre ambos
				left = lt;
			else
				right = rt;
		}
	}
	static double  ts_max (double l, double r) {
		double lt, rt, left=l, right=r;
		
		while (true) {
			if (Math.abs (right-left)<epsilon)
				return (left+right)/2;
			lt = left+(right-left)/3;
			rt = right-(right-left)/3;
			if (fM(lt)<fM(rt))
				left = lt;
			else
				right = rt;
		}
	}
}