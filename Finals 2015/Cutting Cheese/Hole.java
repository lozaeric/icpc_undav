import java.util.Arrays;


public class Hole {
	private double center[],radio;

	public Hole (double[] center, double radio) {
	   this.center = center;
	   this.radio = radio;
   }

	public double[] getCenter () {
		return center;
	}

	public double getRadio () {
		return radio;
	}
	
	public double getPeso () {
		return 4*Math.PI*Math.pow (radio,3)/3;
	}

	public String toString () {
	   return "Hole [center=" + Arrays.toString (center) + ", radio=" + radio
	         + "]";
   }

	public double getPeso (double inicio, double fin) {
		double left = center[2]-radio, right=center[2]+radio;
		
		
		if (fin<=left||inicio>=right)
			return 0;
		else if (inicio<=left && right<=fin) 
			return getPeso ();
		else if (inicio<=left && fin<right) 
			return Math.PI*(fin-left)*(fin-left)/3*(3*radio-(fin-left));
		else if (inicio>left && right<=fin) 
			return Math.PI*(right-inicio)*(right-inicio)/3*(3*radio-(right-inicio));
		else { 
			return getPeso ()
					 -Math.PI*(right-fin)*(right-fin)/3*(3*radio-(right-fin))
					 -Math.PI*(inicio-left)*(inicio-left)/3*(3*radio-(inicio-left));
		}
   }
	
	
}
