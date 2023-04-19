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
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            a[i] = x;
        }
        int swaps = 0;
        int l = 0, r = n-1;
        while(true) {
            while(a[l] == 1 && l < r) l++;
            while(a[r] == 0 && l < r) r--;
            if(l >= r)
                break;
            l++;
            r--;
            swaps++;
        }
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(swaps);
    }
}
