import java.util.Arrays;

public class Main {
	public static void main (String[] args) {
		StockPrice sp = new StockPrice (42,1,23,4,8,10);
		System.out.println (sp);
		
		sp = new StockPrice (100,7,615,998,801,3);
		System.out.println (sp);
		
		sp = new StockPrice (100,432,406,867,60,1000);
		System.out.println (sp);
	}
}
