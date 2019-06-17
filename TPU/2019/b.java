import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String input = in.nextLine(), ok = in.nextLine();
		String answer = "Accepted";
		
		if (!input.equals(ok)) {
			if (input.replace(" ", "").equals(ok.replace(" ","")))
				answer = "Presentation Error";
			else
				answer = "Wrong Answer";
		}
		System.out.println(answer);
	}
}