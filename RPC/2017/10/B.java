import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


class B {
	static int arr[];
    public static void main(String[] args) {
       Scanner in = new Scanner(System.in);
       arr = new int[]{4,2,3,5,1,0};
       int t = in.nextInt();
       while (t-- > 0) {
    	   int n = in.nextInt();
    	   int array[] = new int[n], _array[] = new int[n], max=0;
    	   for (int i=0; i<n; i++) {
    		  array[i] = in.nextInt();
    		  _array[i] = -array[i];
    	   }
    	   for (int i=0; i<array.length; i++) {
    		   if (i==array.length-1)
    			   max = Math.max(max, lis(array,0,i+1));
    		   else if (i==0)
    			   max = Math.max(max, lis(_array,0,array.length));
    		   else
    			   max = Math.max(max, lis(array,0,i+1)+lis(_array,i+1,array.length));
    	   }
    	   System.out.println(max);
       }
    }
	    
	static int lis (int array[], int inicio, int fin) { 
		if (fin-inicio==1)
			return 1;
		ArrayList<Integer> l = new ArrayList<> ();
		int[] L_id = new int[array.length], P = new int[array.length];
		int lis=0, lis_end=0;
		
		for (int i=inicio; i<fin; i++) {
			int pos = Collections.binarySearch (l, array[i]);
			if (pos<0) 
				pos = -(pos+1);
			if (pos==l.size ()) 
				l.add (array[i]);
			else 
				l.set (pos, array[i]);
	      L_id[pos] = i;
	      P[i] = pos > 0 ? L_id[pos - 1] : -1;
	      if (pos + 1 > lis) {
	        lis = pos + 1;
	        lis_end = i;
	      }
		}
		int s = 0;
		for (int x = lis_end; x >= 0; x = P[x]) 
			s++;
		return s;
	}
}
