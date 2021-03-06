import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Passing {		
	static int matrix[][];
	static Long dp[][][], MAX_VALUE = 100_000_000_000L;
	static int mx[] = {-1, 0, 1}, my[] = {1,1,1};
	
	public static void main (String[] args) {
		FastReader in = new FastReader();
		int r = in.nextInt(), c = in.nextInt(), p = in.nextInt();
		matrix = new int[r][c]; 
		dp = new Long[r][c][p+1];
		for (int i=0; i<matrix.length; i++) {
			for (int j=0; j<matrix[0].length; j++) {
				matrix[i][j] = in.nextInt();
			}
		}
		
		Long result = Long.MAX_VALUE;
		for (int i=0; i<matrix.length; i++) {
			if (matrix[i][0] > -1) {
				result = Math.min(result, matrix[i][0]+solve(i, 0, p));
			}
		}
        System.out.println(result >= 10_000_000_000L? "impossible":result);
	}
	
	static Long solve(int i, int j, int p) {
		if(matrix[i][j] == -1) {
			return MAX_VALUE;
		}
		if (j == matrix[0].length - 1) {
			return p == 0? 0L:MAX_VALUE;
		}
		if (p == -1) {
			return MAX_VALUE;
		}
		if(dp[i][j][p] != null) {
			return dp[i][j][p];
		}
		
		long r = MAX_VALUE;
		for (int m = 0, ii, jj; m < mx.length; m++) {
			ii = i+mx[m];
			jj = j+my[m];
			if (ii < 0 || ii >= matrix.length || jj < 0 || jj >= matrix[0].length || matrix[ii][jj] == -1) 
				continue;
			r = Math.min(r, matrix[ii][jj]+solve(ii, jj, isPass(ii, jj)? p-1:p));
		}
		dp[i][j][p] = r;
		return r;
	}
	
	static boolean isPass(int i, int j) {
		if (i == 0 || i == matrix.length-1)
			return false;
		if (j == 0 || j == matrix[0].length-1)
			return false;
		if (matrix[i+1][j] == -1 || matrix[i-1][j] == -1 || matrix[i][j+1] == -1 || matrix[i][j-1] == -1)
			return false;
		return matrix[i+1][j] > matrix[i][j] && matrix[i-1][j] > matrix[i][j] && matrix[i][j+1] < matrix[i][j] && matrix[i][j-1] < matrix[i][j];
	}
	

	
	static class FastReader {
	    BufferedReader br;
	    StringTokenizer st;

	    public FastReader() {
	        br = new BufferedReader(new InputStreamReader(System.in));
	    }

	    String next() {
	 	   String  line;
	       while (st == null || !st.hasMoreElements()) {
	          line = nextLine ();
	          if (line == null)
	             return null;
	          st = new StringTokenizer(line);
	       }
	       return st.nextToken();
	    }

	    String nextLine() {
	       String str = null;
	       try {
	           str = br.readLine();
	       }
	       catch (IOException e)
	       {}
	       return str;
	    }
	    
	    Integer nextInt() {
	  	   String element = next ();
	       return element==null? null:Integer.parseInt(element);
	    }
	}
}