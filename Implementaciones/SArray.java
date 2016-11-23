
import java.util.Scanner;

public class SArray {
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		String ss = in.next(), pp = in.next();
		// existencia de pattern en string
		SuffixArray tree = new SuffixArray (ss.toCharArray());
		int ans[] = tree.stringMatching(pp.toCharArray());
		System.out.print(ans[0]+" "+ans[1]);
	}

	
	static class SuffixArray{
		char T[];                        // the input string, up to 100K characters
		int n;                                           
		int[] RA, tempRA,  SA, tempSA, c;                                         
		
		public SuffixArray  (char T[]) {
			this.T = T;
			n = T.length;
			RA = new int[100010];
			tempRA = new int[100010];
			SA = new int[100010];
			tempSA = new int[100010];
			c = new int[100010];
			constructSA();
		}
		
		private void constructSA() {              // this version can go up to 100000 characters
		    int i, k, r;
		    for (i = 0; i < n; i++) 
		    	RA[i] = T[i];                      // initial rankings
		    for (i = 0; i < n; i++) 
		    	SA[i] = i;          // initial SA: {0, 1, 2, ..., n-1}
		    for (k = 1; k < n; k <<= 1) {            // repeat sorting process log n times
		      countingSort(k);       // actually radix sort: sort based on the second item
		      countingSort(0);               // then (stable) sort based on the first item
		      tempRA[SA[0]] = r = 0;                  // re-ranking; start from rank r = 0
		      for (i = 1; i < n; i++)                         // compare adjacent suffices
		        tempRA[SA[i]] =      // if same pair => same rank r; otherwise, increase r
		          (RA[SA[i]] == RA[SA[i-1]] && RA[SA[i]+k] == RA[SA[i-1]+k]) ? r : ++r;
		      for (i = 0; i < n; i++)                          // update the rank array RA
		        RA[i] = tempRA[i];
		    } 
		}
		
		private void countingSort(int k) {
		    int i, sum, maxi = Math.max(300, n);   // up to 255 ASCII chars or length of n
		    for (i = 0; i < c.length; i++) c[i] = 0;                // clear frequency table
		    for (i = 0; i < n; i++)                    // count the frequency of each rank
		      c[i + k < n ? RA[i + k] : 0]++;
		    for (i = sum = 0; i < maxi; i++) {
		      int t = c[i]; c[i] = sum; sum += t;
		    }
		    for (i = 0; i < n; i++)               // shuffle the suffix array if necessary
		      tempSA[c[SA[i] + k < n ? RA[SA[i] + k] : 0]++] = SA[i];
		    for (i = 0; i < n; i++)                          // update the suffix array SA
		      SA[i] = tempSA[i];
		}
		

		private int strncmp(char[] a, int i, char[] b, int j, int n){
		    for (int k=0; i+k < a.length && j+k < b.length; k++){
		      if (a[i+k] != b[j+k]) return a[i+k] - b[j+k];
		    }
		    return 0;
		}

		int[] stringMatching(char P[]) {                           // string matching in O(m log n)
		    int lo = 0, hi = n-1, mid = lo, m = P.length;                 // valid matching = [0 .. n-1]
		    while (lo < hi) {                                          // find lower bound
		      mid = (lo + hi) / 2;                                   // this is round down
		      int res = strncmp(T, SA[mid], P, 0, m);       // try to find P in suffix 'mid'
		      if (res >= 0) hi = mid;             // prune upper half (notice the >= sign)
		      else          lo = mid + 1;                // prune lower half including mid
		    }                                           // observe `=' in "res >= 0" above
		    if (strncmp(T,SA[lo], P,0, m) != 0) return new int[]{-1, -1};         // if not found
		    int[] ans = new int[]{ lo, 0} ;

		    lo = 0; hi = n - 1; mid = lo;
		    while (lo < hi) {                 // if lower bound is found, find upper bound
		      mid = (lo + hi) / 2;
		      int res = strncmp(T, SA[mid], P,0, m);
		      if (res > 0) hi = mid;                                   // prune upper half
		      else         lo = mid + 1;                 // prune lower half including mid
		    }                                // (notice the selected branch when res == 0)
		    if (strncmp(T, SA[hi], P,0, m) != 0) hi--;                      // special case
		    ans[1] = hi;
		    return ans;
		} // return lower/upper bound as the first/second item of the pair, respectively
	}
	
}
