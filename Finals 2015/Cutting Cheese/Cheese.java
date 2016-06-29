import java.util.Arrays;

public class Cheese {
	private int slices;
	private Hole holes[];
	private double peso, cutPositions[];
	
	public Cheese (int slices, Hole[] holes) {
	   this.slices = slices;
	   this.holes = holes;
	   cutPositions = new double [slices];
		peso = 100*100*100;
		for (Hole h : holes) 
			peso -= h.getPeso ();
   }
	
	public Cheese (int slices) {
	   this.slices = slices;
	   cutPositions = new double [slices];
		peso = 100*100*100;
   }
	
	public void calculatePesos () {
		if (holes == null) {
			for (int i=0; i<cutPositions.length; i++) 
				cutPositions[i] = 100d/slices;
			return;
		}
		double offset = 0;
		for (int i=0; i<cutPositions.length-1; i++) {
			cutPositions[i] = bs_peso (offset, offset, 100, getSlicePeso ())-offset;
			offset += cutPositions[i];
		}
		cutPositions[cutPositions.length-1] = 100-offset;
	}
	
	public double bs_peso (double offset, double inicio, double fin, double peso) {
		double mid = inicio+(fin-inicio)/2;
		double actual = getPeso (offset, mid);
		
		if (Math.abs (peso-actual) < 0.0000001) 
			return mid;
		else if (peso < actual) 
			return bs_peso (offset, inicio, mid, peso);
		else if (peso > actual) 
			return bs_peso (offset, mid, fin, peso);
		
		return 0;
	}
	
	public double getPeso (double inicio, double fin) {
		double peso = (fin-inicio)*100*100;
		if (holes!=null) {
			for (Hole h : holes) 
				peso -= h.getPeso (inicio, fin);
		}
		return peso;
	}
	
	public double getTotalPeso () {
		return peso;
	}
	
	public double getSlicePeso () {
		return peso/slices;
	}

	public String toString () {
	   return "Cheese [slices=" + slices + ", cutPositions="
	         + Arrays.toString (cutPositions) + "]";
   }
}

