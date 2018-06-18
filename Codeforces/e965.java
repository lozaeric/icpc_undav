import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class e965 {
	static int n;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		Trie t = new Trie();

		for (int i=0; i<n; i++) 
			t.add(in.next().toCharArray());
		Iterator<Integer> it = solve(t.root,0).iterator();
		int sum = 0;
		while(it.hasNext())
			sum += it.next();
		System.out.println(sum);
		
	}
	
	static PriorityQueue<Integer> solve(Node cur, int depth) {
		PriorityQueue<Integer> ans = new PriorityQueue<Integer>(n, new Reverse());
		
		for (int i=0; i<26; i++) {
			if (cur.go[i] != null) {
				PriorityQueue<Integer> t = solve(cur.go[i], depth+1);
				ans.addAll(t);
			}
		}
		if (cur.term) {
			ans.add(depth);
		} else if (depth>0) {
			ans.remove();
			ans.add(depth);
		}
		return ans;
	}
	
	static class Reverse implements Comparator<Integer>{
		public int compare(Integer s1, Integer s2) {
			if (s1 < s2)
				return 1;
			else if (s1 > s2)
				return -1;
			return 0;
		}
	}
	
	static class Trie {
		Node root = new Node();
		
		void add(char word[]) {
			Node cur = root;
			for (char c : word) {
				if (cur.go[c-'a'] == null)
					cur.go[c-'a'] = new Node();
				cur = cur.go[c-'a'];
			}
			cur.term = true;
		}
	}

	static class Node {
		Node go[] = new Node[26];
		boolean term = false;
	}
}
