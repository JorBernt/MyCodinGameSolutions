import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Number of elements which make up the association table.
        int Q = in.nextInt(); // Number Q of file names to be analyzed.
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < N; i++) {
            String EXT = in.next(); // file extension
            String MT = in.next(); // MIME type.
            if(MT.length() <= 50 && EXT.length() <= 10)
            map.put(EXT.toLowerCase(), MT);
        }

        in.nextLine();
        for (int i = 0; i < Q; i++) {
            String FNAME = in.nextLine(); // One file name per line.
            if(FNAME.charAt(FNAME.length()-1) == '.') {
                FNAME = "INVALID";
            }
           
            String[] FEXT= FNAME.split("\\.");

            
            if(FEXT.length > 1 && FNAME.length() <= 256) {
                if(FEXT[FEXT.length-1].length() <= 10) {
                if(map.containsKey(FEXT[FEXT.length-1].toLowerCase())) {
               System.out.println(map.get(FEXT[FEXT.length-1].toLowerCase()));
            }
              else {
              System.out.println("UNKNOWN");
            }
            
            }
              else {
              System.out.println("UNKNOWN");
            }
            }
            else {
              System.out.println("UNKNOWN");
            }
        }



        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");


        // For each of the Q filenames, display on a line the corresponding MIME type. If there is no corresponding type, then display UNKNOWN.

    }
}
