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
        int res = 0;
        int cpy = n;
        int p = 2;
        while(p <= cpy) {
            while(cpy%p == 0) {
                cpy /= p;
                res += n/p;
            }
            p+=1;
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(res);
    }
}

