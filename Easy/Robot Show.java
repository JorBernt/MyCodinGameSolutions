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
        int L = in.nextInt();
        int N = in.nextInt();
        
        int left = L, right = 0;
        for (int i = 0; i < N; i++) {
            int b = in.nextInt();
            left = Math.min(left, b);
            right = Math.max(right, b);
        }
        System.out.println(Math.max(L-left, right));
    }
}
