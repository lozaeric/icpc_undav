import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class LIS {
	static ArrayList<Integer> array = new ArrayList<Integer> ();
	
	public static void main (String[] args) {
		Scanner in = new Scanner (System.in);
		Integer n = in.nextInt ();
		while (n!=null) {
			array.add (n);
			n = in.nextInt ();
		}
		lis ();
	}
	
	static void lis () {
		ArrayList<Integer> l = new ArrayList<Integer> ();
		int[] L_id = new int[array.size ()], P = new int[array.size ()];
		int lis=0, lis_end=0;
		
		for (int i=0; i<array.size (); i++) {
			int v = array.get (i);
			int pos = Collections.binarySearch (l, v);
			if (pos<0) 
				pos = -(pos+1);
			if (pos==l.size ()) 
				l.add (v);
			else 
				l.set (pos, v);
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
			s.push(array.get (x));
		System.out.println (size+"\n-");
		System.out.println(array.get (x));
		while (!s.isEmpty()) 
			System.out.println(s.pop());
	}	
}
