class TernarySearch {
	double epsilon = 0.000001d;
	
	// Ternary Search
	// O(log N)
	// busqueda de max y min en funciones unimodales
	
	double f (double i) { //funcion de valor
		return (i-5)*(i-5);
	}
	
	double find_min (double l, double r) {
		double lt, rt, left=l, right=r;
		
		while (true) {
			if (Math.abs (right-left)<epsilon)
				return (left+right)/2;
			lt = left+(right-left)/3;
			rt = right-(right-left)/3;
			if (f(lt)>f(rt)) // importante para find_max
				left = lt;
			else
				right = rt;
		}
	}
}