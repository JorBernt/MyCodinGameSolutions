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
        Map<Integer, Integer> memo = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int A = in.nextInt();
            int B = in.nextInt();
            int max = 0;
            int ind = 0;
            for(int j = A; j <= B; j++) {
                int n = rec(j, memo);
                if(n>max) {
                    max = n;
                    ind = j;
                }
            }
            System.out.println(ind + " " + max);
        }
    }

    static int rec(int n, Map<Integer, Integer> memo) {
        if(memo.containsKey(n)) {
            return memo.get(n);
        }
        if(n==1) {
            memo.put(n,1);
        }
        else if(n%2==0) {
            memo.put(n,1 + rec(n/2, memo));
        }
        else if(n%2==1) {
            memo.put(n, 1 + rec(n*3+1, memo));
        }
        return memo.get(n);
    }
}


