class Kmp {
	int back[];
	char s[],p[];
	
	// Ocurrencias de un string P en otro string S
	// O (n+m)
	// cuantas ocurrencias y en que indices
	
	void preKmp () {
		int i=0, j=-1;
		back = new int[s.length+p.length];
		back[0] = -1;
		while  (i<p.length) {
			while (j>=0 && p[i]!=p[j])
				j = back[j];
			i++; j++;
			back[i] = j;
		}
	}
	
	void kmp () {
		int i=0, j=0;
		while  (i<s.length) {
			while (j>=0 && s[i]!=p[j])
				j = back[j];
			i++; j++;
			if (j==p.length) {
				System.out.println ("found ["+(i-j)+","+(i-1)+"]");
				j = back[j];
			}
		}
	}
}
