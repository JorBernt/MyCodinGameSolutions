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
        int R = in.nextInt();
        int V = in.nextInt();
        Map<Integer, Integer> vaults = new HashMap<>();

        for (int i = 0; i < V; i++) {
            int C = in.nextInt();
            int N = in.nextInt();
            int combinations = 1;
            
            for(int j = 0; j < C; j++) {
                if(N > 0) {
                    combinations *= 10;
                    N--;
                }
                else combinations *= 5;
            }
            vaults.put(i, combinations);
        }

        int prog = 0;
        int[] robbers = new int[R];
        for(int i = 0; i < R; i++) {
            robbers[i] = vaults.get(prog);
            prog++;
            }
        while (prog < vaults.size()) {
            int min = Integer.MAX_VALUE;
            for(int i = 0; i < robbers.length; i++) {
                if(robbers[i] < min) {
                    min = robbers[i];
                }
            }
            for(int i = 0; i < robbers.length; i++) {
                if(robbers[i] == min) {
                    robbers[i] += vaults.get(prog);
                    prog++;
                }
            }
        }
        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < robbers.length; i++) {
            if(robbers[i] > ans) {
                ans = robbers[i];
            }
        }
        System.out.println(ans);
    }
}
