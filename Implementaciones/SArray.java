class SArray {
	// Suffix array
	// string matching O(m log n)
	// construccion O(n log n)

	char T[];                        
	int n;                                           
	int[] RA, tempRA,  SA, tempSA, c;                                         
	
	public SArray  (char T[]) {
		this.T = T;
		n = T.length;
		RA = new int[100010];
		tempRA = new int[100010];
		SA = new int[100010];
		tempSA = new int[100010];
		c = new int[100010];
		constructSA();
	}
	
	void constructSA() {             
	    int i, k, r;
	    for (i = 0; i < n; i++) 
	    	RA[i] = T[i];                      
	    for (i = 0; i < n; i++) 
	    	SA[i] = i;          
	    for (k = 1; k < n; k <<= 1) {           
	      countingSort(k);      
	      countingSort(0);            
	      tempRA[SA[0]] = r = 0;                 
	      for (i = 1; i < n; i++)                         
	        tempRA[SA[i]] = (RA[SA[i]] == RA[SA[i-1]] && RA[SA[i]+k] == RA[SA[i-1]+k]) ? r : ++r;
	      for (i = 0; i < n; i++)                         
	        RA[i] = tempRA[i];
	    } 
	}
	
	void countingSort(int k) {
	    int i, sum, maxi = Math.max(300, n);   
	    for (i = 0; i < c.length; i++) 
	   	c[i] = 0;               
	    for (i = 0; i < n; i++)                    
	      c[i + k < n ? RA[i + k] : 0]++;
	    for (i = sum = 0; i < maxi; i++) {
	      int t = c[i]; c[i] = sum; sum += t;
	    }
	    for (i = 0; i < n; i++)              
	      tempSA[c[SA[i] + k < n ? RA[SA[i] + k] : 0]++] = SA[i];
	    for (i = 0; i < n; i++)                         
	      SA[i] = tempSA[i];
	}
	

	int strncmp(char[] a, int i, char[] b, int j, int n){
	    for (int k=0; i+k < a.length && j+k < b.length; k++)
	      if (a[i+k] != b[j+k]) 
	    	  return a[i+k] - b[j+k];
	    return 0;
	}

	int[] stringMatching(char P[]) {                           
	    int lo = 0, hi = n-1, mid = lo, m = P.length;                
	    while (lo < hi) {                                         
	      mid = (lo + hi) / 2;                                   
	      int res = strncmp(T, SA[mid], P, 0, m);       
	      if (res >= 0) hi = mid;             
	      else          lo = mid + 1;                
	    }                                           
	    if (strncmp(T,SA[lo], P,0, m) != 0) 
	   	 return new int[]{-1, -1};        
	    int[] ans = new int[]{ lo, 0} ;

	    lo = 0; hi = n - 1; mid = lo;
	    while (lo < hi) {                 
	      mid = (lo + hi) / 2;
	      int res = strncmp(T, SA[mid], P,0, m);
	      if (res > 0) 
	      	hi = mid;                                   
	      else         
	      	lo = mid + 1;                 
	    }                               
	    if (strncmp(T, SA[hi], P,0, m) != 0) 
	   	 hi--;                     
	    ans[1] = hi;
	    return ans;
	} 
	
}
