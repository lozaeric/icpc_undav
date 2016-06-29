import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class Estacion {
	private Punto centro;
	private double radio;
	
	public Estacion (Punto centro, int radio) {
		this.centro = centro;
		this.radio = radio;
	}
	
	public Punto intersecar (Estacion otra) {
		double variacionX,variacionY,pendiente,distanciaPuntoMedio, distanciaCentros = calcularDistancia (centro,otra.centro);
		Punto puntoMedio;
		
		if (distanciaCentros+Math.min (radio, otra.radio)<=Math.max (radio, otra.radio)) 
			return radio<=otra.radio? centro:otra.centro;
		else if (radio+otra.radio>=distanciaCentros) {
			distanciaPuntoMedio = radio - (radio+otra.radio-distanciaCentros)/2;
			if (centro.getX ()!=otra.centro.getX ()) {
				pendiente = (centro.getY ()-otra.centro.getY ())/(centro.getX ()-otra.centro.getX ());
				variacionX = distanciaPuntoMedio/Math.sqrt (1+Math.pow (pendiente, 2));
				if (centro.getX ()>otra.centro.getX ())
					variacionX *= -1;
				puntoMedio = new Punto (centro.getX ()+variacionX,centro.getY ()+variacionX*pendiente);
			}
			else {
				variacionY = distanciaPuntoMedio;
				if (centro.getY ()>otra.centro.getY ())
					variacionY*=-1;
				puntoMedio = new Punto (centro.getX (),centro.getY ()+variacionY);
			}
			return puntoMedio;
		}
		return null;
	}
	
	public static int intersecar (Estacion estaciones[]) {
		HashSet<Punto> puntos = new HashSet<Punto>();
		Punto posiblePunto;
		int cont=0, max=-1;
		
		for (int i=0; i<estaciones.length-1; i++) {
			for (int j=i+1; j<estaciones.length; j++) {
				posiblePunto = estaciones[i].intersecar (estaciones[j]);
				if (posiblePunto != null)
					puntos.add (posiblePunto);
			}
		}
		for (Punto p : puntos) {
			cont=0;
			for (Estacion e : estaciones) {
				if (e.pertenece (p))
					cont++;
			}
			if (cont>max)
				max = cont;
		}
		return Math.max (interseccionMaxima (puntos, estaciones), max);
	}
	
	private static int interseccionMaxima (HashSet<Punto> puntos, Estacion estaciones[]) {
		int cont = 0;
		Punto centro, eliminable=null;
		double sumaX,sumaY,maxDistancia, distancia;
		
		while (puntos.size ()>1) {
			sumaX=0;
			sumaY=0;
			for (Punto p : puntos) {
				sumaX += p.getX ();
				sumaY += p.getY ();
			}
			centro = new Punto (sumaX/puntos.size (), sumaY/puntos.size ());
			cont = 0;
			for (Estacion e : estaciones) {
				if (e.pertenece (centro))
					cont++;
			}
			if (cont==puntos.size ())
				return puntos.size ();
			maxDistancia = -1;
			for (Punto p : puntos) {
				distancia = calcularDistancia (p, centro);
				if (distancia>maxDistancia) {
					maxDistancia = distancia;
					eliminable = p;
				}
			}
			puntos.remove (eliminable);
		}
		return cont;
	}
	
	public boolean pertenece (Punto punto) {
		return calcularDistancia (centro, punto)<=radio;
	}

	private static double calcularDistancia (Punto punto, Punto otroPunto) {
		return Math.sqrt (Math.pow (punto.getX ()-otroPunto.getX (), 2)+Math.pow (punto.getY ()-otroPunto.getY (), 2));
   }
}
