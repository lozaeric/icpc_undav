import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class complexity {
	static HashMap<StringBuilder, Integer>  dp = new HashMap<>();
	
	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		String w = in.next();
		int cont[] = new int[30];
		
		if (valido(w))
			System.out.println(0);
		else {
			ArrayList<Caracter> caracteres = new ArrayList<>();
			for (char c : w.toCharArray())
				cont[c-'a']++;
			for (char c='a'; c-1!='z'; c++) {
				caracteres.add(new Caracter(c,cont[c-'a']));
			}
			Collections.sort(caracteres);
			System.out.println (w.length()-caracteres.get(0).v-caracteres.get(1).v);
		}
	}

	/*
	static int go (StringBuilder w) {
		if (valido (w))
			return 0;
		if (dp.containsKey (w))
			return dp.get(w);
		System.out.println (w);
		int min = 100000000;
		for (int i=0; i<w.length(); i++) 
			min = Math.min(min, 1+go(new StringBuilder(w).deleteCharAt(i)));
		dp.put(w, min);
		return min;
	}
	*/
	
	
	static boolean valido (String w) {
		if (w.length()<=2)
			return true;
		HashSet<Character> caracteres = new HashSet<> ();
		for (char c : w.toCharArray()) 
			caracteres.add(c);
		
		return caracteres.size()<=2;
		
	}
	
	static class Caracter implements Comparable<Caracter>{
		int v;
		char c;
		
		public Caracter (char c, int v) {
			this.c = c;
			this.v = v;
		}

		@Override
		public int compareTo(Caracter o) {
			return o.v-v;
		}
		
		
	}
}
