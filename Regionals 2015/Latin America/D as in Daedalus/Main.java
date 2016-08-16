import java.util.Arrays;
import java.util.Scanner;

class Main {
	public static void main (String[] args) {
		Scanner in = new Scanner (System.in);
		Game g;
		int input[][],row,col;
		
		while (in.hasNext ()) {
			col = in.nextInt ()+1;
			row = in.nextInt ();
			input = new int[row][col];
			for (int i=0; i<input.length; i++) {
				for (int j=0; j<input[0].length; j++) 
					input[i][j] = in.nextInt ();
			}
			g = new Game (input);
			System.out.println (g.obtenerPuntosPerdidos ());
		}
	}
	
	static class Game {
		private int rondas[][], budgets[];
		private static final int cartasPosibles[] = {1,10,100,1000,10000}; 
		
		public Game (int input[][]) {
			budgets = new int[input.length];
			rondas = new int[input.length][input[0].length-1];
			
			for (int i=0; i<budgets.length;i++) 
				budgets[i] = input[i][0];
			for (int i=0; i<input.length;i++) {
				for (int j=1; j<input[0].length;j++) 
					rondas[i][j-1]  = input[i][j];
			}
		}
		
		public int obtenerPuntosPerdidos () {
			int suma = 0;
			
			for (int i=0; i<rondas.length; i++) 
				suma += ejecutarRonda (budgets[i],rondas[i]);
			return suma;
		}
		
		private static int ejecutarRonda (int budget, int jugadores[]) {
			int puntosPosibles = budget, mejorOpcion, opcionElegida = jugadores[0], elegida;
			
			for (int i=1; i<jugadores.length;i++) 
				puntosPosibles -= jugadores[i];
			if (puntosPosibles<1) 
				return 0;
			elegida = (int) Math.log10 (puntosPosibles);
			if (elegida>=4)
				mejorOpcion = cartasPosibles [4];
			else
				mejorOpcion = cartasPosibles[elegida];
			if (mejorOpcion >= opcionElegida) 
				return mejorOpcion-opcionElegida;
			return mejorOpcion; 
		}
	}

}