import java.util.ArrayList;


public class Ciudad {
	private int n, equipo;
	private ArrayList<Integer> vecinos;
	
	public Ciudad (int n) {
		this.n = n;
		vecinos = new ArrayList<Integer>();
	}
	
	public void setEquipo (int e) {
		this.equipo = e;
	}
	public int getEquipo () {
		return equipo;
	}
	public void agregarVecino (int v) {
		vecinos.add (v);
	}
	public ArrayList<Integer> getVecinos () {
		return vecinos;
	}
	public boolean tieneEquipo () {
		return equipo>0;
	}
	public int getN () {
		return n;
	}
	public String toString () {
	   return "(Ciudad " + n + ": equipo=" + equipo + ", vecinos=" + vecinos+ ")";
   }
	
}
