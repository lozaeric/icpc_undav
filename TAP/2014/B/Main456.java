    import java.util.Scanner;
     
    public class Main456 {
    	private static int array[] = {1,4,13,40,121,364,1093};
    	
    	public static void main (String[] args) {
    		Scanner in = new Scanner (System.in);
    		int n = in.nextInt();
    		
    		for (int i=0; i<n; i++) {
    			int test = in.nextInt();
    			System.out.println (calcular(test, inmediatoSuperior(test)));
    		}
    	}
    	
    	public static String calcular (int n, int  m) {
    		int max = inmediatoSuperior(n);
    		int potenciaTres = (int) Math.pow(3,max);
    		
    		if(n==0) {
    			StringBuilder a = new StringBuilder();
    			for (int i=0; i<=m;i++) 
    				a.append("0");
    			return a.toString();
    		}
 			StringBuilder a = new StringBuilder();
 			for (int i=max; i<m;i++) 
 				a.append("0");
 			a.append("+");
    		if (n<potenciaTres) 
    			return a+invertir(calcular(potenciaTres-n,max-1));
    		return a+calcular(n-potenciaTres,max-1);
    	}
    	
    	public static int inmediatoSuperior (int n) {
    		for (int i=0; i<array.length; i++) {
    			if (array[i]>=n)
    				return i;
    		}
    		return 0;
    	}
     
    	private static String invertir(String calculado) {
    		char[] res = new char[calculado.length()], cal = calculado.toCharArray();
    		
    		for (int i=0; i<cal.length; i++) {
    			if (cal[i]=='+')
    				res[i]='-';
    			else if (cal[i]=='-')
    				res[i]='+';
    			else
    				res[i]='0';
    		}
    		return new String(res);
    	}
    	
    	
    }
     