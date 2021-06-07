import java.util.*;
import java.util.stream.Collectors;
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
        int[][] map = new int[n][n];
        int d = 1;
        int colEnd = n, colStart = 0;
        int rowEnd = n, rowStart = 0;

        while(d <= n*n) {
            for(int i = colStart; i < colEnd; i++) {
                map[rowStart][i] = d++;
            }
            rowStart++;
            for(int i = rowStart; i < rowEnd; i++) {
                map[i][colEnd-1] = d++;
            }
            colEnd--;
            for(int i = colEnd-1; i >= colStart; i--){
                map[rowEnd-1][i] = d++;
            }
            rowEnd--;
            for(int i = rowEnd-1; i>=rowStart; i--) {
                map[i][colStart] = d++;
            }
            colStart++;
        }

        long sum = 0;

        for(int i = 0, j = 0; i < n; i++,j++) {
            sum+=map[i][j];
            if(n%2==1 && i == n/2) {
                continue;
            }            
            sum+=map[i][n-1-j];
        }

        System.out.println(sum);
    }
}
