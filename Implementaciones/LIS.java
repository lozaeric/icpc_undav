import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;


public class LIS {
	static int array[];
	
	public static void main (String[] args) {
		array = new int[] {8,9,10,8,9,7,1,11};
		System.out.println ("Longest increasing subsequence");
		lis ();
	}
	
	static void lis () { // O (n log n)
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
		print (lis_end,P,l.size ());
	}
	
	static void print(int end, int[] p, int size) {
		int x = end;
		ArrayDeque<Integer> s = new ArrayDeque<Integer>();
		for (; p[x] >= 0; x = p[x]) 
			s.push(array[x]);
		System.out.println ("length: "+size);
		System.out.print(array[x]+" ");
		while (!s.isEmpty()) 
			System.out.print(s.pop()+" ");
		System.out.println ();
	}	
}
