class Euclid {
	// greatest common divisors, least common multiple, extended euclid para ecuaciones diofanticas
	// O (log n)
	
	int gcd (int a, int b) { 
		return b == 0 ? a : gcd(b, a % b); 
	}
	
	int lcm(int a, int b) { 
		return a * (b / gcd(a, b)); 
	}
	
	int x,y,d;
	void extendedEuclid (int a, int b) { //ax + by = multiplo de d , d = gcd(a,b)
		if (b==0) {
			x = 1;
			y = 0;
			d = a;
		}
		else {
			extendedEuclid (b,a%b);
			int x1=y, y1=x-(a/b)*y;
			x = x1; 
			y = y1;
		}
	}
}
