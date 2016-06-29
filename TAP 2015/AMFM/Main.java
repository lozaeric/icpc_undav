public class Main {
	public static void main (String[] args) {
		Estacion e = new Estacion (new Punto (-1,0), 2);
		Estacion e2 = new Estacion (new Punto (1,0), 2);
		Estacion e3 = new Estacion (new Punto (0,-2), 1);
		Estacion e4 = new Estacion (new Punto (0,0), 1);
		Estacion e5 = new Estacion (new Punto (0,2), 1);
		Estacion es[] = {e,e2,e3,e4,e5};

		/* circulo dentro de circulo
		Estacion e = new Estacion (new Punto (0,0), 3);
		Estacion e2 = new Estacion (new Punto (0,3), 1);
		Estacion es[] = {e,e2};		
		*/
		System.out.println  (Estacion.intersecar (es));
	}
}