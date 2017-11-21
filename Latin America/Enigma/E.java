import java.util.HashMap;
import java.util.Scanner;

public class E {
	static HashMap<Integer, String> mem = new HashMap<Integer, String>();
	static char[] num;
	static int k;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		num = in.next().toCharArray();
		k = in.nextInt();
		String res = solve(0,0);
		if (res.length()==num.length && res.charAt(res.length()-1)!='*')
			System.out.println(res);
		else
			System.out.println("*");
	}
	
	static String solve(int i, int acc) {
		if (i==num.length) 
			return acc==0? "":"*";
		int index = i*1000+acc;
		if (mem.containsKey(index))
			return mem.get(index);
		String res = "*";
		if (num[i]=='?') {
			for (int j=i==0? 1:0; j<10; j++) {
				String r = j+solve(i+1, (acc*10+j)%k);
				if (r.charAt(r.length()-1)!='*') {
					res = r;
					break;
				}
			}
		}
		else {
			res = num[i]+solve(i+1, (acc*10+(num[i]-'0'))%k);
		}
		mem.put(index,res);
		return res;
	}
	
}
