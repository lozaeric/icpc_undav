import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// Equipo: Knights of the Silver Hand
// Integrantes: Loza Eric, Manka Patricio, Martinho Matias
// JAP 2016
// El programa muestra todos los asientos optimos 

public class Main {
	//true => si el asiento esta ocupado
	//false => si esta libre
	static boolean array[] = {true,true,false,false,false};
	//static boolean array[] = {true,false,false,false,true};
	//static boolean array[] = {false,true,true,false,false,true};
	//static boolean array[] = {true,false,false,false,false,true};
	//static boolean array[] = {true,false,false,false,true,false,false,false,true};
	//static boolean array[] = {true,false,true,false,false,true,true,true,true};
	//static boolean array[] = {true,false,false,false,true,false,false,false,false,true,false,false,false,true,false,false};
	
	public static void main(String[] args) {
		ArrayList<Integer> ocupados = new ArrayList<Integer> ();
		ArrayList<Segmento> segmentos = new ArrayList<Segmento> ();
		int ei,ef;
		
		for (int i=0; i<array.length; i++) {
			if (array[i]) 
				ocupados.add(i); //guardo los indices de los asientos ocupados en una lista
		}
		if (ocupados.isEmpty()) { //si ningun asiento esta ocupado, elijo los extremos
			if (array.length==1)
				System.out.println(0);
			else
				System.out.println (0+" "+(array.length-1));
			return;
		}
		else if (ocupados.size()==array.length) { // si estan todos ocupados, no me puedo sentar
			System.out.println (-1);
			return;
		}
		//un segmento es una secuencia consecutiva de asientos vacios
		//analizo por separado los segmentos que son extremos y los intermedios
		ei = ocupados.get(0); 
		for (int i=1, ant = ei; i<ocupados.size(); i++) {
			segmentos.add(new Segmento (ant, ocupados.get(i)));
			ant = ocupados.get (i);
		}
		ef = ocupados.get(ocupados.size()-1);
		//ordeno los segmentos intermedios de forma descendente para identificar rapidamente los mejores
		Collections.sort(segmentos);
		
		int max = -1; 
		if (!segmentos.isEmpty())  //si hay un solo asiento ocupado pueden no existir segmentos intermedios
		   max = segmentos.get(0).getMinDistancia(); //encuentro la mayor distancia a un asiento ocupado viendo solo segmentos intermedios
		int distMinEi = ei-1, distMinEf = array.length-2-ef;
		int maxE = Math.max(distMinEi, distMinEf); //encuentro la mayor distancia a un ocupado viendo los extremos
		
		if (maxE>=max) { // si las distancias maximas de los extremos son iguales o mejores que las de los segmentos, prefiero los extremos
			if (distMinEi==maxE)
				System.out.println (0);
			if (distMinEf==maxE)
				System.out.println (array.length-1);
		}
		else { 
			boolean esCero = max==0;
			if (!esCero) { //no voy a estar inmediatamente al lado de un asiento ocupado
				for (Segmento s : segmentos) {
					if (max==s.getMinDistancia()) 
						System.out.println (Arrays.toString (s.getPosicion()));
					else
						break;
				}
			}
			else { // necesariamente voy a estar al lado de por lo menos un asiento ocupado
				boolean solucionado=false;
				for (Segmento s : segmentos) {
					if (max==s.getMinDistancia() && s.f-s.i>2) { //busco un segmento en el cual no este rodeado de asientos ocupados
						System.out.println (Arrays.toString (s.getPosicion()));
						solucionado = true;
					}
					else if (max>s.getMinDistancia())
						break;
				}
				if (!solucionado) { //si llegue a este punto, obligatoriamente voy a sentarme entre dos asientos ocupados
					for (Segmento s : segmentos) {
						if (max==s.getMinDistancia()) 
							System.out.println (Arrays.toString (s.getPosicion()));
						else
							break;
					}	
				}
			}
		}
		
	}

	static class Segmento implements Comparable<Segmento> {
		int i,f;
		
		public Segmento (int i, int f) {
			this.i = i; this.f = f;
		}
		
		public String toString () {
			return "i="+i+", f="+f+" => "+getMinDistancia ();
		}
		
		public int[] getPosicion () { // posiciones optimas para sentarse dentro de este segmento
			boolean esPar = (f-i-1)%2==0;
			if (!esPar)
				return new int[]{i+getMinDistancia()+1};
			return new int [] {i+getMinDistancia()+1, f-getMinDistancia()-1};
		}
		
		public int getMinDistancia () { // distancia minima a algun asiento ocupado si me siento de forma optima en el segmento
			return (f-i-2)/2;
		}

		@Override
		public int compareTo(Segmento s) {
			return s.getMinDistancia()-getMinDistancia ();
		}
		
	}
}
