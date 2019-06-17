import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		
		while (t-- > 0) {
			int c = in.nextInt(), r = in.nextInt();
			int votes[] = new int[c], winnerVotes = 0, winnerIndex = -1;
			for (int i=0; i<r; i++) {
				for (int j=0; j<c; j++) {
					votes[j] += in.nextInt();
					if (winnerVotes < votes[j]) {
						winnerVotes = votes[j];
						winnerIndex = j+1;
					}
				}
			}
			System.out.println(winnerIndex);
		}		
	}
}