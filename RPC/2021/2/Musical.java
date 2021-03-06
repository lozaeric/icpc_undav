import java.util.ArrayList;
import java.util.Scanner;

public class Musical {
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		ArrayList<Node> values = new ArrayList<Node>(n);

		for (int i=0; i<n; i++) {
			values.add(new Node(i+1, in.nextInt()));
		}
		Node curr = values.get(0);
		int currIndex = 0;
		for (int i=0; i<n-1; i++) {
			int j = (currIndex + (curr.value-1)) % values.size();
			values.remove(j);
			currIndex = j % values.size();
			curr = values.get(currIndex);
		}
		System.out.println(values.get(0).pos);
	}
	
	static class Node {
		int pos, value;
		
		public Node(int pos, int value) {
			this.pos = pos;
			this.value = value;
		}
	}
}