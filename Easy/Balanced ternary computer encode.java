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
        int N = in.nextInt();

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
        int[] ar = {0,1,-1};
        String[] s = {"0","1","T"};
        StringBuilder sb = new StringBuilder();

        do {
            int rest = 0;
            rest = (N+30000)%3;
            N -=ar[rest];
            N /=3;
            sb.append(s[rest]);

        }
        while(N!=0);

        System.out.print(sb.reverse().toString());
    }
}
