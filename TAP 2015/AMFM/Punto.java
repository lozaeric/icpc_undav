
public class Punto {
	private double x,y;
	
	public Punto (double x, double y) {
	   super ();
	   this.x = x;
	   this.y = y;
   }

	public double getX () {
		return x;
	}

	public double getY () {
		return y;
	}

	public String toString () {
	   return "Punto [x=" + x + ", y=" + y + "]";
   }

	public int hashCode () {
	   final int prime = 31;
	   int result = 1;
	   long temp;
	   temp = Double.doubleToLongBits (x);
	   result = prime * result + (int) (temp ^ (temp >>> 32));
	   temp = Double.doubleToLongBits (y);
	   result = prime * result + (int) (temp ^ (temp >>> 32));
	   return result;
   }

	public boolean equals (Object obj) {
	   if (this == obj)
		   return true;
	   if (obj == null)
		   return false;
	   if (getClass () != obj.getClass ())
		   return false;
	   Punto other = (Punto) obj;
	   if (Double.doubleToLongBits (x) != Double.doubleToLongBits (other.x))
		   return false;
	   if (Double.doubleToLongBits (y) != Double.doubleToLongBits (other.y))
		   return false;
	   return true;
   }


}
