import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    static int[][] result = new int[6][6];
    static int bestDistance = Integer.MAX_VALUE;
    static int solution = 6;
    static int finalValue = 0;
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        finalValue = in.nextInt();
        for(int i = 0; i < 6; i++) {
            result[0][i] = in.nextInt();
        }

        calculate(0);

        System.out.println(bestDistance == 0 ? "POSSIBLE":"IMPOSSIBLE");
        System.out.println(bestDistance == 0 ? solution : bestDistance);

    }

    static void calculate(int lvl) {
        int tempDistance = Math.abs(finalValue-result[lvl][0]);
        if(tempDistance <= bestDistance) {
            bestDistance = tempDistance;
            if(bestDistance == 0) {
                solution = Math.min(solution, lvl);
            }
        }

        if(lvl == 5) return;

        for(int i = 0; i < 6-lvl-1; i++){
            for(int j = i+1; j < 6-lvl; j++){
                int a = result[lvl][i];
                int b = result[lvl][j];

                int l = 1;
                for(int k = 0; k < 6-lvl; k++) {
                    if(k != i && k != j) {
                        result[lvl+1][l++] = result[lvl][k];
                    }
                }

                result[lvl + 1][0] = a + b;
                calculate(lvl+1);

                result[lvl + 1][0] = a * b;
                calculate(lvl+1);

                if(a!=b){
                    result[lvl + 1][0] = Math.abs(a-b);
                    calculate(lvl+1);
                }

                if(Math.min(a,b) != 0 && a % b == 0) {
                    result[lvl + 1][0] = a / b;
                    calculate(lvl+1);
                }
                else if(Math.min(a,b) != 0 && b % a == 0) {
                    result[lvl + 1][0] = b / a;
                    calculate(lvl+1);
                }

            }
        }
    }
}
