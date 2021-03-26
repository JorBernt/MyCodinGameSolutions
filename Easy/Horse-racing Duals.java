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
        int[] pow = new int[N];
        for (int i = 0; i < N; i++) {
            pow[i] = in.nextInt();
        }
        Arrays.sort(pow);
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < N-1; i++) {
            int k = pow[i+1]-pow[i];
            if(k < min) {
                min = k;
            }
        }
        System.out.println(min);
    }
}
