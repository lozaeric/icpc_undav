public class Main {
	public static void main (String[] args) {
		// problema 7206
		
		int input[][] = {{300,10,100,10,1,10},{1100,100,10,100,1,1000},{1200,100,100,10,1,1000}};
		int input2[][] = {{2000,1000,1000,1000},{21,1,1,10}};
		int input3[][] = {{125000,1000,1000,1000},{21,1,1,10}};
		Game g = new Game (input);
		
		System.out.println (g.obtenerPuntosPerdidos ());
	}
}