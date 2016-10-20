import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
	PrintWriter out = new PrintWriter(System.out);
	out.println  ("Hola");
	out.flush ();
____________________________________________________

	StringBuilder out = new StringBuilder  ();
	out.append("Hola").append("\n");
	System.out.print (out);
*/


class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
 	   String  line;
        while (st == null || !st.hasMoreElements()) {
           line = nextLine ();
           if (line == null)
              return null;
           st = new StringTokenizer(line);
        }
        return st.nextToken();
    }

    Integer nextInt() {
 	   String element = next ();
        return element==null? null:Integer.parseInt(element);
    }

    Long nextLong() {
 	   String element = next ();
        return element==null? null:Long.parseLong(element);
    }

    Double nextDouble() {
 	   String element = next ();
        return element==null? null:Double.parseDouble(element);
    }

    String nextLine() {
        String str = null;
        try {
            str = br.readLine();
        }
        catch (IOException e)
        {}
        return str;
    }
}