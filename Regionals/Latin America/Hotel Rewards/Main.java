import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(), k = in.nextInt(), suma=0;
		int prices[] = new int[n];
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		
		for (int i=0; i<n; i++) {
			prices[i] = in.nextInt();
			suma += prices[i];
		}
		for (int i=0; i<n; i++) {
			heap.add(prices[i]);
			int gratis = (i+1)/(k+1);
			while (heap.size()>gratis)
				heap.remove();
		}
		int ahorro = 0;
		for (int h : heap) 
			ahorro += h;
		
		//System.out.println(heap);
		System.out.println(suma-ahorro);
	}
}

