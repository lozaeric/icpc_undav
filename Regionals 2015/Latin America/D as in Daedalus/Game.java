public class Game {
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
		
		for (int i=0; i<rondas.length; i++) {
			suma += ejecutarRonda (budgets[i],rondas[i]);
			//System.out.println ("Puntos extra en ronda "+(i+1)+": "+ejecutarRonda (budgets[i],rondas[i]));
		}
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
