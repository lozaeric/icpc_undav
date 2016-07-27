import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class Favoritismo  {
	private Ciudad[] ciudades;
	private int rivalidades[][];
	
	public Favoritismo (int n, int rivalidades[][]) {
		ciudades = new Ciudad[n];
		for (int i=0; i<n; i++)
			ciudades[i] = new Ciudad(i+1);
		this.rivalidades = rivalidades;
	}
	public void crearRuta (int i, int j) {
		ciudades[i-1].agregarVecino (j);
		ciudades[j-1].agregarVecino (i);
	}
	public int buscarMinimo () {
		int max=0;
		Ciudad inicio = null, masVecinos = null;
		
		for (int i=0; i<ciudades.length; i++) { //busco ciudad con mas vecinos y que tenga equipo
			if (ciudades[i].getVecinos ().size ()>max) {
				max = ciudades[i].getVecinos ().size ();
				masVecinos = ciudades[i];
				if (masVecinos.tieneEquipo ())
					inicio = masVecinos;
			}
		}
		if (inicio == null) { // si ninguna ciudad tiene equipo asignado, asigno el equipo de menor rivalidad a la ciudad con mas vecinos
			masVecinos.setEquipo (calcularEquipoMenosRivalidad ());
			inicio = masVecinos;
		}
		return minimoDisturbio(inicio);
	}
	
	private int calcularEquipoMenosRivalidad () { //busco el minimo disturbio posible entre equipos de la matriz rivalidad
		int equipo = -1, min = Integer.MAX_VALUE, rivalidad;
		
		for (int i=1; i<=rivalidades.length; i++) {
			for (int j=1; j<=rivalidades.length; j++) {
				rivalidad = getRivalidad (i,j);
				if (rivalidad==1)
					return i;
				else if (rivalidad<min) {
					min = rivalidad;
					equipo = i;
				}
			}				
		}
	   return equipo;
   }
	public int minimoDisturbio (Ciudad ciudad) { //usando el argumento como inicio del arbol, lo recorro a lo ancho
		int suma = 0;
		boolean recorridas[] = new boolean [ciudades.length];
		ArrayDeque<Ciudad> pila = new ArrayDeque<Ciudad>();
		Ciudad actual, vecina;
		
		pila.add (ciudad);
		do { 
			actual = pila.removeFirst ();
			recorridas[actual.getN ()-1]=true;
			for (Integer c : actual.getVecinos ()) {
				if (!recorridas[c-1]) { //voy asignando equipos a las que corresponde y sumo disturbio
					vecina = ciudades[c-1];
					if (!vecina.tieneEquipo ())
						suma += elegirEquipo (vecina, actual.getEquipo ());
					else
						suma += getRivalidad (actual.getEquipo (), vecina.getEquipo ());
					pila.add (vecina);
				}
			}
		} while (!pila.isEmpty ());
		return suma;
	}
	
	public int elegirEquipo (Ciudad una, int equipoVecina) { //teniendo en cuenta el  disturbio con el equipo de la vecina, seteo mi equipo 
		int min=Integer.MAX_VALUE;
		for (int i=1; i<=rivalidades.length; i++) {
			if (getRivalidad(equipoVecina, i)<min) {
				una.setEquipo (i);
				min = getRivalidad(equipoVecina, i);
			}
		}
		return min;
	}

	public void setEquipo (int c, int e) {
		ciudades[c-1].setEquipo (e);
	}
	public int getRivalidad (int e, int e2) {
		return rivalidades[e-1][e2-1];
	}
	public String toString () {
	   return Arrays.toString (ciudades);
   }
	
	
}
