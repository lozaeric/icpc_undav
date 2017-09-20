import java.util.Scanner;

 class C {
	public static void main(String args[]) {
		Scanner in = new Scanner (System.in);
		while (in.hasNext()) {
			int n = in.nextInt(), b = in.nextInt(), raiz = (int) Math.pow(2, n);
			System.out.println("Instructions: "+recorrer(raiz-b));
		}
	}
	
	static String recorrer (int v) {
		StringBuilder recorrido = new StringBuilder();
		while (v>1) {
			if (v%2==0) {
				v >>= 1;
				recorrido.insert(0, "L");
			}
			else {
				v = (v-1)>>1;
				recorrido.insert(0, "R");
			}
		}
		return recorrido.toString();
	}
}
