import java.util.Scanner;


public class Coj {
   static char[] seq, subseq;
   static long[][] tbl;

   public static long countMatches() {
       tbl = new long[seq.length + 1][subseq.length + 1];

       for (int row = 0; row < tbl.length; row++)
           for (int col = 0; col < tbl[row].length; col++)
               tbl[row][col] = countMatchesFor(row, col);

       return tbl[seq.length][subseq.length];
   }

   private static long countMatchesFor(int seqDigitsLeft, int subseqDigitsLeft) {
       if (subseqDigitsLeft == 0)
           return 1;

       if (seqDigitsLeft == 0)
           return 0;

       char currSeqDigit = seq[seq.length-seqDigitsLeft];
       char currSubseqDigit = subseq[subseq.length-subseqDigitsLeft];

       long result = 0;

       if (currSeqDigit == currSubseqDigit)
           result += tbl[seqDigitsLeft - 1][subseqDigitsLeft - 1];

       result += tbl[seqDigitsLeft - 1][subseqDigitsLeft];

       return result;
   }
   
	public static void main(String args[])
	{
		Scanner in = new Scanner (System.in);
		in.nextInt ();
      seq = in.next ().toCharArray ();
      subseq = "COW".toCharArray ();
      System.out.println (countMatches ());
	}
}

