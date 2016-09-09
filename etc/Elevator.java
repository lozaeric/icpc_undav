import java.util.ArrayDeque;
import java.util.Scanner;


public class Elevator {
	static int floors,up,down,goal,start;

	public static void main (String[] args) {
		Scanner in = new Scanner (System.in);
		floors = in.nextInt ();
		start = in.nextInt ()-1;
		goal = in.nextInt ()-1;
		up = in.nextInt ();
		down = in.nextInt ();
		
		long dist = bfs ();
		System.out.println (dist==-1? "use the stairs":dist);
	}

	
	public static long bfs () {
		ArrayDeque <Integer> cola = new ArrayDeque <Integer> ();
		Integer actual;
		long distancias[] = new long[floors];
		
		cola.add (start);
		distancias[start] = 0;
		do  {
			actual = cola.remove ();
			if (actual==goal)
				return distancias[actual];
			if (up!=0 && actual+up<floors && distancias[actual+up]==0) {
				cola.add (actual+up);
				distancias[actual+up] = distancias[actual]+1;
			}
			if (down!=0 && actual-down>=0 && distancias[actual-down]==0) {
				cola.add (actual-down);
				distancias[actual-down] = distancias[actual]+1;
			}
			
		} while (!cola.isEmpty ());
		return -1;
	}
	
 }
