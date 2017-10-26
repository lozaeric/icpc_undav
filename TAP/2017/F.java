import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class F {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		SegmentTree inicio = new SegmentTree(new int[100001]), fin = new SegmentTree(new int[100001]);
		ArrayList<Intervalo> intervalos = new ArrayList<Intervalo> ();
		
		int n = Integer.parseInt(in.readLine()), suma_total = 0;
		while (n-- > 0) {
			String line[] = in.readLine().split(" ");
			int tipo = Integer.parseInt(line[0]);
			if (tipo==1) {
				Intervalo nuevo = new Intervalo(Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]));
				inicio.update_point(nuevo.i, nuevo.v);
				fin.update_point(nuevo.f, nuevo.v);
				intervalos.add(nuevo);
				suma_total += nuevo.v;
			}
			else if (tipo==2) {
				Intervalo cancelado = intervalos.get(Integer.parseInt(line[1])-1);
				inicio.update_point(cancelado.i, 0);
				fin.update_point(cancelado.f, 0);			
				suma_total -= cancelado.v;
			}
			else {
				int i = Integer.parseInt(line[1]), j = Integer.parseInt(line[2]);
				System.out.println(suma_total-fin.rmq(1, i)-inicio.rmq(j, 100000));
			}
		}
	}
	
	static class Intervalo {
		int i,f,v;
		public Intervalo(int i, int f, int v) {
			this.i = i;
			this.f = f;
			this.v = v;
		}
	}
	
	static class SegmentTree {
		int st[],n;
		
		// Segment Tree
		// max,min o suma en un rango en O(log n)
		// modificacion de un elemento en O(log n)
		
		public SegmentTree (int values[]) {
			n = values.length;
			st = new int[4*n];
		}
		
		void update_point(int p, int L, int R, int idx, int new_value) {
			   if (idx > R || idx < L)
			      return;
			   if (L == idx && R == idx) {
			      st[p] = new_value; 
			      return;
			   }
			   int left = p<<1, right = left+1, mid = (L+R)>>1;
			   update_point(left , L, mid, idx, new_value);
			   update_point(right, mid+1, R , idx, new_value);
			   st[p] = st[left]+st[right];  // importante
		}
		  
		void update_point(int idx, int new_value) {
			update_point(1, 0, n - 1, idx, new_value); 
		}
		
		int rmq (int i, int j) {
			return rmq(1,0,n-1,i,j);
		}
		
		int rmq(int p, int l, int r, int i, int j) {
			if (i > r || j < l) 
				return 0;    // importante
			if (l>=i && r<=j)
				return st[p];
			int left = p<<1, right = left+1, mid = (l+r)>>1;
			return rmq(left,l,mid, i, j)+rmq(right, mid+1, r, i, j);  // importante
		}
	}
}
