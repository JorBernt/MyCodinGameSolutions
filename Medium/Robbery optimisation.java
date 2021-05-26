import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    static long max  = 0;
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        long[] houses = new long[N];
        for (int i = 0; i < N; i++) {
            long housevalue = in.nextLong();
            houses[i] = housevalue;
        }
        long[] memo = new long[N];
        System.out.println(rob(houses, memo,0, 0));

    }

    static long rob(long[] houses, long[] memo, int index, long total) {
        if(index >= houses.length) {
            return total;
        }
        if(memo[index] > 0) return total+memo[index];
        memo[index] = Math.max(rob(houses, memo, index+1, total), rob(houses, memo, index+2, total+houses[index]));
        return memo[index];

    }
}
