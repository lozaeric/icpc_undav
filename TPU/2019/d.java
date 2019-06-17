import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int ang[] = new int [3], sum = 0, same = 0;
		String answer = "Error";
		
		for (int i=0; i<ang.length; i++) {
			ang[i] = in.nextInt();
			sum += ang[i];
			for (int j=i-1; j>=0; j--){
				if (ang[i] == ang[j]) {
					same++;
					break;
				}
			}
		}
		if (sum == 180) {
			switch(same) {
			case 0:
				answer = "Scalene";
				break;
			case 1:
				answer = "Isosceles";
				break;
			case 2:
				answer = "Equilateral";
				break;
			}
		}
		System.out.println(answer);		
	}
}