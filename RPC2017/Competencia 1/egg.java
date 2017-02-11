import java.util.Scanner;

public class egg {

	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		int e = in.nextInt(), f = in.nextInt();
		int maxSafe = 1, minBroken = f;
		int[] eggs = new int[e];
		for (int i=0,floor; i<e; i++) {
			floor = in.nextInt();
			String des = in.next();
			if (des.equals("SAFE"))
				maxSafe = Math.max(maxSafe, floor);
			else
				minBroken = Math.min(minBroken, floor);
		}
		System.out.println ((maxSafe+1)+" "+(minBroken-1));
	}

}
