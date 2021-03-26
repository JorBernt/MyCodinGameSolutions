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
        int n = in.nextInt();
        int high = 0, max = 0;
        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            if(v>high){
                high = v;
            }
            max = Math.max(Math.abs(high-v), max);

        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(-1*max);
    }
}
