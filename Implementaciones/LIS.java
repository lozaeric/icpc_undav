import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;


public class LIS {
	// longest increasing (o decreasing) subsequence
	// O (n log n)
	
	static void lis (int array[]) { 
		ArrayList<Integer> l = new ArrayList<Integer> ();
		int[] L_id = new int[array.length], P = new int[array.length];
		int lis=0, lis_end=0;
		
		for (int i=0; i<array.length; i++) {
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
		//rebuild lis
		ArrayDeque<Integer> s = new ArrayDeque<Integer>();
		for (int x = lis_end; x >= 0; x = P[x]) 
			s.push(array[x]);
		System.out.println (s.size ()+"\n"+s);
	}
}
