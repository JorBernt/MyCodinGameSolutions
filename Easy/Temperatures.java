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
        int n = in.nextInt(); // the number of temperatures to analyse
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int t = in.nextInt(); // a temperature expressed as an integer ranging from -273 to 5526
            if(Math.abs(t) <= Math.abs(min) ) {
                if(Math.abs(t) == Math.abs(min)) {
                    min = Math.max(t, min);
                }
                else {
                    min = t;
                }
                
            } 
        
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
        if(n == 0) 
            System.out.println(0);
        
        else
        System.out.println(min);
    }
}
