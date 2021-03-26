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
        int a = 0;
        int i = 0;
        while(a<N) {
            i++;
            a = (int)Math.pow(2,i);
        }
        System.err.println(i);
        int l = N-(int)Math.pow(2,i-1);
        
        //To understand this formula, check out: https://www.youtube.com/watch?v=uCsD3ZGzMgE
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(2*l == 0?1:2*l);
    }
}
