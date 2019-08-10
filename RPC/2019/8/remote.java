import java.util.Scanner;

public class remote {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		boolean forbidden[] = new boolean[10];
		for (int i=0; i<n; i++)
			forbidden[in.nextInt()] = true;
		int target = in.nextInt();

		String low = "-1000000", high = "1000000";
		for (int i=target; i<=999; i++) {
			String newTarget = String.valueOf(i);
			if (isValid(forbidden, newTarget)) {
				high = newTarget;
				break;
			}
		}
		for (int i=target; i>=0; i--) {
			String newTarget = String.valueOf(i);
			if (isValid(forbidden, newTarget)) {
				low = newTarget;
				break;
			}
		}
		int res = Math.min(target-Integer.parseInt(low), Integer.parseInt(high)-target);
		System.out.println(res);
	}
	
	static boolean isValid (boolean forbidden [], String number) {
		for (int i=0; i<number.length(); i++)
			if (forbidden[number.charAt(i)-'0'])
				return false;
		return true;
	}
}