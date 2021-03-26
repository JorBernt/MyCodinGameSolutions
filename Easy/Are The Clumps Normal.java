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
        String N = in.next();
        int base = 1;
        int prevclumps = 0;
        int clumps = 0;
        while(base <= 9) {
            clumps = clump(N, base, 0);
            if(clumps < prevclumps) {
                System.out.println(base);
                return;
            }
            base++;
            prevclumps = clumps;
        }
        
        
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println("Normal");
    }

    public static int clump(String s, int base, int clumps) {
        if(s.length() == 1) {
            clumps++;
            return clumps;
        }
        int d = (int)(s.charAt(0)-'0')%base;
        for(int i = 1; i < s.length(); i++) {
            int n = (int)(s.charAt(i)-'0');
            if(n%base == d) continue;
            else {
                clumps++;
                if(i < s.length()) {
                    return clump(s.substring(i), base, clumps);
                }
            }
        }
        return clumps;
    }
}
