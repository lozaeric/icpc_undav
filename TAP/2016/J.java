import java.util.Arrays;
import java.util.Scanner;
     
public class J {
	 static int num[],l,c;
  	 public static void main (String[] args) {
   	Scanner in = new Scanner (System.in);
   	num = new int [in.nextInt ()];
   	l = in.nextInt ();
   	c = in.nextInt ();
   	for (int i=0; i<num.length; i++)
   		num[i] = in.nextInt ();
   	Arrays.sort (num);
   	System.out.println (solve(num.length-1, c));
    }
  	 
  	 public static char solve (int i, int c) {
  		 if (i<0 && c>=0)
  			 return 'S';
  		 if (c<=0)
  			 return 'N';
  		 return solve (i-l, c-num[i]);
  	 }
}
     