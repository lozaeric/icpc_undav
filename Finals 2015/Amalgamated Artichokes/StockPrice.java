import java.text.DecimalFormat;



public class StockPrice {
	private int p,a,b,c,d,n;
	private double valores[], cambios[];

	public StockPrice (int p, int a, int b, int c, int d, int n) {
	   this.p = p;
	   this.a = a;
	   this.b = b;
	   this.c = c;
	   this.d = d;
	   this.n = n;
		valores = new double[n];
		cambios = new double[n-1];
		
		for (int i=1; i<=n; i++) {
			valores[i-1] = p*(Math.sin (a*i+b)+Math.cos (c*i+d)+2);
			if (i>1)
				cambios[i-2] = valores[i-1]-valores[i-2];
		}
   }
	
	public double[] calcular () {
		double r[] = findMinSubarray (cambios, 0, cambios.length-1);
		
		if (r[2]>=0) 
			return new double [] {0,0,0};
		return new double[] {r[0]+1,r[1]+2,Math.abs (r[2])}; 
	}
	
	private static double[] findCrossSubarray (double array[], int low, int mid, int high) {
		double leftSum = Double.MAX_VALUE, rightSum = Double.MAX_VALUE, sum=0;
		int maxLeft = 0, maxRight = 0;
		
		for (int i=mid; i>=low; i--) {
			sum += array[i];
			if (sum<leftSum) {
				leftSum = sum;
				maxLeft = i;
			}
		}
		sum = 0;
		for (int i=mid+1; i<high; i++) {
			sum += array[i];
			if (sum<rightSum) {
				rightSum = sum;
				maxRight = i;
			}
		}
		
		return (new double [] {maxLeft, maxRight, leftSum+rightSum});
	}
	
	private static double[] findMinSubarray (double array[], int low, int high) {
		if (high-low==1) {
			if (array[low]+array[high]<=array[low] && array[low]+array[high]<=array[high])
				return new double[] {low,high,array[low]+array[high]};
			else if (array[low]<=array[high]) 
				return new double[] {low,low,array[low]};
			else if (array[high]<=array[low]) 
				return new double[] {high,high,array[high]};
		}
		else if (high==low) 
			return new double[] {high,high,array[high]};
		
		
		int mid = (high+low)/2;
		double[] leftA = findMinSubarray (array, low, mid), rightA = findMinSubarray (array, mid+1, high), crossA = findCrossSubarray (array, low, mid, high);
		
		
		if (leftA[2]<=rightA[2] && leftA[2]<=crossA[2]) 
			return leftA;
		else if (rightA[2]<=leftA[2] && rightA[2]<=crossA[2]) 
			return rightA;
		else
			return crossA;
	}
	
	public double[] getCambios () {
		return cambios;
	}
	public double[] getValores () {
		return valores;
	}
	
	public String toString () {
		double r[] = calcular ();
		DecimalFormat seisDecimales=new DecimalFormat("0.000000"); 
		return "Inicio: "+r[0]+"\tFin: "+r[1]+"\tValor: "+seisDecimales.format (r[2]);
	}
}
