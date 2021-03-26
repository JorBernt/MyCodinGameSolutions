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
        int r1 = in.nextInt();
        for(int i = 0; i < r1; i++) {
            for(int j = i+1; j < r1;) {
                System.err.println(j);
                if(nextNumber(j) == r1) {
                    System.out.println("YES");
                    return;
                }
                j += nextNumber(j);
            }
        }
        System.out.println("NO");
    }


    static long nextNumber(long n) {
        long sum = 0;
        for(char c : Long.toString(n).toCharArray()) {
            sum += Character.getNumericValue(c);
        }
        return n+sum;
    }
}
