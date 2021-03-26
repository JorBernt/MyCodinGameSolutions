import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/


class Solution {
    static int[] prizes;
    static int[][] ar;
    static int N,R,max, cmin,cmax;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        R = in.nextInt();
        prizes = new int[N];
        for (int i = 0; i < N; i++) {
            int PRIZE = in.nextInt();
            prizes[i] = PRIZE;
        }
        ar = new int[N+1][R+1];
        for(int i = 0; i < N+1; i++) {
            for(int j = 0; j < R+1; j++) {
                ar[i][j] = -1;
            }
        }
        cmin=0;
        cmax=0;
        max = 0;
        System.out.print(maxMoney(0,R));
        
    }

    static int maxMoney(int d,int r) {
        int prizeIncluded = 0, prizeExcluded=0;
        if(d == N) return 0;
        if(r == 0) return maxMoney(d+1,R);

        if(ar[d+1][R] == -1) {
            prizeExcluded = maxMoney(d+1, R);
            ar[d+1][R] = prizeExcluded;
        }
        else {
            prizeExcluded = ar[d+1][R];
        }
        if(ar[d+1][r-1] == -1) {
            prizeIncluded = prizes[d] + maxMoney(d+1,r-1);
            ar[d+1][r-1] = prizeIncluded;
        }
        else {
            prizeIncluded = ar[d+1][r-1];
        }
        return Math.max(prizeExcluded, prizeIncluded);

    }
}
