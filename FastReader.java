import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

import java.io.*;
import java.util.*;
PrintWriter printer = new PrintWriter(System.out);
printer.println("Hola mundo");
printer.close();

*/

class FastReader
   {
       BufferedReader br;
       StringTokenizer st;

       public FastReader()
       {
           br = new BufferedReader(new
                    InputStreamReader(System.in));
       }

       String next()
       {
           while (st == null || !st.hasMoreElements())
           {
               try
               {
                   st = new StringTokenizer(br.readLine());
               }
               catch (IOException  e)
               {
                   e.printStackTrace();
               }
           }
           return st.nextToken();
       }

       int nextInt()
       {
           return Integer.parseInt(next());
       }

       long nextLong()
       {
           return Long.parseLong(next());
       }

       double nextDouble()
       {
           return Double.parseDouble(next());
       }

       String nextLine()
       {
           String str = "";
           try
           {
               str = br.readLine();
           }
           catch (IOException e)
           {
               e.printStackTrace();
           }
           return str;
       }
   }
