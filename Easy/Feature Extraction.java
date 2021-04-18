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
        int r = in.nextInt();
        int c = in.nextInt();
        int[][] pixels = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                pixels[i][j] = in.nextInt();
            }
        }
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] weights = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                weights[i][j] = in.nextInt();
            }
        }
        int rowStart=0, rowEnd=m, colStart=0, colEnd=n;
        int[][] ans = new int[r-m+1][c-n+1];
        
        while(rowEnd <= r) {
            for(int row = rowStart, i = 0; i < m && row < rowEnd; i++, row++) {
                for(int col = colStart, j = 0; j < n && col < colEnd; j++, col++) {
                    ans[rowStart][colStart] += pixels[row][col]*weights[i][j]; 
                }
            }
            if(colStart + n  < c) {
                colStart++;
                colEnd++;
            }
            else {
                colStart = 0;
                rowStart++;
                rowEnd++;
            }
        }
        for(int[] a : ans) {
            System.out.println(Arrays.stream(a).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

    }
}
